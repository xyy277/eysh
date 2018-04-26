package com.aebiz.app.accuser.modules.services.impl;


import com.aebiz.app.accuser.modules.models.Sc_account_loginlog;
import com.aebiz.app.accuser.modules.services.ScAccountLoginlogService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ScAccountLoginlogServiceImpl extends BaseServiceImpl<Sc_account_loginlog> implements ScAccountLoginlogService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    @Override
    @Transactional
    public void doLogin(String accountId, String cookieSkuStr, String cookieNumStr, Sc_account_loginlog accountLogin) {
        this.insert(accountLogin);
    }
}
