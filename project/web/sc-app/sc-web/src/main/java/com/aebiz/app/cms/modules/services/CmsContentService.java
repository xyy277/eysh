package com.aebiz.app.cms.modules.services;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_content;

import java.util.List;
import java.util.Map;

public interface CmsContentService extends BaseService<Cms_content> {
    /**
     * 文章tab列表统计
     *
     * @param site_id
     * @param channel_id
     * @return
     */
    public Map getStatisticMap(String site_id, String channel_id);

    /**
     * 保存文章信息
     *
     * @param cmsContent      文章Modl
     * @param tagNmae         标签名称
     * @param type            type
     * @param txt             文章内容
     * @param picPaths        图片集
     * @param attachmentPaths 文件集合
     * @param user            登录用户信息
     * @param ip              用户ip
     * @param resUrl          访问地址
     */
    public void addDo(Cms_content cmsContent, String tagNmae, String type, String txt, String picPaths, String attachmentPaths, Sys_user user, String ip, String resUrl);

    /**
     * 修改文章信息
     *
     * @param cmsContent      文章Modl
     * @param tagNmae         标签名称
     * @param type            type
     * @param txtContent      文章内容
     * @param picPaths        图片集
     * @param attachmentPaths 文件集合
     * @param user            登录用户信息
     * @param ip              用户ip
     * @param resUrl          访问地址
     */
    public void editDo(Cms_content cmsContent, String tagNmae, String type, String txtContent, String picPaths, String attachmentPaths, Sys_user user, String ip, String resUrl);

    /**
     * 文章审核
     *
     * @param is_checked    审核结果
     * @param check_opinion 审核原因
     * @param flowid        工作流id
     * @param content       文章Modl
     * @param step          步骤
     * @param user          登录用户信息
     * @param ip            用户ip
     * @param resUrl        访问地址
     * @return
     */
    public String savecheck(String is_checked, String check_opinion, String flowid, Cms_content content, Integer step, Sys_user user, String ip, String resUrl);

    /**
     * 批量终审通过
     *
     * @param ids    文章ids
     * @param user   登录用户信息
     * @param ip     用户ip
     * @param resUrl 访问地址
     */
    public void savechecks(String ids, Sys_user user, String ip, String resUrl);

    /**
     * 文章推送
     *
     * @param content_ids 文章ids
     * @param channel_id  栏目id
     */
    public void pushSave(String[] content_ids, String channel_id);


    /**
     * 获取阅读量最多的文章
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Cms_content> getReadMoreContent(Integer pageNo, Integer pageSize);

    /**
     * 通过栏目获取 该栏目下的文章
     *
     * @param channel_id 栏目id
     * @return
     */
    List<Cms_content> getContentByChinnelId(String channel_id);

}