package com.aebiz.app.sms.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sms.modules.models.Sms_mould_info;
import com.aebiz.app.sms.modules.services.SmsMouldInfoService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SmsMouldInfoServiceImpl extends BaseServiceImpl<Sms_mould_info> implements SmsMouldInfoService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
