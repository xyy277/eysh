package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sys.modules.models.Sys_qq;
import com.aebiz.app.sys.modules.services.SysQqService;
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

@Controller
@RequestMapping("/platform/sys/qq")
public class SysQqController {
    private static final Log log = Logs.get();
    @Autowired
	private SysQqService sysQqService;

    @RequestMapping("")
    @RequiresPermissions("consult.ocs.qq")
	public String index() {
		return "pages/platform/sys/qq/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("consult.ocs.qq")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return sysQqService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("consult.ocs.qq")
    public String add() {
    	return "pages/platform/sys/qq/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sys_qq")
    @RequiresPermissions("consult.ocs.qq.add")
    public Object addDo(Sys_qq sysQq, HttpServletRequest req) {
		try {
			sysQqService.insert(sysQq);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("consult.ocs.qq")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", sysQqService.fetch(id));
		return "pages/platform/sys/qq/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sys_qq")
    @RequiresPermissions("consult.ocs.qq.edit")
    public Object editDo(Sys_qq sysQq, HttpServletRequest req) {
		try {
            sysQq.setOpBy(StringUtil.getUid());
			sysQq.setOpAt((int) (System.currentTimeMillis() / 1000));
			sysQqService.updateIgnoreNull(sysQq);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sys_qq")
    @RequiresPermissions("consult.ocs.qq.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				sysQqService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				sysQqService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("consult.ocs.qq")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysQqService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sys/qq/detail";
    }

}
