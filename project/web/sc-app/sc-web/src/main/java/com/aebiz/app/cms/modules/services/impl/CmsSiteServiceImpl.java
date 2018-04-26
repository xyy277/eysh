package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CmsSiteServiceImpl extends BaseServiceImpl<Cms_site> implements CmsSiteService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    @Autowired
    private SysUserService sysUserService;
    @Override
    public List<Record> getSiteRole(Sys_user user) {
        List<String> roleIdList=sysUserService.getRoleIdList(user);
        Sql sql = Sqls.create("select distinct a.id,a.site_name,a.site_sname,a.site_domain,a.site_path,a.site_css,a.is_static from cms_site a,cms_site_role b " +
                "where a.id=b.siteid and b.roleid in (@roleId)" );
        sql.setParam("roleId",roleIdList.toArray());
        return this.list(sql);
    }
}
