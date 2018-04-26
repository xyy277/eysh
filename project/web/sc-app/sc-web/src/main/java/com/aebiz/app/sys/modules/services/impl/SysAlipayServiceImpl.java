package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sys.modules.models.Sys_alipay;
import com.aebiz.app.sys.modules.services.SysAlipayService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysAlipayServiceImpl extends BaseServiceImpl<Sys_alipay> implements SysAlipayService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
