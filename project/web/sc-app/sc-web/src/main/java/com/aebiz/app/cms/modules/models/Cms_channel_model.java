package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/8 14:49
 * 站点、栏目、模型关联表
 */
@Table("cms_channel_model")
public class Cms_channel_model<Record> extends BaseModel implements Serializable {
    @Column
    @Comment("站点id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String site_id;
    @Column
    @Comment("栏目id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String channel_id;
    @Column
    @Comment("模型id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String model_id;
    @Column
    @Comment("模型内容")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String tpl_content;

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getTpl_content() {
        return tpl_content;
    }

    public void setTpl_content(String tpl_content) {
        this.tpl_content = tpl_content;
    }
}
