package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.tourist.modules.models.Tour_user_concern;
import com.aebiz.app.tourist.modules.services.TourUserConcernService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TourUserConcernServiceImpl extends BaseServiceImpl<Tour_user_concern> implements TourUserConcernService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
