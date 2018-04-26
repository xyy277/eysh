package com.aebiz.app.web.commons.utils;

import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.sys.modules.models.Sys_unit;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.commons.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.nutz.http.Http;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zyang on 2017/8/31.
 */
public class SSOUtil {
    private static final Log log = Logs.get();
    private static PropertiesProxy config = SpringUtil.getBean("config", PropertiesProxy.class);
    public static String USER_LOGIN_API = "";
    private static Pattern jsonpPattern = Pattern.compile("^\\w*\\((.*)\\)$");

    public static boolean doLogin(String username, String password, String capt) {
//        Response resp = Http.get("");
//        resp.getContent();
        return true;
    }

    public static Map ssoRegister(String loginname, String password, String phone, String type) {
        String registerUrl = config.get("api.user.register", "http://218.22.2.28:8082/register/xcom/system/user/registInterface.do");
        Map params = new HashMap();
        Map result = new HashMap();
        params.put("groupid", "1302767702718000");
        params.put("orgname", "合肥市");
        params.put("name", loginname);
        params.put("loginName", loginname);
        params.put("password", password);
        params.put("repassword", password);
        params.put("mobilePhone", phone);
        params.put("type", type);
        // 本地添加用户，让消息不接受
        config.set("localAdd", "true");
        String content = Http.post(registerUrl, params, 30 * 1000);
        log.debug(String.format("请求地址[%s]\r\n响应内容：%s", registerUrl, content));
        if ("注册失败！".equals(content)) {
            result.put("code", 1);
            return result;
        }
        // 如果是jsonp请求，提取出json
        Matcher matcher = jsonpPattern.matcher(content);
        if (matcher.find()) {
            content = matcher.group(1);
        }
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("code", 1);
        }
        return result;
    }

    public static Map ssoAddUser(Sys_user user, String type) {
        String registerUrl = config.get("api.user.register", "http://218.22.2.28:8082/standard/api/user/registInterface.do");
        Map params = new HashMap();
        Map result = new HashMap();
        params.put("groupid", user.getUnitid());
        params.put("orgname", "");
        try {
            params.put("name", URLEncoder.encode(user.getUsername(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            params.put("name", user.getUsername());
        }
        params.put("loginName", user.getLoginname());
        params.put("password", user.getPassword());
        params.put("repassword", user.getPassword());
        params.put("mobilePhone", user.getMobile());
        params.put("type", type);
        // 本地添加用户，让消息不接受
        config.set("localAdd", "true");
        String content = Http.post(registerUrl, params, 30 * 1000);
        log.debug(String.format("请求地址[%s]\r\n响应内容：%s", registerUrl, content));
        if ("注册失败！".equals(content)) {
            result.put("code", 1);
            return result;
        }
        // 如果是jsonp请求，提取出json
        Matcher matcher = jsonpPattern.matcher(content);
        if (matcher.find()) {
            content = matcher.group(1);
        }
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("code", 1);
        }
        return result;
    }

    public static Map ssoAddAccUser(Sc_account_user scAccountUser, String type) {
        String registerUrl = config.get("api.user.register", "http://218.22.2.28:8082/standard/api/user/registInterface.do");
        Map params = new HashMap();
        Map result = new HashMap();
        // params.put("groupid", "1302767702718000");
        params.put("orgname", "安徽省");
        String nickName = scAccountUser.getLoginname();
        try {
            nickName = URLEncoder.encode(scAccountUser.getNickname(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("name", nickName);
        params.put("loginName", scAccountUser.getLoginname());
        params.put("password", scAccountUser.getPassword());
        params.put("repassword", scAccountUser.getPassword());
        params.put("mobilePhone", scAccountUser.getMobile());
        params.put("type", type);
        // 本地添加用户，让消息不接受
        config.set("localAdd", "true");
        String content = Http.post(registerUrl, params, 10 * 1000);
        log.debug(String.format("请求地址[%s]\r\n响应内容：%s", registerUrl, content));
        if ("注册失败！".equals(content)) {
            result.put("code", 1);
            return result;
        }
        // 如果是jsonp请求，提取出json
        Matcher matcher = jsonpPattern.matcher(content);
        if (matcher.find()) {
            content = matcher.group(1);
        }
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("code", 1);
        }
        return result;
    }

    /**
     * sso重置密码操作
     * <p>也可用于修改密码，需要注意的是用于修改密码时，应先本地验证操作的合法性</p>
     *
     * @param loginname   需要重置密码的用户名
     * @param newPassword 新的密码
     * @return
     */
    public static Map ssoResetPassword(String loginname, String newPassword) {
        String resetPasswordUrl = config.getOrDefault("api.user.password.reset", "http://218.22.2.28:8082/register/xcom/system/user/passwordRestInterface.do");
        Map params = new HashMap();
        Map result = new HashMap();
        params.put("loginname", loginname);
        params.put("newPassword", newPassword);

        String content = Http.post(resetPasswordUrl, params, 30 * 1000);
        log.debug(String.format("请求地址[%s]\r\n响应内容：%s", resetPasswordUrl, content));
        if ("操作失败！".equals(content)) {
            result.put("code", 1);
            return result;
        }
        // 如果是jsonp请求，提取出json
        Matcher matcher = jsonpPattern.matcher(content);
        if (matcher.find()) {
            content = matcher.group(1);
        }
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("code", 1);
        }

        return result;
    }

    /**
     * 根据登录名和手机号判断用户是否存在
     *
     * @param loginName
     * @param phoneNum
     * @return
     */
    public static Map ssoCheckUser(String loginName, String phoneNum) {
        String checkUserUrl = config.getOrDefault("api.user.exist.check", "http://218.22.2.28:8081/sso_server/checkLoginNameAndPhoNo");
        Map params = new HashMap();
        Map result = new HashMap();
        if (StringUtils.isNotBlank(loginName)) {
            params.put("loginName", loginName);
        }
        if (StringUtils.isNotBlank(phoneNum)) {
            params.put("phoneNum", phoneNum);
        }
        String content = Http.post(checkUserUrl, params, 30 * 1000);
        log.debug(String.format("请求地址[%s]\r\n响应内容：%s", checkUserUrl, content));
        if ("true".equals(content)) {
            result.put("code", 0);
            return result;
        }
        if ("false".equals(content)) {
            result.put("code", 1);
            return result;
        }
        // 如果是jsonp请求，提取出json
        Matcher matcher = jsonpPattern.matcher(content);
        if (matcher.find()) {
            content = matcher.group(1);
        }
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("code", 1);
        }

        return result;
    }

    public static Map ssoAddOrUpdateUnit(Sys_unit unit) {
        String unitSaveUrl = config.get("api.unit.saveOrUpdate");
        Map params = new HashMap();
        Map result = new HashMap();
        if (unit.getId() != null) {
            params.put("id", unit.getId());
        }
        if (unit.getLocation() != null) {
            params.put("location", unit.getLocation());
        }
        params.put("parentId", unit.getParentId());
        params.put("name", unit.getName());
        params.put("code", unit.getUnitcode());
        params.put("address", unit.getAddress());
        params.put("telephone", unit.getTelephone());

        params.put("description", unit.getNote());
        String content = Http.post(unitSaveUrl, params, 30 * 1000);
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("flag", false);
        }
        return result;
    }

    public static Map ssoDeleteUnit(Sys_unit unit) {
        String unitSaveUrl = config.get("api.unit.del");
        Map params = new HashMap();
        Map result = new HashMap();

        params.put("dels", unit.getId());
        params.put("rootid", unit.getParentId());
        String content = Http.post(unitSaveUrl, params, 30 * 1000);
        try {
            result = Json.fromJson(Map.class, content);
            return result;
        } catch (Exception e) {
            result.put("flag", false);
        }
        return result;
    }

    public static boolean isSSO() {
        // 以动态配置项为主 on 开启 off 关闭
        String ssoFlagStr = Globals.MyConfig.get("SSO_FLAG");
        if (StringUtils.isBlank(ssoFlagStr)) {
            ssoFlagStr = StringUtils.isBlank(Globals.SSO_FLAG) ? "off" : Globals.SSO_FLAG;
        }
        return false;
//        return "on".equalsIgnoreCase(ssoFlagStr);
    }

    /**
     * 获取当前站点的基础Url http(s)://host:(port)(应用名)
     *
     * @param request
     * @return
     */
    public static String getBaseUrl(HttpServletRequest request) {
        String url = "";
        int port = request.getServerPort();
        url = request.getScheme() + "://" + request.getServerName()
                + (port == 80 ? "" : (":" + port)) + request.getContextPath();
        if (url.endsWith("/")) {
            url = StringUtils.removeEnd(url, "/");
        }
        return url;
    }

}
