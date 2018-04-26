package com.aebiz.app.web.commons.quartz.job;

import com.aebiz.app.cms.modules.models.*;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysTaskService;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.sys.modules.services.impl.SysTaskServiceImpl;
import com.aebiz.app.sys.modules.services.impl.SysUserServiceImpl;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.LoginMemberUtil;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.MoneyUtil;
import com.aebiz.commons.utils.SpringUtil;
import com.aebiz.app.cms.modules.services.*;
import com.aebiz.app.cms.modules.services.impl.*;
import com.aebiz.app.web.commons.utils.StringUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定时页面采集 静态化生成
 * Created by 277 on 2018/1/17 0017.
 */
public class ContentCollectorJob implements Job {

    private static final Log log = Logs.get();
    private Map<String, List<String>> collectMap = new HashMap<String, List<String>>();

    private SysTaskService sysTaskService = SpringUtil.getBean("sysTaskServiceImpl", SysTaskServiceImpl.class);

    private CmsAcquisitionService cmsAcquisitionService = SpringUtil.getBean("cmsAcquisitionServiceImpl", CmsAcquisitionServiceImpl.class);

    private StaticPageService staticPageService = SpringUtil.getBean("staticPageServiceImpl", StaticPageServiceImpl.class);

    private SysUserService sysUserService = SpringUtil.getBean("sysUserServiceImpl", SysUserServiceImpl.class);

    private CmsAcqLogService cmsAcqLogService = SpringUtil.getBean("cmsAcqLogServiceImpl", CmsAcqLogServiceImpl.class);

    private CmsSiteService cmsSiteService = SpringUtil.getBean("cmsSiteServiceImpl", CmsSiteServiceImpl.class);

    private CmsChannelService cmsChannelService = SpringUtil.getBean("cmsChannelServiceImpl", CmsChannelServiceImpl.class);

    private CmsTopicService cmsTopicService = SpringUtil.getBean("cmsTopicServiceImpl", CmsTopicServiceImpl.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug(context.getJobDetail() + "采集任务开始执行");
        String taskId = context.getJobDetail().getKey().getName();
        Date now=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        now = calendar.getTime(); //获取前一天日期
        try {
            List<Cms_acquisition> acquisitions = cmsAcquisitionService.query(Cnd.NEW());
            for (Cms_acquisition acquisition : acquisitions) {
                log.debug("-------------------------------------开始采集-----------------------------------------");
                log.debug("=================================开始采集:"+acquisition.getAcq_name()+"=================================");
                // status = 1 表示开始采集 , 0 停止 , 2 暂停  , 3 暂停后启动
                acquisition.setStatus(1);
                acquisition.setStart_time(DateUtil.getDateTime());
                cmsAcquisitionService.updateIgnoreNull(acquisition);
                try {
                    collect(acquisition);
                }catch (Exception e){
                    e.printStackTrace();
                    log.debug(e.fillInStackTrace());
                }
            }
            log.debug("-------------------------------------开始生成静态页面-----------------------------------------");
            Sys_user user = sysUserService.fetch(Cnd.where("loginname","=","superadmin"));
            List<Record> siteList = cmsSiteService.getSiteRole(user);
            for (Record record : siteList) {
                String siteId = record.getString("id");
                Cms_site site = cmsSiteService.fetch(siteId);

                try {
                    log.debug("首页静态话开始");
                    boolean re = staticPageService.geIndex(site, addUtils());
                    if (re) log.debug("首页静态化生成成功");
                    else log.debug("首页静态化生成失败");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug("首页生成页面失败");
                }

                try {
                    log.debug("栏目页静态化开始");      //默认所有栏目
                    int count = staticPageService.channelPages(siteId, "", true, false, addUtils());
                    log.debug("栏目页生成了" + count + " 个HTML页面");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug("栏目页生成页面失败");
                }

                List<Cms_channel> channels = cmsChannelService.query(Cnd.where("site_id", "=", siteId).asc("id"));
                try {
                    log.debug("内容页静态化开始");
                    int counts=0;
                    for(Cms_channel channel : channels){
                        int count = staticPageService.contentPages(siteId, channel.getId(), now , false, addUtils());
                        counts+=count;
                        log.debug(channel.getName()+"栏目生成了" + count + "个内容HTML页面");
                    }
                    log.debug("内容页一共生成了" + counts + "个HTML页面");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug("内容页生成页面失败");
                    int count = staticPageService.contentPages(siteId, "", now , false, addUtils());
                    log.debug("内容页一共生成了" + count + "个HTML页面");
                }

                try {
                    log.debug("专题页静态化开始");
                    List<Cms_topic> topics =cmsTopicService.query(Cnd.NEW());
                    int counts=0;
                    if(topics.size()!=0){
                        for(Cms_topic topic : topics){
                            int count = staticPageService.topicPages(siteId, topic.getId(), addUtils());
                            counts+=count;
                            log.debug( topic.getName() + "的专题页生成了" + count + "个HTML页面");
                        }
                    }
                    log.debug("专题页一共生成了" + counts + "个HTML页面");
                    log.debug("专题页静态化结束");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug("专题页生成页面失败");
                }
                log.debug("-------------------------------------静态化页面生成结束-----------------------------------------");
            }
            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行成功").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        } catch (Exception e) {
            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行失败").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        } finally {
            log.debug("采集与静态化生成执行完毕");
        }

    }

