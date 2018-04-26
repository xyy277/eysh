package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/21.
 */
@Table("tour_journey")
public class Tour_journey extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String title;

    @Column
    @Comment("审核状态 -1：回收站 0:待审 1：审核通过 2:审核未通过")
    @ColDefine(type = ColType.INT)
    private Integer status;

    @Column
    @Comment("发布者")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String author;

    @Column
    @Comment("点击量")
    @ColDefine(type = ColType.INT)
    private Integer readnum;

    @Column
    @Comment("点赞数")
    @ColDefine(type = ColType.INT)
    private Integer up_number;

    @Column
    @Comment("评论数")
    @ColDefine(type = ColType.INT)
    private Integer comment_no;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReadnum() {
        return readnum;
    }

    public void setReadnum(Integer readnum) {
        this.readnum = readnum;
    }

    public Integer getUp_number() {
        return up_number;
    }

    public void setUp_number(Integer up_number) {
        this.up_number = up_number;
    }

    public Integer getComment_no() {
        return comment_no;
    }

    public void setComment_no(Integer comment_no) {
        this.comment_no = comment_no;
    }
}
