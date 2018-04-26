package com.aebiz.app.sys.modules.services;

import com.aebiz.app.sys.modules.models.Sys_log;
import com.aebiz.baseframework.base.service.BaseService;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface SysLogService extends BaseService<Sys_log> {

    void dropLogTable();
}
