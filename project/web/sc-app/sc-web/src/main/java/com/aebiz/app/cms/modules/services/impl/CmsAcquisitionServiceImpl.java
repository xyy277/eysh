package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.models.Cms_content_txt;
import com.aebiz.app.cms.modules.services.CmsContentService;
import com.aebiz.app.cms.modules.services.CmsContentTxtService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_acquisition;
import com.aebiz.app.cms.modules.services.CmsAcquisitionService;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CmsAcquisitionServiceImpl extends BaseServiceImpl<Cms_acquisition> implements CmsAcquisitionService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    @Autowired
    private CmsContentService cmsContentService;
    @Autowired
    private CmsContentTxtService cmsContentTxtService;
    @Override
    @Transactional
    public void saveCollectMsg(Cms_content content,Cms_content_txt content_txt){
        Cms_content ccc = cmsContentService.insert(content);
        content_txt.setContent_id(ccc.getId());
        cmsContentTxtService.insert(content_txt);
    }
}
