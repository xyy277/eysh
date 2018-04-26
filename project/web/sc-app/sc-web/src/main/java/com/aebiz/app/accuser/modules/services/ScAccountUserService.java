package com.aebiz.app.accuser.modules.services;

import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.baseframework.base.service.BaseService;

/**
 * Created by wangcx on 2017/8/22.
 */
public interface ScAccountUserService extends BaseService<Sc_account_user> {

    Sc_account_user getAccount(String accountId);

    Sc_account_user getAccountByLoginname(String loginname);

    boolean checkUnique(String fieldName,String fieldValue);
}
