package com.aebiz.app.web.modules.controllers.open.api.mobile;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_loginlog;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountLoginlogService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.sys.modules.services.SysApiService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.redis.RedisService;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.UserAgentUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机移动端 注册登录相关功能接口
 * <p>
 * Created by tony on 2017/9/19.
 */
@Controller
@CrossOrigin
@RequestMapping("/open/api/mobile/app")
public class ApiMobileRegisterController {
    private static final Log log = Logs.get();

    @Autowired
    private RedisService redisService;
    @Autowired
    private SysApiService sysApiService;
    @Autowired
    private ScAccountUserService scAccountUserService;
    @Autowired
    private ScAccountLinkService scAccountLinkService;
    @Autowired
    private ScAccountLoginlogService scAccountLoginService;

    /**
     * 手机APP注册接口
     *
     * @param mobile      注册手机号码
     * @param password    用户密码
     * @param smsCode     接收的手机验证码
     * @param accountType // 账户类型 详见sc_account_link用户类型静态常量MEMBERTYPEORG...
     * @return
     */
    @RequestMapping(value = "/register")
    @SJson("full")
    @SLog
    public Object mobileRegister(@RequestParam String mobile,
                                 @RequestParam String password,
                                 @RequestParam String smsCode,
                                 @RequestParam String accountType) {
        try {
            //首先校验手机号是否已经注册
            if (!checkPhone(mobile)) {
                return new Result(3, "手机号码已经存在！", null);
            }

            // 校验(手机+验证码)组合信息是否正确
            String sendCode = "";
            try (Jedis jedis = redisService.jedis()) {
                sendCode = Strings.sBlank(jedis.get("mobileCode" + mobile));
            }
            if (!sendCode.equals(Strings.sBlank(mobile) + Strings.sBlank(smsCode))) {
                return new Result(3, "验证码输入错误或已经失效,请重新输入", null);
            }

            // 判断用户类型是否正确, APP端仅允许企业(accountType=03)、创业者(accountType=04)、专家(accountType=05)进行注册
            // 服务机构仅仅允许登录，不允许注册, 此处accountType参数与PC端保持一致
            String[] allowTypes = {"03", "04", "05"};
            if (!Strings.isin(allowTypes, accountType)) {
                return new Result(3, "用户类型参数有误!", null);
            }
            if (Strings.equals(String.valueOf(SSOUtil.ssoRegister(mobile, password, mobile, accountType).get("code")), "1")) {
                return new Result(3, "后台操作发生错误，请稍后再试!", null);
            }

            // 设置用户注册账户信息
            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();

            Sc_account_user user = new Sc_account_user();
            user.setLoginname(Strings.sBlank(mobile));
            user.setNickname("m" + Strings.sBlank(mobile));
            user.setPassword(hashedPasswordBase64);
            user.setMobile(Strings.sBlank(mobile));
            user.setSalt(salt);

            // 设置账户关联信息
            return Result.success("用户注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MobileAPI error:" + e.getMessage());
            return Result.error("用户注册失败");
        }
    }

