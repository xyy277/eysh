package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.cms.modules.models.Cms_acq_log;
import com.aebiz.app.cms.modules.services.CmsAcqLogService;
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
@RequestMapping("/platform/cms/acq/log")
public class CmsAcqLogController {
    private static final Log log = Logs.get();
    @Autowired
	private CmsAcqLogService cmsAcqLogService;

    @RequestMapping("")
    @RequiresPermissions("platform.cms.acq.log")
	public String index() {
		return "pages/platform/cms/acq/log/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.acq.log")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return cmsAcqLogService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.acq.log")
    public String add() {
    	return "pages/platform/cms/acq/log/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_acq_log")
    @RequiresPermissions("platform.cms.acq.log.add")
    public Object addDo(Cms_acq_log cmsAcqLog, HttpServletRequest req) {
		try {
			cmsAcqLogService.insert(cmsAcqLog);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.acq.log")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", cmsAcqLogService.fetch(id));
		return "pages/platform/cms/acq/log/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_acq_log")
    @RequiresPermissions("platform.cms.acq.log.edit")
    public Object editDo(Cms_acq_log cmsAcqLog, HttpServletRequest req) {
		try {
            cmsAcqLog.setOpBy(StringUtil.getUid());
			cmsAcqLog.setOpAt((int) (System.currentTimeMillis() / 1000));
			cmsAcqLogService.updateIgnoreNull(cmsAcqLog);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_acq_log")
    @RequiresPermissions("platform.cms.acq.log.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				cmsAcqLogService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				cmsAcqLogService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.cms.acq.log")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", cmsAcqLogService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/cms/acq/log/detail";
    }

}
