package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/14 9:22
 * 内容、栏目、站点关联表
 */
@Table("cms_content_channel")
public class Cms_content_channel extends BaseModel implements Serializable {
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;
    @Column
    @Comment("栏目id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String channel_id;
    @Column
    @Comment("站点id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String site_id;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }
}
