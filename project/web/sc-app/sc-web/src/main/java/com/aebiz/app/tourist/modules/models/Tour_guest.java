package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/*******************************
 * Copyright (C),2018-2099, ZJJ
 * Title : 游客
 * File name : Tour_guest
 * Author : zhoujiajun
 * Date : 2019/4/22 10:44
 * Version : 1.0
 * Description : 
 ******************************/
@Table("tour_guest")
public class Tour_guest extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 128)
    @Comment("ip")
    private String ip;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 128)
    @Comment("昵称")
    private String nickname;

    @Column
    @ColDefine(type = ColType.INT)
    @Comment("游戏得分")
    private Integer score;

    @Column
    @ColDefine(type = ColType.INT)
    @Comment("游戏排名")
    private Integer rank;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
