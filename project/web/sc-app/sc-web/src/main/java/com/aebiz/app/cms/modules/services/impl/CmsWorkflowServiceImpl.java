package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.models.Cms_workflow_info;
import com.aebiz.app.cms.modules.services.CmsWorkflowInfoService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_workflow;
import com.aebiz.app.cms.modules.services.CmsWorkflowService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CmsWorkflowServiceImpl extends BaseServiceImpl<Cms_workflow> implements CmsWorkflowService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    @Autowired
    private CmsWorkflowInfoService cmsWorkflowInfoService;
    @Override
    @Transactional
    public void delete(String[] ids, String id) {
        if(ids!=null&&ids.length>0){
            this.delete(ids);
            this.deleteCmsWorkflowInfo(Cnd.where("flowid", "in", ids));
        }else{
            this.deleteCmsWorkflowInfo(Cnd.where("flowid", "=", id));
            this.delete(id);
        }
    }

    @Override
    @Transactional
    public void editDo(List<Cms_workflow_info> infolist, Cms_workflow cmsWorkflow, List<Cms_workflow_info> list) {
        this.updateIgnoreNull(cmsWorkflow);
        if(infolist!=null && infolist.size()>0){
            String[] infoIds=new String[infolist.size()];
            for(int i=0;i<infolist.size();i++){
                infoIds[i]=infolist.get(i).getId();
            }
            cmsWorkflowInfoService.delete(infoIds);
        }
        if(list!=null && list.size()>0){
            cmsWorkflowInfoService.insert(list);
        }
    }

    @Override
    @Transactional
    public void addDo(Cms_workflow cmsWorkflow, List<Cms_workflow_info> list) {
        Cms_workflow res=this.insert(cmsWorkflow);
        if(res!=null && list!=null && list.size()>0){
            for(Cms_workflow_info info: list){
                info.setFlowid(res.getId());
            }
            cmsWorkflowInfoService.insert(list);
        }
    }

    /**
     * 删除子表信息
     */
    private void deleteCmsWorkflowInfo(Cnd cnd){
        List<Cms_workflow_info> infolist=cmsWorkflowInfoService.query(cnd);
        if(infolist!=null && infolist.size()>0){
            String[] infoIds=new String[infolist.size()];
            for(int i=0;i<infolist.size();i++){
                infoIds[i]=infolist.get(i).getId();
            }
            cmsWorkflowInfoService.delete(infoIds);
        }
    }
}
