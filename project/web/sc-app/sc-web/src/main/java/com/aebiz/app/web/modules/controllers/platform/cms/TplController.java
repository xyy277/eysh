package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.services.CmsLogService;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.FileUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.dao.entity.Record;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: jxx
 * @date: 2017/7/11 16:19
 * 模版管理
 */
@Controller
@RequestMapping("/platform/cms/tpl")
public class TplController {
    private static final Log log = Logs.get();
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsLogService cmsLogService;
    /**
     * 模板管理首页
     *
     * @param req
     */
    @RequestMapping("")
    @RequiresPermissions("cms.tpl")
    public String index(HttpServletRequest req) {
        String userId= StringUtil.getUid();
        String req_site_id=req.getParameter("site_id");
        Sys_user user=sysUserService.fetch(userId);
        List< Record > list =cmsSiteService.getSiteRole(user);
        String siteId="";
        String sitePath="";
        if (list.size() > 0) {
            Record site = list.get(0);
            siteId = site.getString("id");
            sitePath = "/WEB-INF/cmstemplate/" + site.get("site_path") + "/".replaceAll("\\\\", "/");
            if(!Strings.isBlank(req_site_id)){
                Cms_site cms_site=cmsSiteService.fetch(req_site_id);
                siteId=req_site_id;
                sitePath = "/WEB-INF/cmstemplate/" + cms_site.getSite_path() + "/".replaceAll("\\\\", "/");
            }
        }
        req.setAttribute("siteId", siteId);
        req.setAttribute("sitePath",sitePath);
        req.setAttribute("list",list);
        return "pages/platform/cms/tpl/index";

    }

    /**
     * 转到新建目录页面
     *
     * @param req
     */
    @RequestMapping("tplAdddir")
    @RequiresPermissions("cms.tpl")
    public String toaddDir(HttpServletRequest req) {
        String path=req.getParameter("path");
        req.setAttribute("path",path);
        return "pages/platform/cms/tpl/tplAdddir";
    }

    /**
     * 转到模板上传页面
     *
     * @param path
     * @param req
     */
    @RequestMapping("cplUpload")
    @RequiresPermissions("cms.tpl")
    public String upload(@PathVariable("path") String path, HttpServletRequest req) {
        req.setAttribute("path", path);
        return "pages/platform/cms/tpl/cplUpload";
    }

    /**
     * 上传模板文件 tpl
     *
     * @param tmpFile
     * @param req
     * @return
     */
    @RequestMapping("uploadOneSave")
    @SJson
    @RequiresPermissions("cms.tpl.uploadOneSave")
    public Object uploadOneSave(@RequestParam("Filedata") MultipartFile tmpFile, HttpServletRequest req) {
        String path1= req.getParameter("path");
        if (tmpFile == null) {
            return Result.error("globals.upload.error");
        }
        String filename = tmpFile.getOriginalFilename();
        String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
        String filePath = Globals.APP_ROOT+"/" + "upload/temp/" + filename;
        try {
            if (path1.startsWith("/"))
                path1 = path1.substring(1).replace("/","\\");
                String path = Globals.APP_ROOT+"\\" + path1  +"\\"+ filename;
                File ff = new File(path);
            if (ff.exists()) {
                return Result.error("globals.upload.error");//文件已存在，跳过
            }
            if (!"html".equals(fileExtName.toLowerCase())) {
                return Result.error("globals.upload.error");//文件格式不对
            }
            //转存文件
            tmpFile.transferTo(new File(filePath));
            //读取文件
            File uploadFile = new File(filePath);
            Files.move(uploadFile, ff);//移动文件
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("globals.upload.error");
        }

        return Result.success("globals.upload.success");

    }

    @RequestMapping("getPath")
    @RequiresPermissions("cms.tpl")
    public String getPath(@PathVariable("site_id") String site_id, HttpServletRequest req) {
        Cms_site cms_site = cmsSiteService.fetch(site_id);
        return Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + cms_site.getSite_path() + "/".replaceAll("\\\\", "/");
    }

