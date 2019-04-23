package com.aebiz.app.web.modules.controllers.platform.tourist;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.tourist.modules.models.Tour_guest;
import com.aebiz.app.tourist.modules.services.TourGuestService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/platform/tourist/guest")
public class TourGuestController {
    private static final Log log = Logs.get();
    @Autowired
	private TourGuestService tourGuestService;

    @RequestMapping("")
    @RequiresPermissions("eysh.tour.guest")
	public String index() {
		return "pages/platform/tourist/guest/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("eysh.tour.guest")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return tourGuestService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("eysh.tour.guest")
    public String add() {
    	return "pages/platform/tourist/guest/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Tour_guest")
    @RequiresPermissions("eysh.tour.guest.add")
    public Object addDo(Tour_guest tourGuest, HttpServletRequest req) {
		try {
			tourGuestService.insert(tourGuest);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("eysh.tour.guest")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", tourGuestService.fetch(id));
		return "pages/platform/tourist/guest/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Tour_guest")
    @RequiresPermissions("eysh.tour.guest.edit")
    public Object editDo(Tour_guest tourGuest, HttpServletRequest req) {
		try {
            tourGuest.setOpBy(StringUtil.getUid());
			tourGuest.setOpAt((int) (System.currentTimeMillis() / 1000));
			tourGuestService.updateIgnoreNull(tourGuest);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Tour_guest")
    @RequiresPermissions("eysh.tour.guest.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				tourGuestService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				tourGuestService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("eysh.tour.guest")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", tourGuestService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/tourist/guest/detail";
    }

}
