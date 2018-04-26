package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.services.CmsAccessRecordService;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zyang on 2017/8/2.
 */
@Controller
@RequestMapping("/platform/cms/record")
public class CmsAccessRecordController {

    @Autowired
    private CmsAccessRecordService cmsAccessRecordService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsSiteService cmsSiteService;

    @RequestMapping(value = {"/", "/index"})
    @RequiresPermissions("cms.site.record")
    public String index(HttpServletRequest request) {
        // 获取站点列表
        Sys_user user = sysUserService.fetch(StringUtil.getUid());

        List<String> roleIdList = sysUserService.getRoleIdList(user);
        Sql sql = Sqls.create("select  distinct a.id,a.site_name,a.site_sname,a.site_domain,a.site_path,a.site_css,a.is_static from cms_site a,cms_site_role b " +
                "where a.id=b.siteid and b.roleid in (@roleId)");
        sql.setParam("roleId", roleIdList.toArray());
        List<Record> siteList = cmsSiteService.list(sql);

        request.setAttribute("siteList", siteList);
        return "pages/platform/cms/record/index";
    }

    @RequestMapping("/getByTime")
    @SJson("full")
    @RequiresPermissions("cms.site.record")
    public Result getByTime(@RequestParam("siteId") String siteId) {
        Map data = new HashMap<String, Object>();
        // 今天的
        Map today = new HashMap<String, Integer>();
        Date date = new Date();

        long[] sAndE = cmsAccessRecordService.getDayStartAndEnd(date);
        int tUV = cmsAccessRecordService.countUV(siteId, date);
        int tPV = cmsAccessRecordService.countPV(siteId, date);
        int tIP = cmsAccessRecordService.countIP(siteId, date);
        today.put("UV", tUV);
        today.put("PV", tPV);
        today.put("IP", tIP);
        data.put("today", today);

        // 今天已经过去的时间
        double pass = date.getTime() - sAndE[0];

        // 昨天的
        Map yesterday = new HashMap<String, Integer>();
        date.setTime(date.getTime() - 3600 * 24 * 1000);
        int yUV = cmsAccessRecordService.countUV(siteId, date);
        int yPV = cmsAccessRecordService.countPV(siteId, date);
        int yIP = cmsAccessRecordService.countIP(siteId, date);
        yesterday.put("UV", yUV);
        yesterday.put("PV", yPV);
        yesterday.put("IP", yIP);
        data.put("yesterday", yesterday);

        // 预计今天的
        double per = pass / (3600 * 24 * 1000);
        int wUV = (int) Math.ceil(tUV / per);
        int wPV = (int) Math.ceil(tPV / per);
        int wIP = (int) Math.ceil(tIP / per);
        Map willToday = new HashMap<String, Integer>();
        willToday.put("UV", wUV);
        willToday.put("PV", wPV);
        willToday.put("IP", wIP);
        data.put("willToday", willToday);
        return Result.success("获取成功", data);
    }

    @RequestMapping("/getByOs")
    @SJson("full")
    @RequiresPermissions("cms.site.record")
    public Result getByOs(HttpServletRequest request) {
        String siteId = request.getParameter("siteId");
        StringBuffer url = request.getRequestURL();
        String host = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();

        Map data = new HashMap<String, Object>();

        Date date = new Date();

        // 计算机端数据
        List<Record> pcList = new ArrayList<>();
        Record allr = cmsAccessRecordService.countByOs(siteId, date, 0);

        int allBR = cmsAccessRecordService.countBR(siteId, date, host, 0, "");
        double br = 0;
        if (allBR > 0)
            br = (double) allBR / allr.getInt("PV");
        DecimalFormat df = new DecimalFormat("0.00%");
        allr.set("BR", df.format(br));
        pcList.add(allr);
        List<Record> list = cmsAccessRecordService.countByBrowser(siteId, date, 0);
        for (Record r : list) {
            String browser = r.getString("browser");
            int bBr = cmsAccessRecordService.countBR(siteId, date, host, 0, browser);
            double nbr = 0.00;
            if (bBr > 0)
                nbr = (double) bBr / r.getInt("PV");
            r.set("BR", df.format(nbr));
        }
        pcList.addAll(list);
        data.put("pc", pcList);
        // 移动端数据
        List<Record> mbList = new ArrayList<>();
        Record allMr = cmsAccessRecordService.countByOs(siteId, date, 1);

        int allMBR = cmsAccessRecordService.countBR(siteId, date, host, 1, "");
        list.clear();
        list = cmsAccessRecordService.countByBrowser(siteId, date, 1);

        double mbr = 0;
        if (allMBR > 0) {
            mbr = (double) allMBR / allMr.getInt("PV");
        }
        allMr.set("BR", df.format(mbr));
        mbList.add(allMr);

        for (Record r : list) {
            String browser = r.getString("browser");
            int bBr = cmsAccessRecordService.countBR(siteId, date, host, 1, browser);
            double nbr = 0.00;
            if (bBr > 0)
                nbr = (double) bBr / r.getInt("PV");
            r.set("BR", df.format(nbr));
        }
        mbList.addAll(list);
        data.put("mb", mbList);
        return Result.success("获取成功", data);
    }

    @RequestMapping("/getByHours")
    @SJson("full")
    @RequiresPermissions("cms.site.record")
    public Result getByHours(HttpServletRequest request) {
        String siteId = request.getParameter("siteId");
        StringBuffer url = request.getRequestURL();
        String host = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
        Map data = new HashMap<String, Object>();
        List dr = new ArrayList();
        List so = new ArrayList();
        List wl = new ArrayList();
        long start = cmsAccessRecordService.getDayStartAndEnd(new Date())[0];

        for (int i = 0; i < 24; i++) {
            int a = cmsAccessRecordService.countDR(siteId, start, start + 3600000);
//            int b = cmsAccessRecordService.countSO(siteId, start, start + 3600000);
            int c = cmsAccessRecordService.countWL(siteId, start, start + 3600000, host);
            dr.add(a);
            so.add(0);
            wl.add(c);
            start += 3600000;
        }
        data.put("dr", dr);
        data.put("so", so);
        data.put("wl", wl);
        return Result.success("获取成功", data);
    }
}
