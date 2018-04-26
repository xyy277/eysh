package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/21.
 */
@Table("tour_journey_txt")
public class Tour_journey_txt extends BaseModel implements Serializable {

    @Column
    @Comment("游记id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String journey_id;

    @Column
    @Comment("游记内容")
    @ColDefine(type = ColType.TEXT)
    private String content;

    public String getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(String journey_id) {
        this.journey_id = journey_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
