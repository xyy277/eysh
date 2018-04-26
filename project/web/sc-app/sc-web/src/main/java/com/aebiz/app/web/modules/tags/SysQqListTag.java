package com.aebiz.app.web.modules.tags;

import com.aebiz.app.sys.modules.services.SysQqService;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zyang on 2017/9/16.
 */
@Component
@Scope("prototype")
public class SysQqListTag extends GeneralVarTagBinding {
    @Autowired
    private SysQqService sysQqService;

    @Override
    public void render() {
        this.binds(sysQqService.query(Cnd.where("delFlag","=",false)));
        this.doBodyRender();
    }

}