    /**
     * 采集
     * @param acq 采集数据
     */
    private void collect(Cms_acquisition acq)throws Exception {
        BufferedReader br = null;
        try {
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
                    if (urlStr.indexOf("/") != 0)
                        urlStr = acq.getContent_prefix() + "/" + urlStr;
                    else
                        urlStr = acq.getContent_prefix() + urlStr;
                }
                Cms_acq_log cal = cmsAcqLogService.fetch(Cnd.where("url", "=", urlStr.trim()));
                if (cal == null) {
                    cal = new Cms_acq_log();
                    getCollectMsg(urlStr,acq);
                    cal.setUrl(urlStr.trim());
                    if (cmsAcqLogService.insert(cal) != null) {
                        collectMap.get(acq.getId()).add(0, DateUtil.getDateTime() + " 第 " + i + " 条  URL:" + urlStr + " 文章采集成功!");
                    } else {
                        collectMap.get(acq.getId()).add(0, DateUtil.getDateTime() + " 第 " + i + " 条  URL:" + urlStr + " 文章采集失败!");
                    }
                } else {
                    collectMap.get(acq.getId()).add(0, DateUtil.getDateTime() + " 第 " + i + " 条  URL:" + urlStr + " 已采集，跳过!");
                }
                collectMap.put(acq.getId(), collectList);
                i++;
            }
            if(br!=null)br.close(); // 读取完成后关闭读取器
            // status = 1 表示开始采集 , 0 停止 , 2 暂停  , 3 暂停后启动
            acq.setStatus(0);
            acq.setEnd_time(DateUtil.getDateTime());
            cmsAcquisitionService.updateIgnoreNull(acq);
        }catch (Exception e){
            log.debug(e.fillInStackTrace());
            log.debug("=======================采集发生"+e.getMessage()+"异常=======================");
        }finally {
            if(br!=null)br.close();
            log.debug("=========================采集完成=========================");
        }
    }

    /**
     * 采集信息
     * @param urlStr
     * @param acq
     */
    public void getCollectMsg(String urlStr,Cms_acquisition acq) {
        try {
            String nowUrl="";
            nowUrl = urlStr.substring(0, urlStr.lastIndexOf("/"));
            URL url = new URL(urlStr);
            InputStreamReader isr = new InputStreamReader(url.openStream(), acq.getPage_encoding());
            BufferedReader br = new BufferedReader(isr);
            String strRead = "";
            Sys_user user = sysUserService.fetch(Cnd.where("loginname","=","superadmin"));
            Cms_content content = new Cms_content();
            Cms_content_txt content_txt = new Cms_content_txt();
            //获取组装后的数据
            this.getContent(content, acq, user);
            while ((strRead = br.readLine()) != null) {
                strRead = strRead.trim();
                if (strRead.indexOf(acq.getTitle_start()) != -1) {//标题
                    String titleStr = "";
                    if (strRead.equals(acq.getTitle_start())) {
                        titleStr = checkNoteHtml(br, acq.getTitle_end(),acq,nowUrl).toString();
                    } else {
                        titleStr = strRead.replace(acq.getTitle_start(), "").replace(acq.getTitle_end(), "");
                    }
                    content.setTitle(titleStr);
                } else if (strRead.indexOf(acq.getContent_start()) != -1) {//内容
                    Map<String, StringBuffer> map = checkContentNoteHtml(br, acq.getContent_end(),acq,nowUrl);
                    content_txt.setTxt(map.get("content").toString());
                    String titlePicPath = map.get("img").toString(); // getFirstPic(brforpic,acq.getContent_end());
                    if (!"".equals(titlePicPath)) content.setHas_title_img(titlePicPath);
                } else if (!"".equals(StringUtil.null2String(acq.getReleasetime_start())) && strRead.indexOf(acq.getReleasetime_start()) != -1) {//发布时间
                    String dateStr = "";
                    if (strRead.equals(acq.getReleasetime_start())) {
                        dateStr = checkNoteHtml(br, acq.getReleasetime_end().toString(),acq,nowUrl).toString().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                                "<[^>]*>", "").replaceAll("[(/>)<]", "");
                    } else {
                        int prepos = strRead.indexOf(acq.getReleasetime_start());
                        int sufpos = strRead.indexOf(acq.getReleasetime_end());
                        dateStr = strRead.substring(prepos, sufpos);

                        dateStr = dateStr.replace(acq.getReleasetime_start(), "").replace(acq.getReleasetime_end(), "").replace("&#160;&#160;&#160;&#160;&#160;", "");
                    }
                    //content_ext.setAdd_time(dateStr);
                    content.setPub_time(dateStr.trim());

                } else if (!"".equals(StringUtil.null2String(acq.getAuthor_start())) && strRead.indexOf(acq.getAuthor_start()) != -1) {//作者
                    String authorStr = "";
                    if (strRead.equals(acq.getAuthor_start())) {
                        authorStr = checkNoteHtml(br, acq.getAuthor_end().toString(),acq,nowUrl).toString().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                                "<[^>]*>", "").replaceAll("[(/>)<]", "");
                    } else {
                        int prepos = strRead.indexOf(acq.getAuthor_start());
                        int sufpos = strRead.indexOf(acq.getAuthor_end());
                        authorStr = authorStr.substring(prepos, sufpos);
                        authorStr = authorStr.replace(acq.getAuthor_start(), "").replace(acq.getAuthor_end(), "").replace("&#160;&#160;&#160;&#160;&#160;", "");
                    }
                    content.setAuthor(authorStr.trim());
                } else if (!"".equals(StringUtil.null2String(acq.getOrigin_start())) && strRead.indexOf(acq.getOrigin_start()) != -1) {//来源
                    String originStr = "";
                    if (strRead.equals(acq.getOrigin_start())) {
                        originStr = checkNoteHtml(br, acq.getOrigin_end().toString(),acq,nowUrl).toString().replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                                "<[^>]*>", "").replaceAll("[(/>)<]", "");
                    } else {
                        originStr = strRead.replace(acq.getOrigin_start(), "").replace(acq.getOrigin_end(), "");
                    }
                    content.setOrigin(originStr.trim());
                }
            }
            cmsAcquisitionService.saveCollectMsg(content, content_txt);

        } catch (Exception e) {
            log.debug(e);
            e.printStackTrace();
        }
    }

    private StringBuffer checkNoteHtml(BufferedReader br, String endHtml ,Cms_acquisition acq ,String nowUrl) {
        StringBuffer sb = new StringBuffer();
        try {
            String strRead = "";
            endHtml = endHtml.trim();
            while ((strRead = br.readLine()) != null) {
                strRead = strRead.trim();
                if (strRead.indexOf(endHtml) == -1) {
                    if (strRead.indexOf("src=\"/") != -1) {
                        strRead = strRead.replace("src=\"/", "src=\"" + acq.getImg_prefix() + "/");
                    } else if (strRead.indexOf("src=\"./") != -1) {
                        strRead = strRead.replace("src=\"./", "src=\"" + nowUrl + "/");
                    } else if (strRead.indexOf("SRC=\"") != -1) {
                        strRead = strRead.replace("SRC=\"/", "src=\"" + acq.getImg_prefix() + "/");
                    } else if (strRead.toLowerCase().indexOf("href=\"/") != -1) {
                        strRead = strRead.replace("href=\"/", "href=\"" + acq.getContent_prefix() + "/");
                    } else if (strRead.toLowerCase().indexOf("HREF=\"/") != -1) {
                        strRead = strRead.replace("HREF=\"/", "href=\"" + acq.getContent_prefix() + "/");
                    }
                    sb.append(strRead);
                } else {
                    return sb;
                }
            }
        } catch (Exception e) {
            log.debug(e);
        }
        return sb;
    }

    private Map<String, StringBuffer> checkContentNoteHtml(BufferedReader br, String endHtml,Cms_acquisition acq ,String nowUrl) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbImg = new StringBuffer();
        Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
        String strPicPath = "";
        try {
            String strRead = "";
            String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
            endHtml = endHtml.trim();
            int first = 0;
            while ((strRead = br.readLine()) != null) {
                strRead = strRead.trim();
                if (strRead.indexOf(endHtml) == -1) {
                    if (strRead.indexOf("src=\"/") != -1) {
                        strRead = strRead.replace("src=\"/", "src=\"" + acq.getImg_prefix() + "/");
                    } else if (strRead.indexOf("src=\"./") != -1) {
                        strRead = strRead.replace("src=\"./", "src=\"" + nowUrl + "/");
                    } else if (strRead.indexOf("SRC=\"") != -1) {
                        strRead = strRead.replace("SRC=\"/", "src=\"" + acq.getImg_prefix() + "/");
                    } else if (strRead.toLowerCase().indexOf("href=\"/") != -1) {
                        strRead = strRead.replace("href=\"/", "href=\"" + acq.getContent_prefix() + "/");
                    } else if (strRead.toLowerCase().indexOf("HREF=\"/") != -1) {
                        strRead = strRead.replace("HREF=\"/", "href=\"" + acq.getContent_prefix() + "/");
                    }
                    if (first < 1 && strRead.indexOf("src=") > 0) {
                        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                        while (matcher.find()) {
                            strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                            sbImg.append(strPicPath);
                            first = 1;
                            break;
                        }
                    }
                    sb.append(strRead);
                } else {
                    map.put("content", sb);
                    map.put("img", sbImg);
                    return map;
                }
            }
        } catch (Exception e) {
            log.debug(e);
        }
        map.put("content", sb);
        map.put("img", sbImg);
        return map;
    }

    private String getFirstPic(BufferedReader br, String endHtml,Cms_acquisition acq ,String nowUrl) {
        String strPicPath = "";
        try {
            String strRead = "";
            endHtml = endHtml.trim();
            String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
            while ((strRead = br.readLine()) != null) {
                strRead = strRead.trim();
                if (strRead.indexOf(endHtml) == -1) {
                    if (strRead.indexOf("src=\"/") != -1) {
                        strRead = strRead.replace("src=\"/", "src=\"" + acq.getImg_prefix() + "/");
                        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                        while (matcher.find()) {
                            strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                            break;
                        }
                        break;
                    } else if (strRead.indexOf("src=\"./") != -1) {
                        strRead = strRead.replace("src=\"./", "src=\"" + nowUrl + "/");
                        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                        while (matcher.find()) {
                            strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                            break;
                        }
                        break;
                    } else if (strRead.indexOf("SRC=\"") != -1) {
                        strRead = strRead.replace("SRC=\"/", "src=\"" + acq.getImg_prefix() + "/");
                        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(strRead);
                        while (matcher.find()) {
                            strPicPath = matcher.group().substring(0, matcher.group().length() - 1);
                            break;
                        }
                        break;
                    }
                } else {
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
     *
     * @param content 文章Model
     * @param acq     采集Model
     * @param user    用户信息
     */
    private void getContent(Cms_content content, Cms_acquisition acq, Sys_user user) {
        /** 插入Cms_content 记录**/
        try {
            content.setSite_id(StringUtil.null2String(acq.getSite_id()));
            content.setChannel_id(StringUtil.null2String(acq.getChannel_id()));
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
            String tplPath = Globals.APP_ROOT + "/WEB-INF/cmstemplate/kebo/default/content/新闻内容_文章展示.html";  //"D:/WorkSpace/kebo/project/web/sc-app/sc-web/target/aebizcms/WEB-INF/cmstemplate/kebo/default/content/新闻内容_文章展示.html";//
            tplPath = tplPath.replaceAll("\\\\|//", "/");
            tplPath = tplPath.replaceAll("//", "/");
            content.setTpl_content(tplPath);
        } catch (Exception e) {
            log.debug(e);
        }
    }


    /**
     * 获取url列表
     *
     * @param br
     * @param htmlStart
     * @param htmlEnd
     * @param urlStart
     * @param urlEnd
     * @return
     * @throws IOException
     */
    private List<String> getUrlList(BufferedReader br, String htmlStart, String htmlEnd, String urlStart, String urlEnd) throws IOException {
        // 如果 BufferedReader 读到的内容不为空
        String strRead = ""; // 新增一个空字符串strRead来装载 BufferedReader 读取到的内容
        List<String> htmlList = new ArrayList<String>();
        while ((strRead = br.readLine()) != null) {
            if (strRead.trim().indexOf(htmlStart) != -1) {
                while ((strRead = br.readLine()) != null) {
                    if (!strRead.trim().equals(htmlEnd)) {
                        htmlList.add(strRead.trim());
                    } else {
                        break;
                    }
                }
            }
        }
        List<String> urlList = new ArrayList<String>();
        for (String html : htmlList) {
            if (html.indexOf(urlStart) != -1) {
                if (html.indexOf(urlEnd) != -1) {
                    String[] newlist = html.split("</li><li>");
                    for (String onenew : newlist) {
                        onenew = onenew.substring(onenew.indexOf(urlStart), onenew.lastIndexOf(urlEnd));
                        urlList.add(onenew.replace(urlStart, "").replace(urlEnd, ""));
                    }
                } else {
                    html = html.substring(html.indexOf(urlStart), html.lastIndexOf(urlEnd));
                    urlList.add(html.replace(urlStart, "").replace(urlEnd, ""));
                }

            }
        }
        return urlList;
    }

    private Map<String, Object> addUtils() {
        Map map = new HashMap<String, Object>();
        map.put("app_name", Globals.APP_NAME);
        map.put("app_short_name", Globals.APP_SHORT_NAME);
        map.put("date", SpringUtil.getBean(DateUtil.class));
        map.put("string", SpringUtil.getBean(com.aebiz.commons.utils.StringUtil.class));
        map.put("money", SpringUtil.getBean(MoneyUtil.class));
        map.put("area", SpringUtil.getBean(MoneyUtil.class));
        map.put("member", SpringUtil.getBean(LoginMemberUtil.class));
        map.put("config", Globals.MyConfig);
        map.put("site_index_qq", Globals.SITE_INDEX_QQ);
        map.put("staticBase", Globals.STATIC_URL);
        map.put("siteDomain", Globals.SITE_URL);
        map.put("site_name", Globals.SITE_NAME);
        map.put("advisory_tel", Globals.ADVISORY_TEL);
        return map;
    }
}
