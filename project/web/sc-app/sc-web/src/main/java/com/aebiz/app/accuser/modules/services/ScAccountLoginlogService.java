package com.aebiz.app.accuser.modules.services;

import com.aebiz.app.accuser.modules.models.Sc_account_loginlog;
import com.aebiz.baseframework.base.service.BaseService;

public interface ScAccountLoginlogService extends BaseService<Sc_account_loginlog> {

    void doLogin(String accountId, String cookieSkuStr, String cookieNumStr, Sc_account_loginlog accountLogin);
}
