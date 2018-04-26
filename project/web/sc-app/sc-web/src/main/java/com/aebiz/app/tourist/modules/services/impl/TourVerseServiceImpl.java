package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.tourist.modules.models.Tour_Verse;
import com.aebiz.app.tourist.modules.services.TourVerseService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TourVerseServiceImpl extends BaseServiceImpl<Tour_Verse> implements TourVerseService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
