package com.aebiz.app.cms.modules.services;

import com.aebiz.app.cms.modules.models.Cms_access_record;
import com.aebiz.baseframework.base.service.BaseService;
import org.nutz.dao.entity.Record;

import java.util.Date;
import java.util.List;

/**
 * 访问统计的业务
 * Created by zyang on 2017/8/2.
 */
public interface CmsAccessRecordService extends BaseService<Cms_access_record> {

    /**
     * 根据站点id获取指定时间内的访客数（UV）
     *
     * @param siteId
     * @param start
     * @param end
     * @return
     */
    int countUV(String siteId, long start, long end);

    /**
     * 根据站点id获取指定日期当天的访客数（UV）
     *
     * @param siteId
     * @param date
     * @return
     */
    int countUV(String siteId, Date date);

    /**
     * 根据站点id获取指定时间内的浏览量（PV）
     *
     * @param siteId
     * @param start
     * @param end
     * @return
     */
    int countPV(String siteId, long start, long end);

    /**
     * 根据站点id获取指定日期当天的浏览量（PV）
     *
     * @param siteId
     * @param date
     * @return
     */
    int countPV(String siteId, Date date);

    /**
     * 根据站点id获取指定时间内的ip数（IP）
     *
     * @param siteId
     * @param start
     * @param end
     * @return
     */
    int countIP(String siteId, long start, long end);

    /**
     * 根据站点id获取指定日期当天的ip数（IP）
     *
     * @param siteId
     * @param date
     * @return
     */
    int countIP(String siteId, Date date);

    /**
     * 根据站点id获取不同系统的指定日期的UV数
     *
     * @param siteId
     * @param date
     * @param ostype 0计算机，1移动端
     * @return
     */
    int countUVByOs(String siteId, Date date, int ostype);

    /**
     * 根据站点id获取不同系统的指定日期的PV数
     *
     * @param siteId
     * @param date
     * @param ostype 0计算机，1移动端
     * @return
     */
    int countPVByOs(String siteId, Date date, int ostype);

    /**
     * 根据站点id获取不同系统的指定日期的ip数（IP）
     *
     * @param siteId
     * @param date
     * @param ostype 0计算机，1移动端
     * @return
     */
    int countIPByOs(String siteId, Date date, int ostype);

    Record countByOs(String siteId, Date date, int ostype);

    /**
     * 根据浏览器分组返回UV、IP、PV、AT数据
     *
     * @param siteId
     * @param date
     * @param ostype
     * @return
     */
    List<Record> countByBrowser(String siteId, Date date, int ostype);

    int countBR(String siteId, Date date, String host, int ostype, String browser);

    long[] getDayStartAndEnd(Date date);

    int countDR(String siteId, long start, long end);

    int countSO(String siteId, long start, long end);

    int countWL(String siteId, long start, long end, String host);

    /**
     * 统计文章阅读量，前提是在统计代码中传入了文章id
     * 该方法每个用户每天每篇文章只统计一次
     *
     * @param artId 文章id
     * @return
     */
    int countArtReadNum(String artId);

    /**
     * 统计文章浏览量，前提是在统计代码中传入了文章id
     * 该方法只要访问了就统计
     *
     * @param artId 文章id
     * @return
     */
    int countArtViews(String artId);

    /**
     * 统计该页面的浏览量，不过滤相同用户多次的访问
     *
     * @param path
     * @return
     */
    int countPageViews(String path);

    /**
     * 统计该页面的浏览量，过滤相同用户同一天的多次访问
     *
     * @param path
     * @return
     */
    int countPageFirstView(String path);
}
