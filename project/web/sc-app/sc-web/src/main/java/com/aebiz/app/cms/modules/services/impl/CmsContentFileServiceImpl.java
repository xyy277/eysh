package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_content_file;
import com.aebiz.app.cms.modules.services.CmsContentFileService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CmsContentFileServiceImpl extends BaseServiceImpl<Cms_content_file> implements CmsContentFileService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
