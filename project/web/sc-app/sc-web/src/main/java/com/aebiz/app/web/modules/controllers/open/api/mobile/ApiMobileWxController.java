package com.aebiz.app.web.modules.controllers.open.api.mobile;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_loginlog;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountLoginlogService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.sys.modules.services.SysApiService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.redis.RedisService;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.UserAgentUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.weixin.bean.WxPayUnifiedOrder;
import org.nutz.weixin.impl.WxApi2Impl;
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
 * 手机移动端-微信功能接口
 * <p>
 * Created by tony on 2017/9/19.
 */
@Controller
@CrossOrigin
@RequestMapping("/open/api/mobile/wx")
public class ApiMobileWxController {
    private static final Log log = Logs.get();
    private static final String HASH_KEY = "scxm";

    @Autowired
    private PropertiesProxy config;
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
     * 将微信公众号与系统用户账号绑定
     *
     * @param openid    微信用户唯一标志
     * @param accountId 当前登录用户id(Sc_account_user)
     * @param timestamp 时间戳
     * @param hashKey   安全校验字符串=md5(openid + accountId + timestamp + HASH_KEY)
     * @return
     */
    @RequestMapping(value = "/bind")
    @SJson("full")
    @SLog
    public Object wxBind(@RequestParam String openid,
                         @RequestParam String accountId,
                         @RequestParam String timestamp,
                         @RequestParam String hashKey) {
        try {
            // 首先校验传递参数openid、accountId的正确性
            String sign = Lang.md5(openid + accountId + timestamp + HASH_KEY);
            if (!Strings.equalsIgnoreCase(sign, hashKey)) {
                return Result.error("传递参数信息有误");
            }

            return Result.success("ok");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("WxMobileAPI error: " + e.getMessage());
            return Result.error("fail");
        }
    }

    /**
     * 将微信公众号与系统用户账号解除绑定
     *
     * @param openid    微信用户唯一标志
     * @param accountId 当前登录用户id(Sc_account_user)
     * @param timestamp 时间戳
     * @param hashKey   安全校验字符串=md5(openid + accountId + timestamp + HASH_KEY)
     * @return
     */
    @RequestMapping(value = "/unbind")
    @SJson("full")
    @SLog
    public Object wxUnbind(@RequestParam String openid,
                           @RequestParam String accountId,
                           @RequestParam String timestamp,
                           @RequestParam String hashKey) {
        try {
            // 首先校验传递参数openid、accountId的正确性
            String sign = Lang.md5(openid + accountId + timestamp + HASH_KEY);
            if (!Strings.equalsIgnoreCase(sign, hashKey)) {
                return Result.error("传递参数信息有误");
            }
            return Result.success("ok");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("WxMobileAPI error: " + e.getMessage());
            return Result.error("fail");
        }
    }

