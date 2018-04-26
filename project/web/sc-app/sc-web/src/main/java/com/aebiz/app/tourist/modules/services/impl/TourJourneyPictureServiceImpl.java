package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.tourist.modules.models.Tour_journey_picture;
import com.aebiz.app.tourist.modules.services.TourJourneyPictureService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TourJourneyPictureServiceImpl extends BaseServiceImpl<Tour_journey_picture> implements TourJourneyPictureService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
