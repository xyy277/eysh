package com.aebiz.app.accuser.modules.services.impl;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ScAccountLinkServiceImpl extends BaseServiceImpl<Sc_account_link> implements ScAccountLinkService {

    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    private static Log log = Logs.get();
    @Autowired
    private ScAccountUserService scAccountUserService;
    @Autowired
    private ScAccountLinkService scAccountLinkService;

    @Override
    public List<String> getServiceOrgAccountIds(String serviceOrgId) {
        List<String> accountIds = new ArrayList<>();
        List<Sc_account_link> linkList = scAccountLinkService.query(Cnd.where("table_id", "=", serviceOrgId));
        if (linkList != null && linkList.size() > 0) {
            for (Sc_account_link link : linkList) {
                accountIds.add(link.getAccount_id());
            }
            return accountIds;
        }
        return null;
    }

    @Override
    @Transactional
    public void addDo(Sc_account_user scAccountUser) {
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPasswordBase64 = new Sha256Hash(scAccountUser.getPassword(), salt, 1024).toBase64();
        scAccountUser.setSalt(salt);
        scAccountUser.setPassword(hashedPasswordBase64);
        scAccountUserService.insertOrUpdate(scAccountUser);
        Sc_account_link user = (Sc_account_link) SecurityUtils.getSubject().getPrincipal();
        Sc_account_link storeUser = new Sc_account_link();
        storeUser.setAccount_id(scAccountUser.getId());
        storeUser.setTable_id(user.getTable_id());
        storeUser.setType(3);
        scAccountLinkService.insert(storeUser);
    }

    @Override
    @Transactional
    public Sc_account_link addDo(Sc_account_user user, Integer type) {
        // 如果已经有用户了说明已经通过消息队列同步了用户
        Sc_account_user messUser = scAccountUserService.fetch(Cnd.where("loginname", "=", user.getLoginname()));
        Sc_account_link link = null;
        if (messUser != null) {
            user = messUser;
            link = scAccountLinkService.fetch(Cnd.where("account_id", "=", user.getId()));
            log.debug(String.format("从消息队列添加的用户：{%s}", user));
        }
        // 如果已经存在了link以及已经有了对应的基本信息说明资料都是完整的
        if (link == null) {
            link = new Sc_account_link();
        }
        if (link.getTable_id() != null) {
            link.setScAccountUser(user);
            return link;
        }
        // 说明没有用户或资料不完整，更新用户
        user = scAccountUserService.insertOrUpdate(user);
        log.debug(String.format("从本地添加的用户：{%s}", user));
        // 说明没有link，完善资料
        link.setType(type);
        link.setAccount_id(user.getId());
            // 创建资金账户

            // 设置默认角色
             return link;
    }

    /**
     * 校验用户账户信息完整度
     * 返回true表示信息已经填写完整
     * 返回false表示个人信息需要完善
     *
     * @param accountId
     * @return
     */
    @Override
    public boolean checkFullStatus(String accountId) {
        Sc_account_link scAccountLink = scAccountLinkService.fetch(Cnd.where("account_id", "=", accountId));
        if (scAccountLink == null) {
            return false;
        }
        return false;
    }


}
