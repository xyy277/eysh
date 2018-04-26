package com.aebiz.app.web.modules.controllers.platform.sms;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sms.modules.models.Sms_tele_info;
import com.aebiz.app.sms.modules.services.SmsTeleInfoService;
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
@RequestMapping("/platform/sms/tele/info")
public class SmsTeleInfoController {
    private static final Log log = Logs.get();
    @Autowired
	private SmsTeleInfoService smsTeleInfoService;

    @RequestMapping("")
    @RequiresPermissions("platform.sms.tele.info")
	public String index() {
		return "pages/platform/sms/tele/info/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sms.tele.info")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return smsTeleInfoService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sms.tele.info")
    public String add() {
    	return "pages/platform/sms/tele/info/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sms_tele_info")
    @RequiresPermissions("platform.sms.tele.info.add")
    public Object addDo(Sms_tele_info smsTeleInfo, HttpServletRequest req) {
		try {
            smsTeleInfo.setEnd_time(DateUtil.getTime(new Date()));
            smsTeleInfoService.insert(smsTeleInfo);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sms.tele.info")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", smsTeleInfoService.fetch(id));
		return "pages/platform/sms/tele/info/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sms_tele_info")
    @RequiresPermissions("platform.sms.tele.info.edit")
    public Object editDo(Sms_tele_info smsTeleInfo, HttpServletRequest req) {
		try {
            smsTeleInfo.setOpBy(StringUtil.getUid());
			smsTeleInfo.setOpAt((int) (System.currentTimeMillis() / 1000));
			smsTeleInfoService.updateIgnoreNull(smsTeleInfo);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sms_tele_info")
    @RequiresPermissions("platform.sms.tele.info.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				smsTeleInfoService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				smsTeleInfoService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sms.tele.info")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", smsTeleInfoService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sms/tele/info/detail";
    }

}
