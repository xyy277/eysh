package com.aebiz.app.sys.modules.services;

import com.aebiz.app.sys.modules.models.Sys_menu;
import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.baseframework.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface SysRoleService extends BaseService<Sys_role> {
    List<String> getPermissionNameList(Sys_role role);

    List<Sys_menu> getMenusAndButtons(String id);

    List<Sys_menu> getDatas(String id);
    /**
     * 根据角色批量查询菜单和按钮
     * @param roleIds 角色ids
     * @return
     */
    List<Sys_menu> getMenusAndButtons(String roleIds[]) ;
    /**
     * 根据角色批量查询菜单
     * @param roleIds 角色ids
     * @return
     */
    List<Sys_menu> getDatas(String []roleIds);

    void del(String roleid);

    void del(String[] roleids);

    /**
     * 编辑保存Cms资源权限
     * @param roleId 角色id
     * @param channel_ids 栏目ids
     * @param siteIdMap key：栏目id;value:站点id
     * @param btnMap key:按钮key+栏目id;value:按钮key
     * @param newIds 除按钮权限外的栏目权限及站点权限
     */
    void editCmsMenuDo(String roleId, List<String> channel_ids,Map<String,String> siteIdMap,Map<String,String> btnMap,String [] newIds);
}
