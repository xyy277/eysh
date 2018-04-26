package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sys.modules.models.Sys_subscribe;
import com.aebiz.app.sys.modules.services.SysSubscribeService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysSubscribeServiceImpl extends BaseServiceImpl<Sys_subscribe> implements SysSubscribeService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
