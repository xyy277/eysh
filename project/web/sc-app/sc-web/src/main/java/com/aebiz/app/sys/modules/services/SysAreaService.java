package com.aebiz.app.sys.modules.services;

import com.aebiz.app.sys.modules.models.Sys_area;
import com.aebiz.baseframework.base.service.BaseService;

import java.util.List;
import java.util.Map;

public interface SysAreaService extends BaseService<Sys_area> {
    void clearCache();

    String getNameByCode(String code);

    String getNameById(String id);

    String getParentCode(String childCode);

    Map getArea(String code);

    Sys_area getByCode(String code);

    List<Sys_area> getAreaNodeList(String code);

    void save(Sys_area shoparea, String pid);

    void deleteAndChild(Sys_area shoparea);

    /**
     * 获取列表
     * @return
     */
    List<Sys_area> getShopAreaList();

}
