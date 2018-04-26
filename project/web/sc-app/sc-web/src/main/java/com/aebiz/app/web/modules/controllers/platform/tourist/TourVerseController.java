package com.aebiz.app.web.modules.controllers.platform.tourist;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.tourist.modules.models.Tour_Verse;
import com.aebiz.app.tourist.modules.services.TourVerseService;
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
@RequestMapping("/platform/tourist/verse")
public class TourVerseController {
    private static final Log log = Logs.get();
    @Autowired
	private TourVerseService tourVerseService;

    @RequestMapping("")
    @RequiresPermissions("eysh.tour.verse")
	public String index() {
		return "pages/platform/tourist/verse/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("eysh.tour.verse")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return tourVerseService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("eysh.tour.verse")
    public String add() {
    	return "pages/platform/tourist/verse/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Tour_Verse")
    @RequiresPermissions("eysh.tour.verse.add")
    public Object addDo(Tour_Verse tourVerse, HttpServletRequest req) {
		try {
			tourVerseService.insert(tourVerse);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("eysh.tour.verse")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", tourVerseService.fetch(id));
		return "pages/platform/tourist/verse/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Tour_Verse")
    @RequiresPermissions("eysh.tour.verse.edit")
    public Object editDo(Tour_Verse tourVerse, HttpServletRequest req) {
		try {
            tourVerse.setOpBy(StringUtil.getUid());
			tourVerse.setOpAt((int) (System.currentTimeMillis() / 1000));
			tourVerseService.updateIgnoreNull(tourVerse);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Tour_Verse")
    @RequiresPermissions("eysh.tour.verse.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				tourVerseService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				tourVerseService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("eysh.tour.verse")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", tourVerseService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/tourist/verse/detail";
    }

}
