package com.aebiz.app.web.modules.controllers.platform.sms;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sms.modules.models.Sms_tele_log;
import com.aebiz.app.sms.modules.services.SmsTeleLogService;
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
import java.util.Date;

@Controller
@RequestMapping("/platform/sms/tele/log")
public class SmsTeleLogController {
    private static final Log log = Logs.get();
    @Autowired
	private SmsTeleLogService smsTeleLogService;

    @RequestMapping("")
    @RequiresPermissions("platform.sms.tele.log")
	public String index(HttpServletRequest req) {
        req.setAttribute("today", DateUtil.getDate());
        req.setAttribute("month", DateUtil.format(new Date(), "yyyy-MM"));
		return "pages/platform/sms/tele/log/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sms.tele.log")
    public Object data(HttpServletRequest req,DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        String beginDate = req.getParameter("beginDate").replace("-","");
        String endDate = req.getParameter("endDate").replace("-","");

        if (Strings.isEmpty(beginDate) || Strings.isEmpty(endDate)) {
            beginDate = DateUtil.format(new Date(), "yyyyMMddHHmmss");
            endDate = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        }
        return smsTeleLogService.getData(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null, beginDate, endDate);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sms.tele.log")
    public String add() {
    	return "pages/platform/sms/tele/log/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sms_tele_log")
    @RequiresPermissions("platform.sms.tele.log.add")
    public Object addDo(Sms_tele_log smsTeleLog, HttpServletRequest req) {
		try {
			smsTeleLogService.insert(smsTeleLog);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sms.tele.log")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", smsTeleLogService.fetch(id));
		return "pages/platform/sms/tele/log/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sms_tele_log")
    @RequiresPermissions("platform.sms.tele.log.edit")
    public Object editDo(Sms_tele_log smsTeleLog, HttpServletRequest req) {
		try {
            smsTeleLog.setOpBy(StringUtil.getUid());
			smsTeleLog.setOpAt((int) (System.currentTimeMillis() / 1000));
			smsTeleLogService.updateIgnoreNull(smsTeleLog);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sms_tele_log")
    @RequiresPermissions("platform.sms.tele.log.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				smsTeleLogService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				smsTeleLogService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sms.tele.log")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", smsTeleLogService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sms/tele/log/detail";
    }

}
