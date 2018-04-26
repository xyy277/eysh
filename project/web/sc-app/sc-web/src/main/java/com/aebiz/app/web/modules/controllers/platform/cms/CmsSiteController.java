package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.services.CmsLogService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;

@Controller
@RequestMapping("/platform/cms/site")
public class CmsSiteController {
    private static final Log log = Logs.get();
    @Autowired
	private CmsSiteService cmsSiteService;
    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("")
    @RequiresPermissions("cms.site.settings")
	public String index() {
		return "pages/platform/cms/site/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("cms.site.settings")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return cmsSiteService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("cms.site.settings")
    public String add() {
    	return "pages/platform/cms/site/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_site")
    @RequiresPermissions("cms.site.settings.add")
    public Object addDo(Cms_site cmsSite, HttpServletRequest req) {
		try {
            String ip= Lang.getIP(req);
            String resUrl=req.getRequestURI();
            String userId=StringUtil.getUid();
            Sys_user user=sysUserService.fetch(userId);
            doDir(cmsSite,0);
            // site path要唯一
            Cms_site site = cmsSiteService.fetch(Cnd.where("site_path", "=", cmsSite.getSite_path()));
            if (site != null) return Result.error("站点路径已存在");
            cmsSiteService.insert(cmsSite);
            cmsLogService.info("0", 1, cmsSite.getId(),"添加站点", "site_id:" + cmsSite.getId() + ";site_name:" + cmsSite.getSite_name(), user, ip,resUrl);
            return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("cms.site.settings")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", cmsSiteService.fetch(id));
		return "pages/platform/cms/site/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_site")
    @RequiresPermissions("cms.site.settings.edit")
    public Object editDo(Cms_site cmsSite, HttpServletRequest req) {
		try {
		    String ip= Lang.getIP(req);
            String resUrl=req.getRequestURI();
            String userId=StringUtil.getUid();
            Sys_user user=sysUserService.fetch(userId);
            cmsSite.setOpBy(StringUtil.getUid());
			cmsSite.setOpAt((int) (System.currentTimeMillis() / 1000));
            cmsSiteService.updateIgnoreNull(cmsSite);
            doDir(cmsSite,0);
            cmsLogService.info("0", 1, cmsSite.getId(),"修改站点", "site_id:" + cmsSite.getId() + ";site_name:" + cmsSite.getSite_name(), user,ip,resUrl);
            return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_site")
    @RequiresPermissions("cms.site.settings.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
            String ip= Lang.getIP(req);
            String resUrl=req.getRequestURI();
            String userId=StringUtil.getUid();
            Sys_user user=sysUserService.fetch(userId);
            Cms_site cms_site=cmsSiteService.fetch(id);
            doDir(cms_site,1);
            cmsSiteService.delete(id);
            req.setAttribute("id", id);
            cmsLogService.info("0", 1, cms_site.getId(),"删除站点", "site_id:" + cms_site.getId() + ";site_name:" + cms_site.getSite_name(), user, ip,resUrl);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("cms.site.settings")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", cmsSiteService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/cms/site/detail";
    }
    /**
     * 创建或删除站点文件夹
     * @param cms_site
     * @param tag
     * @return
     */
    private boolean doDir(Cms_site cms_site,int tag){
        ArrayList<String> paths=new ArrayList<String>();
        paths.add(Globals.APP_ROOT +"/cms/"+cms_site.getSite_path()+"/res/"+cms_site.getSite_css()+"/css/");
        paths.add(Globals.APP_ROOT +"/cms/"+cms_site.getSite_path()+"/res/"+cms_site.getSite_css()+"/js/");
        paths.add(Globals.APP_ROOT +"/cms/"+cms_site.getSite_path()+"/res/"+cms_site.getSite_css()+"/images/");
        paths.add(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/");
        paths.add(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/include/");
        paths.add(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/index/");
        paths.add(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/channel/");
        paths.add(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/content/");
        paths.add(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/topic/");
        try{

            if(tag==0){
                for(int i=0;i<paths.size();i++){


                    Files.createDirIfNoExists(paths.get(i));

                }
                Files.createFileIfNoExists(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/"+cms_site.getSite_css()+"/index/首页.html");
            }
            if(tag==1){
                File f=null;
                f=new File(Globals.APP_ROOT +"/cms/"+cms_site.getSite_path()+"/");
                Files.deleteDir(f);
                f=new File(Globals.APP_ROOT +"/WEB-INF/cmstemplate/"+cms_site.getSite_path()+"/");
                Files.deleteDir(f);
            }


        }catch(Exception e){
            log.debug(e);
            return false;
        }
        return true;
    }
}
