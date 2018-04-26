package com.aebiz.app.sms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by zzhu on 2017/9/5.
 */
@Table("sms_tele_log_${id}")
public class Sms_tele_log extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("appid")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String appid;


    @Column
    @Comment("来源号码")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String fm;

    @Column
    @Comment("转移至号码")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String tm;

    @Column
    @Comment("绑定时间")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer bind_time;

    @Column
    @Comment("操作类型")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String do_type;

    @Column
    @Comment("虚拟号码")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String vm;

    @Column
    @Comment("场景")
    @ColDefine(type = ColType.INT, width = 5)
    private Integer scene;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public Integer getBind_time() {
        return bind_time;
    }

    public void setBind_time(Integer bind_time) {
        this.bind_time = bind_time;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public String getDo_type() {
        return do_type;
    }

    public void setDo_type(String do_type) {
        this.do_type = do_type;
    }
}
