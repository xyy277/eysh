package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.sys.modules.models.Sys_app_unit;
import com.aebiz.app.sys.modules.services.SysApiService;
import com.aebiz.app.sys.modules.services.SysAppUnitService;
import com.aebiz.app.sys.modules.services.SysUnitService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/platform/sys/api/unit")
public class SysAppUnitController {
    private static final Log log = Logs.get();
    @Autowired
    private SysAppUnitService sysAppUnitService;
    @Autowired
    private SysApiService sysApiService;
    @Autowired
    private SysUnitService sysUnitService;

    @RequestMapping("")
    @RequiresPermissions("platform.sys.api.unit")
    public String index() {
        return "pages/platform/sys/api/unit/index";
    }

    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sys.api.unit")
    public Object data(DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        return sysAppUnitService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, "unit|app");
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sys.api.unit")
    public String add(HttpServletRequest req) {
        req.setAttribute("appUnitList", sysAppUnitService.query(Cnd.NEW())); // 获取所有appUnit列表
        req.setAttribute("apiList", sysApiService.query(Cnd.NEW())); // 获取所有app列表
        req.setAttribute("unitList", sysUnitService.query(Cnd.NEW())); // 获取所有单位列表
        return "pages/platform/sys/api/unit/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sys_app_unit")
    @RequiresPermissions("platform.sys.api.unit.add")
    public Object addDo(Sys_app_unit ctAppUnit, @RequestParam(value = "appIds") String[] ids) {
        try {
            ctAppUnit.setOpBy(StringUtil.getUid());
            ctAppUnit.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysAppUnitService.saveInfo(ctAppUnit, ids);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }


    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sys.api.unit")
    public String edit(@PathVariable String id, HttpServletRequest req) {
        req.setAttribute("id", id);
        req.setAttribute("list", sysAppUnitService.query(Cnd.where("unitId", "=", id)));
        req.setAttribute("apiList", sysApiService.query(Cnd.NEW())); // 获取所有app列表
        req.setAttribute("unitList", sysUnitService.query(Cnd.NEW())); // 获取所有单位列表
        return "pages/platform/sys/api/unit/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sys_app_unit")
    @RequiresPermissions("platform.sys.api.unit.edit")
    public Object editDo(Sys_app_unit ctAppUnit, @RequestParam(value = "appIds") String[] ids) {
        try {
            ctAppUnit.setOpBy(StringUtil.getUid());
            ctAppUnit.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysAppUnitService.saveInfo(ctAppUnit, ids);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sys_app_unit")
    @RequiresPermissions("platform.sys.api.unit.delete")
    public Object delete(@PathVariable String id) {
        try {
            sysAppUnitService.deleteInfo(id);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sys.api.unit")
    public String detail(@PathVariable String id, HttpServletRequest req) {
        req.setAttribute("id", id);
        req.setAttribute("list", sysAppUnitService.query(Cnd.where("unitId", "=", id)));
        req.setAttribute("apiList", sysApiService.query(Cnd.NEW())); // 获取所有app列表
        req.setAttribute("unitList", sysUnitService.query(Cnd.NEW())); // 获取所有单位列表
        return "pages/platform/sys/api/unit/detail";
    }

}