    // 校验手机号是否已存在
    private boolean checkPhone(String phone) {
        Sc_account_user user = scAccountUserService.fetch(Cnd.where(Cnd.exps("mobile", "=", phone).or("loginname", "=", phone)).and("delFlag", "=", false));
        if (user != null) {
            return false;
        } else {
            // 如果开启了SSO，再到sso服务器去检查用户是否存在
            if (SSOUtil.isSSO()) {
                Map result = SSOUtil.ssoCheckUser(phone, "");
                if (StringUtil.str2int(Strings.sNull(result.get("code")), 1) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 手机APP登录接口
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @return
     */
    @RequestMapping(value = "/login")
    @SJson("full")
    @SLog
    public Object mobileLogin(@RequestParam String username, @RequestParam String password,
                              @RequestParam(value = "captcha", defaultValue = "", required = false) String captcha,
                              HttpServletRequest request) {
        try {
            // 首先校验登录用户账号是否正确
            Sc_account_user accountUser = scAccountUserService.fetch(Cnd.where("delFlag", "=", false).and("loginname", "=", username));
            if (accountUser == null) {
                return new Result(3, "用户账号不存在", null);
            }

            String saltPassword = new Sha256Hash(password, accountUser.getSalt(), 1024).toBase64();
            if (!Strings.equals(saltPassword, Strings.sBlank(accountUser.getPassword()))) {
                return new Result(3, "账号密码不正确", null);
            }

            Sc_account_link accountLink = scAccountLinkService.fetch(Cnd.where("account_id", "=", accountUser.getId()));

            // 记录登录信息

            //设置返回token信息和用户信息
            Calendar now = Calendar.getInstance();
            now.add(Calendar.HOUR, 1); //设置登录1小时以内有效
            //String token = Lang.md5(System.currentTimeMillis() + "");
            String token = sysApiService.generateToken(now.getTime(), accountUser.getId());
            // 将登录用户相关信息存放redis缓存中
            try (Jedis jedis = redisService.jedis()) {
                jedis.set("accountLinkTableId:" + accountUser.getId(), accountLink.getTable_id());
                jedis.expire("accountLinkTableId:" + accountUser.getId(), 3600);
                jedis.set("accountLinkType:" + accountUser.getId(), String.valueOf(accountLink.getType()));
                jedis.expire("accountLinkType:" + accountUser.getId(), 3600);
            }

            // 返回手机APP登录用户信息
            Map<String, Object> map = new HashMap<String, Object>();
            accountUser.setPassword("");
            accountUser.setSalt("");
            map.put("accountUser", accountUser);
            map.put("accountLink", accountLink); //用户关联信息
            map.put("token", token);

            return Result.success("ok", map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MobileAPI error:" + e.getMessage());
            return Result.error("fail");
        }
    }

    /**
     * 手机端用户退出APP
     *
     * @param accountId
     * @param token
     * @return
     */
    @RequestMapping(value = "/logout")
    @SJson("full")
    @SLog
    public Object mobileLogout(@RequestParam String accountId,
                               @RequestParam String token) {
        try (Jedis jedis = redisService.jedis()) {
            jedis.del("accountLinkType:" + accountId);
            jedis.del("accountLinkTableId:" + accountId);
            jedis.del(("apitoken:" + accountId).getBytes());
            return Result.success("ok");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MobileAPI error:" + e.getMessage());
            return Result.error("fail");
        }
    }

    /**
     * 手机APP修改密码接口
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param accountId   当前登录用户Id(Sc_account_user)
     * @return
     */
    @RequestMapping(value = "/changePassword")
    @SJson("full")
    @SLog
    public Object changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String accountId) {
        try {
            Sc_account_user scAccountUser = scAccountUserService.fetch(Cnd.where("id", "=", accountId));
            String old = new Sha256Hash(oldPassword, scAccountUser.getSalt(), 1024).toBase64();
            if (Strings.equals(old, scAccountUser.getPassword())) {
                if (Strings.equals(String.valueOf(SSOUtil.ssoResetPassword(scAccountUser.getLoginname(), newPassword).get("code")), "1")) {
                    return new Result(3, "后台操作发生错误，请稍后再试!", null);
                }
                RandomNumberGenerator rng = new SecureRandomNumberGenerator();
                String salt = rng.nextBytes().toBase64();
                String hashedPasswordBase64 = new Sha256Hash(newPassword, salt, 1024).toBase64();
                scAccountUser.setSalt(salt);
                scAccountUser.setPassword(hashedPasswordBase64);
                scAccountUserService.update(Chain.make("salt", salt).add("password", hashedPasswordBase64), Cnd.where("id", "=", scAccountUser.getId()));
                return Result.success("ok");
            } else {
                return new Result(3, "原密码信息不正确", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MobileAPI error:" + e.getMessage());
            return Result.error("fail");
        }
    }
}