    /**
     * 微信账号登录
     *
     * @param openid    微信用户唯一标志
     * @param timestamp 时间戳
     * @param hashKey   安全校验字符串=md5(openid + timestamp + HASH_KEY)
     * @param request
     * @return
     */
    @RequestMapping(value = "/auth")
    @SJson("full")
    @SLog
    public Object wxAuth(@RequestParam String openid,
                         @RequestParam String timestamp,
                         @RequestParam String hashKey,
                         HttpServletRequest request) {
        try {
            // 首先校验传递参数openid的正确性
            String sign = Lang.md5(openid + timestamp + HASH_KEY);
            if (!Strings.equalsIgnoreCase(sign, hashKey)) {
                return Result.error("传递参数信息有误");
            }
            // 进行实际登录操作
            // 记录登录信息
            Sc_account_loginlog accountLogin = new Sc_account_loginlog();
            accountLogin.setLogin_ip(Lang.getIP(request));
            accountLogin.setLogin_at((int) (System.currentTimeMillis() / 1000));
            accountLogin.setClient_type("weixin");
            OperatingSystem operatingSystem = UserAgentUtils.getOperatingSystem(request);
            if (operatingSystem != null) {
                accountLogin.setClient_name(operatingSystem.getName());
            }
            Browser browser = UserAgentUtils.getBrowser(request);
            if (browser != null) {
                accountLogin.setClient_browser(browser.getName());
            }
            scAccountLoginService.insert(accountLogin);

            //设置返回token信息和用户信息
            Calendar now = Calendar.getInstance();
            now.add(Calendar.HOUR, 1); //设置登录1小时以内有效
            //String token = Lang.md5(System.currentTimeMillis() + "");
            // 将登录用户相关信息存放redis缓存中
            try (Jedis jedis = redisService.jedis()) {

            }
            // 返回手机APP登录用户信息
            Map<String, Object> map = new HashMap<String, Object>();
            return Result.success("ok", map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("WxMobileAPI error: " + e.getMessage());
            return Result.error("fail");
        }
    }

    /**
     * APP端微信支付
     *
     * @param openid    微信验证openid
     * @param orderId   订单Id(Sc_service_order)
     * @param mobile    订单联系号码
     * @param payBody   支付内容信息
     * @param timestamp 时间戳
     * @param hashKey   安全校验字符串=md5(openid + orderId + mobile + payBody + timestamp + HASH_KEY)
     * @param req
     * @return
     */
    @RequestMapping(value = "/wxPay")
    @SJson("full")
    @SLog
    public Object wxPayment(@RequestParam String openid,
                            @RequestParam String orderId,
                            @RequestParam String mobile,
                            @RequestParam String payBody,
                            @RequestParam String timestamp,
                            @RequestParam String hashKey,
                            HttpServletRequest req) {
        try {
            // 首先校验传递参数orderId、payBody的正确性
            String sign = Lang.md5(openid + orderId + mobile + payBody + timestamp + HASH_KEY);
            if (!Strings.equalsIgnoreCase(sign, hashKey)) {
                return Result.error("传递参数信息有误");
            }

            // 更新订单联系手机号码

            //创建一个wxApi2
            WxApi2Impl wxApi2 = new WxApi2Impl();
            WxPayUnifiedOrder order = new WxPayUnifiedOrder();
            order.setAppid(config.get("AppId"));
            order.setMch_id(config.get("Mchid"));
            order.setNonce_str(R.UU32());
            //商品描述
            order.setBody(payBody);
            //商家数据包，存入需要的订单组号
            order.setAttach(orderId);
            //商户订单号---存入规则-日期加6位随机数
            String tradeNo = "12312";
            order.setOut_trade_no(tradeNo);
            //计算订单总金额
            /*Sc_service_order orderMain = scServiceOrderService.getField("pay_money", Cnd.where("delFlag", "=", false).and("id", "=", orderId));
            if (orderMain == null) {
                return Result.error("订单不存在");
            }
            int money = orderMain.getPay_money();*/

            int money = 0;
            order.setTotal_fee(money);
            order.setSpbill_create_ip(Globals.APP_IP);
            String str = Globals.APP_DOMAIN;
            order.setNotify_url("http://" + Globals.APP_DOMAIN + "/open/pay/wxPay/payOrderBack");//支付结果回调通知地址
            //交易类型
            order.setTrade_type("JSAPI");
            order.setOpenid(openid); //JSAPI调用方式需要传递openid参数
            //商品Id
            order.setProduct_id(orderId);//NATIVE时填写
            NutMap params = wxApi2.pay_jsapi(config.get("key"), order);

            return Result.success("ok", params);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("wxPay Error: " + e.getMessage());
            return Result.error("fail");
        }
    }

    /**
     * APP端专家咨询微信支付
     *
     * @param openid    微信验证openid
     * @param orderId   专家咨询订单Id(Sc_expert_order)
     * @param timestamp 时间戳
     * @param hashKey   安全校验字符串=md5(openid + orderId + timestamp + HASH_KEY)
     * @param req
     * @return
     */
    @RequestMapping(value = "/wxExpertPay")
    @SJson("full")
    @SLog
    public Object wxExpertPayment(@RequestParam String openid,
                                  @RequestParam String orderId,
                                  @RequestParam String timestamp,
                                  @RequestParam String hashKey,
                                  HttpServletRequest req) {
        try {
            // 首先校验传递参数orderId、payBody的正确性
            String sign = Lang.md5(openid + orderId + timestamp + HASH_KEY);
            if (!Strings.equalsIgnoreCase(sign, hashKey)) {
                return Result.error("传递参数信息有误");
            }

            //创建一个wxApi2
            WxApi2Impl wxApi2 = new WxApi2Impl();
            WxPayUnifiedOrder order = new WxPayUnifiedOrder();
            order.setAppid(config.get("AppId"));
            order.setMch_id(config.get("Mchid"));
            order.setNonce_str(R.UU32());
            //商品描述
            order.setBody("专家咨询");
            //商家数据包，存入需要的订单组号
            order.setAttach(orderId);
            //商户订单号---存入规则-日期加6位随机数
            String tradeNo = "123";
            order.setOut_trade_no(tradeNo);
            //计算订单总金额
            int money = 0;
            order.setTotal_fee(money);
            order.setSpbill_create_ip(Globals.APP_IP);
            order.setNotify_url("http://" + Globals.APP_DOMAIN + "/open/pay/wxExpertPay/payOrderBack");//支付结果回调通知地址
            //交易类型
            order.setTrade_type("JSAPI");
            order.setOpenid(openid); //JSAPI调用方式需要传递openid参数
            //专家Id
            order.setProduct_id(orderId);//NATIVE时填写
            NutMap params = wxApi2.pay_jsapi(config.get("key"), order);

            return Result.success("ok", params);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("wxExpertPay Error: " + e.getMessage());
            return Result.error("fail");
        }
    }

}
