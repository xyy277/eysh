package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/21.
 */
@Table("tour_journey_check")
public class Tour_journey_check extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 待审核
     */
    public static final Integer WAIT_CHECK_STATUS = 0;

    /**
     * 审核通过
     */
    public static final Integer CHECK_STATUS = 1;

    /**
     * 审核不通过
     */
    public static final Integer UN_CHECK_STATUS = 2;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("游记id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String journey_id;

    @Column
    @Comment("审核时间")
    @ColDefine(type = ColType.INT,width =32)
    private Integer check_time;

    @Column
    @Comment("审核状态")
    @ColDefine(type = ColType.INT)
    private Integer check_status;//0:审核不通过；1:审核通过

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(String journey_id) {
        this.journey_id = journey_id;
    }

    public Integer getCheck_time() {
        return check_time;
    }

    public void setCheck_time(Integer check_time) {
        this.check_time = check_time;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }
}
