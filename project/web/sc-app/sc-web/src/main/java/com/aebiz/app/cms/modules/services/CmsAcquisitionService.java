package com.aebiz.app.cms.modules.services;

import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.models.Cms_content_txt;
import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_acquisition;

public interface CmsAcquisitionService extends BaseService<Cms_acquisition>{
    /**
     * 保存采集的文章信息
     * @param content 文章Model
     * @param content_txt 文章内容Model
     */
    public void saveCollectMsg(Cms_content content, Cms_content_txt content_txt);
}
