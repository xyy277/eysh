package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.cms.modules.models.Sc_notice;
import com.aebiz.app.cms.modules.services.ScNoticeService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/platform/cms/notice")
public class ScNoticeController {
    private static final Log log = Logs.get();
    @Autowired
	private ScNoticeService scNoticeService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("")
    @RequiresPermissions("platform.cms.notice")
	public String index() {
		return "pages/platform/cms/notice/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.notice")
    public Object data(DataTable dataTable,HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        String title= req.getParameter("title").trim();
        String pub_time1 = StringUtils.trimToEmpty(req.getParameter("pub_time1"));
        String pub_time2 = StringUtils.trimToEmpty(req.getParameter("pub_time2"));
        if (!Strings.isBlank(pub_time1)) {
            cnd.and("pub_time", ">=", pub_time1);
        }
        if (!Strings.isBlank(pub_time2)) {
            cnd.and("pub_time", "<=", pub_time2);
        }
        if (!Strings.isBlank(title)) cnd.and("title", "like", "%"+title+"%");
        return scNoticeService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.notice")
    public String add(HttpServletRequest req) {
        String userId=StringUtil.getUid();
        Sys_user user=sysUserService.fetch(userId);
        req.setAttribute("username", user.getUsername());
        return "pages/platform/cms/notice/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sc_notice")
    @RequiresPermissions("platform.cms.notice.add")
    public Object addDo(Sc_notice scNotice, HttpServletRequest req) {
		try {
            String time = DateUtil.getDateTime();
            scNotice.setAdd_time(time);
			scNoticeService.insert(scNotice);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.notice")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", scNoticeService.fetch(id));
		return "pages/platform/cms/notice/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sc_notice")
    @RequiresPermissions("platform.cms.notice.edit")
    public Object editDo(Sc_notice scNotice, HttpServletRequest req) {
		try {
            scNotice.setOpBy(StringUtil.getUid());
			scNotice.setOpAt((int) (System.currentTimeMillis() / 1000));
			scNoticeService.updateIgnoreNull(scNotice);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sc_notice")
    @RequiresPermissions("platform.cms.notice.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				scNoticeService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				scNoticeService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.cms.notice")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", scNoticeService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/cms/notice/detail";
    }

}
