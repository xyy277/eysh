package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_workflow_info;
import com.aebiz.app.cms.modules.services.CmsWorkflowInfoService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CmsWorkflowInfoServiceImpl extends BaseServiceImpl<Cms_workflow_info> implements CmsWorkflowInfoService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
