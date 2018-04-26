package com.aebiz.app.web.modules.controllers.platform.sms;

import com.aebiz.app.sys.modules.models.Sys_dict;
import com.aebiz.app.sys.modules.services.SysDictService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sms.modules.models.Sms_mould_info;
import com.aebiz.app.sms.modules.services.SmsMouldInfoService;
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
import java.util.List;

@Controller
@RequestMapping("/platform/sms/mould/info")
public class SmsMouldInfoController {
    private static final Log log = Logs.get();
    @Autowired
	private SmsMouldInfoService smsMouldInfoService;

    @Autowired
    private SysDictService sysDictService;

    @RequestMapping("")
    @RequiresPermissions("platform.sms.mould.info")
	public String index(HttpServletRequest req) {
        req.setAttribute("mouldTypeList", sysDictService.getSubListByCode("sms_mould_type"));
		return "pages/platform/sms/mould/info/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sms.mould.info")
    public Object data(DataTable dataTable,HttpServletRequest req) {
		Cnd cnd = Cnd.NEW();
		String name=req.getParameter("name");
        if (!Strings.isBlank(name)) cnd.and("name", "like", "%"+name+"%");
        cnd.and("delFlag","=",0);
    	return smsMouldInfoService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sms.mould.info")
    public String add( HttpServletRequest req) {
        List<Sys_dict> i=sysDictService.getSubListByCode("sms_mould_type");
        req.setAttribute("mouldTypeList", i);
    	return "pages/platform/sms/mould/info/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sms_mould_info")
    @RequiresPermissions("platform.sms.mould.info.add")
    public Object addDo(Sms_mould_info smsMouldInfo, HttpServletRequest req) {
		try {
		    String code=smsMouldInfo.getCode();
		    Cnd cnd=Cnd.NEW();
		    cnd.and("code","=",code);
		    cnd.and("delFlag","=",0);
            List<Sms_mould_info> sms_mould_info=smsMouldInfoService.query(cnd);
            if(sms_mould_info.size()!=0){
                return Result.error("globals.result.error");
            }
			smsMouldInfoService.insert(smsMouldInfo);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sms.mould.info")
    public String edit(@PathVariable String id,HttpServletRequest req) {
        Sms_mould_info obj=smsMouldInfoService.fetch(id);
        List<Sys_dict> i=sysDictService.getSubListByCode("sms_mould_type");
		req.setAttribute("obj", obj);
        req.setAttribute("mouldTypeList", i);
		return "pages/platform/sms/mould/info/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sms_mould_info")
    @RequiresPermissions("platform.sms.mould.info.edit")
    public Object editDo(Sms_mould_info smsMouldInfo, HttpServletRequest req) {
		try {
            smsMouldInfo.setOpBy(StringUtil.getUid());
			smsMouldInfo.setOpAt((int) (System.currentTimeMillis() / 1000));
			smsMouldInfoService.updateIgnoreNull(smsMouldInfo);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sms_mould_info")
    @RequiresPermissions("platform.sms.mould.info.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				smsMouldInfoService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				smsMouldInfoService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sms.mould.info")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
		    Sms_mould_info obj=smsMouldInfoService.fetch(id);
            req.setAttribute("obj", obj);
            req.setAttribute("mouldType", sysDictService.fetch(obj.getType()).getName());
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sms/mould/info/detail";
    }

}
