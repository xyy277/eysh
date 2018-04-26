package com.aebiz.app.sms.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sms.modules.models.Sms_tele_info;
import com.aebiz.app.sms.modules.services.SmsTeleInfoService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SmsTeleInfoServiceImpl extends BaseServiceImpl<Sms_tele_info> implements SmsTeleInfoService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
