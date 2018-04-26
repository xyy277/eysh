package com.aebiz.app.web.modules.controllers.platform.sys;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.ShiroUtil;
import com.aebiz.app.sys.modules.models.Sys_unit;
import com.aebiz.app.sys.modules.services.SysUnitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.aebiz.baseframework.base.Result;
import com.aebiz.commons.utils.StringUtil;

@Controller
@RequestMapping("/platform/sys/unit")
public class SysUnitController {
    @Autowired
    private SysUnitService sysUnitService;
    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private PropertiesProxy config;
    private static Log log = Logs.get();

    @RequestMapping("")
    @RequiresPermissions("sys.manager.unit")
    public String index(HttpServletRequest req) {
        if (shiroUtil.hasRole("sysadmin")) {
            req.setAttribute("obj", sysUnitService.query(Cnd.where("parentId", "=", "").or("parentId", "is", null).asc("path")));
        }else {
            Sys_user user = (Sys_user) shiroUtil.getPrincipal();
            if (user != null) {
                req.setAttribute("obj", sysUnitService.query(Cnd.where("id", "=", user.getUnitid()).asc("path")));
            }
        }
        return "pages/platform/sys/unit/index";
    }

    @RequestMapping(value = {"/add/{id}", "/add"})
    @RequiresPermissions("sys.manager.unit")
    public String add(@PathVariable(required = false) String id, HttpServletRequest req) {
        req.setAttribute("obj", Strings.isBlank(id) ? null : sysUnitService.fetch(id));
        return "pages/platform/sys/unit/add";
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("sys.manager.unit")
    public String detail(@PathVariable String id, HttpServletRequest req) {
        req.setAttribute("obj", Strings.isBlank(id) ? null : sysUnitService.fetch(id));
        return "pages/platform/sys/unit/detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addDo")
    @SJson
    @RequiresPermissions("sys.manager.unit.add")
    public Object addDo(Sys_unit unit, String parentId) {
        try {
            if (SSOUtil.isSSO()) {
                unit.setParentId(parentId);
                // 屏蔽掉消息队列
                // config.put("localAddUnit","true");
                Map result = SSOUtil.ssoAddOrUpdateUnit(unit);
                // if(Objects.equals(result.get("code"),"0")){
                //     sysUnitService.save(unit, parentId);
                // }
                Result rs = Lang.map2Object(result, Result.class);
                return rs;
            } else {
                sysUnitService.save(unit, parentId);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/child/{id}")
    @RequiresPermissions("sys.manager.unit")
    public String child(@PathVariable String id, HttpServletRequest req) {
        req.setAttribute("obj", sysUnitService.query(Cnd.where("parentId", "=", id).asc("location").asc("path")));
        return "pages/platform/sys/unit/child";
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("sys.manager.unit")
    public String edit(@PathVariable String id, HttpServletRequest req) {
        Sys_unit unit = sysUnitService.fetch(id);
        if (unit != null && unit.getParentId() != null) {
            req.setAttribute("parentUnit", sysUnitService.fetch(unit.getParentId()));
        }
        req.setAttribute("obj", unit);
        return "pages/platform/sys/unit/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editDo")
    @SJson
    @RequiresPermissions("sys.manager.unit.edit")
    public Object editDo(Sys_unit unit, HttpServletRequest req) {
        try {
            unit.setOpBy(StringUtil.getUid());
            unit.setOpAt((int) (System.currentTimeMillis() / 1000));
            if (SSOUtil.isSSO()) {
                // 屏蔽掉消息队列
                config.put("localAddUnit", "true");
                Map result = SSOUtil.ssoAddOrUpdateUnit(unit);
                if (Objects.equals(result.get("code"), "0")) {
                    sysUnitService.updateIgnoreNull(unit);
                }
                // Result rs = Lang.map2Object(result, Result.class);
                // return rs;
            } else {
                sysUnitService.updateIgnoreNull(unit);
            }

            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    @SJson
    @RequiresPermissions("sys.manager.unit.delete")
    public Object delete(@PathVariable String id, HttpServletRequest req) {
        try {
            Sys_unit unit = sysUnitService.fetch(id);
            if (SSOUtil.isSSO()) {
                // 屏蔽掉消息队列
                // config.put("localAddUnit","true");
                Map result = SSOUtil.ssoDeleteUnit(unit);
                sysUnitService.delete(id);
                // if(Objects.equals(result.get("code"),"0")){
                //     sysUnitService.delete(id);
                // }
                Result rs = Lang.map2Object(result, Result.class);
                return rs;
            } else {
                sysUnitService.deleteAndChild(unit);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            log.error("删除单位出错", e);
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("sys.manager.unit")
    public Object tree(@PathVariable(required = false) String pid, HttpServletRequest req) {
        List<Sys_unit> list = new ArrayList<>();
        if (shiroUtil.hasRole("sysadmin")) {
            Cnd cnd = Cnd.NEW();
            if (Strings.isBlank(pid)) {
                cnd.and("parentId", "=", "").or("parentId", "is", null);
            } else {
                cnd.and("parentId", "=", pid);
            }
            cnd.asc("path");
            list = sysUnitService.query(cnd);
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
        List<Map<String, Object>> tree = new ArrayList<>();
        for (Sys_unit unit : list) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", unit.getId());
            obj.put("text", unit.getName());
            obj.put("children", unit.isHasChildren());
            if (!unit.isHasChildren()) {
                obj.put("icon", Globals.APP_BASE + "/assets/platform/images/icons/filetype/txt.gif");
            }
            tree.add(obj);
        }
        return tree;
    }
}
