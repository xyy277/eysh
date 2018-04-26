package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.app.sys.modules.models.Sys_app_unit;
import com.aebiz.app.sys.modules.services.SysAppUnitService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SysAppUnitServiceImpl extends BaseServiceImpl<Sys_app_unit> implements SysAppUnitService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }


    /**
     * 保存应用与单位对应关系
     *
     * @param info
     * @param ids
     */
    @Transactional
    @Override
    public void saveInfo(Sys_app_unit info, String[] ids) {
        dao().clear(this.getEntityClass(), Cnd.where("unitId", "=", info.getUnitId()));
        for (int i = 0; i < ids.length; i++) {
            info.setAppId(ids[i]);
            dao().insert(info);
        }
    }

    /**
     * 删除应用与单位对应关系
     *
     * @param unitId
     */
    @Transactional
    @Override
    public void deleteInfo(String unitId) {
        dao().clear(this.getEntityClass(), Cnd.where("unitId", "=", unitId));
    }

    @Override
    public Sys_app_unit getAppUintByUnitId(String unitId) {
        return dao().query(Sys_app_unit.class, Cnd.where("unitId", "=", unitId)).get(0);
    }
}
