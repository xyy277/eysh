package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.tourist.modules.models.Tour_linkman;
import com.aebiz.app.tourist.modules.services.TourLinkmanService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TourLinkmanServiceImpl extends BaseServiceImpl<Tour_linkman> implements TourLinkmanService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
