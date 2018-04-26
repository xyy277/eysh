package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_channel_role;
import com.aebiz.app.cms.modules.services.CmsChannelRoleService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CmsChannelRoleServiceImpl extends BaseServiceImpl<Cms_channel_role> implements CmsChannelRoleService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
