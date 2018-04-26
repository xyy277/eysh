package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.*;
import com.aebiz.app.cms.modules.models.em.AcquisitionStatusEnum;
import com.aebiz.app.cms.modules.services.*;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/platform/cms/acquisition")
public class CmsAcquisitionController {
    private static final Log log = Logs.get();
    private Map<String,List<String>> collectMap = new HashMap<String,List<String>>();
    private Map<String,CollectThread> threadMap = new HashMap<String,CollectThread>();

    @Autowired
    private CmsAcquisitionService cmsAcquisitionService;
    @Autowired
    private CmsModelService cmsModelService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsAcqLogService cmsAcqLogService;
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("")
    @RequiresPermissions("platform.cms.acquisition")
    public String index() {
        return "pages/platform/cms/acquisition/index";
    }

    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.acquisition")
    public Object data(DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        return cmsAcquisitionService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.acquisition")
    public String add(HttpServletRequest req) {
        List<Cms_model> cmsModelList = cmsModelService.query(Cnd.orderBy().asc("location"));
        List<Cms_site> cmsSiteList = cmsSiteService.query(Cnd.orderBy().asc("location"));
        req.setAttribute("cmsModelList", cmsModelList);
        req.setAttribute("cmsSiteList", cmsSiteList);
        return "pages/platform/cms/acquisition/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition.add")
    public Object addDo(Cms_acquisition cmsAcquisition, HttpServletRequest req) {
        try {
            cmsAcquisition.setStatus(AcquisitionStatusEnum.STOP.getKey());
            cmsAcquisitionService.insert(cmsAcquisition);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.acquisition")
    public String edit(@PathVariable String id,HttpServletRequest req) {
        List<Cms_model> cmsModelList = cmsModelService.query(Cnd.orderBy().asc("location"));
        List<Cms_site> cmsSiteList = cmsSiteService.query(Cnd.orderBy().asc("location"));
        req.setAttribute("cmsModelList", cmsModelList);
        req.setAttribute("cmsSiteList", cmsSiteList);
        req.setAttribute("obj", cmsAcquisitionService.fetch(id));
        return "pages/platform/cms/acquisition/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition.edit")
    public Object editDo(Cms_acquisition cmsAcquisition, HttpServletRequest req) {
        try {
            cmsAcquisition.setOpBy(StringUtil.getUid());
            cmsAcquisition.setOpAt((int) (System.currentTimeMillis() / 1000));
            cmsAcquisitionService.updateIgnoreNull(cmsAcquisition);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
        try {
            if(ids!=null&&ids.length>0){
                cmsAcquisitionService.delete(ids);
                req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
            }else{
                cmsAcquisitionService.delete(id);
                req.setAttribute("id", id);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.cms.acquisition")
    public String detail(@PathVariable String id, HttpServletRequest req) {
        try{
            List<String> list = collectMap.get(id);
            req.setAttribute("list",list);
            req.setAttribute("id",id);
            req.setAttribute("size",list == null ? 0 : list.size());
            if(threadMap!=null && threadMap.size()>0){
                req.setAttribute("flag",threadMap.get(id).getCms()==null ? "2" : "1");
            }
        }catch(Exception e){
            e.printStackTrace();
            log.info(e);
        }
        return "pages/platform/cms/acquisition/detail";
    }
    /**
     * 查看采集记录
     * @param id
     * @return
     */
    @RequestMapping(value = {"/showCollect/{id}"})
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition")
    public Object showCollect(@PathVariable String id,HttpServletRequest req){
        try{

            List<String> list = collectMap.get(id);
            req.setAttribute("list",list);
            req.setAttribute("id",id);
            req.setAttribute("size",list == null ? 0 : list.size());
            req.setAttribute("flag",threadMap.get(id).getCms()==null ? "2" : "1");
            return Result.success();
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    @RequestMapping(value = {"/getChannelSelected"})
    @SJson
    @RequiresPermissions("platform.cms.acquisition")
    public Object getChannelSelected(HttpServletRequest req){
        String site_id=req.getParameter("site_id");
        return cmsChannelService.query(Cnd.where("site_id", "=", site_id).asc("location"));
    }
    /**
     *  开始采集
     */
    @RequestMapping(value = {"/doStart"})
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition")
    public Object doStart(HttpServletRequest req){
        String id=req.getParameter("id");
        try {
            Cms_acquisition cms_acquisition=cmsAcquisitionService.fetch(id);
            // status = 1 表示开始采集 , 0 停止 , 2 暂停  , 3 暂停后启动
            cms_acquisition.setStatus(AcquisitionStatusEnum.START.getKey());
            cms_acquisition.setStart_time(DateUtil.getDateTime());
            cmsAcquisitionService.updateIgnoreNull(cms_acquisition);
            CollectThread collectThread = new CollectThread(cms_acquisition);
            threadMap.put(id, collectThread);
            threadMap.get(id).start();
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    /**
     *  暂停采集
     */
    @RequestMapping(value = {"/pause"})
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition")
    public Object pause(HttpServletRequest req){
        try{
            String id=req.getParameter("id");
            Cms_acquisition ca=cmsAcquisitionService.fetch(id);
            if(ca.getStatus()==AcquisitionStatusEnum.TIMEOUT.getKey()){
                ca.setStatus(AcquisitionStatusEnum.CARRYON.getKey());
                ca.setStart_time(DateUtil.getDateTime());
            }else{
                ca.setStatus(AcquisitionStatusEnum.TIMEOUT.getKey());
                ca.setStart_time(DateUtil.getDateTime());
            }
            cmsAcquisitionService.updateIgnoreNull(ca);
            threadMap.get(id).suspend();
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    /**
     *  停止采集
     */
    @RequestMapping(value = {"/doStop"})
    @SJson
    @SLog(description = "Cms_acquisition")
    @RequiresPermissions("platform.cms.acquisition")
    public Object doStop(HttpServletRequest req){
        try{
            String id=req.getParameter("id");
            Cms_acquisition ca=cmsAcquisitionService.fetch(id);
            ca.setStatus(AcquisitionStatusEnum.STOP.getKey());
            ca.setEnd_time(DateUtil.getDateTime());
            cmsAcquisitionService.updateIgnoreNull(ca);
            if(threadMap!=null && threadMap.size()>0){
                threadMap.get(id).stop();
                threadMap.put(id,null);
            }
            if(collectMap!=null && collectMap.size()>0){
                collectMap.remove(id);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    /**
     * 采集线程
     */
    private class CollectThread implements Runnable {
        //线程对象引用
        private Thread myThread;
        private volatile boolean threadSuspended = true;
        private boolean threadFlag = true;
        private Cms_acquisition acq;
        private Thread thisThread = null;
        private String userId=StringUtil.getUid();

        public Cms_acquisition getCms() {
            return this.acq;
        }

        public CollectThread(Cms_acquisition acq) {
            this.acq = acq;
        }


        public void start() {
            myThread = new Thread(this, "myThread");
            myThread.start();
        }

        @Override
        public synchronized void run() {
            thisThread = Thread.currentThread();
            try {
                if (myThread == thisThread) {
                    synchronized (this) {
                        BufferedReader br = null;
                        List<String> addrList = new ArrayList<String>();
                        addrList.add(acq.getPlan_list());
                        if (acq.getDynamic_addr() != null) {
                            int start = acq.getDynamic_start();
                            int end = acq.getDynamic_end();
                            String addr = acq.getDynamic_addr();
                            for (int i = start, count = end; i <= count; i++) {
                                addrList.add(addr.replace("[page]", String.valueOf(i)));
                            }
                        }
                        List<String> urlList = new ArrayList<String>();
                        for (String addr : addrList) {
                            URL url = new URL(addr);
                            InputStreamReader isr = new InputStreamReader(url.openStream(), acq.getPage_encoding());
                            br = new BufferedReader(isr);
                            urlList.addAll(getUrlList(br, acq.getLinkset_start(), acq.getLinkset_end(), acq.getLink_start(), acq.getLink_end()));

                        }
                        Collections.reverse(urlList);
                        //循环获取url集合
                        int i = 1;
                        List<String> collectList = new ArrayList<String>();
                        collectMap.put(acq.getId(), collectList);
                        for (String urlStr : urlList) {
                            if (urlStr.indexOf("/") == 0 || urlStr.indexOf("http") == -1 || urlStr.indexOf("HTTP") == -1) {
                                if(urlStr.indexOf("/") != 0)
                                    urlStr = acq.getContent_prefix() + "/" + urlStr;
                                else
                                    urlStr = acq.getContent_prefix() + urlStr;
                            }
                            if (threadFlag) {
                                Cms_acq_log cal = cmsAcqLogService.fetch(Cnd.where("url", "=", urlStr.trim()));
                                if (cal == null) {
                                    cal = new Cms_acq_log();
                                    getCollectMsg(urlStr);
                                    cal.setUrl(urlStr.trim());
                                    if (cmsAcqLogService.insert(cal)!=null) {
                                        collectMap.get(acq.getId()).add(0, DateUtil.getDateTime() + " 第 " + i + " 条  URL:" + urlStr + " 文章采集成功!");
                                    } else {
                                        collectMap.get(acq.getId()).add(0, DateUtil.getDateTime()+ " 第 " + i + " 条  URL:" + urlStr + " 文章采集失败!");
                                    }
                                } else {
                                    collectMap.get(acq.getId()).add(0, DateUtil.getDateTime()+ " 第 " + i + " 条  URL:" + urlStr + " 已采集，跳过!");
                                }
                                collectMap.put(acq.getId(), collectList);
                                while (true) {
                                    if (threadSuspended) {
                                        break;
                                    } else {
                                        Thread.sleep(1000 * 10);
                                    }
                                }
                            } else {
                                return;
                            }
                            i++;
                            Thread.sleep(5000);
                        }
                        br.close(); // 读取完成后关闭读取器
                        // status = 1 表示开始采集 , 0 停止 , 2 暂停  , 3 暂停后启动
                        acq.setStatus(AcquisitionStatusEnum.STOP.getKey());
                        acq.setEnd_time(DateUtil.getDateTime());
                        cmsAcquisitionService.updateIgnoreNull(acq);
                        acq = null;
                    }
                }
            } catch (InterruptedException e) {
                log.debug(e);
                return;
            } catch (IOException e1) {
                log.debug(e1);
            }
        }
        /**
         * 停止线程:
         */
        public void stop(){
            threadFlag = false;
        }


        /**
         * 挂起线程：
         */
        public boolean suspend(){
            threadSuspended=!threadSuspended;
            return threadSuspended;
        }

        /**
         *  采集url文章页信息
         * @param urlStr
         */
        String nowUrl = "";
        public void getCollectMsg(String urlStr){
            try {
                nowUrl = urlStr.substring(0,urlStr.lastIndexOf("/"));
                URL url = new URL(urlStr);
                InputStreamReader isr = new InputStreamReader(url.openStream(), acq.getPage_encoding());
                BufferedReader br = new BufferedReader(isr);
                String strRead = "";
                Sys_user user = sysUserService.fetch(userId);
                Cms_content content = new Cms_content();
                Cms_content_txt content_txt = new Cms_content_txt();
                //获取组装后的数据
                this.getContent(content,acq,user);
                while ((strRead = br.readLine()) != null) {
                    strRead = strRead.trim();
                    if(strRead.indexOf(acq.getTitle_start()) != -1){//标题
                        String titleStr = "";
                        if(strRead.equals(acq.getTitle_start())){
                            titleStr = checkNoteHtml(br,acq.getTitle_end()).toString();
                        }else{
                            titleStr = strRead.replace(acq.getTitle_start(),"").replace(acq.getTitle_end(),"");
                        }
                        content.setTitle(titleStr);
                    }else if(strRead.indexOf(acq.getContent_start()) != -1){//内容
                        Map<String, StringBuffer> map = checkContentNoteHtml(br,acq.getContent_end());
                        content_txt.setTxt(map.get("content").toString());
                        String titlePicPath = map.get("img").toString(); // getFirstPic(brforpic,acq.getContent_end());
                        if(!"".equals(titlePicPath)) content.setHas_title_img(titlePicPath);
                    }else if(!"".equals(com.aebiz.app.web.commons.utils.StringUtil.null2String(acq.getReleasetime_start())) && strRead.indexOf(acq.getReleasetime_start()) != -1){//发布时间
                        String dateStr = "";
                        if(strRead.equals(acq.getReleasetime_start())){
                            dateStr = checkNoteHtml(br,acq.getReleasetime_end().toString()).toString().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                                    "<[^>]*>", "").replaceAll("[(/>)<]", "");
                        }else{
                            int prepos = strRead.indexOf(acq.getReleasetime_start());
                            int sufpos = strRead.indexOf(acq.getReleasetime_end());
                            dateStr = strRead.substring(prepos,sufpos);

                            dateStr = dateStr.replace(acq.getReleasetime_start(),"").replace(acq.getReleasetime_end(),"").replace("&#160;&#160;&#160;&#160;&#160;","");
                        }
                        //content_ext.setAdd_time(dateStr);
                        content.setPub_time(dateStr.trim());

                    }else if(!"".equals(com.aebiz.app.web.commons.utils.StringUtil.null2String(acq.getAuthor_start())) && strRead.indexOf(acq.getAuthor_start()) != -1){//作者
                        String authorStr = "";
                        if(strRead.equals(acq.getAuthor_start())){
                            authorStr = checkNoteHtml(br,acq.getAuthor_end().toString()).toString().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                                    "<[^>]*>", "").replaceAll("[(/>)<]", "");
                        }else{
                            int prepos = strRead.indexOf(acq.getAuthor_start());
                            int sufpos = strRead.indexOf(acq.getAuthor_end());
                            authorStr = authorStr.substring(prepos,sufpos);
                            authorStr = authorStr.replace(acq.getAuthor_start(),"").replace(acq.getAuthor_end(),"").replace("&#160;&#160;&#160;&#160;&#160;","");
                        }
                        content.setAuthor(authorStr.trim());
                    }else if(!"".equals(com.aebiz.app.web.commons.utils.StringUtil.null2String(acq.getOrigin_start())) && strRead.indexOf(acq.getOrigin_start()) != -1){//来源
                        String originStr = "";
                        if(strRead.equals(acq.getOrigin_start())){
                            originStr = checkNoteHtml(br,acq.getOrigin_end().toString()).toString().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                                    "<[^>]*>", "").replaceAll("[(/>)<]", "");
                        }else{
                            originStr = strRead.replace(acq.getOrigin_start(),"").replace(acq.getOrigin_end(),"");
                        }
                        content.setOrigin(originStr.trim());
                    }
                }
                cmsAcquisitionService.saveCollectMsg(content,content_txt);

            } catch (Exception e) {
                log.debug(e);
                e.printStackTrace();
            }
        }

        private StringBuffer checkNoteHtml(BufferedReader br,String endHtml){
            StringBuffer sb = new StringBuffer();
            try {
                String strRead = "";
                endHtml = endHtml.trim();
                while((strRead = br.readLine()) != null){
                    strRead = strRead.trim();
                    if(strRead.indexOf(endHtml) == -1){
                        if(strRead.indexOf("src=\"/") != -1){
                            strRead = strRead.replace("src=\"/","src=\""+acq.getImg_prefix()+"/");
                        }else if(strRead.indexOf("src=\"./") != -1){
                            strRead = strRead.replace("src=\"./","src=\""+nowUrl+"/");
                        }else if(strRead.indexOf("SRC=\"") != -1){
                            strRead = strRead.replace("SRC=\"/","src=\""+acq.getImg_prefix()+"/");
                        }else if(strRead.toLowerCase().indexOf("href=\"/") != -1){
                            strRead = strRead.replace("href=\"/","href=\""+acq.getContent_prefix()+"/");
                        }else if(strRead.toLowerCase().indexOf("HREF=\"/") != -1){
                            strRead = strRead.replace("HREF=\"/","href=\""+acq.getContent_prefix()+"/");
                        }
                        sb.append(strRead);
                    }else{
                        return sb;
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            return sb;
        }

        private Map<String, StringBuffer> checkContentNoteHtml(BufferedReader br,String endHtml){
            StringBuffer sb = new StringBuffer();
            StringBuffer sbImg = new StringBuffer();
            Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
            String strPicPath = "";
            try {
                String strRead = "";
                String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
                endHtml = endHtml.trim();
                int first = 0;
                while((strRead = br.readLine()) != null){
                    strRead = strRead.trim();
                    if(strRead.indexOf(endHtml) == -1){
                        if(strRead.indexOf("src=\"/") != -1){
                            strRead = strRead.replace("src=\"/","src=\""+acq.getImg_prefix()+"/");
                        }else if(strRead.indexOf("src=\"./") != -1){
                            strRead = strRead.replace("src=\"./","src=\""+nowUrl+"/");
                        }else if(strRead.indexOf("SRC=\"") != -1){
                            strRead = strRead.replace("SRC=\"/","src=\""+acq.getImg_prefix()+"/");
                        }else if(strRead.toLowerCase().indexOf("href=\"/") != -1){
                            strRead = strRead.replace("href=\"/","href=\""+acq.getContent_prefix()+"/");
                        }else if(strRead.toLowerCase().indexOf("HREF=\"/") != -1){
                            strRead = strRead.replace("HREF=\"/","href=\""+acq.getContent_prefix()+"/");
                        }
                        if(first<1 && strRead.indexOf("src=")>0){
                            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                            while (matcher.find()) {
                                strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                                sbImg.append(strPicPath);
                                first = 1;
                                break;
                            }
                        }
                        sb.append(strRead);
                    }else{
                        map.put("content",sb);
                        map.put("img",sbImg);
                        return map;
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            map.put("content",sb);
            map.put("img",sbImg);
            return map;
        }

        private String getFirstPic(BufferedReader br,String endHtml){
            String strPicPath = "";
            try {
                String strRead = "";
                endHtml = endHtml.trim();
                String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
                while((strRead = br.readLine()) != null){
                    strRead = strRead.trim();
                    if(strRead.indexOf(endHtml) == -1){
                        if(strRead.indexOf("src=\"/") != -1){
                            strRead = strRead.replace("src=\"/","src=\""+acq.getImg_prefix()+"/");
                            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                            while (matcher.find()) {
                                strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                                break;
                            }
                            break;
                        }else if(strRead.indexOf("src=\"./") != -1){
                            strRead = strRead.replace("src=\"./","src=\""+nowUrl+"/");
                            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                            while (matcher.find()) {
                                strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                                break;
                            }
                            break;
                        }else if(strRead.indexOf("SRC=\"") != -1){
                            strRead = strRead.replace("SRC=\"/","src=\""+acq.getImg_prefix()+"/");
                            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                            while (matcher.find()) {
                                strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                                break;
                            }
                            break;
                        }
                    }else{
                        return strPicPath;
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
            return strPicPath;
        }

        /**
         * 组装文章信息
         * @param content 文章Model
         * @param acq 采集Model
         * @param user 用户信息
         */
        private void getContent(Cms_content content,Cms_acquisition acq,Sys_user user){
            /** 插入Cms_content 记录**/
            try {
                content.setSite_id(com.aebiz.app.web.commons.utils.StringUtil.null2String(acq.getSite_id()));
                content.setChannel_id(com.aebiz.app.web.commons.utils.StringUtil.null2String(acq.getChannel_id()));
                content.setModel_id(acq.getModel_id());
                content.setUser_id(user.getId());
                content.setUnit_id(user.getUnitid());
                content.setSort_time(DateUtil.getDateTime());
                content.setStatus(3);// 0表示草稿，3表示已终审 ContentStatusEnum.DRAFTS.getKey()
                //content.setHas_title_img("0");
                /** 插入 Cms_content_ext 记录 根据 Cms_content.content_id **/
                content.setPub_time(DateUtil.getDateTime());
                content.setIs_bold("N");
                content.setAuthor(user.getUsername());
                content.setLink_("");
                content.setAdd_time(DateUtil.getDateTime());
                content.setIs_static(1); //CmsIsStaticEnum.RELEASED.getKey() java.lang.NoClassDefFoundError: Could not initialize class CmsIsStaticEnum
                content.setIs_index(0);
                String app_root = Globals.APP_ROOT;
                String tplPath = app_root + "/WEB-INF/cmstemplate/kebo/default/content/新闻内容_文章展示.html";  //"D:/WorkSpace/kebo/project/web/sc-app/sc-web/target/aebizcms/WEB-INF/cmstemplate/kebo/default/content/新闻内容_文章展示.html";//
                tplPath = tplPath.replaceAll("\\\\|//", "/");
                tplPath = tplPath.replaceAll("//", "/");
                content.setTpl_content(tplPath);
            }
            catch (Exception e) {
                log.debug(e);
            }
        }
    }

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
     *  获取url列表
     * @param br
     * @param htmlStart
     * @param htmlEnd
     * @param urlStart
     * @param urlEnd
     * @return
     * @throws IOException
     */
    private List<String> getUrlList(BufferedReader br,String htmlStart,String htmlEnd,String urlStart,String urlEnd) throws  IOException{
        // 如果 BufferedReader 读到的内容不为空
        String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容
        List<String> htmlList = new ArrayList<String>();
        while ((strRead = br.readLine()) != null) {
            if(strRead.trim().indexOf(htmlStart)!=-1){
                while((strRead = br.readLine()) != null){
                    if(!strRead.trim().equals(htmlEnd)){
                        htmlList.add(strRead.trim());
                    }else{
                        break;
                    }
                }
            }
        }
        List<String> urlList = new ArrayList<String>();
        for(String html : htmlList) {
            if(html.indexOf(urlStart)!=-1){
                if(html.indexOf(urlEnd)!=-1)
                {
                    String[] newlist = html.split("</li><li>");
                    for(String onenew : newlist)
                    {
                        onenew = onenew.substring(onenew.indexOf(urlStart),onenew.lastIndexOf(urlEnd));
                        urlList.add(onenew.replace(urlStart, "").replace(urlEnd, ""));
                    }
                }
                else{
                    html = html.substring(html.indexOf(urlStart),html.lastIndexOf(urlEnd));
                    urlList.add(html.replace(urlStart, "").replace(urlEnd, ""));
                }

            }
        }
        return urlList;
    }
}
