package com.aebiz.app.web.modules.controllers.front.front;

import com.aebiz.app.sms.modules.services.SmsSendLogService;
import com.aebiz.app.sys.modules.services.SysDictService;
import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.TourUserService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zyang on 2017/9/13.
 */
@Controller
@RequestMapping("/register")
public class FrontRegisterController {
    private static final Log log = Logs.get();
    @Autowired
    private PropertiesProxy config;
    @Autowired
    private TourUserService tourUserService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SmsSendLogService smsSendLogService;

    @RequestMapping(value = {"", "/"})
    public String index(HttpServletRequest request) {
        String returnUrl=(String) request.getAttribute("uri");
        if(Strings.isNotBlank(returnUrl)){
            request.setAttribute("returnUrl",returnUrl);
        }
        return "pages/front/tourist/register";
    }

    @RequestMapping(value = {"/{type}"})
    public String register(@PathVariable(required = false) String type, HttpServletRequest request) {
        if (type != null) {
            return "pages/front/tourist/register/" + type;
        }
        return "pages/front/tourist/register";
    }

    @RequestMapping("/registerSms")
    @SJson
    public Object registerSms(HttpServletRequest req){
        String phone=req.getParameter("phone");
        if(Strings.isBlank(phone))return Result.error("手机号空");
        StringBuilder captcha=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<6;i++){
            captcha.append(random.nextInt(10));
        }
        log.debug(phone);
        log.debug(captcha);
        Map<String,Object> map=new HashMap<>();
        map.put("code",captcha);
        req.getSession().setAttribute("registerCaptcha",captcha);
        smsSendLogService.sendSms(phone,"SMS_001",map,"");
        return Result.success("验证码为："+captcha);
    }

    @RequestMapping("/verifyCode")
    @SJson
    public Object verify(HttpServletRequest req){//注册验证码的验证
        String randCode=req.getParameter("code");
        if(Strings.isBlank(randCode)){
            return Result.error("验证码空异常");
        }
        if(!randCode.equals(req.getSession().getAttribute("registerCaptcha"))){
            return Result.error("验证码错误");
        }
        return Result.success();
    }

    @RequestMapping(value = {"/service2"})
    public String registerService2(HttpServletRequest request) {
        String id = Strings.sNull(request.getSession().getAttribute("registedUid"));
        // if ("".equals(id)) {
        //     return "redirect:/register/service";
        // }
        request.setAttribute("orgProperty", sysDictService.getSubListByCode("org_property"));
        request.setAttribute("registerType", sysDictService.getSubListByCode("register_type"));
        request.setAttribute("industryCode", sysDictService.getSubListByCode("industry_code"));
        request.setAttribute("accountId", id);

        return "pages/front/front/register/service2";
    }

    @RequestMapping("/sendCaptcha")
    @SLog
    @SJson
    public Object sendCaptcha(@RequestParam String phone, HttpServletRequest request) {
        if (!checkPhone(phone)) return Result.error("手机号已被注册！");
        String captcha = R.captchaNumber(6);
        log.debug("验证码：" + captcha);
        request.getSession().setAttribute("SMScaptcha:" + phone, captcha);
        Map<String, Object> map = new HashMap<>();
        map.put("number", captcha);
        try {
            String code = smsSendLogService.sendSms(phone, "SMS_001", map, "");
            if (code != null && "OK".equalsIgnoreCase(code)) {
                return Result.success();
            } else {
                return Result.error();
            }
        } catch (Exception e) {
            log.error("发送验证码出错 (102):\r\n", e);
            return Result.error();
        }

    }

    @RequestMapping("/checkCaptcha")
    @SLog
    @SJson
    public boolean checkCaptcha(String captcha, String phone, HttpServletRequest request) {
        if (captcha == null) return false;
        String SMScaptcha = (String) request.getSession().getAttribute("SMScaptcha:" + phone);
        if (SMScaptcha == null) return false;
        if (captcha.equals(SMScaptcha)) {
            request.getSession().removeAttribute("SMScaptcha:" + phone);
            return true;
        }
        return false;
    }

    @RequestMapping("/checkCapt")
    @SLog
    @SJson
    public void checkCapt(String captcha, String phone, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (captcha == null) response.sendError(404);
        String SMScaptcha = (String) request.getSession().getAttribute("SMScaptcha:" + phone);
        if (SMScaptcha == null) response.sendError(404);
        if (!captcha.equals(SMScaptcha)) response.sendError(404);
    }

    @RequestMapping("/checkPhone")
    @SLog
    @SJson
    public boolean checkPhone(String phone) {
        Tour_user user = tourUserService.fetch(Cnd.where(Cnd.exps("mobile", "=", phone).and("delFlag", "=", false)));
        if (user != null) {
            return false;
        } else {
            return true;
        }
    }
    @RequestMapping("/checkLoginname")
    @SLog
    @SJson
    public boolean checkLoginname(String loginname) {
        Tour_user user = tourUserService.fetch(Cnd.where(Cnd.exps("loginname", "=", loginname).and("delFlag", "=", false)));
        if (user != null) {
            return false;
        } else {
            return true;
        }
    }
    @RequestMapping("/registerDo")
    @SLog
    @SJson
    public Result registerDo(HttpServletRequest request) {
        String loginname=request.getParameter("loginname");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String qq=request.getParameter("qq");
        try {
            if(!checkLoginname(loginname)){
                return Result.error("用户名已存在");
            }
            if (!checkPhone(phone)) {
                return Result.error("手机号已存在！");
            }
            Tour_user user = null;
            // 如果有值说明已通过消息同步存入了
            String msuid = config.remove("msuid." + phone);
            if (!StringUtils.isBlank(msuid)) {
                user = tourUserService.fetch(msuid);
            }
            if (user == null) {
                user = new Tour_user();
            }
            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();
            user.setLoginname(loginname);
            user.setNickname("小"+name.toCharArray()[0]);
            user.setPassword(hashedPasswordBase64);
            user.setMobile(phone);
            user.setName(name);
            user.setQq(qq);
            user.setSalt(salt);
            tourUserService.insert(user);
            // 注册成功设置到session中去
            user.setPassword("");
            user.setSalt("");
            request.getSession().setAttribute("user", user);
            return Result.success("注册成功", user.getId());
        } catch (Exception e) {
            return Result.error("注册失败");
        }
    }

}
