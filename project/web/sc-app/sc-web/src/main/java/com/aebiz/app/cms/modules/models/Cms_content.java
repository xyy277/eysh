package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: jxx
 * @date: 2017/7/7 11:06
 * 文章主表
 */

@Table("cms_content")
public class Cms_content extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @Comment("专题id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String topic_id;
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
    @Comment("单位id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String unit_id;
    @Column
    @Comment("审核状态 -1：回收站")
    @ColDefine(type = ColType.INT)
    private Integer status;
    @Column
    @Comment("标题图片")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String has_title_img;
    @Column
    @Comment("排序时间")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String sort_time;
    @Column
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR, width = 80)
    private String title;
    @Column
    @Comment("外部链接")
    @ColDefine(type = ColType.VARCHAR, width = 150)
    private String link_;
    @Column
    @Comment("短标题")
    @ColDefine(type = ColType.VARCHAR, width = 60)
    private String short_title;
    @Column
    @Comment("短标题颜色")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String title_color;
    @Column
    @Comment("短标题是否加粗")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String is_bold;
    @Column
    @Comment("发布者")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String author;
    @Column
    @Comment("来源")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String origin;
    @Column
    @Comment("来源URL")
    @ColDefine(type = ColType.VARCHAR, width = 150)
    private String origin_url;
    @Column
    @Comment("摘要")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String description;
    @Column
    @Comment("多媒体")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String media_path;
    @Column
    @Comment("播放器")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String media_type;
    @Column
    @Comment("固顶级别")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String top_level;
    @Column
    @Comment("新增时间")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String add_time;
    @Column
    @Comment("发布时间")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String pub_time;
    @Column
    @Comment("标题图片")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String title_img;
    @Column
    @Comment("")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String title_img_s;
    @Column
    @Comment("指定模板")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String tpl_content;
    @Column
    @Comment("发布状态")
    @ColDefine(type = ColType.INT)
    private Integer is_static;
    @Column
    @Comment("")
    @ColDefine(type =ColType.INT)
    private Integer is_index;

    private String filePath;//文件下载链接
    /**
     * 站点名称
     */
    private String site_name;
    /**
     * 栏目名称
     */
    private String channel_name;

    @One(field = "channel_id")
    private Cms_channel channel;

    private String pub_time_date;//发布日期

    private String content_txt;//文章内容

    private String content_txt_cut;//精简后的文章内容



    @Column
    @Comment("招聘部门")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String department;

    @Column
    @Comment("招聘地区")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String area;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContent_txt() {
        return content_txt;
    }

    public void setContent_txt(String content_txt) {
        this.content_txt = content_txt;
    }

    public String getPub_time_date(){
        return "["+StringUtils.substring(pub_time,0,10)+"]";
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink_() {
        return link_;
    }

    public void setLink_(String link_) {
        this.link_ = link_;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getTitle_color() {
        return title_color;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public void setTitle_color(String title_color) {
        this.title_color = title_color;
    }

    public String getIs_bold() {
        return is_bold;
    }

    public void setIs_bold(String is_bold) {
        this.is_bold = is_bold;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia_path() {
        return media_path;
    }

    public void setMedia_path(String media_path) {
        this.media_path = media_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }

    public String getTitle_img_s() {
        return title_img_s;
    }

    public void setTitle_img_s(String title_img_s) {
        this.title_img_s = title_img_s;
    }

    public String getTpl_content() {
        return tpl_content;
    }

    public void setTpl_content(String tpl_content) {
        this.tpl_content = tpl_content;
    }

    public Integer getIs_static() {
        return is_static;
    }

    public void setIs_static(Integer is_static) {
        this.is_static = is_static;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSort_time() {
        return sort_time;
    }

    public void setSort_time(String sort_time) {
        this.sort_time = sort_time;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHas_title_img() {
        return has_title_img;
    }

    public void setHas_title_img(String has_title_img) {
        this.has_title_img = has_title_img;
    }

    public String getTop_level() {
        return top_level;
    }

    public void setTop_level(String top_level) {
        this.top_level = top_level;
    }

    public Integer getIs_index() {
        return is_index;
    }

    public void setIs_index(Integer is_index) {
        this.is_index = is_index;
    }

    public Cms_channel getChannel() {
        return channel;
    }

    public void setChannel(Cms_channel channel) {
        this.channel = channel;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContent_txt_cut() {
        String str ="";
        if (Strings.isNotBlank(content_txt)){
            str = content_txt;
            str = str.replaceAll("<style.*</style>", "");
            Pattern p_html=Pattern.compile("<[^>]+>",Pattern.CASE_INSENSITIVE);
            Matcher m_html=p_html.matcher(str);
            str=m_html.replaceAll("").trim(); //过滤html标签
            str.replaceAll("&nbsp;","");//过滤空格符号
            if(str.length()>100){
                str = str.substring(0,100)+"...";//截取字符串
            }
        }
        return str;
    }

    public void setContent_txt_cut(String content_txt_cut) {
        this.content_txt_cut = content_txt_cut;
    }
}