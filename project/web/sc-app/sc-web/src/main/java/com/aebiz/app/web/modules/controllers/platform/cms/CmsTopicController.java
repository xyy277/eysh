package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.cms.modules.models.Cms_topic;
import com.aebiz.app.cms.modules.services.CmsTopicService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题管理
 */
@Controller
@RequestMapping("/platform/cms/topic")
public class CmsTopicController {
    private static final Log log = Logs.get();
    @Autowired
	private CmsTopicService cmsTopicService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @RequestMapping("")
    @RequiresPermissions("platform.cms.topic")
	public String index(HttpServletRequest req) {
        String userId=StringUtil.getUid();
        String site_id=req.getParameter("site_id");
        Sys_user user=sysUserService.fetch(userId);
        List< Record > records =cmsSiteService.getSiteRole(user);
        String site_id1="";
        if(records!=null && records.size()>0){
            site_id1 = records.get(0).getString("id");
            if(!Strings.isBlank(site_id)){
                site_id1=site_id;
            }
        }
        req.setAttribute("site_id", Strings.sBlank(site_id1));
        req.setAttribute("list",records);
		return "pages/platform/cms/topic/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.topic")
    public Object data(DataTable dataTable,HttpServletRequest req) {
        String siteId= req.getParameter("id");
        Cnd cnd = Cnd.NEW();
        if (!Strings.isBlank(siteId)) cnd.and("site_id", "=", siteId);
        cnd.asc("priority");
    	return cmsTopicService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.topic")
    public String add(HttpServletRequest req) {
        String ids=Strings.sBlank(req.getParameter("ids"));
        String site_id=req.getParameter("site_id");
        Cms_site site = cmsSiteService.fetch(site_id);
        List<String> modellist = new ArrayList<String>();
        //获取专题模版
        this.getTplcontent(modellist,site);
        req.setAttribute("modellist", modellist);
        req.setAttribute("site_id", site_id);
        req.setAttribute("ids", ids);
        return "pages/platform/cms/topic/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_topic")
    @RequiresPermissions("platform.cms.topic.add")
    public Object addDo(Cms_topic cmsTopic, HttpServletRequest req) {
		try {
			cmsTopicService.insert(cmsTopic);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.topic")
    public String edit(@PathVariable String id,HttpServletRequest req) {
        String site_id=req.getParameter("site_id");
        Cms_site site = cmsSiteService.fetch(site_id);
        List<String> modellist = new ArrayList<String>();
        //获取专题模版
        this.getTplcontent(modellist,site);
        req.setAttribute("site_id", site_id);
        req.setAttribute("modellist", modellist);
		req.setAttribute("obj", cmsTopicService.fetch(id));
		return "pages/platform/cms/topic/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_topic")
    @RequiresPermissions("platform.cms.topic.edit")
    public Object editDo(Cms_topic cmsTopic, HttpServletRequest req) {
		try {
            cmsTopic.setOpBy(StringUtil.getUid());
			cmsTopic.setOpAt((int) (System.currentTimeMillis() / 1000));
			cmsTopicService.updateIgnoreNull(cmsTopic);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_topic")
    @RequiresPermissions("platform.cms.topic.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
		    //判断专题下面是否管理内容，关联不允许删除
            cmsTopicService.delete(id);
            req.setAttribute("id", id);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("platform.cms.topic")
    public Object tree(@PathVariable(required = false) String pid,HttpServletRequest req) {
        String site_id=req.getParameter("site_id");
        Cnd cnd=Cnd.where("1", "=", "1");
        if(!Strings.isEmpty(pid)){
            cnd.and("id", "=", Strings.sBlank(pid));
        }
        if(!Strings.isEmpty(site_id)){
            cnd.and("site_id","=",site_id);
        }
        List<Cms_topic> list = cmsTopicService.query(cnd.asc("priority"));
        List<Map<String, Object>> tree = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        if (Strings.isBlank(pid)) {
            obj.put("id", "");
            obj.put("text", "专题列表");
            obj.put("children", false);
            tree.add(obj);
        }
        for (Cms_topic unit : list) {
            obj = new HashMap<>();
            obj.put("id", unit.getId());
            obj.put("text", unit.getName());
            obj.put("icon","/assets/platform/images/icons/filetype/txt.gif");
            obj.put("children", false);
            tree.add(obj);
        }
        return tree;
    }

    /**
     * 关联栏目
     * @param req
     * @param ids
     * @return
     */
    @RequestMapping("/chooseChannel")
    @RequiresPermissions("platform.cms.topic")
    public Object editCmsMenu(HttpServletRequest req,@RequestParam(required = false) String ids) {
        String[] idss = StringUtils.split(ids, ",");
        String site_id=req.getParameter("site_id");
        //查询所有的一级目录
        Sql sql = Sqls.create("select parentId,id,site_id,name,hasChildren from cms_channel a where a.site_id=@site_id " );
        sql.setParam("site_id",site_id);
        List<Record> cms_channels =  cmsChannelService.list(sql);
        List<NutMap> menus = new ArrayList<>();
        boolean selected=false;
        if(cms_channels!=null && cms_channels.size()>0) {
            for (Record record : cms_channels) {
                String id = record.getString("id");
                if("".equals(Strings.sNull(record.get("parentId")))){
                    NutMap map = new NutMap();
                    map.put("id",id);
                    map.put("text", record.getString("name"));
                    map.put("data", "");
                    map.put("parent","#");
                    map.put("state", NutMap.NEW().addv("selected", false));
                    menus.add(map);
                }else{
                    NutMap map = new NutMap();
                    map.put("id", id);
                    map.put("text", record.getString("name"));
                    map.put("data", "");
                    for(int i=0;i<idss.length;i++){
                        if(idss[i].equals(id)){
                            selected=true;
                        }
                    }
                    map.put("state", NutMap.NEW().addv("selected", selected));
                    map.put("parent", "".equals(Strings.sNull(record.get("parentId"))) ? site_id : record.get("parentId"));
                    menus.add(map);
                }
            }
        }
        req.setAttribute("menus", Json.toJson(menus));
        return "pages/platform/cms/topic/chooseChannel";
    }
    @RequestMapping("/sort")
    @RequiresPermissions("platform.cms.topic")
    public String sort(HttpServletRequest req) {
        String site_id=req.getParameter("site_id");
        List<Cms_topic> list = cmsTopicService.query(Cnd.where("delFlag", "=", false).and("site_id","=",site_id).asc("priority"));
        req.setAttribute("firstChannels", list);
        req.setAttribute("site_id",site_id);
        return "pages/platform/cms/topic/sort";
    }

    @RequestMapping("/sortDo")
    @SJson
    @RequiresPermissions("platform.cms.topic.sort")
    public Object sortDo(@RequestParam("ids") String ids, HttpServletRequest req) {
        try {
            String[] sortIds = StringUtils.split(ids, ",");
            int i = 0;
            String site_id=req.getParameter("site_id");
            cmsTopicService.dao().execute(Sqls.create("update cms_topic set priority=0 where site_id='"+site_id+"'"));
            for (String s : sortIds) {
                if (!Strings.isBlank(s)) {
                    cmsTopicService.update(org.nutz.dao.Chain.make("priority", i), Cnd.where("id", "=", s));
                    i++;
                }
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    /**
     * 获取专题模块
     * @param modellist 组装的结果集
     * @param site 站点
     */
    private void getTplcontent(List<String> modellist,Cms_site site){
        String tplBase = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path()).replaceAll("\\\\|//", "/");
        String tpl_path = tplBase + "/" + site.getSite_css() + "/topic/";
        File dir = new File(tpl_path);
        File[] files = dir.listFiles();
        // String path=Globals.APP_ROOT +"/WEB-INF/cmstemplate/" + site.getSite_path();
        if (null != files) {
            for (File f : files) {
                if (f.isFile() && f.getName().toLowerCase().indexOf("html") > 0) {
                    // String res=path.replace("/","\\");
                    // String res2=res.replace("\\\\","\\");
                    modellist.add(f.getPath().replaceAll("\\\\", "\\").replace(tplBase, ""));
                }
            }
        }
    }
}
