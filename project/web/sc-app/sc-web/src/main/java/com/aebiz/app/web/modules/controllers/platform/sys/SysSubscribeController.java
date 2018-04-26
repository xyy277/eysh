package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SFile;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sys.modules.models.Sys_subscribe;
import com.aebiz.app.sys.modules.services.SysSubscribeService;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.app.web.commons.utils.ScExcelUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/platform/sys/subscribe")
public class SysSubscribeController {
    private static final Log log = Logs.get();
    @Autowired
	private SysSubscribeService sysSubscribeService;

    @RequestMapping("")
    @RequiresPermissions("platform.sys.subscribe")
	public String index() {
		return "pages/platform/sys/subscribe/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sys.subscribe")
    public Object data(DataTable dataTable, HttpServletRequest req) {
		Cnd cnd = Cnd.NEW();
        String org_name = req.getParameter("org_name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String startAt = req.getParameter("startAt");
        String endAt = req.getParameter("endAt");
        String title = req.getParameter("titles");
        String column = req.getParameter("columns");

        if (!Strings.isBlank(org_name)) cnd.and("org_name", "like", "%" + org_name + "%");
        if (!Strings.isBlank(phone)) cnd.and("phone", "like", "%" + phone + "%");
        if (!Strings.isBlank(email)) cnd.and("email", "like", "%" + email + "%");
        if (!Strings.isBlank(startAt)){
            cnd.and("opAt", ">=", Times.ams(startAt+" 00:00:00")/1000 );
        }
        if (!Strings.isBlank(endAt)){
            cnd.and("opAt", "<=",  Times.ams(endAt+" 23:59:59")/1000);
        }
    	return sysSubscribeService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sys.subscribe")
    public String add() {
    	return "pages/platform/sys/subscribe/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sys_subscribe")
    @RequiresPermissions("platform.sys.subscribe.add")
    public Object addDo(Sys_subscribe sysSubscribe, HttpServletRequest req) {
		try {
			sysSubscribeService.insert(sysSubscribe);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sys.subscribe")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", sysSubscribeService.fetch(id));
		return "pages/platform/sys/subscribe/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sys_subscribe")
    @RequiresPermissions("platform.sys.subscribe.edit")
    public Object editDo(Sys_subscribe sysSubscribe, HttpServletRequest req) {
		try {
            sysSubscribe.setOpBy(StringUtil.getUid());
			sysSubscribe.setOpAt((int) (System.currentTimeMillis() / 1000));
			sysSubscribeService.updateIgnoreNull(sysSubscribe);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sys_subscribe")
    @RequiresPermissions("platform.sys.subscribe.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				sysSubscribeService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				sysSubscribeService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sys.subscribe")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
		    Sys_subscribe info = sysSubscribeService.fetch(id);
            req.setAttribute("obj", sysSubscribeService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sys/subscribe/detail";
    }


    /**
     * 导出服务对象查询列表信息
     *
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/expExcel"})
    @SFile
    @RequiresPermissions("platform.sys.subscribe.exp")
    public File expExcel(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Cnd cnd = Cnd.NEW();

        String org_name = req.getParameter("org_name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String startAt = req.getParameter("startAt");
        String endAt = req.getParameter("endAt");
        String title = req.getParameter("titles");
        String column = req.getParameter("columns");

        if (!Strings.isBlank(org_name)) cnd.and("org_name", "like", "%" + org_name + "%");
        if (!Strings.isBlank(phone)) cnd.and("phone", "like", "%" + phone + "%");
        if (!Strings.isBlank(email)) cnd.and("email", "like", "%" + email + "%");
        if (!Strings.isBlank(startAt)){
            cnd.and("opAt", ">=", Times.ams(startAt+" 00:00:00")/1000 );
        }
        if (!Strings.isBlank(endAt)){
            cnd.and("opAt", "<=",  Times.ams(endAt+" 23:59:59")/1000);
        }
        // 增加访问权限过滤
        List<Sys_subscribe> list = sysSubscribeService.query(cnd);

        String fname = "订阅客户信息表";
        String[] titles = title.split(",");
        String[] columns = column.split(",");

        List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();


        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            Sys_subscribe info = list.get(i);
            for (int j=0;j<columns.length;j++){
                if (columns[j].equals("org_name")){
                    map.put(titles[j], info.getOrg_name());
                }else if (columns[j].equals("email")){
                    map.put(titles[j], info.getEmail());
                }else if (columns[j].equals("phone")){
                    map.put(titles[j], info.getPhone());
                }else if (columns[j].equals("opAt")){
                    map.put(titles[j], DateUtil.getDate(info.getOpAt(),"yyyy-MM-dd"));
                }
            }
            maplist.add(map);
        }

        return ScExcelUtils.getExcel(req, res, fname, maplist, titles);
    }
}
