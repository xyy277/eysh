package com.aebiz.app.cms.modules.services;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_log;

public interface CmsLogService extends BaseService<Cms_log>{
    /**
     * 记录操作日志
     * @param content_id 内容id
     * @param category
     * @param site_id 站点id
     * @param title 操作标题
     * @param content 操作内容
     * @param user 操作人
     * @param ip 用户ip
     * @param resUrl 请求地址
     */
    public void info(String content_id, int category, String site_id, String title, String content, Sys_user user, String ip, String resUrl);

    }
