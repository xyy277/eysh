package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.*;
import com.aebiz.app.cms.modules.services.*;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.web.commons.utils.PinyinUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.DB;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.Static;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/platform/cms/channel")
public class CmsChannelController {
    private static final Log log = Logs.get();
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsModelService cmsModelService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsWorkflowService cmsWorkflowService;
    @Autowired
    private CmsChannelModelService cmsChannelModelService;
    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private CmsContentService cmsContentService;

    @RequestMapping("")
    @RequiresPermissions("platform.cms.channel")
    public String index(HttpServletRequest req) {
        String userId = StringUtil.getUid();
        String req_site_id = req.getParameter("site_id");
        Sys_user user = sysUserService.fetch(userId);
        List<Record> records = cmsSiteService.getSiteRole(user);
        String siteId = "";
        if (records != null && records.size() > 0) {
            siteId = records.get(0).getString("id");
        }
        if (!Strings.isBlank(req_site_id)) {
            siteId = req_site_id;
        }
        req.setAttribute("siteId", Strings.sBlank(siteId));
        req.setAttribute("list", records);
        return "pages/platform/cms/channel/index";
    }

    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.channel")
    public Object data(DataTable dataTable, HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        String id = req.getParameter("id");
        String site_id = req.getParameter("site_id");
        if (!Strings.isBlank(site_id)) cnd.and("site_id", "=", site_id);
        if ("root".equals(id) || Strings.isBlank(id)) {
            String dbType = cmsChannelService.dao().getJdbcExpert().getDatabaseType();
            if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
                cnd.and(new Static("( parentId is null or parentId = '')"));
            } else {
                cnd.and("parentId", "=", "");
            }
        } else {
            cnd.and("parentId", "=", id);
        }
        return cmsChannelService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.channel")
    public String add(HttpServletRequest req) {
        String model_id = req.getParameter("model_id");
        String site_id = req.getParameter("site_id");
        String channel_id = req.getParameter("channel_id");

        List<Cms_model> models = cmsModelService.query(Cnd.where("id", "=", Strings.sBlank(model_id)));
        Cms_model model = new Cms_model();
        if (models != null && models.size() > 0) {
            model = models.get(0);
        }
        List<Cms_site> sites = cmsSiteService.query(Cnd.where("id", "=", Strings.sBlank(site_id)));
        Cms_site site = new Cms_site();
        if (sites != null && sites.size() > 0) {
            site = sites.get(0);
        }
        String channel_name = "顶级栏目";
        String channel_path = "";
        Cms_channel cms_channel = new Cms_channel();
        if (!Strings.isEmpty(channel_id)) {
            List<Cms_channel> list = cmsChannelService.query(Cnd.where("id", "=", Strings.sBlank(channel_id)).asc("location"));
            if (list != null && list.size() > 0) {
                cms_channel = list.get(0);
                channel_name = cms_channel.getName();
                channel_path = cms_channel.getPath();
            }
        }
        List<String> modellist = new ArrayList<String>();
        //获取栏目模版
        this.getTplchannel(modellist, site, model);
        //工作流
        List<Cms_workflow> wflist = cmsWorkflowService.query(Cnd.orderBy().asc("location"));
        req.setAttribute("wflist", wflist);
        req.setAttribute("model", model);
        req.setAttribute("channel", cms_channel);
        req.setAttribute("modellist", modellist);
        req.setAttribute("channel_name", channel_name);
        req.setAttribute("model_id", model_id);
        req.setAttribute("site_id", site_id);
        req.setAttribute("channel_id", channel_id);
        req.setAttribute("channel_path", channel_path);
        req.setAttribute("mjson", "");
        return "pages/platform/cms/channel/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_channel")
    @RequiresPermissions("platform.cms.channel.add")
    public Object addDo(Cms_channel cmsChannel, HttpServletRequest req) {
        try {
            String ip = Lang.getIP(req);
            String resUrl = req.getRequestURI();
            String userId = StringUtil.getUid();
            if (cmsChannel.getParentId().equals("root")) {
                cmsChannel.setParentId("");
            }
            Sys_user user = sysUserService.fetch(userId);
            String mjson = req.getParameter("mjson");
            List<Cms_channel_model> models = this.getChannelModes(mjson, cmsChannel);
            cmsChannelService.addDo(cmsChannel, models, user, ip, resUrl);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.channel")
    public String edit(@PathVariable String id, HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        Cms_channel channel = cmsChannelService.fetch(id);
        Cms_model model = cmsModelService.fetch(Strings.sBlank(channel.getModel_id()));
        Cms_site site = cmsSiteService.fetch(Strings.sBlank(site_id));
        String channel_name = "顶级栏目";
        Cms_channel cms_channel = new Cms_channel();
        if (!Strings.isEmpty(id)) {
            List<Cms_channel> list = cmsChannelService.query(Cnd.where("id", "=", Strings.sBlank(channel.getParentId())).asc("location"));
            if (list != null && list.size() > 0) {
                cms_channel = list.get(0);
                channel_name = cms_channel.getName();
            }
        }
        List<String> modellist = new ArrayList<String>();
        //获取栏目模版
        this.getTplchannel(modellist, site, model);
        JSONArray array = new JSONArray();
        Sql sql = Sqls.create("select * from Cms_channel_model where site_id=@s and channel_id=@c");
        sql.params().set("s", channel.getSite_id());
        sql.params().set("c", channel.getId());
        List<Record> mj = cmsChannelModelService.list(sql);
        mj.forEach(m -> {
            JSONObject obj = new JSONObject();
            obj.put("id", m.getString("model_id"));
            obj.put("tpl", m.getString("tpl_content"));
            array.put(obj);
        });
        //工作流
        List<Cms_workflow> wflist = cmsWorkflowService.query(Cnd.orderBy().asc("location"));
        req.setAttribute("wflist", wflist);
        req.setAttribute("sjchannel", cms_channel);
        req.setAttribute("modellist", modellist);
        req.setAttribute("channel_name", channel_name);
        req.setAttribute("model", model);
        req.setAttribute("channel", channel);
        req.setAttribute("mjson", array);
        req.setAttribute("obj", cmsChannelService.fetch(id));
        return "pages/platform/cms/channel/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_channel")
    @RequiresPermissions("platform.cms.channel.edit")
    public Object editDo(Cms_channel cmsChannel, HttpServletRequest req) {
        try {
            String mjson = req.getParameter("mjson");
            String userId = StringUtil.getUid();
            Sys_user user = sysUserService.fetch(userId);
            String ip = Lang.getIP(req);
            String resUrl = req.getRequestURI();
            List<Cms_channel_model> models = this.getChannelModes(mjson, cmsChannel);
            cmsChannelService.editDo(cmsChannel, models, user, ip, resUrl);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_channel")
    @RequiresPermissions("platform.cms.channel.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids", required = false) String[] ids, HttpServletRequest req) {
        /* 判断该栏目下是否有文章，如果有不允许删除栏目 */
        List<Cms_content> cmsLists = cmsContentService.query(Cnd.where("channel_id", "=", id).and("status", "<>", "-1"));
        if (cmsLists.size() != 0) {
            return Result.error("cms.channel.delete.isContent");
        }
        //判断该栏目是否存在子栏目，存在不允许删除
        List<Cms_channel> chList = cmsChannelService.query(Cnd.where("parentId", "=", id));
        if (chList.size() != 0) {
            return Result.error("cms.channel.delete.isChannel");
        }
        try {
            String ip = Lang.getIP(req);
            String resUrl = req.getRequestURI();
            String userId = StringUtil.getUid();
            Sys_user user = sysUserService.fetch(userId);
            Cms_channel cms_channel = cmsChannelService.fetch(id);
            cmsChannelService.deleteAndChild(cms_channel, user, ip, resUrl);
            req.setAttribute("id", id);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.cms.channel")
    public String detail(@PathVariable String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", cmsChannelService.fetch(id));
        } else {
            req.setAttribute("obj", null);
        }
        return "pages/platform/cms/channel/detail";
    }

    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("platform.cms.channel")
    public Object tree(@PathVariable(required = false) String pid, HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        Cnd cnd = Cnd.NEW();
        String dbType = cmsChannelService.dao().getJdbcExpert().getDatabaseType();
        if (StringUtils.isNotEmpty(pid)) {
            cnd.and("parentId", "=", Strings.sBlank(pid));
        } else {
            if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
                cnd.and(new Static(" parentId is null "));
            } else {
                cnd.and("parentId", "=", Strings.sBlank(pid));
            }
        }
        if (!Strings.isEmpty(site_id)) {
            cnd.and("site_id", "=", site_id);
        }
        List<Cms_channel> list = cmsChannelService.query(cnd.asc("location"));
        List<Map<String, Object>> tree = new ArrayList<>();
        if (Strings.isBlank(pid)) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", "root");
            obj.put("text", "栏目列表");
            obj.put("pid", "");
            obj.put("children", false);
            tree.add(obj);
        }
        list.forEach(unit -> {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", unit.getId());
            obj.put("pid", unit.getParentId());
            obj.put("text", unit.getName());
            if (!unit.isHasChildren()) obj.put("icon", "/assets/platform/images/icons/filetype/txt.gif");
            obj.put("children", unit.isHasChildren());
            tree.add(obj);
        });
        return tree;
    }

    @RequestMapping("/channelModelAdd")
    @RequiresPermissions("platform.cms.channel")
    public String model(HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        String mjson = req.getParameter("mjson");
        List<Cms_site> sites = cmsSiteService.query(Cnd.where("id", "=", Strings.sBlank(site_id)));
        Cms_site site = new Cms_site();
        if (sites != null && sites.size() > 0) {
            site = sites.get(0);
        }
        List<Cms_model> mlist = cmsModelService.query(Cnd.where("1", "=", 1).asc("location"));
        Map<String, List<String>> modelmap = new HashMap();
        // 站点模板基础目录
        String tplBase = Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path();
        tplBase = tplBase.replaceAll("\\\\|//", "/");
        File dir = new File(tplBase + "/" + site.getSite_css() + "/content/");
        File[] files = dir.listFiles();

        for (int i = 0; i < mlist.size(); i++) {
            Cms_model model = mlist.get(i);
            List<String> modellist = new ArrayList<String>();
            if (null != files) {
                for (File f : files) {
                    if (!f.getName().startsWith(model.getTpl_content_perfix()))
                        continue;
                    if (f.isFile() && f.getName().toLowerCase().indexOf("html") > 0) {
                        String listFPath = f.getPath().replaceAll("\\\\", "/");
                        modellist.add(listFPath.replace(tplBase, ""));
                    }
                }
            }
            modelmap.put(model.getId(), modellist);
        }
        req.setAttribute("mjson", mjson);
        req.setAttribute("mlist", mlist);
        req.setAttribute("site_id", site_id);
        req.setAttribute("modelmap", modelmap);
        return "pages/platform/cms/channel/model/add";
    }

    @RequestMapping("/sort")
    @RequiresPermissions("platform.cms.channel")
    public String sort(HttpServletRequest req) {
        String site_id = req.getParameter("site_id");
        List<Cms_channel> list = cmsChannelService.query(Cnd.where("site_id", "=", site_id).asc("location"));
        List<Cms_channel> firstChannel = new ArrayList<>();
        Map<String, List<Cms_channel>> secondChannel = new HashMap<>();
        list.forEach(cmsChannel -> {
            if (!Strings.isEmpty(cmsChannel.getParentId())) {
                List<Cms_channel> s = secondChannel.get(cmsChannel.getParentId());
                if (s == null) s = new ArrayList<>();
                s.add(cmsChannel);
                secondChannel.put(cmsChannel.getParentId(), s);
            } else {
                firstChannel.add(cmsChannel);
            }
        });
        req.setAttribute("firstChannels", firstChannel);
        req.setAttribute("secondChannels", secondChannel);
        req.setAttribute("site_id", site_id);
        return "pages/platform/cms/channel/sort";
    }

    @RequestMapping("/sortDo")
    @SJson
    @RequiresPermissions("platform.cms.channel.sort")
    public Object sortDo(@RequestParam("ids") String ids, HttpServletRequest req) {
        try {
            String ip = Lang.getIP(req);
            String resUrl = req.getRequestURI();
            String userId = StringUtil.getUid();
            Sys_user user = sysUserService.fetch(userId);
            String site_id = req.getParameter("site_id");
            String[] menuIds = StringUtils.split(ids, ",");
            int i = 0;
            cmsChannelService.dao().execute(Sqls.create("update cms_channel set location=0 where site_id='" + site_id + "'"));
            for (String s : menuIds) {
                if (!Strings.isBlank(s)) {
                    cmsChannelService.update(org.nutz.dao.Chain.make("location", i), Cnd.where("id", "=", s));
                    i++;
                }
            }
            cmsLogService.info("0", 2, site_id, "栏目排序", "", user, ip, resUrl);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 获取栏目名称汉语拼音首字母 为路径
     *
     * @return
     */
    @RequestMapping("/getPinyin")
    @SJson
    @RequiresPermissions("platform.cms.channel")
    public Object getPinyin(HttpServletRequest req) {
        try {
            String name = req.getParameter("name");
            if (name == null) return Result.success("");
            ;
            String path = PinyinUtil.cn2py(name);
            return Result.success(path);
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 检查栏目访问路径
     *
     * @return
     */
    @RequestMapping("/checkpath")
    @SJson
    @RequiresPermissions("platform.cms.channel")
    public Object checkpath(HttpServletRequest req) {
        try {
            String path = req.getParameter("path");
            String site_id = req.getParameter("site_id");
            String id = req.getParameter("id");
            StringBuilder sb = new StringBuilder();
            sb.append("select count(*) from cms_channel where site_id=@s and LOWER(path)=@p");
            if (!Strings.isEmpty(id)) {
                sb.append(" and id <> @id");
            }
            Sql sql = Sqls.create(sb.toString());
            if (!Strings.isEmpty(id)) {
                sql.params().set("id", id);
            }
            sql.params().set("s", site_id);
            sql.params().set("p", com.aebiz.app.web.commons.utils.StringUtil.null2String(path).toLowerCase());
            Boolean res = cmsChannelService.count(sql) > 0;
            if (res) {
                return Result.success("cms.channel.column.pathe.exist");
            }
            return Result.error("globals.result.error");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 获取栏目模块
     *
     * @param modellist 组装的结果集
     * @param site      站点
     * @param model     模型
     */
    private void getTplchannel(List<String> modellist, Cms_site site, Cms_model model) {
        String tplBase = Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path();
        log.debug(String.format("栏目的tplBase是：[%s]", tplBase));
        tplBase = tplBase.replaceAll("\\\\", "/").replaceAll("//", "/");
        String tpl_path = tplBase + "/" + site.getSite_css() + "/channel/";
        File dir = new File(tpl_path);
        File[] files = dir.listFiles();
        if (null != files) {
            for (File f : files) {
                if (!f.getName().startsWith(model.getTpl_channel_perfix()))
                    continue;
                if (f.isFile() && f.getName().toLowerCase().indexOf("html") > 0) {
                    String listFPath = f.getPath().replaceAll("\\\\", "/");
                    log.debug(String.format("modelList[文件名：%s，显示文件路径：%s]", f.getPath(), listFPath.replace(tplBase, "")));
                    modellist.add(listFPath.replace(tplBase, ""));
                }
            }
        }
    }

    /**
     * 组装页面传过来的内容模版集合
     *
     * @param mjson      内容模版字符串
     * @param cmsChannel 栏目信息
     */
    private List<Cms_channel_model> getChannelModes(String mjson, Cms_channel cmsChannel) {
        List<Cms_channel_model> models = new ArrayList<>();
        if (!"".equals(mjson)) {
            JSONArray array = new JSONArray(mjson);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Cms_channel_model m = new Cms_channel_model();
                m.setSite_id(cmsChannel.getSite_id());
                m.setChannel_id(cmsChannel.getId());
                m.setModel_id(com.aebiz.app.web.commons.utils.StringUtil.null2String(obj.get("id")));
                m.setTpl_content(com.aebiz.app.web.commons.utils.StringUtil.null2String(obj.get("tpl")));
                m.setOpBy(StringUtil.getUid());
                m.setOpAt((int) (System.currentTimeMillis() / 1000));
                models.add(m);
            }
        }
        return models;
    }
}
