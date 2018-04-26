package com.aebiz.app.cms.modules.services;

import com.aebiz.app.cms.modules.models.Cms_workflow_info;
import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_workflow;

import java.util.List;

public interface CmsWorkflowService extends BaseService<Cms_workflow>{
    /**
     * 新增工作流信息
     * @param cmsWorkflow 工作流主表Model
     * @param list 工作量步骤明细表
     */
    public void addDo(Cms_workflow cmsWorkflow,List<Cms_workflow_info> list);
    /**
     * 修改工作流信息
     * @param infolist 删除的工作量步骤明细表
     * @param cmsWorkflow 工作流主表Model
     * @param list 工作量步骤明细表
     */
    public void editDo(List<Cms_workflow_info> infolist,Cms_workflow cmsWorkflow,List<Cms_workflow_info> list);
    /**
     * 批量删除或单个删除
     * @param ids
     * @param id
     */
    public void delete(String[] ids, String id);
}
