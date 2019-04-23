package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: 277
 * @date: 2018/3/17 12:41
 * 用户帐号表(eysh)
 */
@Table("tour_user")
public class Tour_user extends BaseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("用户名")
    @ColDefine(type=ColType.VARCHAR, width = 100)
    private String loginname;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 128)
    @Comment("昵称")
    private String nickname;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 128)
    @Comment("加密密码")
    private String password;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("加密盐")
    private String salt;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 20)
    @Comment("手机号码")
    private String mobile;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("电子邮箱")
    private String email;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 20)
    @Comment("QQ")
    private String qq;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 10)
    @Comment("真实姓名")
    private String name;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 18)
    @Comment("身份证号")
    private String id_num;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("省")
    private String province;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("市")
    private String city;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("县区")
    private String area;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    @Comment("详细地址")
    private String address;

    @Column
    @ColDefine(type = ColType.INT, width = 32)
    @Comment("登录次数")
    private Integer login_num;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("最后登录IP")
    private String last_ip;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("最后登录时间")
    private String last_time;

    private String pic;

    @Column
    @ColDefine(type = ColType.INT)
    @Comment("游戏得分")
    private Integer score = 0;

    @Column
    @ColDefine(type = ColType.INT)
    @Comment("游戏次数")
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLogin_num() {
        return login_num;
    }

    public void setLogin_num(Integer login_num) {
        this.login_num = login_num;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
