package com.aebiz.app.web.modules.controllers.platform.sms;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sms.modules.models.Sms_send_log;
import com.aebiz.app.sms.modules.services.SmsSendLogService;
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
@RequestMapping("/platform/sms/send/log")
public class SmsSendLogController {
    private static final Log log = Logs.get();
    @Autowired
	private SmsSendLogService smsSendLogService;

    @RequestMapping("")
    @RequiresPermissions("platform.sms.send.log")
	public String index(HttpServletRequest req) {
       /* Cnd cnd=Cnd.NEW();
        cnd.and("delFlag","=",0);
        List<Sms_mould_info> smsMouldInfos=smsMouldInfoService.query(cnd);
        req.setAttribute("smsMouldInfos",smsMouldInfos);*/
        req.setAttribute("today", DateUtil.getDate());
        req.setAttribute("month", DateUtil.format(new Date(), "yyyy-MM"));
		return "pages/platform/sms/send/log/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sms.send.log")
    public Object data(HttpServletRequest req,DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        String beginDate = req.getParameter("beginDate").replace("-","");
        String endDate = req.getParameter("endDate").replace("-","");

        if (Strings.isEmpty(beginDate) || Strings.isEmpty(endDate)) {
            beginDate = DateUtil.format(new Date(), "yyyyMMddHHmmss");
            endDate = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        }
        return  smsSendLogService.getData(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null, beginDate, endDate);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sms.send.log")
    public String add() {
    	return "pages/platform/sms/send/log/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sms_send_log")
    @RequiresPermissions("platform.sms.send.log.add")
    public Object addDo(Sms_send_log smsSendLog, HttpServletRequest req) {
		try {
			smsSendLogService.insert(smsSendLog);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sms.send.log")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", smsSendLogService.fetch(id));
		return "pages/platform/sms/send/log/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sms_send_log")
    @RequiresPermissions("platform.sms.send.log.edit")
    public Object editDo(Sms_send_log smsSendLog, HttpServletRequest req) {
		try {
            smsSendLog.setOpBy(StringUtil.getUid());
			smsSendLog.setOpAt((int) (System.currentTimeMillis() / 1000));
			smsSendLogService.updateIgnoreNull(smsSendLog);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sms_send_log")
    @RequiresPermissions("platform.sms.send.log.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				smsSendLogService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				smsSendLogService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sms.send.log")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", smsSendLogService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sms/send/log/detail";
    }

}
