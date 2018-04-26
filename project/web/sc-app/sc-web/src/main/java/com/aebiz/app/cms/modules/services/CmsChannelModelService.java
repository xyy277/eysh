package com.aebiz.app.cms.modules.services;

import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_channel_model;

public interface CmsChannelModelService extends BaseService<Cms_channel_model>{
    /**
     * 删除栏目关联表表数据
     * @param cmsChannelId 栏目id
     */
    public void deleteByCmsChannelId(String cmsChannelId) ;

}
