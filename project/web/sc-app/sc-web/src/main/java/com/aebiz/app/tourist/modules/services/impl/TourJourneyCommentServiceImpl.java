package com.aebiz.app.tourist.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.tourist.modules.models.Tour_journey_comment;
import com.aebiz.app.tourist.modules.services.TourJourneyCommentService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TourJourneyCommentServiceImpl extends BaseServiceImpl<Tour_journey_comment> implements TourJourneyCommentService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
