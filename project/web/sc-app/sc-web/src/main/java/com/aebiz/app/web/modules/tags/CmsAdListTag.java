package com.aebiz.app.web.modules.tags;

import com.aebiz.app.cms.modules.models.Cms_link;
import com.aebiz.app.cms.modules.services.CmsLinkClassService;
import com.aebiz.app.cms.modules.services.CmsLinkService;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zzhu on 2017/9/29.
 * 静态页双创资讯广告
 */
@Component
@Scope("prototype")
public class CmsAdListTag extends GeneralVarTagBinding {
    @Autowired
    private PropertiesProxy config;
    @Autowired
    private CmsLinkClassService cmsLinkClassService;
    @Autowired
    private CmsLinkService cmsLinkService;

    @Override
    public void render() {
        String ad=config.get("front.double.info.list.ad");
        String classId=cmsLinkClassService.getLinkClassIdByKey(ad);
        List<Cms_link> links=cmsLinkService.query(Cnd.where("classId","=",classId));
        this.binds(links);
        this.doBodyRender();
    }
}
