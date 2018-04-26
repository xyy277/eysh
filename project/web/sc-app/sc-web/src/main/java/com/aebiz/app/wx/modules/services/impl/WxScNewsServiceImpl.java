package com.aebiz.app.wx.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.wx.modules.models.Wx_sc_news;
import com.aebiz.app.wx.modules.services.WxScNewsService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxScNewsServiceImpl extends BaseServiceImpl<Wx_sc_news> implements WxScNewsService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
