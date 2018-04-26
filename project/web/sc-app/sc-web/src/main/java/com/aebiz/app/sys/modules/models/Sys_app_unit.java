package com.aebiz.app.sys.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 应用与单位对应关系配置
 * Created by tony on 2017/8/29.
 */
@Table("sys_app_unit")
@PK({"unitId", "appId"})
public class Sys_app_unit extends BaseModel implements Serializable {

    @Column
    @ColDefine(type = ColType.VARCHAR, notNull = true, width = 32)
    @Comment("单位ID")
    private String unitId;

    @Column
    @ColDefine(type = ColType.VARCHAR, notNull = true, width = 32)
    @Comment("应用appId")
    private String appId;

    @One(field = "unitId")
    private Sys_unit unit;

    @One(field = "appId", key = "appId")
    private Sys_api app;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Sys_unit getUnit() {
        return unit;
    }

    public void setUnit(Sys_unit unit) {
        this.unit = unit;
    }

    public Sys_api getApp() {
        return app;
    }

    public void setApp(Sys_api app) {
        this.app = app;
    }
}
