package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/21.
 */
@Table("tour_journey_picture")
public class Tour_journey_picture extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @Comment("图片存放路径")
    @ColDefine(type = ColType.VARCHAR,width =256)
    private String path;

    @Column
    @Comment("是否标题图")
    @ColDefine(type = ColType.VARCHAR,width =1)
    private String is_title;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIs_title() {
        return is_title;
    }

    public void setIs_title(String is_title) {
        this.is_title = is_title;
    }
}
