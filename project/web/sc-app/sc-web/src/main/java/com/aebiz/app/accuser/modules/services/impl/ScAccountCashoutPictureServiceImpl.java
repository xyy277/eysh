package com.aebiz.app.accuser.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.accuser.modules.models.Sc_account_cashout_picture;
import com.aebiz.app.accuser.modules.services.ScAccountCashoutPictureService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScAccountCashoutPictureServiceImpl extends BaseServiceImpl<Sc_account_cashout_picture> implements ScAccountCashoutPictureService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
