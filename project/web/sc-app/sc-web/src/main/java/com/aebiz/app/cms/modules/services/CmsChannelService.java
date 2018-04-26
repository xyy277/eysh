package com.aebiz.app.cms.modules.services;

import com.aebiz.app.cms.modules.models.Cms_channel_model;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_channel;

import java.util.List;

public interface CmsChannelService extends BaseService<Cms_channel>{
    /**
     * 新增栏目信息
     * @param cms_channel 栏目信息
     * @param models 内容模版
     * @param user 用户信息
     * @param ip 用户登录ip
     * @param resUrl 访问地址
     */
    public void addDo(Cms_channel cms_channel, List<Cms_channel_model> models, Sys_user user, String ip, String resUrl);
    /**
     * 修改栏目信息
     * @param cms_channel 栏目信息
     * @param models 内容模版
     * @param user 用户信息
     * @param ip 用户登录ip
     * @param resUrl 访问地址
     */
    public void editDo(Cms_channel cms_channel,List<Cms_channel_model> models,Sys_user user, String ip, String resUrl) throws Exception;
    /**
     * 删除栏目信息
     * @param cms_channel
     * @param user 用户信息
     * @param ip 用户登录ip
     * @param resUrl 访问地址
     */
    public void deleteAndChild(Cms_channel cms_channel,Sys_user user, String ip, String resUrl)throws Exception ;

}
