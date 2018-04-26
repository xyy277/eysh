package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.Cms_workflow;
import com.aebiz.app.cms.modules.models.Cms_workflow_info;
import com.aebiz.app.cms.modules.services.CmsWorkflowInfoService;
import com.aebiz.app.cms.modules.services.CmsWorkflowService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.DB;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/platform/cms/workflow")
public class CmsWorkflowController {
    private static final Log log = Logs.get();
    @Autowired
	private CmsWorkflowService cmsWorkflowService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CmsWorkflowInfoService cmsWorkflowInfoService;
    @RequestMapping("")
    @RequiresPermissions("platform.cms.workflow")
	public String index() {
		return "pages/platform/cms/workflow/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.workflow")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return cmsWorkflowService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.cms.workflow")
    public String add(HttpServletRequest req) {
        String userId=StringUtil.getUid();
        Sys_user user=sysUserService.fetch(userId);
        // TODO: 2017/8/3  
        // 判断是否是管理员权限，管理员取所有角色
        //Sql sql= Sqls.create("SELECT A.ID,CONCAT(if(B.NAME,CONCAT(B.Name,'-'),''), A.NAME) AS TNAME FROM SYS_ROLE A,SYS_UNIT B WHERE A.UNITID= B.ID AND ORDER BY A.ID ASC");
        String dbType=cmsWorkflowService.dao().getJdbcExpert().getDatabaseType();
        Sql sql=null;
        if(DB.ORACLE.name().equals(dbType)|| DB.DM.name().equals(dbType)){
            sql= Sqls.create("SELECT A.ID,CONCAT(decode(B.NAME,CONCAT(B.Name,'-'),''), A.NAME) AS TNAME FROM SYS_ROLE A,SYS_UNIT B WHERE A.UNITID= B.ID AND A.UNITID=@u ORDER BY A.ID ASC");
        }else{
            sql= Sqls.create("SELECT A.ID,CONCAT(if(B.NAME,CONCAT(B.Name,'-'),''), A.NAME) AS TNAME FROM SYS_ROLE A,SYS_UNIT B WHERE A.UNITID= B.ID AND A.UNITID=@u ORDER BY A.ID ASC");
        }
        sql.params().set("u", user.getUnitid());
        req.setAttribute("rolelist", sysUserService.list(sql));
    	return "pages/platform/cms/workflow/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Cms_workflow")
    @RequiresPermissions("platform.cms.workflow.add")
    public Object addDo(Cms_workflow cmsWorkflow, HttpServletRequest req) {
		try {
		    String signs=req.getParameter("signs");
		    String roles=req.getParameter("roles");
            List<Cms_workflow_info> list=this.getWorkInfos(cmsWorkflow,roles,signs);
            cmsWorkflowService.addDo(cmsWorkflow,list);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.cms.workflow")
    public String edit(@PathVariable String id,HttpServletRequest req) {
        String userId=StringUtil.getUid();
        Sys_user user=sysUserService.getField("id",userId);
        Sql sql=null;
        String dbType=cmsWorkflowService.dao().getJdbcExpert().getDatabaseType();
        if(DB.ORACLE.name().equals(dbType)|| DB.DM.name().equals(dbType)){
            sql= Sqls.create("SELECT A.ID,CONCAT(decode(B.NAME,CONCAT(B.Name,'-'),''), A.NAME) AS TNAME FROM SYS_ROLE A,SYS_UNIT B WHERE A.UNITID= B.ID AND A.UNITID=@u ORDER BY A.ID ASC");
        }else{
            sql= Sqls.create("SELECT A.ID,CONCAT(if(B.NAME,CONCAT(B.Name,'-'),''), A.NAME) AS TNAME FROM SYS_ROLE A,SYS_UNIT B WHERE A.UNITID= B.ID AND A.UNITID=@u ORDER BY A.ID ASC");
        }
        sql.params().set("u", user.getUnitid());

        Condition cnd= Cnd.where("flowid", "=", id).asc("STEP");
        req.setAttribute("infolist",cmsWorkflowInfoService.query(cnd) );
        req.setAttribute("rolelist", sysUserService.list(sql));
		req.setAttribute("obj", cmsWorkflowService.fetch(id));
		return "pages/platform/cms/workflow/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Cms_workflow")
    @RequiresPermissions("platform.cms.workflow.edit")
    public Object editDo(Cms_workflow cmsWorkflow, HttpServletRequest req) {
		try {
            String signs=req.getParameter("signs");
            String roles=req.getParameter("roles");
            cmsWorkflow.setOpBy(StringUtil.getUid());
			cmsWorkflow.setOpAt((int) (System.currentTimeMillis() / 1000));
            List<Cms_workflow_info> infolist=cmsWorkflowInfoService.query(Cnd.where("flowid", "=", cmsWorkflow.getId()).asc("STEP"));
            List<Cms_workflow_info> list=this.getWorkInfos(cmsWorkflow,roles,signs);
            cmsWorkflowService.editDo(infolist,cmsWorkflow,list);
            return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Cms_workflow")
    @RequiresPermissions("platform.cms.workflow.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false) String ids, HttpServletRequest req) {
		try {
            if(ids!=null){
                String ids1[]=StringUtils.split(ids,",");
                if(ids1.length>0){
                    cmsWorkflowService.delete(ids1);
                    req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids1));
                }
            }else{
                cmsWorkflowService.delete(id);
                req.setAttribute("id", id);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
    /**
     * 组织从页面获取的工作流步骤信息
     * @param cmsWorkflow
     * @param roles
     * @param signs
     * @return
     */
    private List<Cms_workflow_info> getWorkInfos(Cms_workflow cmsWorkflow,String roles,String signs){
        if(!"".equals(com.aebiz.app.web.commons.utils.StringUtil.null2String(roles))&& com.aebiz.app.web.commons.utils.StringUtil.null2String(roles).length()>1){
            List<Cms_workflow_info> list=new ArrayList<>();
            String[] role=roles.substring(0).split(",");
            String[] sign=signs.substring(0).split(",");
            for(int i=0;i<role.length;i++){
                if(StringUtils.isNotEmpty(role[i])){
                    Cms_workflow_info info=new Cms_workflow_info();
                    info.setFlowid(cmsWorkflow.getId());
                    info.setRoleid(com.aebiz.app.web.commons.utils.StringUtil.null2String(role[i]));
                    info.setStep(i);
                    info.setType(Integer.parseInt(sign[i]));
                    info.setDelFlag(false);
                    list.add(info);
                }
            }
            return list;
        }
        return  null;
    }
}
