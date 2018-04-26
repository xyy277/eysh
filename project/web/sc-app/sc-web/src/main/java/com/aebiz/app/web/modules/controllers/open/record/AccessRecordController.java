package com.aebiz.app.web.modules.controllers.open.record;

import com.aebiz.app.cms.modules.models.Cms_access_record;
import com.aebiz.app.cms.modules.services.CmsAccessRecordService;
import com.aebiz.app.cms.modules.services.CmsContentService;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.redis.RedisService;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.CookieUtil;
import com.aebiz.commons.utils.UserAgentUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 访问统计控制器
 * Created by zyang on 2017/8/2.
 */
@Controller
@RequestMapping("/open/record")
public class AccessRecordController {
    // 日志
    private static final Log log = Logs.get();
    @Autowired
    private CmsAccessRecordService cmsAccessRecordService;
    @Autowired
    private CmsContentService cmsContentService;
    @Autowired
    private RedisService redisService;
    @RequestMapping(value = {"/_img"}, method = RequestMethod.GET)
    public void count(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        log.info("request:" + request);
        String cookieKey = "_v";
        long now = System.currentTimeMillis();

        // 访问到页面的时间
        String timeStr = request.getParameter("time");
        long time = now;
        if (StringUtils.isNotEmpty(timeStr)) {
            try {
                time = Long.parseLong(timeStr);
            } catch (NumberFormatException e) {
                log.error(e);
            }
        }
        // 站点标识
        String siteId = StringUtils.trimToEmpty(request.getParameter("account"));
        // 可能存在的用户id
        String uid = StringUtils.trimToEmpty(request.getParameter("uid"));
        // 可能存在的文章id，用于统计文章阅读量，虽然也可以根据path 判断，但是可能会不够准确
        String artId = StringUtils.trimToEmpty(request.getParameter("artId"));
        // 访问路径
        String path = request.getParameter("path");
        // 访问域名
        String domain = request.getParameter("domain");
        // 访问页面的标题
        String title = request.getParameter("title");
        // Referrer
        String referrer = request.getParameter("referrer");
        // 屏幕尺寸
        String screen = request.getParameter("screen");
        // 屏幕颜色深度
        String colorDepth = request.getParameter("colorDepth");
        // 语言
        String lang = request.getParameter("lang");
        // IP
        String ip = StringUtil.getClientIPAddress(request);
        // 客户端系统
        String os = UserAgentUtils.getOperatingSystem(request).getName();
        // 浏览器版本
        String browser = UserAgentUtils.getBrowser(request).getName();
        // 系统类型,0 PC端 1 移动端
        int ostype = UserAgentUtils.isComputer(request) ? 0 : 1;

        // 根据不同站点应有不同的cookieKey值,主要用于防止出现域名没改，换了网站的低概率情况
        if ("".equals(siteId)) {
            // 如果没有设置站点的唯一标识码就使用域名
            // 这个要不要无所谓了主要为了保持key长度一致
            cookieKey += "_" + StringUtil.getMD5(domain);
        } else {
            // 否则使用siteId
            cookieKey += "_" + StringUtil.getMD5(siteId);
        }
        // 用户唯一标识
        String vid = "";
        // 存在uid则用uid作为标识
        if (!"".equals(uid)) {
            vid = StringUtil.getMD5(uid);
        } else {
            // 通过cookie查看是否访问过
            vid = StringUtils.trimToEmpty(CookieUtil.getCookie(request, cookieKey));
        }

        if ("".equals(vid)) {
            // 没有根据这些参数生成
            vid = StringUtil.getMD5(screen + colorDepth + lang + ip);
        }

        // 设置Cookie，三年过期
        CookieUtil.setCookie(response, cookieKey, vid, 3600 * 24 * 365 * 3);

        long _t = 0;
        try {
            _t = (long) request.getSession().getAttribute(StringUtil.getMD5(referrer));
        } catch (Exception e) {
            _t = 0;
        }

        if (_t <= 0) {
            _t = time;
        }

        long duration = time - _t;// ms
        // 数据记录进数据库
        Cms_access_record record = new Cms_access_record();
        record.setVid(vid);
        record.setVip(ip);
        record.setAtime(time);
        record.setBrowser(browser);
        record.setOs(os);
        record.setReferrer(referrer);
        record.setPath(path);
        record.setTitle(title);
        record.setSiteId(siteId);
        record.setOstype(ostype);
        record.setDuration(0L);
        record.setDomain_(domain);
        record.setArtId(artId);
        cmsAccessRecordService.insert(record);
        // 设置path为referrer的记录的duration
        cmsAccessRecordService.update(Chain.make("duration", duration), Cnd.where("path", "=", referrer).and("atime", "=", _t));
        // 设置时间
        request.getSession().setAttribute(StringUtil.getMD5(path), time);

        response.setContentType("image/jpeg");
        ImageIO.write(image, "gif", response.getOutputStream());
    }

    /**
     * 这个方法获取的是有限制的阅读量，每个用户每天计算一次
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/countArtReadNum")
    @SJson(jsonp = true)
    public Result countArtReadNum(HttpServletRequest request) {
        String artId = request.getParameter("artId");
        int num = 0;
        String msg;
        // 没传文章id获取该页面的浏览量
        if (StringUtils.isEmpty(artId)) {
            String path = request.getParameter("path");
            num = cmsAccessRecordService.countPageFirstView(path);
            msg = "view";
        } else {
            num = cmsAccessRecordService.countArtReadNum(artId);
            msg = "read";
        }
        return Result.success(msg, num);
    }

    /**
     * 这个方法获取的是文章的浏览量
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/countArtViews")
    @SJson(jsonp = true)
    public Result countArtViews(HttpServletRequest request) {
        String artId = request.getParameter("artId");
        int num = 0;
        String msg;
        // 没传文章id获取该页面的浏览量
        if (StringUtils.isEmpty(artId)) {
            String path = request.getParameter("path");
            num = cmsAccessRecordService.countPageViews(path);
            msg = "view";
        } else {
            num = cmsAccessRecordService.countArtViews(artId);
            msg = "read";
        }
        return Result.success(msg, num);
    }

}
