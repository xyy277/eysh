package com.aebiz.app.web.modules.controllers.platform.tourist;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.tourist.modules.models.Tour_linkman;
import com.aebiz.app.tourist.modules.services.TourLinkmanService;
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
@RequestMapping("/platform/tourist/linkman")
public class TourLinkmanController {
    private static final Log log = Logs.get();
    @Autowired
	private TourLinkmanService tourLinkmanService;

    @RequestMapping("")
    @RequiresPermissions("eysh.tour.linkman")
	public String index() {
		return "pages/platform/tourist/linkman/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("eysh.tour..linkman")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return tourLinkmanService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("eysh.tour.linkman")
    public String add() {
    	return "pages/platform/tourist/linkman/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Tour_linkman")
    @RequiresPermissions("eysh.tour.linkman.add")
    public Object addDo(Tour_linkman tourLinkman, HttpServletRequest req) {
		try {
			tourLinkmanService.insert(tourLinkman);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("eysh.tour.linkman")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", tourLinkmanService.fetch(id));
		return "pages/platform/tourist/linkman/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Tour_linkman")
    @RequiresPermissions("eysh.tour.linkman.edit")
    public Object editDo(Tour_linkman tourLinkman, HttpServletRequest req) {
		try {
            tourLinkman.setOpBy(StringUtil.getUid());
			tourLinkman.setOpAt((int) (System.currentTimeMillis() / 1000));
			tourLinkmanService.updateIgnoreNull(tourLinkman);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Tour_linkman")
    @RequiresPermissions("platform.tourist.linkman.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				tourLinkmanService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				tourLinkmanService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.tourist.linkman")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", tourLinkmanService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/tourist/linkman/detail";
    }

}
