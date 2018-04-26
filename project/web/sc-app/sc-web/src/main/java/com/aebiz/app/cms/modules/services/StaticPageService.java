package com.aebiz.app.cms.modules.services;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.models.Cms_topic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zyang
 *         生成静态页面业务层
 */
public interface StaticPageService {
    /**
     * 生成首页静态页面
     *
     * @param site 站点信息
     * @return boolean 成功与否
     * @throws IOException
     */
    boolean geIndex(Cms_site site) throws IOException;

    /**
     * 生成首页静态页面
     *
     * @param site 站点信息
     * @param data 需要在页面模板上获取的参数
     * @return boolean 成功与否
     * @throws IOException
     */
    boolean geIndex(Cms_site site, Map<String, Object> data) throws IOException;

    /**
     * 批量生成首页静态页面
     *
     * @param sites 站点列表
     * @return 成功生成的页面数
     * @throws IOException
     */
    int geIndexs(List<Cms_site> sites) throws IOException;

    /**
     * 批量生成首页静态页面
     *
     * @param sites 站点列表
     * @param data  需要在页面模板上获取的参数
     * @return 成功生成的页面数
     * @throws IOException
     */
    int geIndexs(List<Cms_site> sites, Map<String, Object> data) throws IOException;

    /**
     * 删除首页静态页面
     *
     * @param site
     * @return boolean  成功与否
     * @throws IOException
     */
    boolean delIndex(Cms_site site) throws IOException;

    /**
     * 生成栏目页静态页面
     *
     * @param siteId       站点id
     * @param channelId    栏目id（为空默认为所有栏目）
     * @param containChild 是否包含子节点
     * @param isStatic     是否生成过了
     * @return 生成的页面数
     * @throws IOException
     */
    int channelPages(String siteId, String channelId, boolean containChild, boolean isStatic) throws IOException;

    int channelPages(String siteId, String channelId, boolean containChild, boolean isStatic, Map data) throws IOException;
    boolean channelPage(Cms_channel c, boolean isStatic, Map<String, Object> data) throws IOException;

    /**
     * 删除栏目静态页
     *
     * @param c 栏目对象
     * @throws IOException
     */
    void delChannelPage(Cms_channel c) throws IOException;

    /**
     * 内容页静态化
     *
     * @param content  内容对象
     * @param isStatic 是否已经生成
     * @param data     需要在页面模板上获取的参数
     * @return boolean
     */
    boolean contentPage(Cms_content content, boolean isStatic, Map data) throws FileNotFoundException;

    /**
     * 返回具体错误信息
     *
     * @param content 内容对象
     * @param data    需要在页面模板上获取的参数
     * @return
     */
    String contentPage(Cms_content content, Map data);

    /**
     * 批量生成内容页面,返回具体错误信息
     *
     * @param contents
     * @return
     */
    String contentPages(List<Cms_content> contents);

    /**
     * 内容页静态化
     *
     * @param content 内容对象
     * @param data    需要在页面模板上获取的参数
     * @return 页面内容字符串
     */
    String contentPageHmtl(Cms_content content, Map data);

    /**
     * 内容页静态化
     *
     * @param content
     * @return 页面内容字符串
     */
    String contentPageHmtl(Cms_content content);

    /**
     * 批量生成内容静态页
     *
     * @param siteId    站点id
     * @param channelId 栏目
     * @param start     开始时间
     * @param isStatic  是否已经生成过
     * @param data      需要传入页面模板的参数
     * @return
     * @throws IOException
     */
    int contentPages(String siteId, String channelId, Date start, Boolean isStatic, Map data) throws IOException;

    /**
     * 删除内容静态页
     *
     * @param c
     */
    void deleteContent(Cms_content c);

    /**
     * 更新站点首页和所属的所有栏目页
     * 多线程方式
     * @param siteId
     */
    void updateAllWithMultiThread(String siteId);

    /**
     * 非多线程
     *
     * @param siteId
     */
    void updateAll(String siteId);

    void updateIndex(String siteId, boolean useThread);

    /**
     * 更新栏目和首页
     *
     * @param c
     * @param s
     */
    void updateChannelAndIndex(Cms_channel c, Cms_site s);

    /**
     * 更新栏目和首页和专题页
     *
     * @param c
     * @param s
     */
    void updateChannelAndIndexAndTopic(Cms_channel c, Cms_site s, Cms_topic t);

    /**
     * 生成专题静态页
     *
     * @param topic
     * @param data
     * @return
     * @throws IOException
     */
    boolean topicPage(Cms_topic topic, Map<String, Object> data) throws IOException;

    /**
     * 批量生成专题静态页
     *
     * @param siteId
     * @param topicId
     * @param data
     * @return
     * @throws IOException
     */
    int topicPages(String siteId, String topicId, Map<String, Object> data) throws IOException;

    /**
     * 删除专题静态页
     *
     * @param topic
     * @throws IOException
     */
    void delTopic(Cms_topic topic) throws IOException;

    /**
     * 批量删除专题静态页
     *
     * @param topics
     * @throws IOException
     */
    void delTopics(List<Cms_topic> topics) throws IOException;

}
