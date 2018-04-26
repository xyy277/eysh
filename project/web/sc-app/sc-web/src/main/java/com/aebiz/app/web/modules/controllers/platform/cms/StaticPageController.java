package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.models.Cms_topic;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.app.cms.modules.services.CmsTopicService;
import com.aebiz.app.cms.modules.services.StaticPageService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.LoginMemberUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.MoneyUtil;
import com.aebiz.commons.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyang
 *         生成静态页面
 */
@Controller
@RequestMapping("/platform/cms/staticpage")
public class StaticPageController {
    private static final Log log = Logs.get();
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsTopicService cmsTopicService;
    @Autowired
    private StaticPageService staticPageService;

    @RequestMapping("/index")
    @RequiresPermissions("cms.staticpage.index")
    public String index(HttpServletRequest request) {
        // 获取站点列表
        Sys_user user = sysUserService.fetch(StringUtil.getUid());

        List< Record > siteList =cmsSiteService.getSiteRole(user);

        request.setAttribute("siteList", siteList);
        return "pages/platform/cms/staticpage/index";
    }

    @RequestMapping("/geIndex")
    @RequiresPermissions("cms.staticpage.index.ge")
    @SJson("full")
    public Result geIndex(@RequestParam("siteId") String siteId) throws IOException {
        Cms_site site = cmsSiteService.fetch(siteId);
        boolean re = staticPageService.geIndex(site, addUtils());
        if (re) {
            return Result.success("globals.result.success");
        }
        return Result.error("globals.result.error");
    }

    @RequestMapping("/delIndex")
    @RequiresPermissions("cms.staticpage.index.del")
    @SJson("full")
    public Result delIndex(@RequestParam("siteId") String siteId) throws IOException {
        Cms_site site = cmsSiteService.fetch(siteId);
        boolean re = staticPageService.delIndex(site);
        if (re) {
            return Result.success("globals.result.success");
        }
        return Result.error("globals.result.error");
    }

    @RequestMapping("/channel")
    @RequiresPermissions("cms.staticpage.channel")
    public String channel(HttpServletRequest request) {
        // 获取站点列表
        Sys_user user = sysUserService.fetch(StringUtil.getUid());

        List< Record > siteList =cmsSiteService.getSiteRole(user);

        request.setAttribute("siteList", siteList);
        return "pages/platform/cms/staticpage/channel";
    }

    @RequestMapping("/getChannel")
    @RequiresPermissions("cms.staticpage.channel")
    @SJson("full")
    public Result getChannel(@RequestParam("siteId") String siteId) {
        List<Cms_channel> list = cmsChannelService.query(Cnd.where("site_id", "=", siteId).asc("id"));
        return Result.success("globals.result.success", list);
    }

    @RequestMapping("/geChannel")
    @RequiresPermissions("cms.staticpage.channel.ge")
    @SJson("full")
    public Result geChannel(@RequestParam("siteId") String siteId,
                            @RequestParam("channelId") String channelId,
                            @RequestParam("containChild") boolean containChild,
                            @RequestParam("tag") boolean isStatic) throws IOException {
        int count = staticPageService.channelPages(siteId, channelId, containChild, isStatic, addUtils());
        return Result.success("生成了" + count + " 个HTML页面");
    }

    @RequestMapping("/content")
    @RequiresPermissions("cms.staticpage.content")
    public String content(HttpServletRequest request) {
        // 获取站点列表
        Sys_user user = sysUserService.fetch(StringUtil.getUid());

        List< Record > siteList =cmsSiteService.getSiteRole(user);

        request.setAttribute("siteList", siteList);
        return "pages/platform/cms/staticpage/content";
    }

    @RequestMapping("/geContent")
    @SJson
    @RequiresPermissions("cms.staticpage.content.ge")
    public Result geContent(@RequestParam("siteId") String siteId,
                            @RequestParam("channelId") String channelId,
                            @RequestParam("start") String start,
                            @RequestParam("tag") boolean isStatic) {
        Date s = null;
        long time = DateUtil.getTime(start + " 00:00:00");
        try {
            s = Times.parse("yyyy-MM-dd", start);
        } catch (ParseException e) {
            log.error(start);
        }
        try {
            int count = staticPageService.contentPages(siteId, channelId, s, isStatic, addUtils());
            return Result.success("生成了个" + count + "HTML页面");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("生成页面失败");
        }

    }

    @RequestMapping("/topic")
    @RequiresPermissions("cms.staticpage.topic")
    public String topic(HttpServletRequest request) {
        // 获取站点列表
        Sys_user user = sysUserService.fetch(StringUtil.getUid());

        List< Record > siteList =cmsSiteService.getSiteRole(user);

        request.setAttribute("siteList", siteList);

        return "pages/platform/cms/staticpage/topic";
    }

    @RequestMapping("/geTopic")
    @SJson
    @RequiresPermissions("cms.staticpage.topic.ge")
    public Result geTopic(@RequestParam("siteId") String siteId,
                          @RequestParam("topicId") String topicId) {
        try {
            int count = staticPageService.topicPages(siteId, topicId, addUtils());
            return Result.success("生成了个" + count + "HTML页面");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("生成页面失败");
        }

    }

    @RequestMapping("/delTopic")
    @SJson
    @RequiresPermissions("cms.staticpage.topic.del")
    public Result delTopic(@RequestParam("siteId") String siteId,
                           @RequestParam("topicId") String topicId) {
        Cnd cnd = Cnd.NEW();
        if (!"".equals(StringUtils.trimToEmpty(siteId))) {
            cnd.and("site_id", "=", siteId);
        }
        if (!"".equals(StringUtils.trimToEmpty(topicId))) {
            cnd.and("id", "=", topicId);
        }
        List<Cms_topic> topics = cmsTopicService.query(cnd);
        try {
            staticPageService.delTopics(topics);
            return Result.success("删除成功");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("删除页面失败");
        }

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

    @RequestMapping("/updateStaticpage")
    @SJson
    public Object updateStaticpage() {
        try {
            staticPageService.updateIndex("", false);
            return Result.success();
        } catch (Exception e) {
            return Result.error("系统出错");
        }
    }
}
