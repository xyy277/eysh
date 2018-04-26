package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.*;
import com.aebiz.app.cms.modules.models.em.ChannelCheckedEnum;
import com.aebiz.app.cms.modules.models.em.CmsIsStaticEnum;
import com.aebiz.app.cms.modules.models.em.ContentStatusEnum;
import com.aebiz.app.cms.modules.services.*;
import com.aebiz.app.sys.modules.models.Sys_area;
import com.aebiz.app.sys.modules.models.Sys_dict;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysAreaService;
import com.aebiz.app.sys.modules.services.SysDictService;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.rabbit.RabbitMessage;
import com.aebiz.baseframework.rabbit.RabbitProducer;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.SpringUtil;
import com.aebiz.commons.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.DB;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.Static;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/platform/cms/content")
public class CmsContentController {
    private static final Log log = Logs.get();
    @Autowired
    private CmsContentService cmsContentService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsModelService cmsModelService;
    @Autowired
    private CmsContentSourceService cmsContentSourceService;
    @Autowired
    private CmsContentTxtService cmsContentTxtService;
    @Autowired
    private CmsTagService cmsTagService;
    @Autowired
    private CmsContentPictureService cmsContentPictureService;
    @Autowired
    private CmsContentFileService cmsContentFileService;
    @Autowired
    private CmsTopicService cmsTopicService;
    @Autowired
    private CmsContentCheckService cmsContentCheckService;
    @Autowired
    private CmsChannelRoleService cmsChannelRoleService;
    @Autowired
    private CmsWorkflowInfoService cmsWorkflowInfoService;
    @Autowired
    private CmsWorkflowService cmsWorkflowService;
    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private StaticPageService staticPageService;
    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysAreaService sysAreaService;

