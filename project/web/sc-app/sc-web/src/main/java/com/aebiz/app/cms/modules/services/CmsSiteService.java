package com.aebiz.app.cms.modules.services;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_site;
import org.nutz.dao.entity.Record;

import java.util.List;

public interface CmsSiteService extends BaseService<Cms_site>{
    /**
     * 获取该用户角色关联的站点信息
     * @param user 用户Model
     * @return
     */
    public List<Record> getSiteRole(Sys_user user);
}
