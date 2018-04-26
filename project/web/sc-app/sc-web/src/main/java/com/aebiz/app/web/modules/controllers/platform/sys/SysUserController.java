package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.sys.modules.models.Sys_dict;
import com.aebiz.app.sys.modules.models.Sys_menu;
import com.aebiz.app.sys.modules.models.Sys_unit;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.*;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wizzer on 2017/1/19.
 */
@Controller
@RequestMapping("/platform/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUnitService sysUnitService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private PropertiesProxy config;
    @Autowired
    private ShiroUtil shiroUtil;

    @RequestMapping("")
    @RequiresPermissions("sys.manager.user")
    public String index() {
        return "pages/platform/sys/user/index";
    }


    @RequestMapping(value = {"/add", "/add/{unitid}"})
    @RequiresPermissions("sys.manager.user")
    public Object add(@PathVariable(required = false) String unitid, HttpServletRequest req) {
        req.setAttribute("apiUserAdd", config.get("api.user.add"));
        if (!Strings.isBlank(unitid)) {
            req.setAttribute("obj", sysUnitService.fetch(unitid));
        }
        List<Sys_dict> dictList = sysDictService.getSubListByCode("duties");
        req.setAttribute("dictList", dictList);
        return "pages/platform/sys/user/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @RequiresPermissions("sys.manager.user.add")
    @SLog(description = "新建用户")
    public Object addDo(Sys_user user, HttpServletRequest req) {
        try {
            if (StringUtils.isNotEmpty(Strings.sBlank(user.getLoginname()))) {
                Sys_user u = sysUserService.fetch(Cnd.where("mobile", "=", user.getLoginname()));
                if (u != null) {
                    return Result.error("用户名已存在");
                }
            }
            if (StringUtils.isNotEmpty(Strings.sBlank(user.getMobile()))) {
                Sys_user u = sysUserService.fetch(Cnd.where("mobile", "=", user.getMobile()));
                if (u != null) {
                    return Result.error("手机号已存在");
                }
            }
            sysUserService.addDo(user);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/ssoAddDo")
    @SJson
    @RequiresPermissions("sys.manager.user.add")
    @SLog(description = "新建用户")
    public Object ssoAddDo(Sys_user user, HttpServletRequest req) {
        if (!SSOUtil.isSSO()) {
            return addDo(user, req);
        }
        String type = StringUtils.trimToEmpty(req.getParameter("type"));
        try {
            if (StringUtils.isNotEmpty(Strings.sBlank(user.getLoginname()))) {
                Sys_user u = sysUserService.fetch(Cnd.where("mobile", "=", user.getLoginname()));
                if (u != null) {
                    return Result.error("用户名已存在");
                }
            }
            if (StringUtils.isNotEmpty(Strings.sBlank(user.getMobile()))) {
                Sys_user u = sysUserService.fetch(Cnd.where("mobile", "=", user.getMobile()));
                if (u != null) {
                    return Result.error("手机号已存在");
                }
            }
            return sysUserService.ssoAddDo(user, type);
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("sys.manager.user")
    public Object edit(@PathVariable(required = false) String id, HttpServletRequest req) {
        req.setAttribute("apiUserUpdate", config.get("api.user.update"));
        req.setAttribute("obj", sysUserService.fetchLinks(sysUserService.fetch(id), "unit"));
        List<Sys_dict> dictList = sysDictService.getSubListByCode("duties");
        req.setAttribute("dictList", dictList);
        return "pages/platform/sys/user/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @RequiresPermissions("sys.manager.user.edit")
    @SLog(description = "修改用户")
    public Object editDo(Sys_user user, @RequestParam("oldLoginname") String oldLoginname, @RequestParam("oldMobile") String oldMobile, HttpServletRequest req) {
        try {
            if (!Strings.sBlank(oldMobile).equals(user.getMobile())) {
                Sys_user u = sysUserService.fetch(Cnd.where("mobile", "=", user.getMobile()));
                if (u != null) {
                    return Result.error("手机号已存在");
                }
            }
            sysUserService.editDo(user, req, oldLoginname, oldMobile);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/resetPwd/{id}")
    @SJson
    @RequiresPermissions("sys.manager.user.edit")
    @SLog(description = "重置密码")
    public Object resetPwd(@PathVariable(required = false) String id, HttpServletRequest req) {
        try {
            Sys_user user = sysUserService.fetch(id);
            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String hashedPasswordBase64 = new Sha256Hash("ET922", salt, 1024).toBase64();
            sysUserService.update(Chain.make("salt", salt).add("password", hashedPasswordBase64), Cnd.where("id", "=", id));
            req.setAttribute("loginname", user.getLoginname());
            return Result.success("globals.result.success", "ET922");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/ssoResetPwd/{id}")
    @SJson
    @RequiresPermissions("sys.manager.user.edit")
    @SLog(description = "重置密码")
    public Object ssoResetPwd(@PathVariable(required = false) String id, HttpServletRequest req) {
        try {
            if (!SSOUtil.isSSO()) {
                return resetPwd(id, req);
            }
            Sys_user user = sysUserService.fetch(id);
            Map result = SSOUtil.ssoResetPassword(user.getLoginname(), "ET922");
            if (StringUtil.str2int((String) result.getOrDefault("code", "1")) == 0) {
                return resetPwd(id, req);
            } else {
                return Result.error((String) result.getOrDefault("msg", "globals.result.error"));
            }
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/enable/{userId}")
    @SJson
    @RequiresPermissions("sys.manager.user.edit")
    @SLog(description = "启用用户")
    public Object enable(@PathVariable(required = false) String userId, HttpServletRequest req) {
        try {
            sysUserService.update(Chain.make("disabled", false), Cnd.where("id", "=", userId));
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/disable/{userId}")
    @SJson
    @RequiresPermissions("sys.manager.user.edit")
    @SLog(description = "禁用用户")
    public Object disable(@PathVariable(required = false) String userId, HttpServletRequest req) {
        try {
            String loginname = sysUserService.fetch(userId).getLoginname();
            if ("superadmin".equals(loginname)) {
                return Result.error("system.not.allow");
            }
            sysUserService.update(Chain.make("disabled", true), Cnd.where("id", "=", userId));
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("sys.manager.user")
    public Object detail(@PathVariable(required = false) String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Sys_user user = sysUserService.fetch(id);
            req.setAttribute("obj", sysUserService.fetchLinks(user, "roles"));
            ;
        }

        return "pages/platform/sys/user/detail";

    }

    @RequestMapping("/menu/{id}")
    @RequiresPermissions("sys.manager.user")
    public Object menu(@PathVariable(required = false) String id, HttpServletRequest req) {
        Sys_user user = sysUserService.fetch(id);
        Sql sql = Sqls.create("SELECT A.ID FROM SYS_ROLE A,SYS_UNIT B WHERE A.UNITID= B.ID AND A.UNITID=@u ORDER BY A.ID ASC");
        sql.params().set("u", user.getUnitid());
        List<Record> rolelist = sysUserService.list(sql);
        List<Object> list = new ArrayList<>();
        for (Record r : rolelist) {
            list.add(r.get("id"));
        }
        String roleIds[] = StringUtils.join(list.toArray(), ",").split(",");
        List<Sys_menu> menus = sysRoleService.getMenusAndButtons(roleIds);
        List<Sys_menu> datas = sysRoleService.getDatas(roleIds);
        List<Sys_menu> firstMenus = new ArrayList<>();
        List<Sys_menu> secondMenus = new ArrayList<>();
        for (Sys_menu menu : menus) {
            for (Sys_menu bt : datas) {
                if (menu.getPath().equals(bt.getPath().substring(0, bt.getPath().length() - 4))) {
                    menu.setHasChildren(true);
                    break;
                }
            }
            if (menu.getPath().length() == 4) {
                firstMenus.add(menu);
            } else {
                secondMenus.add(menu);
            }
        }
        req.setAttribute("userFirstMenus", firstMenus);
        req.setAttribute("userSecondMenus", secondMenus);
        req.setAttribute("jsonSecondMenus", Json.toJson(secondMenus));
        req.setAttribute("obj", user);
        return "pages/platform/sys/user/menu";

    }

    @RequestMapping("/data")
    @SJson("{locked:'password|salt',ignoreNull:false}") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("sys.manager.user")
    public Object data(@RequestParam("unitid") String unitid, @RequestParam("loginname") String loginname, @RequestParam("username") String username, DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        if (!Strings.isBlank(unitid) && !"root".equals(unitid))
            cnd.and("unitid", "=", unitid);
        if (!Strings.isBlank(loginname))
            cnd.and("loginname", "like", "%" + loginname + "%");
        if (!Strings.isBlank(username))
            cnd.and("username", "like", "%" + username + "%");
        return sysUserService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("sys.manager.user")
    public Object tree(@PathVariable(required = false) String pid) {
        List<Sys_unit> list = new ArrayList<>();
        List<Map<String, Object>> tree = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        if (shiroUtil.hasRole("sysadmin")) {
            Cnd cnd = Cnd.NEW();
            if (Strings.isBlank(pid)) {
                cnd.and("parentId", "=", "").or("parentId", "is", null);
            } else {
                cnd.and("parentId", "=", pid);
            }
            cnd.asc("path");
            list = sysUnitService.query(cnd);
            if (Strings.isBlank(pid)) {
                obj.put("id", "root");
                obj.put("text", "所有用户");
                obj.put("children", false);
                tree.add(obj);
            }
        } else {
            Sys_user user = (Sys_user) shiroUtil.getPrincipal();
            if (user != null && Strings.isBlank(pid)) {
                list = sysUnitService.query(Cnd.where("id", "=", user.getUnitid()).asc("path"));
            } else {
                Cnd cnd = Cnd.NEW();
                if (Strings.isBlank(pid)) {
                    cnd.and("parentId", "=", "").or("parentId", "is", null);
                } else {
                    cnd.and("parentId", "=", pid);
                }
                cnd.asc("path");
                list = sysUnitService.query(cnd);
            }
        }
        for (Sys_unit unit : list) {
            obj = new HashMap<>();
            obj.put("id", unit.getId());
            obj.put("text", unit.getName());
            if (!unit.isHasChildren()) {
                obj.put("icon", Globals.APP_BASE + "/assets/platform/images/icons/filetype/txt.gif");
            }
            obj.put("children", unit.isHasChildren());
            tree.add(obj);
        }
        return tree;
    }

    @RequestMapping("/pass")
    @RequiresAuthentication
    public String pass() {
        return "pages/platform/sys/user/pass";
    }

    @RequestMapping("/custom")
    @RequiresAuthentication
    public String custom() {
        return "pages/platform/sys/user/custom";
    }

    @RequestMapping("/mode")
    @RequiresAuthentication
    public String mode() {
        return "pages/platform/sys/user/mode";
    }

    @RequestMapping("/modeDo")
    @RequiresAuthentication
    @SJson
    public Object modeDo(@RequestParam("mode") String mode, HttpServletRequest req) {
        try {
            sysUserService.update(Chain.make("loginPjax", "true".equals(mode)), Cnd.where("id", "=", StringUtil.getUid()));
            Subject subject = SecurityUtils.getSubject();
            Sys_user user = (Sys_user) subject.getPrincipal();
            if ("true".equals(mode)) {
                user.setLoginPjax(true);
            } else {
                user.setLoginPjax(false);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }


    @RequestMapping("/customDo")
    @RequiresAuthentication
    @SJson
    public Object customDo(@RequestParam("ids") String ids, HttpServletRequest req) {
        try {
            sysUserService.update(Chain.make("customMenu", ids), Cnd.where("id", "=", StringUtil.getUid()));
            Subject subject = SecurityUtils.getSubject();
            Sys_user user = (Sys_user) subject.getPrincipal();
            if (!Strings.isBlank(ids)) {
                user.setCustomMenu(ids);
                user.setCustomMenus(sysMenuService.query(Cnd.where("id", "in", ids.split(","))));
            } else {
                user.setCustomMenu("");
                user.setCustomMenus(new ArrayList<>());
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/doChangePassword")
    @RequiresAuthentication
    @SJson
    public Object doChangePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest req) {
        Subject subject = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) subject.getPrincipal();
        String old = new Sha256Hash(oldPassword, user.getSalt(), 1024).toBase64();
        if (old.equals(user.getPassword())) {
            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String hashedPasswordBase64 = new Sha256Hash(newPassword, salt, 1024).toBase64();
            user.setSalt(salt);
            user.setPassword(hashedPasswordBase64);
            sysUserService.update(Chain.make("salt", salt).add("password", hashedPasswordBase64), Cnd.where("id", "=", user.getId()));
            return Result.success("sys.login.error.success");
        } else {
            return Result.error("sys.login.error.oldpassword");
        }
    }

    @RequestMapping("/ssoChangePassword")
    @RequiresAuthentication
    @SJson
    public Object ssoChangePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest req) {
        if (!SSOUtil.isSSO()) return doChangePassword(oldPassword, newPassword, req);
        Subject subject = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) subject.getPrincipal();
        String old = new Sha256Hash(oldPassword, user.getSalt(), 1024).toBase64();
        if (old.equals(user.getPassword())) {
            Map result = SSOUtil.ssoResetPassword(user.getLoginname(), newPassword);
            if (StringUtil.str2int((String) result.getOrDefault("code", "1")) == 0) {
                RandomNumberGenerator rng = new SecureRandomNumberGenerator();
                String salt = rng.nextBytes().toBase64();
                String hashedPasswordBase64 = new Sha256Hash(newPassword, salt, 1024).toBase64();
                user.setSalt(salt);
                user.setPassword(hashedPasswordBase64);
                sysUserService.update(Chain.make("salt", salt).add("password", hashedPasswordBase64), Cnd.where("id", "=", user.getId()));
                return Result.success("sys.login.error.success");
            } else {
                return Result.error((String) result.getOrDefault("msg", "globals.result.error"));
            }
        } else {
            return Result.error("sys.login.error.oldpassword");
        }
    }

}