    /**
     * 新建目录
     *
     * @param req
     * @return
     */
    @SJson
    @RequestMapping("addDir")
    @RequiresPermissions("cms.tpl.addDir")
    public Object addDir(HttpServletRequest req) {
        String path1=req.getParameter("path");
        String name=req.getParameter("name");
        if (path1.startsWith("/"))
            path1 = path1.substring(1);
        String path =Globals.APP_ROOT +"/" + path1 + "/" + name;
        try {
            Files.createDirIfNoExists(path);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return Result.error("system.error");
        }
        return Result.success("system.success");
    }

    /**
     * 转到模板编辑页面
     *
     * @param req
     */

    @RequestMapping("tplEdit")
    @RequiresPermissions("cms.tpl")
    public String editfile(HttpServletRequest req) {
        String site_id=req.getParameter("site_id");
        String path1=req.getParameter("path");
        String name=req.getParameter("name");
        name = com.aebiz.app.web.commons.utils.StringUtil.null2String(name);
        String file;
        String path;
        if (name.indexOf("/") > -1) {
            if(name.startsWith("/"))
                name=name.substring(1);
            file =  Globals.APP_ROOT +"/"+ name;
            path = name.substring(0, name.lastIndexOf("/"));
            name = name.substring(name.lastIndexOf("/") + 1);

        } else {
            if (path1.startsWith("/"))
                path1 = path1.substring(1);
            file =  Globals.APP_ROOT +"/"+ path1 + "/" + name;
            path = path1;
        }
        req.setAttribute("path", path);
        req.setAttribute("name", name);
        req.setAttribute("site_id", site_id);
        String content = Files.read(file);
        req.setAttribute("content", content);
        return "pages/platform/cms/tpl/tplEdit";
    }

    /**
     * 修改保存
     * @param req
     * @return
     */
    @SJson
    @RequestMapping("savefile")
    @RequiresPermissions("cms.tpl.savefile")
    public Object savefile(HttpServletRequest req) {
        String userId=StringUtil.getUid();
        Sys_user user=sysUserService.fetch(userId);
        String site_id=req.getParameter("site_id");
        String path1=req.getParameter("path");
        String name=req.getParameter("name");
        String content=req.getParameter("content");
        String ip= Lang.getIP(req);
        String resUrl=req.getRequestURI();
        name = com.aebiz.app.web.commons.utils.StringUtil.null2String(name);
        if (path1.startsWith("/"))
            path1 = path1.substring(1);
        String path =  Globals.APP_ROOT +"/" + path1 + "/" + name;
        try {
            Files.write(path, content);
            cmsLogService.info("0", 1, site_id,"修改模板",  "/" + path1 + "/" + name, user, ip,resUrl);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return Result.error("system.error");
        }

        return Result.success("system.success");
    }

    /**
     * 跳转到重命名页面
     *
     * @param req
     */
    @RequestMapping("torename")
    @RequiresPermissions("cms.tpl")
    public String torename(HttpServletRequest req) {
        String name=req.getParameter("name");
        String path=req.getParameter("path");
        req.setAttribute("name", name);
        req.setAttribute("path", path);
        return "pages/platform/cms/tpl/tplRename";
    }

    /**
     * 重命名文件或文件夹
     *
     * @param req
     * @return
     */
    @SJson
    @RequestMapping("rename")
    @RequiresPermissions("cms.tpl.rename")
    public Object rename(HttpServletRequest req) {
        String path1=req.getParameter("path");
        String oldname=req.getParameter("oldname");
        String name=req.getParameter("name");
        if (path1.startsWith("/"))
            path1 = path1.substring(1);
        String oldpath =  Globals.APP_ROOT +"/" + path1 + "/" + oldname;
        try {
            Files.rename(new File(oldpath), name);
            return Result.success("system.success");
        } catch (Exception e) {
            log.debug(e.getMessage());
            return Result.error("system.error");
        }
    }

