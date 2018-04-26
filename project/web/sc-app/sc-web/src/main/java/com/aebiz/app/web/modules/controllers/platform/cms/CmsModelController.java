package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.cms.modules.models.Cms_model;
import com.aebiz.app.cms.modules.services.CmsModelService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/platform/cms/model")
public class CmsModelController {
    private static final Log log = Logs.get();
    @Autowired
	private CmsModelService cmsModelService;

    @RequestMapping("")
    @RequiresPermissions("platform.cms.model")
	public String index() {
		return "pages/platform/cms/model/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.model")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return cmsModelService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.model")
    public String add() {
    	return "pages/platform/cms/model/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_model")
    @RequiresPermissions("platform.cms.model.add")
    public Object addDo(Cms_model cmsModel, HttpServletRequest req) {
		try {
			cmsModelService.insert(cmsModel);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.model")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", cmsModelService.fetch(id));
		return "pages/platform/cms/model/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_model")
    @RequiresPermissions("platform.cms.model.edit")
    public Object editDo(Cms_model cmsModel, HttpServletRequest req) {
		try {
            cmsModel.setOpBy(StringUtil.getUid());
			cmsModel.setOpAt((int) (System.currentTimeMillis() / 1000));
			cmsModelService.updateIgnoreNull(cmsModel);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_model")
    @RequiresPermissions("platform.cms.model.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(required = false) String ids, HttpServletRequest req) {
		try {
			if(ids!=null){
                String ids1[]=StringUtils.split(ids,",");
                if(ids1.length>0){
                    cmsModelService.delete(ids1);
                    req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids1));
                }
			}else{
				cmsModelService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/sort")
    @RequiresPermissions("platform.cms.model")
    public String sort(HttpServletRequest req) {
        List<Cms_model> list = cmsModelService.query(Cnd.orderBy().asc("location"));
        req.setAttribute("firstMenus", list);
        return "pages/platform/cms/model/sort";
    }

    @RequestMapping(value = "/sortDo/{ids}")
    @SJson
    @RequiresPermissions("platform.cms.model.sort")
    public Object sortDo(@PathVariable String ids, HttpServletRequest req) {
        try {
            String[] menuIds = StringUtils.split(ids, ",");
            int i = 0;
            cmsModelService.dao().execute(Sqls.create("update cms_model set location=0"));
            for (String s : menuIds) {
                if (!Strings.isBlank(s)) {
                    cmsModelService.update(org.nutz.dao.Chain.make("location", i), Cnd.where("id", "=", s));
                    i++;
                }
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    @RequestMapping(value = "/list")
    @SJson
    @RequiresPermissions("platform.cms.model")
    public Object list(){
        return cmsModelService.query(Cnd.orderBy().asc("location"));
    }
}
