package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sys.modules.models.Sys_qq;
import com.aebiz.app.sys.modules.services.SysQqService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysQqServiceImpl extends BaseServiceImpl<Sys_qq> implements SysQqService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
