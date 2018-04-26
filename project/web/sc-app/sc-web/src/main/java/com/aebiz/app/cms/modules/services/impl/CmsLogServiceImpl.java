package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.cms.modules.models.Cms_log;
import com.aebiz.app.cms.modules.services.CmsLogService;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CmsLogServiceImpl extends BaseServiceImpl<Cms_log> implements CmsLogService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    public void info(String content_id,int category,String site_id,String title,String content,Sys_user user,String ip,String resUrl){
        Cms_log log=new Cms_log();
        log.setSite_id(site_id);
        log.setUser_id(user.getId());
        log.setIp(ip);
        log.setCategory(category);
        log.setContent(content);
        log.setTitle(title);
        log.setUrl( StringUtil.null2String(resUrl));
        log.setLog_time(DateUtil.getDateTime());
        log.setContent_id(content_id);
        this.insert(log);
    }
}
