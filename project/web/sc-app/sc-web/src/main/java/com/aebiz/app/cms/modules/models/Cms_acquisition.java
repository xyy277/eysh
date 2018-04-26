package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/24 13:39
 * 数据采集
 */
@Table("cms_acquisition")
public class Cms_acquisition extends BaseModel implements Serializable {
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
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
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String user_id;
    @Column
    @Comment("采集名称")
    @ColDefine(type = ColType.VARCHAR, width = 150)
    private String acq_name;
    @Column
    @Comment("开始时间")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String start_time;
    @Column
    @Comment("结束时间")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String end_time;
    @Column
    @Comment("采集状态")
    @ColDefine(type = ColType.INT)
    private Integer status;
    @Column
    @Comment("")
    @ColDefine(type = ColType.INT)
    private Integer curr_num;
    @Column
    @Comment("")
    @ColDefine(type = ColType.INT)
    private Integer curr_item;
    @Column
    @Comment("")
    @ColDefine(type = ColType.INT)
    private Integer total_item;
    @Column
    @Comment("暂停时间：单位(毫秒)")
    @ColDefine(type = ColType.INT)
    private Integer pause_time;
    @Column
    @Comment("页面编码")
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String page_encoding;
    @Column
    @Comment("采集地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String plan_list;
    @Column
    @Comment("动态地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String dynamic_addr;
    @Column
    @Comment("动态地址页码开始")
    @ColDefine(type = ColType.INT)
    private Integer dynamic_start;
    @Column
    @Comment("动态地址页码结束")
    @ColDefine(type = ColType.INT)
    private Integer dynamic_end;
    @Column
    @Comment("内容地址集开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String linkset_start;
    @Column
    @Comment("内容地址集结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String linkset_end;
    @Column
    @Comment("内容地址开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String link_start;
    @Column
    @Comment("内容地址结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String link_end;
    @Column
    @Comment("标题开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String title_start;
    @Column
    @Comment("标题结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String title_end;
    @Column
    @Comment("描述开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String description_start;
    @Column
    @Comment("描述结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String description_end;
    @Column
    @Comment("内容开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String content_start;
    @Column
    @Comment("内容结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String content_end;
    @Column
    @Comment("内容地址补全url")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String content_prefix;
    @Column
    @Comment("图片地址补全url")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String img_prefix;
    @Column
    @Comment("浏览量开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String view_start;
    @Column
    @Comment("浏览量结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String view_end;
    @Column
    @Comment("浏览量访问地址开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String view_id_start;
    @Column
    @Comment("浏览量访问地址结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String view_id_end;
    @Column
    @Comment("")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String view_link;
    @Column
    @Comment("发布时间开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String releasetime_start;
    @Column
    @Comment("发布时间结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String releasetime_end;
    @Column
    @Comment("作者开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String author_start;
    @Column
    @Comment("作者结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String author_end;
    @Column
    @Comment("来源开始HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String origin_start;
    @Column
    @Comment("来源结束HTML")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String origin_end;
    @Column
    @Comment("发布时间格式")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String releasetime_format;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAcq_name() {
        return acq_name;
    }

    public void setAcq_name(String acq_name) {
        this.acq_name = acq_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCurr_num() {
        return curr_num;
    }

    public void setCurr_num(Integer curr_num) {
        this.curr_num = curr_num;
    }

    public Integer getCurr_item() {
        return curr_item;
    }

    public void setCurr_item(Integer curr_item) {
        this.curr_item = curr_item;
    }

    public Integer getTotal_item() {
        return total_item;
    }

    public void setTotal_item(Integer total_item) {
        this.total_item = total_item;
    }

    public Integer getPause_time() {
        return pause_time;
    }

    public void setPause_time(Integer pause_time) {
        this.pause_time = pause_time;
    }

    public String getPage_encoding() {
        return page_encoding;
    }

    public void setPage_encoding(String page_encoding) {
        this.page_encoding = page_encoding;
    }

    public String getPlan_list() {
        return plan_list;
    }

    public void setPlan_list(String plan_list) {
        this.plan_list = plan_list;
    }

    public String getDynamic_addr() {
        return dynamic_addr;
    }

    public void setDynamic_addr(String dynamic_addr) {
        this.dynamic_addr = dynamic_addr;
    }

    public Integer getDynamic_start() {
        return dynamic_start;
    }

    public void setDynamic_start(Integer dynamic_start) {
        this.dynamic_start = dynamic_start;
    }

    public Integer getDynamic_end() {
        return dynamic_end;
    }

    public void setDynamic_end(Integer dynamic_end) {
        this.dynamic_end = dynamic_end;
    }

    public String getLinkset_start() {
        return linkset_start;
    }

    public void setLinkset_start(String linkset_start) {
        this.linkset_start = linkset_start;
    }

    public String getLinkset_end() {
        return linkset_end;
    }

    public void setLinkset_end(String linkset_end) {
        this.linkset_end = linkset_end;
    }

    public String getLink_start() {
        return link_start;
    }

    public void setLink_start(String link_start) {
        this.link_start = link_start;
    }

    public String getLink_end() {
        return link_end;
    }

    public void setLink_end(String link_end) {
        this.link_end = link_end;
    }

    public String getTitle_start() {
        return title_start;
    }

    public void setTitle_start(String title_start) {
        this.title_start = title_start;
    }

    public String getTitle_end() {
        return title_end;
    }

    public void setTitle_end(String title_end) {
        this.title_end = title_end;
    }

    public String getDescription_start() {
        return description_start;
    }

    public void setDescription_start(String description_start) {
        this.description_start = description_start;
    }

    public String getDescription_end() {
        return description_end;
    }

    public void setDescription_end(String description_end) {
        this.description_end = description_end;
    }

    public String getContent_start() {
        return content_start;
    }

    public void setContent_start(String content_start) {
        this.content_start = content_start;
    }

    public String getContent_end() {
        return content_end;
    }

    public void setContent_end(String content_end) {
        this.content_end = content_end;
    }

    public String getContent_prefix() {
        return content_prefix;
    }

    public void setContent_prefix(String content_prefix) {
        this.content_prefix = content_prefix;
    }

    public String getImg_prefix() {
        return img_prefix;
    }

    public void setImg_prefix(String img_prefix) {
        this.img_prefix = img_prefix;
    }

    public String getView_start() {
        return view_start;
    }

    public void setView_start(String view_start) {
        this.view_start = view_start;
    }

    public String getView_end() {
        return view_end;
    }

    public void setView_end(String view_end) {
        this.view_end = view_end;
    }

    public String getView_id_start() {
        return view_id_start;
    }

    public void setView_id_start(String view_id_start) {
        this.view_id_start = view_id_start;
    }

    public String getView_id_end() {
        return view_id_end;
    }

    public void setView_id_end(String view_id_end) {
        this.view_id_end = view_id_end;
    }

    public String getView_link() {
        return view_link;
    }

    public void setView_link(String view_link) {
        this.view_link = view_link;
    }

    public String getReleasetime_start() {
        return releasetime_start;
    }

    public void setReleasetime_start(String releasetime_start) {
        this.releasetime_start = releasetime_start;
    }

    public String getReleasetime_end() {
        return releasetime_end;
    }

    public void setReleasetime_end(String releasetime_end) {
        this.releasetime_end = releasetime_end;
    }

    public String getAuthor_start() {
        return author_start;
    }

    public void setAuthor_start(String author_start) {
        this.author_start = author_start;
    }

    public String getAuthor_end() {
        return author_end;
    }

    public void setAuthor_end(String author_end) {
        this.author_end = author_end;
    }

    public String getOrigin_start() {
        return origin_start;
    }

    public void setOrigin_start(String origin_start) {
        this.origin_start = origin_start;
    }

    public String getOrigin_end() {
        return origin_end;
    }

    public void setOrigin_end(String origin_end) {
        this.origin_end = origin_end;
    }

    public String getReleasetime_format() {
        return releasetime_format;
    }

    public void setReleasetime_format(String releasetime_format) {
        this.releasetime_format = releasetime_format;
    }
}
