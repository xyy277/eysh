package com.aebiz.app.accuser.modules.services;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.baseframework.base.service.BaseService;

import java.util.Collection;
import java.util.List;

public interface ScAccountLinkService extends BaseService<Sc_account_link> {

    /**
     * 根据服务机构id,获取该服务机构下面所有的账户ids
     *
     * @param serviceOrgId 服务机构id
     * @return
     */
    public List<String> getServiceOrgAccountIds(String serviceOrgId);

    void addDo(Sc_account_user scAccountUser);

    Sc_account_link addDo(Sc_account_user user, Integer type);


    /**
     * 校验用户账户信息完整度
     * 返回true表示信息已经填写完整
     * 返回false表示个人信息需要完善
     *
     * @param accountId
     * @return
     */
    boolean checkFullStatus(String accountId);

}
