package com.aebiz.app.sys.modules.services;

import com.aebiz.app.sys.modules.models.Sys_app_unit;
import com.aebiz.baseframework.base.service.BaseService;

public interface SysAppUnitService extends BaseService<Sys_app_unit> {
    /**
     * 保存应用与单位对应关系
     *
     * @param info
     * @param ids
     */
    public void saveInfo(Sys_app_unit info, String[] ids);

    /**
     * 删除应用与单位对应关系
     *
     * @param unitId
     */
    public void deleteInfo(String unitId);

    public Sys_app_unit getAppUintByUnitId(String unitId);
}
