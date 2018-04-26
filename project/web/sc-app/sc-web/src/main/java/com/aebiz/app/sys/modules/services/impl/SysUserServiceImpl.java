package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;;
import com.aebiz.app.sys.modules.models.Sys_menu;
import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysMenuService;
import com.aebiz.app.sys.modules.services.SysUnitService;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wizzer on 2016/12/22.
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<Sys_user> implements SysUserService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ScAccountUserService scAccountUserService;
    @Autowired
    private ScAccountLinkService scAccountLinkService;
    @Autowired
    private PropertiesProxy config;
    @Autowired
    private SysUnitService sysUnitService;

    /**
     * 查询用户角色code列表
     *
     * @param user
     * @return
     */
    public List<String> getRoleCodeList(Sys_user user) {
        dao().fetchLinks(user, "roles");
        List<String> roleNameList = new ArrayList<String>();
        for (Sys_role role : user.getRoles()) {
            if (!role.isDisabled())
                roleNameList.add(role.getCode());
        }
        return roleNameList;
    }

    /**
     * 获取用户菜单
     *
     * @param user
     */
    public void fillMenu(Sys_user user) {
        user.setMenus(getMenus(user.getId()));
        //计算左侧菜单
        List<Sys_menu> firstMenus = new ArrayList<>();
        Map<String, List<Sys_menu>> secondMenus = new HashMap<>();
        for (Sys_menu menu : user.getMenus()) {
            if (menu.getPath().length() > 4) {
                List<Sys_menu> s = secondMenus.get(StringUtil.getParentId(menu.getPath()));
                if (s == null) s = new ArrayList<>();
                s.add(menu);
                secondMenus.put(StringUtil.getParentId(menu.getPath()), s);
            } else if (menu.getPath().length() == 4) {
                firstMenus.add(menu);
            }
        }
        user.setFirstMenus(firstMenus);
        user.setSecondMenus(secondMenus);
        if (!Strings.isBlank(user.getCustomMenu())) {
            user.setCustomMenus(sysMenuService.query(Cnd.where("id", "in", user.getCustomMenu().split(","))));
        }
    }

    public void deleteById(String userId) {
        dao().clear("sys_user_unit", Cnd.where("userId", "=", userId));
        dao().clear("sys_user_role", Cnd.where("userId", "=", userId));
        dao().clear("sys_user", Cnd.where("id", "=", userId));
    }

    public void deleteByIds(String[] userIds) {
        dao().clear("sys_user_unit", Cnd.where("userId", "in", userIds));
        dao().clear("sys_user_role", Cnd.where("userId", "in", userIds));
        dao().clear("sys_user", Cnd.where("id", "in", userIds));
    }

    /**
     * 查询用户菜单权限
     *
     * @param userId
     * @return
     */
    public List<Sys_menu> getMenus(String userId) {
        Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.id=b.menuId and a.system=@system and" +
                " b.roleId in(select c.roleId from sys_user_role c,sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=0) and a.disabled=0 and a.isShow=1 and a.type='menu' order by a.location ASC,a.path asc");
        sql.params().set("userId", userId);
        sql.params().set("system", "platform");
        Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(Sys_menu.class);
    }

    /**
     * 查询用户菜单和按钮权限
     *
     * @param userId
     * @return
     */
    public List<Sys_menu> getMenusAndButtons(String userId) {
        Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.id=b.menuId and a.system=@system and" +
                " b.roleId in(select c.roleId from sys_user_role c,sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=0) and a.disabled=0 order by a.location ASC,a.path asc");
        sql.params().set("userId", userId);
        sql.params().set("system", "platform");
        Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(Sys_menu.class);
    }

    /**
     * 查询用户按钮权限
     *
     * @param userId
     * @return
     */
    public List<Sys_menu> getDatas(String userId) {
        Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.id=b.menuId  and a.system=@system and" +
                " b.roleId in(select c.roleId from sys_user_role c,sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=0) and a.disabled=0 and a.type='data' order by a.location ASC,a.path asc");
        sql.params().set("userId", userId);
        sql.params().set("system", "platform");
        Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(Sys_menu.class);
    }

    @Override
    public List<String> getRoleIdList(Sys_user user) {
        dao().fetchLinks(user, "roles");
        List<String> roleIdList = new ArrayList<String>();
        for (Sys_role role : user.getRoles()) {
            if (!role.isDisabled())
                roleIdList.add(role.getId());
        }
        return roleIdList;
    }

    /**
     * 检测字段的唯一性
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return 存在返回true，不存在返回false
     */
    public boolean checkUnique(String fieldName, String fieldValue) {
        if (!Strings.isEmpty(fieldName) && !Strings.isEmpty(fieldValue)) {
            Sys_user accountUser = this.fetch(Cnd.where(fieldName, "=", fieldValue));
            if (accountUser != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void addDo(Sys_user user) {
        // 如果有值说明已通过消息同步存入了
        String msuid = config.remove("msuid." + user.getLoginname());
        if (!StringUtils.isBlank(msuid)) {
            Sys_user user2 = this.fetch(msuid);
            if (user2 != null) {
                user.setId(user2.getId());
            }
        }
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPasswordBase64 = new Sha256Hash(user.getPassword(), salt, 1024).toBase64();
        user.setSalt(salt);
        user.setPassword(hashedPasswordBase64);
        user.setLoginPjax(true);
        user.setLoginCount(0);
        user.setLoginAt(0);
        this.insertOrUpdate(user);

        // Sc_account_user accountUser=new Sc_account_user();
        // accountUser.setSalt(salt);
        // accountUser.setPassword(hashedPasswordBase64);
        // accountUser.setLoginname(user.getLoginname());
        // accountUser.setNickname(user.getUsername());
        // accountUser.setMobile(user.getMobile());
        // scAccountUserService.insert(accountUser);
        //
        // Sys_unit unit=sysUnitService.fetch(user.getUnitid());
        //
        // Sc_service_org_info u = scServiceOrgInfoService.fetch(Cnd.where("org_name", "=", unit.getName()));
        // if (u == null){
        //     Sc_service_org_info orgInfo = new Sc_service_org_info();
        //     orgInfo.setOrg_name(unit.getName());
        //     scServiceOrgInfoService.insert(orgInfo);
        //     u = scServiceOrgInfoService.fetch(Cnd.where("org_name", "=", unit.getName()));
        // }
        //
        // Sc_account_link linkUser = new Sc_account_link();
        // linkUser.setAccount_id(accountUser.getId());
        // linkUser.setTable_id(u.getId());
        // scAccountLinkService.insert(linkUser);
    }

    @Override
    @Transactional
    public Sys_user addDo2(Sys_user user) {
        // 如果有值说明已通过消息同步存入了
        String msuid = config.remove("msuid." + user.getLoginname());
        if (!StringUtils.isBlank(msuid)) {
            Sys_user user2 = this.fetch(msuid);
            if (user2 != null) {
                user.setId(user2.getId());
            }
        }
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPasswordBase64 = new Sha256Hash(user.getPassword(), salt, 1024).toBase64();
        user.setSalt(salt);
        user.setPassword(hashedPasswordBase64);
        user.setLoginPjax(true);
        user.setLoginCount(0);
        user.setLoginAt(0);
        this.insertOrUpdate(user);
        return user;
    }

    @Override
    public Result ssoAddDo(Sys_user user, String type) {
        Map map = SSOUtil.ssoAddUser(user, type);
        if (StringUtil.str2int((String) map.getOrDefault("code", "1")) == 0) {
            this.addDo(user);
            return Result.success();
        } else {
            return Result.error((String) map.getOrDefault("msg", "globals.result.error"));
        }
    }

    @Override
    @Transactional
    public void editDo(Sys_user user, HttpServletRequest req, String oldLoginname, String oldMobile) {
        user.setOpBy(Strings.sNull(req.getAttribute("uid")));
        user.setOpAt((int) (System.currentTimeMillis() / 1000));
        this.updateIgnoreNull(user);

        // Sc_account_user u = scAccountUserService.fetch(Cnd.where("loginname", "=", oldLoginname));
        // u.setOpBy(Strings.sNull(req.getAttribute("uid")));
        // u.setOpAt((int) (System.currentTimeMillis() / 1000));
        // u.setLoginname(user.getLoginname());
        // u.setNickname(user.getUsername());
        // u.setMobile(user.getMobile());
        // scAccountUserService.updateIgnoreNull(u);
        //
        // Sc_account_link linkUser = scAccountLinkService.fetch(Cnd.where("account_id", "=", u.getId()));
        // Sc_service_org_info orgInfo = scServiceOrgInfoService.fetch(Cnd.where("id", "=", linkUser.getTable_id()));
        // Sys_unit unit = sysUnitService.fetch(user.getUnitid());
        // orgInfo.setOrg_name(unit.getName());
        // scServiceOrgInfoService.updateIgnoreNull(orgInfo);
    }
}
