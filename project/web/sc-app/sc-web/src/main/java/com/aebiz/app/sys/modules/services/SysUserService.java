package com.aebiz.app.sys.modules.services;

import com.aebiz.app.sys.modules.models.Sys_menu;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.base.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface SysUserService extends BaseService<Sys_user> {
    List<String> getRoleCodeList(Sys_user user);
    void fillMenu(Sys_user user);

    void deleteById(String userId);

    void deleteByIds(String[] userIds);

    List<Sys_menu> getMenusAndButtons(String id);

    List<Sys_menu> getDatas(String id);

    /**
     * 根据当前用户id,获角色ids
     * @param user
     * @return
     */
    List<String> getRoleIdList(Sys_user user);
    /**
     * 检测字段的唯一性
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     *
     * @return 存在返回true，不存在返回false
     */
    public boolean checkUnique(String fieldName, String fieldValue) ;

    void addDo(Sys_user user);

    Sys_user addDo2(Sys_user user);

    Result ssoAddDo(Sys_user user, String type);

    void editDo(Sys_user user, HttpServletRequest req, String oldLoginname, String oldMobile);
}
