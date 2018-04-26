package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.models.Cms_channel_model;
import com.aebiz.app.cms.modules.services.CmsChannelModelService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CmsChannelModelServiceImpl extends BaseServiceImpl<Cms_channel_model> implements CmsChannelModelService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    @Override
    @Transactional
    public void deleteByCmsChannelId(String cmsChannelId) {
        dao().execute(Sqls.create("delete from cms_channel_model where channel_id = @channel_id ")
                .setParam("channel_id", cmsChannelId));
    }
}
