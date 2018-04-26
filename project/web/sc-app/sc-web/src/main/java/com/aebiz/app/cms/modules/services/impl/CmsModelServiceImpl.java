package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_model;
import com.aebiz.app.cms.modules.services.CmsModelService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CmsModelServiceImpl extends BaseServiceImpl<Cms_model> implements CmsModelService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
