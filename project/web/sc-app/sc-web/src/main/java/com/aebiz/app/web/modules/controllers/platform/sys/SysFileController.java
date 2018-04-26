package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sys.modules.models.Sys_file;
import com.aebiz.app.sys.modules.services.SysFileService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/platform/sys/file")
public class SysFileController {
    private static final Log log = Logs.get();
    @Autowired
    private SysFileService sysFileService;

    @RequestMapping("")
    @RequiresPermissions("platform.sys.file")
    public String index() {
        return "pages/platform/sys/file/index";
    }

    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sys.file")
    public Object data(DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        return sysFileService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sys.file")
    public String add() {
        return "pages/platform/sys/file/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sys_file")
    @RequiresPermissions("platform.sys.file.add")
    public Object addDo(Sys_file sysFile, HttpServletRequest req) {
        try {
            sysFileService.insert(sysFile);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sys.file")
    public String edit(@PathVariable String id, HttpServletRequest req) {
        req.setAttribute("obj", sysFileService.fetch(id));
        return "pages/platform/sys/file/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sys_file")
    @RequiresPermissions("platform.sys.file.edit")
    public Object editDo(Sys_file sysFile, HttpServletRequest req) {
        try {
            sysFile.setOpBy(StringUtil.getUid());
            sysFile.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysFileService.updateIgnoreNull(sysFile);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sys_file")
    @RequiresPermissions("platform.sys.file.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids", required = false) String[] ids, HttpServletRequest req) {
        try {
            File file = null;
            if (ids != null && ids.length > 0) {
                List<Sys_file> files = sysFileService.query(Cnd.where("id", "in", ids));
                for (Sys_file f : files) {
                    file = new File(Globals.APP_ROOT + f.getPath() + "/" + f.getName());
                    if (file.exists()) file.delete();
                }
                sysFileService.delete(ids);
                req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
            } else {
                Sys_file f = sysFileService.fetch(id);
                file = new File(Globals.APP_ROOT + f.getPath() + "/" + f.getName());
                if (file.exists()) file.delete();
                sysFileService.delete(id);
                req.setAttribute("id", id);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sys.file")
    public String detail(@PathVariable String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysFileService.fetch(id));
        } else {
            req.setAttribute("obj", null);
        }
        return "pages/platform/sys/file/detail";
    }

}