    /**
     * 删除一个文件或文件夹
     *
     * @param req
     * @return
     */
    @SJson
    @RequestMapping("delname")
    @RequiresPermissions("cms.tpl.delname")
    public Object delname(HttpServletRequest req) {
        String name=req.getParameter("name");
        String path1=req.getParameter("path");
        if (path1.startsWith("/"))
            path1 = path1.substring(1);
        String path =  Globals.APP_ROOT +"/" + path1 + "/" + name;
        try {
            File f = new File(path);
            if (Files.isFile(f)) {
                Files.deleteFile(f);
                return Result.success("system.success");
            }
            if (Files.isDirectory(f)) {
                Files.deleteDir(f);
                return Result.success("system.success");

            }
            return Result.error("system.error");
        } catch (Exception e) {
            log.debug(e.getMessage());
            return Result.error("system.error");
        }
    }

    /**
     * 批量删除文件或文件夹
     *
     * @param req
     * @return
     */
    @SJson
    @RequestMapping("delnames")
    @RequiresPermissions("cms.tpl.delnames")
    public Object delnames(HttpServletRequest req) {
        String path1=req.getParameter("path");
        String names1=req.getParameter("names");
        String path;
        if (path1.startsWith("/"))
            path1 = path1.substring(1);
        try {
            String[] names = com.aebiz.app.web.commons.utils.StringUtil.null2String(names1).split(",");
            for (int i = 0; i < names.length; i++) {

                if (names[i] == null || "".equals(names[i]))
                    continue;
                path =  Globals.APP_ROOT +"/" + path1 + "/" + names[i];

                File f = new File(path);
                if (Files.isFile(f)) {
                    Files.deleteFile(f);
                }
                if (Files.isDirectory(f)) {
                    Files.deleteDir(f);
                }
            }
            return Result.success("system.success");
        } catch (Exception e) {
            log.debug(e.getMessage());
            return Result.error("system.error");
        }
    }

    /**
     * 获得文件夹和文件列表
     *
     * @return
     */
    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("cms.tpl")
    public String list(HttpServletRequest req) {
        String path1=req.getParameter("path");
        JSONArray array = new JSONArray();
        String root = Globals.APP_ROOT ;
        String path = root + Strings.sBlank(path1);
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (null != files) {
            Calendar cal = Calendar.getInstance();
            for (File f : files) {
                cal.setTimeInMillis(f.lastModified());
                if (f.isDirectory()) {
                    JSONObject obj = new JSONObject();
                    obj.put("name", f.getName());
                    obj.put("size", "5 KB");
                    obj.put("time",DateUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
                    array.put(obj);
                } else {
                    JSONObject obj = new JSONObject();
                    obj.put("name", f.getName());
                    obj.put("size", com.aebiz.app.web.commons.utils.StringUtil.getFileSizeKB(f.length(), 2) + " KB");
                    obj.put("time",DateUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
                    array.put(obj);
                }
            }
        }
        return array.toString();
    }

    /**
     * 获得目录树
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("cms.tpl")
    public Object filetree(HttpServletRequest req) {
        String pid=req.getParameter("pid");
        List<Map<String, Object>> tree = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        String site_id=req.getParameter("site_id");
        pid = com.aebiz.app.web.commons.utils.StringUtil.null2String(pid);
        Cms_site cms_site = cmsSiteService.fetch(site_id);
        if(cms_site!=null){
            String path = Globals.APP_ROOT +"/WEB-INF/cmstemplate/" + cms_site.getSite_path() + "/";
            if ("".equals(pid)) {
                obj.put("id", path.replace(Globals.APP_ROOT, "").replaceAll("\\\\", "/"));
                obj.put("pId", "0");
                obj.put("text", "模板根目录");
                obj.put("url", "javascript:openPath(\"" + path.replaceAll("\\\\", "/").replace(Globals.APP_ROOT, "") + "\")");
                obj.put("target", "_self");
                obj.put("children", false);
                tree.add(obj);
            } else {
                path = Globals.APP_ROOT + pid;
            }
            File dir = new File(path);
            FileUtil.getFileTree(Globals.APP_ROOT.replaceAll("\\\\", "/"), dir, obj, tree);
        }
        return tree;
    }

}
