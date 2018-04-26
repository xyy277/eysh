package com.aebiz.app.web.commons.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by zyang on 2017/8/31.
 */
public class SSOSimpleToken extends UsernamePasswordToken {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SSOSimpleToken(String username, String type) {
        super(username, "");
        this.type = type;
    }
}
