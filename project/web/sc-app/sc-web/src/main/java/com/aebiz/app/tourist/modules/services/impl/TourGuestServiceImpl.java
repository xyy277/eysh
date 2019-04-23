package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.app.tourist.modules.models.Tour_guest;
import com.aebiz.app.tourist.modules.services.TourGuestService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*******************************
 * Copyright (C),2018-2099, ZJJ
 * Title : 
 * File name : TourGuestServiceImpl
 * Author : zhoujiajun
 * Date : 2019/4/22 10:52
 * Version : 1.0
 * Description : 
 ******************************/
@Service
public class TourGuestServiceImpl extends BaseServiceImpl<Tour_guest> implements TourGuestService {

    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
