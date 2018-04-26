package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sys.modules.models.Sys_file;
import com.aebiz.app.sys.modules.services.SysFileService;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysFileServiceImpl extends BaseServiceImpl<Sys_file> implements SysFileService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
}
