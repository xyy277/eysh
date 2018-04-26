package com.aebiz.app.accuser.modules.services.impl;

import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.Strings;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScAccountUserServiceImpl extends BaseServiceImpl<Sc_account_user> implements ScAccountUserService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    /**
     * 通过Id从缓存取用户数据
     *
     * @param Id
     * @return
     */
    @Cacheable(key = "'ACC'+#Id")
    public Sc_account_user getAccount(String Id) {
        return this.fetch(Cnd.where("Id", "=", Id));
    }

    /**
     * 通过loginname从缓存取用户数据
     *
     * @param loginname
     * @return
     */
    @Cacheable(key = "'LOGIN'+#loginname")
    public Sc_account_user getAccountByLoginname(String loginname) {
        return this.fetch(Cnd.where("loginname", "=", loginname).or("email", "=", loginname).or("mobile", "=", loginname).and("delflag","=",false));
    }

    /**
     * 清除帐号缓存,可能存在用户修改登录名,导致旧登录缓存无法清除掉,所以accCache要设置失效时间
     * @param id
     */
    @Async
    public void clearAccount(String id) {
        this.clearGetAccount(id);
        Sc_account_user accountUser = this.getAccount(id);
        this.clearGetAccountByLoginname(accountUser.getLoginname());
        this.clearGetAccountByLoginname(accountUser.getEmail());
        this.clearGetAccountByLoginname(accountUser.getMobile());
    }

    @CacheEvict(key = "'ACC'+#id")
    public void clearGetAccount(String id) {

    }

    @CacheEvict(key = "'LOGIN'+#loginname")
    public void clearGetAccountByLoginname(String loginname) {

    }

    /**
     * 检测字段的唯一性
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     *
     * @return 存在返回true，不存在返回false
     */
    public boolean checkUnique(String fieldName, String fieldValue) {
        if (!Strings.isEmpty(fieldName) && !Strings.isEmpty(fieldValue)) {
            Sc_account_user accountUser = this.fetch(Cnd.where(fieldName, "=", fieldValue));
            if (accountUser != null) {
                return true;
            }
        }
        return false;
    }

}
