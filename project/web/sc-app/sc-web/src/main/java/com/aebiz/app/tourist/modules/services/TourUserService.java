package com.aebiz.app.tourist.modules.services;

import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.tourist.modules.models.Tour_user;

public interface TourUserService extends BaseService<Tour_user>{

    Tour_user getTuoristByLoginname(String loginname);
}
