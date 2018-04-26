package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.TourUserService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TourUserServiceImpl extends BaseServiceImpl<Tour_user> implements TourUserService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    /**
     * 通过loginname从缓存取用户数据
     *
     * @param loginname
     * @return
     */
    @Cacheable(key = "'LOGIN'+#loginname")
    public Tour_user getTuoristByLoginname(String loginname){
        return this.fetch(Cnd.where("loginname", "=", loginname).or("mobile", "=", loginname).or("email","=",loginname).and("delflag","=",false));
    }
}