    @RequestMapping("")
    @RequiresPermissions("platform.cms.content")
    public String index(HttpServletRequest req) {
        String userId = StringUtil.getUid();
        Sys_user user = sysUserService.fetch(userId);
        String req_site_id = req.getParameter("site_id");
        List<Record> records = cmsSiteService.getSiteRole(user);
        String site_id = "";
        if (records != null && records.size() > 0) {
            site_id = records.get(0).getString("id");
            if (!Strings.isBlank(req_site_id)) {
                site_id = req_site_id;
            }
        }
        req.setAttribute("site_id", Strings.sBlank(site_id));
        req.setAttribute("list", records);
        return "pages/platform/cms/content/index";
    }

    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.content")
    public Object data(DataTable dataTable, HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        String siteId = req.getParameter("site_id");
        String status = req.getParameter("status");
        String pub_time1 = req.getParameter("pub_time1");
        String pub_time2 = req.getParameter("pub_time2");
        //status:5查询全部
        if (!"5".equals(status)) {
            if (!Strings.isBlank(status)) cnd.and("status", "=", status);
        }
        String channel_id = req.getParameter("channel_id");
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String origin = req.getParameter("origin");
        if (!Strings.isBlank(origin)) cnd.and("origin", "like", "%" + origin + "%");
        if (!Strings.isBlank(title)) cnd.and("title", "like", "%" + title + "%");
        if (!Strings.isBlank(author)) cnd.and("author", "like", "%" + author + "%");
        if (!Strings.isBlank(pub_time1)) {
            cnd.and("pub_time", ">=", pub_time1);
        }
        if (!Strings.isBlank(pub_time2)) {
            cnd.and("pub_time", "<=", pub_time2);
        }
        if ("root".equals(channel_id) || Strings.isBlank(channel_id)) {

        } else {
            cnd.and("channel_id", "=", channel_id);
        }
        if (!Strings.isBlank(siteId)) cnd.and("site_id", "=", siteId);
        //去除回收站
        cnd.and("status", "!=", "-1");
        return cmsContentService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    /**
     * 获取文章统计数据
     *
     * @return
     */
    @RequestMapping("statistic")
    @SJson
    @RequiresPermissions("platform.cms.content")
    public Object getStatistic(HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        String channel_id = req.getParameter("channel_id");
        return Result.success("", cmsContentService.getStatisticMap(site_id, channel_id));
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.content")
    public String add(HttpServletRequest req) {
        List<Integer> list=new ArrayList<>();
        String site_id = req.getParameter("site_id");
        String channel_id = req.getParameter("channel_id");
        String model_id = req.getParameter("model_id");
        String userId = StringUtil.getUid();
        Sys_user user = sysUserService.fetch(userId);
        //关联专题
        List<Cms_topic> topics = this.getTopics(channel_id, site_id);
        Cms_model model = cmsModelService.fetch(Strings.sBlank(model_id));
        Cms_site site = cmsSiteService.fetch(Strings.sBlank(site_id));
        List<String> modellist = this.getModellist(site, model);
        //部门
        List<Sys_dict> departs=sysDictService.getSubListByCode("department");
        Cms_model cms_model=cmsModelService.fetch(model_id);
        //工作地区
        List<Sys_area> areas=sysAreaService.getAreaNodeList("86");
        req.setAttribute("modelName", cms_model.getModel_name());
        req.setAttribute("departs", departs);
        req.setAttribute("areas", areas);
        req.setAttribute("modellist", modellist);
        req.setAttribute("username", user.getUsername());
        req.setAttribute("site_id", site_id);
        req.setAttribute("model_id", model_id);
        req.setAttribute("content_id", "0");
        req.setAttribute("channel_id", channel_id);
        req.setAttribute("topics", topics);
        return "pages/platform/cms/content/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.add")
    public Object addDo(Cms_content cmsContent, HttpServletRequest req) {
        String tag2 = req.getParameter("tag");
        String type = req.getParameter("type");
        String txt = req.getParameter("txt");
        String ip = Lang.getIP(req);
        String resUrl = req.getRequestURI();
        //附件地址
        String attachmentInfo = req.getParameter("attachmentInfo");
        //图片集
        String picPaths = req.getParameter("picPaths");
        //附件名称
        String[] attachmentNames = req.getParameterValues("attachmentNames");
        String userId = StringUtil.getUid();
        Sys_user user = sysUserService.fetch(userId);
        try {
            cmsContentService.addDo(cmsContent, tag2, type, txt, picPaths, attachmentInfo, user, ip, resUrl);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.content")
    public String edit(@PathVariable String id, HttpServletRequest req) {
        Cms_content content = cmsContentService.fetch(id);
        String model_id = "";
        String site_id = "";
        String channel_id = "";
        if (content != null) {
            List<Cms_content_txt> txts = cmsContentTxtService.query(Cnd.where("content_id", "=", content.getId()));
            Cms_content_txt txt = new Cms_content_txt();
            if (txts != null && txts.size() > 0) {
                txt = txts.get(0);
            }
            model_id = content.getModel_id();
            site_id = content.getSite_id();
            channel_id = content.getChannel_id();
            List<Record> taglist = cmsTagService.list(Sqls.create("SELECT A.* FROM CMS_TAG A ,CMS_CONTENT_TAG B WHERE A.ID=B.TAG_ID AND B.CONTENT_ID='" + content.getId() + "'"));
            String tag = "";
            for (int i = 0; i < taglist.size(); i++) {
                if (i == taglist.size() - 1) {
                    tag += taglist.get(i).getString("name");
                } else tag += taglist.get(i).getString("name") + ",";
            }
            //部门
            List<Sys_dict> departs=sysDictService.getSubListByCode("department");
            Cms_model cms_model=cmsModelService.fetch(model_id);
            //工作地区
            List<Sys_area> areas=sysAreaService.getAreaNodeList("86");
            req.setAttribute("modelName", cms_model.getModel_name());
            req.setAttribute("departs", departs);
            req.setAttribute("areas", areas);
            req.setAttribute("piclist", cmsContentPictureService.query(Cnd.where("content_id", "=", content.getId()).asc("priority")));
            req.setAttribute("filelist", cmsContentFileService.query(Cnd.where("content_id", "=", content.getId()).asc("priority")));
            req.setAttribute("ctxt", txt);
            req.setAttribute("tag", tag);
            List<Cms_topic> topics = this.getTopics(channel_id, site_id);
            req.setAttribute("obj", content);
            Cms_model model = cmsModelService.fetch(Strings.sBlank(model_id));
            Cms_site site = cmsSiteService.fetch(Strings.sBlank(site_id));
            List<String> modellist = this.getModellist(site, model);
            req.setAttribute("modellist", modellist);
            req.setAttribute("site_id", site_id);
            req.setAttribute("model_id", model_id);
            req.setAttribute("content_id", id);
            req.setAttribute("channel_id", channel_id);
            req.setAttribute("topics", topics);
        }
        return "pages/platform/cms/content/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.edit")
    public Object editDo(Cms_content cmsContent, HttpServletRequest req) {
        String txt = req.getParameter("txt");
        String type = req.getParameter("type");
        String tag1 = req.getParameter("tag");
        String userId = StringUtil.getUid();
        Sys_user user = sysUserService.fetch(userId);
        //图片集
        String picPaths = req.getParameter("picPaths");
        //附件名称
        String[] attachmentNames = req.getParameterValues("attachmentNames");
        //附件地址
        String attachmentPaths = req.getParameter("attachmentPaths");
        String ip = Lang.getIP(req);
        String resUrl = req.getRequestURI();
        try {
            cmsContentService.editDo(cmsContent, tag1, type, txt, picPaths, attachmentPaths, user, ip, resUrl);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/delete"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.delete")
    public Object delete(@RequestParam("ids") String ids1, HttpServletRequest req) {
        try {
            String[] ids = StringUtils.split(ids1, ",");
            Cms_channel cms_channel = null;
            if (ids != null && ids.length > 0) {
                // 校验栏目设置为审核后“不能修改删除”
                for (int i = 0, count = ids.length; i < count; i++) {
                    Cms_content content = cmsContentService.fetch(ids[i]);
                    if (cms_channel == null) {
                        cms_channel = cmsChannelService.fetch(content.getChannel_id());
                    }
                    if (ChannelCheckedEnum.MODIFYDELETE.getKey().equals(cms_channel.getChecked()) && content.getStatus() == ContentStatusEnum.FINALIZED.getKey()) {//栏目设置为审核后“不能修改删除”
                        return Result.error("cms.content.check.result.check.deletes");
                    }
                }
                // 删除已经生成的文章页，并且修改文章为未生成静态页状态
                for (int i = 0, count = ids.length; i < count; i++) {
                    Cms_content content = cmsContentService.fetch(ids[i]);
                    content.setIs_static(CmsIsStaticEnum.RELEASED.getKey());
                    cmsContentService.updateIgnoreNull(content);
                    this.staticContentAndTopic(content);

                    //加入搜索引擎job
                   /* Cms_content_estemp estemp = new Cms_content_estemp();
                    estemp.setAction("delete");
                    estemp.setContentId(content.getId());
                    cmsContentEstempService.insert(estemp);*/

                    //mq文章同步es jxx
                    if (SpringUtil.isRabbitEnabled()) {
                        String exchange = "topicExchange";
                        String routeKey = "topic.es.cmsContent";
                        RabbitMessage msg = new RabbitMessage(exchange, routeKey, NutMap.NEW().addv("contentId", content.getId()).addv("action", "delete"));
                        rabbitProducer.sendMessage(msg);
                    }
                }
                req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
            }
            //根据栏目和站点更新栏目页和首页
            staticPageService.updateChannelAndIndex(cms_channel, cmsSiteService.fetch(cms_channel.getSite_id()));
            cmsContentService.update(org.nutz.dao.Chain.make("status", "-1"), Cnd.where("site_id", "=", cms_channel.getSite_id()).and("id", "IN", ids));

            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.cms.content")
    public String detail(@PathVariable String id, HttpServletRequest req, HttpServletResponse res) {
        Cms_content obj = cmsContentService.fetch(id);
        Cms_content_txt txt = cmsContentTxtService.fetch(Cnd.where("content_id", "=", id));
        String content = staticPageService.contentPageHmtl(obj);
        try {
            // res.getWriter().write(content);
            req.setAttribute("obj", obj);
            req.setAttribute("txt", txt.getTxt());
        } catch (Exception e) {
            log.info("platform.cms.content.detail;" + e.getMessage());
            e.printStackTrace();
        }
        return "pages/platform/cms/content/detail";
    }

    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("platform.cms.content")
    public Object tree(@PathVariable(required = false) String pid, HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        Sql sql;
        String userId = StringUtil.getUid();
        Sys_user user = sysUserService.fetch(userId);
        List<String> roleIdList = sysUserService.getRoleIdList(user);
        List<Map<String, Object>> tree = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        if (Strings.isBlank(pid)) {
            obj.put("id", "root");
            obj.put("text", "栏目列表");
            obj.put("children", false);
            tree.add(obj);
            String dbType = cmsContentService.dao().getJdbcExpert().getDatabaseType();
            if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
                sql = Sqls.create("select distinct a.hasChildren,a.id,a.site_id,a.model_id,a.name,a.path,a.title,a.keywords,a.description,a.tpl_channel,a.is_display,a.is_static,a.blank,a.linkurl,a.flowid,a.checked,a.location from cms_channel a,cms_channel_role b where a.site_id=b.site_id and a.id=b.channel_id and b.role_id in (@roleId) and a.site_id=@s and a.parentId is null order by a.location");
            } else {
                sql = Sqls.create("select distinct a.hasChildren,a.id,a.site_id,a.model_id,a.name,a.path,a.title,a.keywords,a.description,a.tpl_channel,a.is_display,a.is_static,a.blank,a.linkurl,a.flowid,a.checked,a.location from cms_channel a,cms_channel_role b where a.site_id=b.site_id and a.id=b.channel_id and b.role_id in (@roleId) and a.site_id=@s and a.parentId = '' order by a.location");
            }
            sql.params().set("s", site_id);
            sql.params().set("roleId", roleIdList.toArray());
        } else {
            sql = Sqls.create("select distinct a.hasChildren,a.id,a.site_id,a.model_id,a.name,a.path,a.title,a.keywords,a.description,a.tpl_channel,a.is_display,a.is_static,a.blank,a.linkurl,a.flowid,a.checked,a.location from cms_channel a,cms_channel_role b where a.site_id=b.site_id and a.id=b.channel_id and b.role_id in (@roleId) and a.site_id=@s and a.parentId = @c order by a.location");
            sql.params().set("s", site_id);
            sql.params().set("c", pid);
            sql.params().set("roleId", roleIdList.toArray());
        }
        List<Record> list = cmsChannelService.list(sql);
        for (Record unit : list) {
            obj = new HashMap<>();
            String id = unit.getString("id");
            obj.put("id", id);
            obj.put("text", unit.getString("name"));
            Sql sql2 = Sqls.create("select count(1) from cms_channel a,cms_channel_role b where a.site_id=b.site_id and a.id=b.channel_id and b.role_id in (@roleId) and a.site_id=@s and a.parentId = @c order by a.location");
            sql2.params().set("s", site_id);
            sql2.params().set("c", id);
            sql2.params().set("roleId", roleIdList.toArray());
            int num = cmsChannelService.count(sql2);
            if (num > 0) {
                obj.put("children", true);
            } else {
                obj.put("children", false);
                obj.put("icon", "/assets/platform/images/icons/filetype/txt.gif");
            }
            tree.add(obj);
        }
        return tree;
    }

    @RequestMapping(value = {"/checkSourceName"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content")
    public Object checkSourceName(HttpServletRequest req, HttpServletResponse response) {
        String name = req.getParameter("name");
        List<Cms_content_source> cmsContentSource = cmsContentSourceService.query(Cnd.where("source", "=", name));
        if (cmsContentSource == null || cmsContentSource.size() == 0) {
            return Result.success("globals.result.success");
        } else {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/addSource"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content")
    public Object addSource(HttpServletRequest req) {
        String name = req.getParameter("name");
        Cms_content_source cmsContentSource = new Cms_content_source();
        cmsContentSource.setSource(name);
        if (Strings.sBlank(name).length() == 0) {
            return Result.error("globals.result.error");
        }
        try {
            cmsContentSourceService.insert(cmsContentSource);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 获取登陆用户当前网站栏目的操作权限，用户可能存在多个角色
     *
     * @return
     */
    @RequestMapping("getChannelRole")
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content")
    public Cms_channel_role getChannelRole(HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        String channel_id = req.getParameter("channel_id");
        String userId = StringUtil.getUid();
        Cms_channel cms_channel = cmsChannelService.fetch(channel_id);
        Sys_user user = sysUserService.fetch(userId);
        List<String> roleIdList = sysUserService.getRoleIdList(user);
        Cms_channel_role cc = new Cms_channel_role();
        cc.setChannel_id(channel_id);
        cc.setSite_id(site_id);
        cc.setHas_add("N");
        cc.setHas_check("N");
        cc.setHas_del("N");
        cc.setHas_move("N");
        cc.setHas_push("N");
        cc.setHas_share("N");
        cc.setHas_static("N");
        cc.setHas_update("N");
        cc.setChecked("Y");
        if (cms_channel != null) {
            String checked = Strings.sBlank(cms_channel.getChecked());
            if (ChannelCheckedEnum.MODIFYDELETE.getKey().equals(checked)) {//栏目设置为审核后“不能修改删除”
                cc.setChecked("N");
            }
        }
        Sql sql = Sqls
                .create("select * from Cms_channel_role where site_id=@s and channel_id=@c and role_id in (@roleId)");
        sql.params().set("s", site_id);
        sql.params().set("c", channel_id);
        sql.setParam("roleId", roleIdList.toArray());
        List<Record> list = cmsChannelRoleService.list(sql);
        for (int i = 0; i < list.size(); i++) {
            Record t = list.get(i);
            if ("Y".equals(t.getString("has_add"))) {
                cc.setHas_add("Y");
            }
            if ("Y".equals(t.getString("has_check"))) {
                cc.setHas_check("Y");
            }
            if ("Y".equals(t.getString("has_del"))) {
                cc.setHas_del("Y");
            }
            if ("Y".equals(t.getString("has_move"))) {
                cc.setHas_move("Y");
            }
            if ("Y".equals(t.getString("has_push"))) {
                cc.setHas_push("Y");
            }
            if ("Y".equals(t.getString("has_share"))) {
                cc.setHas_share("Y");
            }
            if ("Y".equals(t.getString("has_static"))) {
                cc.setHas_static("Y");
            }
            if ("Y".equals(t.getString("has_update"))) {
                cc.setHas_update("Y");
            }
        }
        return cc;
    }

    /**
     * 提交
     *
     * @param ids
     * @param req
     * @return
     */
    @RequestMapping(value = {"/dosubmit"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.dosubmit")
    public Object dosubmit(@RequestParam("ids") String ids, HttpServletRequest req) {
        try {
            String[] ids2 = StringUtils.split(ids, ",");
            int count = cmsContentService.count(Cnd.where("status", "=", 3).and("id", "IN", ids2));
            if (count != 0) {
                return Result.success("cms.content.check.result.check.dosubmits");
            }
            int flag = cmsContentService.update(Chain.make("status", "1"), Cnd.where("id", "IN", ids2));
            if (flag > 0) {
                return Result.success("globals.result.success");
            }
            return Result.error("globals.result.error");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 审核栏目，有工作流单选，无工作流 多选
     *
     * @param ids
     * @param channel_id
     * @param site_id
     * @param req
     */
    @RequestMapping(value = {"/checks"})
    @RequiresPermissions("platform.cms.content")
    public String checks(@RequestParam("ids") String ids, @RequestParam("channel_id") String channel_id, @RequestParam("site_id") String site_id, HttpServletRequest req) {
        Cms_channel channel = cmsChannelService.fetch(channel_id);
        String[] ids2 = StringUtils.split(ids, ",");
        List<Cms_content> contentList = cmsContentService.query(Cnd.where("id", "IN", ids2).and("status", "=", "3"));
        if (contentList.size() != 0) {
            req.setAttribute("resMsg", "cms.content.resMsg.msg1");
        }
        List<Cms_content> conList = cmsContentService.query(Cnd.where("id", "IN", ids2));
        if (!Strings.sBlank(channel.getFlowid()).equals("0") && conList.size() > 1) {
            req.setAttribute("resMsg", "cms.content.resMsg.msg2");
        }
        if (ids2.length == 1 && !Strings.sBlank(channel.getFlowid()).equals("0")) {
            return check(ids2[0], req);
        }
        String content_ids = Arrays.toString(ids2).replace("[", "").replace("]", "");
        req.setAttribute("flowid", 0);
        req.setAttribute("channel", channel);
        req.setAttribute("channel_id", channel_id);
        req.setAttribute("ids", content_ids);
        req.setAttribute("site_id", site_id);
        return "pages/platform/cms/content/check/check";
    }

    /**
     * 审核页面
     *
     * @param content_id
     * @param req
     */
    @RequestMapping(value = {"/check"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content")
    public String check(@RequestParam("content_id") String content_id, HttpServletRequest req) {   //@Param("ids") String[] ids,
        String userId = StringUtil.getUid();
        Sys_user user = sysUserService.fetch(userId);
        List<String> roleIdList = sysUserService.getRoleIdList(user);
        Cms_content cms = cmsContentService.fetch(content_id);
        Cms_channel channel = cmsChannelService.fetch(cms.getChannel_id());
        String flowid = channel.getFlowid();
        if (Strings.sBlank(channel.getFlowid()).length() > 0) {
            Cms_content_check check = cmsContentCheckService.fetch(Cnd.where("content_id", "=", content_id).and("flowid", "=", flowid).and("status", "=", "0"));
            int step = 0;
            if (check != null) {
                step = check.getCheck_step();
            }
            Sql sql = Sqls.create("SELECT * FROM Cms_workflow_info where flowid=@a and step=@b and roleid in (@roleId) ");
            sql.setParam("roleId", roleIdList.toArray());
            sql.params().set("a", flowid);
            sql.params().set("b", step);
            List<Record> list1 = cmsWorkflowInfoService.list(sql);
            Record info = new Record();
            if (list1 != null && list1.size() > 0) {
                info = list1.get(0);
            }
            List<Cms_workflow_info> list = cmsWorkflowInfoService.query(Cnd.where("flowid", "=", flowid).asc("step"));
            req.setAttribute("workflow", cmsWorkflowService.fetch(flowid));
            req.setAttribute("infolist", list);
            req.setAttribute("check", check);
            req.setAttribute("wksize", list.size());
            req.setAttribute("step", step);
            req.setAttribute("info", info);
        }
        req.setAttribute("content", cms);
        req.setAttribute("flowid", flowid);
        req.setAttribute("channel", channel);
        req.setAttribute("channel_id", cms.getChannel_id());
        req.setAttribute("content_id", content_id);  //+"" == "" ? ids[0] : content_id
        req.setAttribute("site_id", cms.getSite_id());
        return "pages/platform/cms/content/check/check";
    }

    /**
     * 审核处理，分别对定义工作流和未定义工作量进行处理
     *
     * @param check_opinion
     * @param is_checked
     * @param flowid
     * @param type
     * @param step
     * @param site_id
     * @param channel_id
     * @param content_id
     * @param req
     * @return
     */
    @RequestMapping(value = {"/savecheck"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.savecheck")
    public Object savecheck(@RequestParam(value = "check_opinion", required = false) String check_opinion, @RequestParam("is_checked") String is_checked, @RequestParam("flowid") String flowid, @RequestParam("type") int type, @RequestParam("step") Integer step, @RequestParam("site_id") String site_id, @RequestParam("channel_id") String channel_id, @RequestParam("content_id") String content_id, HttpServletRequest req) {
        try {
            String userId = StringUtil.getUid();
            Sys_user user = sysUserService.fetch(userId);
            String ip = Lang.getIP(req);
            String resUrl = req.getRequestURI();
            Cms_content content = cmsContentService.fetch(content_id);
            String res = cmsContentService.savecheck(is_checked, check_opinion, flowid, content, step, user, ip, resUrl);
            return Result.success(res);
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/savechecks"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.savechecks")
    public Object savechecks(@RequestParam("site_id") String site_id, @RequestParam("ids") String ids, HttpServletRequest req) {
        try {
            String userId = StringUtil.getUid();
            Sys_user user = sysUserService.fetch(userId);
            String ip = Lang.getIP(req);
            String resUrl = req.getRequestURI();
            cmsContentService.savechecks(ids, user, ip, resUrl);
            return Result.success("cms.content.check.result.final.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/contentMove", "/contentMove/{pid}"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content")
    public Object contentMove(@RequestParam("ids") String[] ids, @PathVariable(required = false) String pid, HttpServletRequest req)
            throws IOException {
        String dbType = cmsContentService.dao().getJdbcExpert().getDatabaseType();
        List<Map<String, Object>> tree = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        if (Strings.isBlank(pid)) {
            obj.put("id", "root");
            obj.put("text", "栏目列表");
            obj.put("children", false);
            tree.add(obj);
        }

        //一级目录为站点
        if (Strings.sBlank(pid).contains("SITE")) {
            List<Cms_channel> list = new ArrayList<>();
            if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
                list = cmsChannelService.query(Cnd.where("site_id", "=", pid.replace("SITE", "")).and(new Static(" parentId is null ")));
            } else {
                list = cmsChannelService.query(Cnd.where("site_id", "=", pid.replace("SITE", "")).and("parentId", "=", ""));
            }
            for (Cms_channel unit : list) {
                obj = new HashMap<>();
                obj.put("id", unit.getId());
                obj.put("text", unit.getName());
                obj.put("children", unit.isHasChildren());
                tree.add(obj);
            }
        } else {
            //上级目录为栏目
            if (!Strings.sBlank(pid).equals("")) {
                List<Cms_channel> list = cmsChannelService.query(Cnd.where("parentId", "=", Strings.sBlank(pid)));
                for (Cms_channel unit : list) {
                    obj = new HashMap<>();
                    obj.put("id", unit.getId());
                    obj.put("text", unit.getName());
                    obj.put("children", unit.isHasChildren());
                    tree.add(obj);
                }
            } else {
                List<Cms_site> siteList = cmsSiteService.query(Cnd.where("1", "=", "1").asc("location"));
                //首次进入 获取站群列表，并且设置为一级树
                for (int i = 0, count = siteList.size(); i < count; i++) {
                    obj = new HashMap<>();
                    Cms_site cs = siteList.get(i);
                    obj.put("id", "SITE" + cs.getId());
                    obj.put("text", cs.getSite_name());
                    obj.put("pId", "root");
                    obj.put("parent", "");
                    int channelCount = 0;
                    if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
                        channelCount = cmsChannelService.count(Cnd.where("site_id", "=", cs.getId()).and(new Static(" parentId is null ")));
                    } else {
                        channelCount = cmsChannelService.count(Cnd.where("site_id", "=", cs.getId()).and("parentId", "=", ""));
                    }
                    obj.put("children", channelCount > 0 ? true : false);
                    tree.add(obj);
                }
            }
        }
        req.setAttribute("tree", tree);
        req.setAttribute("ids", Arrays.toString(ids));
        return tree;
    }

    /**
     * 移动文章
     *
     * @param channel_id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"/moveSave"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.moveSave")
    public Object moveSave(@RequestParam("moveChannel_id") String channel_id, @RequestParam("ids") String ids1) {
        try {
            String[] content_ids = StringUtils.split(ids1, ",");
            Cms_channel cms_channel = cmsChannelService.fetch(channel_id);
            // 删除已经生成的文章页，并且修改文章为未生成静态页状态
            for (int i = 0, count = content_ids.length; i < count; i++) {
                Cms_content content = cmsContentService.fetch(content_ids[i]);
                cmsContentService.update(Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("id", "=", content_ids[i]));
                this.staticContentAndTopic(content);
            }
            /** 根据文章ID 获取 channel_id 和 site_id 跟新栏目和首页 **/
            if (content_ids.length != 0) {
                Cms_content cc = cmsContentService.fetch(content_ids[0]);
                staticPageService.updateChannelAndIndex(cmsChannelService.fetch(channel_id), cmsSiteService.fetch(cc.getSite_id()));
            }
            cmsContentService.update(Chain.make("channel_id", channel_id).add("site_id", cms_channel.getSite_id()).add("status", "0"), Cnd.where("id", "IN", content_ids));
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /*
    推送
     */
    @RequestMapping(value = {"/pushSave"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content")
    public Object pushSave(@RequestParam("moveChannel_id") String channel_id, @RequestParam(value = "ids") String[] content_ids) {
        try {
            cmsContentService.pushSave(content_ids, channel_id);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 发布
     *
     * @param ids1
     * @param req
     * @return
     */
    @RequestMapping(value = {"/dohtmls"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.dohtmls")
    public Object dohtmls(@RequestParam("ids") String ids1, HttpServletRequest req) {
        try {
            String[] ids = StringUtils.split(ids1, ",");
            // 判断是否审核通过
            List<Cms_content> contents = cmsContentService.query(Cnd.where("STATUS", "<>", "3").and("id", "IN", ids));
            if (contents.size() != 0) {
                return Result.error("cms.content.check.result.check.dohtmls");
            }
            contents = cmsContentService.query(Cnd.where("STATUS", "=", "3").and("id", "IN", ids));
            String resMsg = staticPageService.contentPages(contents);
            if (!"0".equals(resMsg)) {//返回错误信息
                return Result.error(resMsg);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 审核记录页面
     *
     * @return
     */
    @RequestMapping("checklog")
    @RequiresPermissions("platform.cms.content")
    public String checklog(HttpServletRequest req) {
        String content_id = req.getParameter("content_id");
        req.setAttribute("content_id", content_id);
        return "pages/platform/cms/content/check/index";
    }

    /**
     * 审核记录列表
     *
     * @return
     */
    @RequestMapping(value = {"/checkloglist"})
    @SJson("full")
    @RequiresPermissions("platform.cms.content")
    public String checkloglist(@RequestParam("content_id") String content_id) {
        // List<Cms_content_check> list= cmsContentCheckService.query(Cnd.where("content_id", "=", content_id).desc("check_step"));
        List<Cms_content_check> list = cmsContentCheckService.query(Cnd.where("content_id", "=", content_id).desc("opAt"));
        Sql sql = Sqls.create("select id,username from sys_user where id in (select user_id from cms_content_check where content_id=@id) or " +
                "id in (select reviewer from cms_content_check where content_id=@id)");
        sql.params().set("id", content_id);
        Map<String, String> hm = cmsContentCheckService.getMap(sql);
        if (hm != null) hm.put("0", "");
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Cms_content_check c = list.get(i);
            JSONObject obj = new JSONObject();
            obj.put("id", c.getId());
            obj.put("check_step", c.getCheck_step());
            obj.put("user_id", com.aebiz.app.web.commons.utils.StringUtil.null2String(hm.get(String.valueOf(c.getUser_id()))));
            obj.put("check_opinion", c.getCheck_opinion());
            obj.put("reviewer", com.aebiz.app.web.commons.utils.StringUtil.null2String(hm.get(String.valueOf(c.getReviewer()))));
            obj.put("is_checked", c.getIs_checked());
            obj.put("check_time", c.getCheck_date());
            array.put(obj);
        }
        return array.toString();
    }

    /**
     * 操作历史页面
     *
     * @return
     */
    @RequestMapping("log")
    @RequiresPermissions("platform.cms.content")
    public String log(HttpServletRequest req) {
        String content_id = req.getParameter("content_id");
        req.setAttribute("content_id", content_id);
        return "pages/platform/cms/log/index";
    }

    /**
     * 操作历史列表
     *
     * @return
     */
    @RequestMapping(value = {"/loglist"})
    @SJson("full")
    @RequiresPermissions("platform.cms.content")
    public String loglist(@RequestParam("content_id") String content_id) {
        List<Cms_log> list = cmsLogService.query(Cnd.where("content_id", "=", content_id).asc("log_time"));
        Sql sql = Sqls.create("select id,username from sys_user where id in (select user_id from Cms_log where content_id=@id)");
        sql.params().set("id", content_id);
        Map<String, String> hm = cmsContentCheckService.getMap(sql);
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Cms_log c = (Cms_log) list.get(i);
            JSONObject obj = new JSONObject();
            obj.put("id", c.getId());
            obj.put("title", c.getTitle());
            obj.put("content", c.getContent());
            obj.put("ip", c.getIp());
            obj.put("log_time", c.getLog_time());
            obj.put("username", com.aebiz.app.web.commons.utils.StringUtil.null2String(hm.get(String.valueOf(c.getUser_id()))));
            array.put(obj);
        }
        return array.toString();
    }
    //==================================================私有方法=====================================================================

    /**
     * 获取模版路径列表
     *
     * @param site
     * @param model
     * @return
     */
    private List<String> getModellist(Cms_site site, Cms_model model) {
        String tplBase = Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path();
        tplBase = tplBase.replaceAll("\\\\|//", "/");
        String tpl_path = tplBase + "/" + site.getSite_css() + "/content/";
        List<String> modellist = new ArrayList<String>();
        File dir = new File(tpl_path);
        File[] files = dir.listFiles();
        if (null != files) {
            for (File f : files) {
                if (!f.getName().startsWith(model.getTpl_content_perfix()))
                    continue;
                if (f.isFile() && f.getName().toLowerCase().indexOf("html") > 0) {
                    String listFPath = f.getPath();
                    if (listFPath.contains("\\")) {
                        listFPath = listFPath.replaceAll("\\\\", "/");
                    }
                    modellist.add(listFPath.replace(tplBase, ""));
                }
            }
        }
        return modellist;
    }

    /**
     * 获取可关联的专题信息
     *
     * @param channel_id
     * @param site_id
     * @return
     */
    private List<Cms_topic> getTopics(String channel_id, String site_id) {
        List<Cms_topic> cms_Topics = cmsTopicService.query(Cnd.where("site_id", "=", site_id).and("channelIds", "like", "%" + channel_id + "%"));
        List<Cms_topic> topics = new ArrayList<Cms_topic>();
        if (cms_Topics != null && cms_Topics.size() > 0) {
            for (Cms_topic topic : cms_Topics) {
                if (Strings.sBlank(topic.getChannelIds()).length() > 0) {
                    if (Strings.sBlank(topic.getChannelIds()).contains(channel_id)) {
                        topics.add(topic);
                    }
                } else {
                    topics.add(topic);
                }
            }
        } else {
            topics = cmsTopicService.query(Cnd.where("site_id", "=", site_id));
        }
        return topics;
    }

    /**
     * 删除文章页及专题页
     *
     * @param content
     */
    private void staticContentAndTopic(Cms_content content) {
        staticPageService.deleteContent(content);//删除文章页
        //删除对应的专题
        if (!Strings.isEmpty(content.getTopic_id())) {
            Cms_topic cms_topic = cmsTopicService.fetch(content.getTopic_id());
            try {
                staticPageService.delTopic(cms_topic);
            } catch (IOException e) {
                log.info("platform.cms.content.staticContentAndTopic;" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
