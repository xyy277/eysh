package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.models.Cms_access_record;
import com.aebiz.app.cms.modules.services.CmsAccessRecordService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.DB;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zyang on 2017/8/2.
 */
@Service
public class CmsAccessRecordServiceImpl extends BaseServiceImpl<Cms_access_record> implements CmsAccessRecordService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    @Override
    public int countUV(String siteId, long start, long end) {
        Sql sql = Sqls.create("SELECT COUNT(DISTINCT vid) FROM cms_access_record $condition");
        sql.setCondition(Cnd.where("siteId", "=", siteId).and("atime", "between", new Object[]{start, end}));
        return this.count(sql);
    }

    @Override
    public int countUV(String siteId, Date date) {
        long[] t = getDayStartAndEnd(date);
        return countUV(siteId, t[0], t[1]);
    }

    @Override
    public int countPV(String siteId, long start, long end) {
        Sql sql = Sqls.create("SELECT COUNT(*) FROM cms_access_record $condition");
        sql.setCondition(Cnd.where("siteId", "=", siteId).and("atime", "between", new Object[]{start, end}));
        return this.count(sql);
    }

    @Override
    public int countPV(String siteId, Date date) {
        long[] t = getDayStartAndEnd(date);
        return countPV(siteId, t[0], t[1]);
    }

    @Override
    public int countIP(String siteId, long start, long end) {
        Sql sql = Sqls.create("SELECT COUNT(DISTINCT vip) FROM cms_access_record $condition");
        sql.setCondition(Cnd.where("siteId", "=", siteId).and("atime", "between", new Object[]{start, end}));
        return this.count(sql);
    }

    @Override
    public int countIP(String siteId, Date date) {
        long[] t = getDayStartAndEnd(date);
        return countIP(siteId, t[0], t[1]);
    }

    @Override
    public int countUVByOs(String siteId, Date date, int ostype) {
        Sql sql = Sqls.create("SELECT COUNT(DISTINCT vid) FROM cms_access_record $condition");
        long[] t = getDayStartAndEnd(date);
        sql.setCondition(Cnd.where("siteId", "=", siteId).and("ostype", "=", ostype).and("atime", "between", new Object[]{t[0], t[1]}));
        return this.count(sql);
    }

    @Override
    public int countPVByOs(String siteId, Date date, int ostype) {
        Sql sql = Sqls.create("SELECT COUNT(*) FROM cms_access_record $condition");
        long[] t = getDayStartAndEnd(date);
        sql.setCondition(Cnd.where("siteId", "=", siteId).and("ostype", "=", ostype).and("atime", "between", new Object[]{t[0], t[1]}));
        return this.count(sql);
    }

    @Override
    public int countIPByOs(String siteId, Date date, int ostype) {
        Sql sql = Sqls.create("SELECT COUNT(DISTINCT vip) FROM cms_access_record $condition");
        long[] t = getDayStartAndEnd(date);
        sql.setCondition(Cnd.where("siteId", "=", siteId).and("ostype", "=", ostype).and("atime", "between", new Object[]{t[0], t[1]}));
        return this.count(sql);
    }

    public Record countByOs(String siteId, Date date, int ostype) {
        Sql sql = Sqls.create("SELECT COUNT(id) AS PV,COUNT(DISTINCT vid) AS UV,COUNT(DISTINCT vip) AS IP,AVG(duration) AS AT FROM cms_access_record $condition");

        Cnd cnd = Cnd.where("siteId", "=", siteId);
        if (date != null) {
            long[] t = getDayStartAndEnd(date);
            cnd.and("atime", "between", new Object[]{t[0], t[1]});
        }
        if (ostype >= 0) {
            cnd.and("ostype", "=", ostype);
        }

        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.record());
        dao().execute(sql);
        Record r = sql.getObject(Record.class);
        return r;
    }

    @Override
    public List<Record> countByBrowser(String siteId, Date date, int ostype) {
        Sql sql = Sqls.create("SELECT browser,COUNT(id) AS PV,COUNT(DISTINCT vid) AS UV,COUNT(DISTINCT vip) AS IP,AVG(duration) AS AT FROM cms_access_record $condition");

        Cnd cnd = Cnd.where("siteId", "=", siteId);
        if (date != null) {
            long[] t = getDayStartAndEnd(date);
            cnd.and("atime", "between", new Object[]{t[0], t[1]});
        }
        if (ostype >= 0) {
            cnd.and("ostype", "=", ostype);
        }
        cnd.groupBy("browser");
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.records());
        dao().execute(sql);
        List<Record> list = sql.getList(Record.class);
        return list;
    }

    @Override
    public int countBR(String siteId, Date date, String host, int ostype, String browser) {
        Sql sql = Sqls.create("SELECT COUNT(*) FROM cms_access_record $condition");
        // 从别的网站过来且持续时间小于5秒的认为是跳出的
        Cnd cnd = Cnd.where("siteId", "=", siteId).and("referrer", "not like", "%" + host + "%").and("duration", "<", 5000);
        if (date != null) {
            long[] t = getDayStartAndEnd(date);
            cnd.and("atime", "between", new Object[]{t[0], t[1]});
        }
        if (ostype >= 0) {
            cnd.and("ostype", "=", ostype);
        }
        if (!"".equals(StringUtils.trimToEmpty(browser))) {
            cnd.and("browser", "=", browser);
        }
        sql.setCondition(cnd);
        return this.count(sql);
    }

    @Override
    public int countDR(String siteId, long start, long end) {
        Sql sql = Sqls.create("SELECT COUNT(*) FROM cms_access_record WHERE siteId=@siteId AND (referrer = '' OR referrer IS NULL ) AND atime BETWEEN @start AND @end");
        sql.setParam("siteId", siteId);
        sql.setParam("start", start);
        sql.setParam("end", end);
        return this.count(sql);
    }

    @Override
    public int countSO(String siteId, long start, long end) {
        Sql sql = Sqls.create("SELECT count (id) FROM cms_access_record WHERE siteId=@siteId AND atime BETWEEN @start AND @end AND $regx ");
        sql.setParam("siteId", siteId);
        sql.setParam("start", start);
        sql.setParam("end", end);
        String dbType = this.dao().getJdbcExpert().getDatabaseType();
        if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
            sql.setVar("regx", ("REGEXP_LIKE(referrer ,'^(http|https)://(www\\.|m\\.)?(baidu|so|sougo|soso|google|bing)\\.(org|com|cn).*','i')"));
        } else {
            sql.setVar("regx", "referrer REGEXP '^(http|https)://(www\\.|m\\.)?(baidu|so|sougo|soso|google|bing)\\.(org|com|cn).*'");
        }
        return this.count(sql);
    }

    @Override
    public int countWL(String siteId, long start, long end, String host) {
        Sql sql = Sqls.create("SELECT count(id) FROM cms_access_record WHERE siteId=@siteId AND atime BETWEEN @start AND @end AND (referrer is NULL OR referrer = '') AND NOT $regx ");
        sql.setParam("siteId", siteId);
        sql.setParam("start", start);
        sql.setParam("end", end);
        String dbType = this.dao().getJdbcExpert().getDatabaseType();
        if (DB.ORACLE.name().equals(dbType) || DB.DM.name().equals(dbType)) {
            sql.setVar("regx", ("REGEXP_LIKE(referrer ,'^(http|https)://(www\\.|m\\.)?(baidu|so|sougo|soso|google|bing)\\.(org|com|cn).*','i')"));
        } else {
            sql.setVar("regx", "referrer REGEXP '^(http|https)://(www\\.|m\\.)?(baidu|so|sougo|soso|google|bing)\\.(org|com|cn).*'");
        }
        return this.count(sql);
    }

    @Override
    public int countArtReadNum(String artId) {
        String dbType = this.dao().getJdbcExpert().getDatabaseType();
        // 每个用户浏览的每篇文章每天只统计一次
        String s;
        if (dbType.equals("MYSQL")) {
            s = "SELECT vid,FROM_UNIXTIME(a.atime/1000,'%Y-%m-%d') AS d ,COUNT(artId) FROM cms_access_record a WHERE artId=@artId GROUP BY vid,d";
        } else {
            s = "SELECT vid FROM cms_access_record a WHERE artId=@artId GROUP BY vid,TO_CHAR (a.atime / (1000 * 60 * 60 * 24) + TO_DATE ('1970-01-01 08:00:00','YYYY-MM-DD HH:MI:SS'),'YYYY-MM-DD')";
        }
        Sql sql = Sqls.create(s);
        sql.setParam("artId", artId);
        List<Record> list = this.list(sql);
        return list.size();
    }

    @Override
    public int countArtViews(String artId) {
        // 该方法只要访问了这篇文章就统计
        Sql sql = Sqls.create("SELECT COUNT(artId) FROM cms_access_record  WHERE artId=@artId");
        sql.setParam("artId", artId);
        return this.count(sql);
    }

    @Override
    public int countPageViews(String path) {
        // 该方法只要访问了该页面就统计
        Sql sql = Sqls.create("SELECT COUNT(path) FROM cms_access_record  WHERE path=@path");
        sql.setParam("path", path);
        return this.count(sql);
    }

    @Override
    public int countPageFirstView(String path) {
        String dbType = this.dao().getJdbcExpert().getDatabaseType();
        // 每个用户浏览的每篇文章每天只统计一次
        String s;
        if (dbType.equals("MYSQL")) {
            s = "SELECT vid,FROM_UNIXTIME(a.atime/1000,'%Y-%m-%d') AS d FROM cms_access_record a WHERE path=@path GROUP BY vid,d";
        } else {
            s = "SELECT vid FROM cms_access_record a WHERE path=@path GROUP BY vid,TO_CHAR (a.atime / (1000 * 60 * 60 * 24) + TO_DATE ('1970-01-01 08:00:00','YYYY-MM-DD HH:MI:SS'),'YYYY-MM-DD')";
        }
        Sql sql = Sqls.create(s + ",COUNT(path) FROM cms_access_record a WHERE path=@path GROUP BY vid,d");
        sql.setParam("path", path);
        List<Record> list = this.list(sql);
        return list.size();
    }

    public long[] getDayStartAndEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long start = c.getTime().getTime();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        long end = c.getTime().getTime();
        return new long[]{start, end};
    }


}
