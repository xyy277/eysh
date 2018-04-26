package com.aebiz.app.web.modules.controllers.open.pay.wxpay;

import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.lang.Streams;
import org.nutz.lang.Xmls;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.weixin.bean.WxPayUnifiedOrder;
import org.nutz.weixin.impl.WxApi2Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ThinkPad on 2017/6/19.
 */
@Controller
@RequestMapping("/open/pay/wxPay")
public class WxpayController {

    private static final Log log = Logs.get();
    @Autowired
    private PropertiesProxy config;

    /**
     * 订单微信支付
     * @param orderId
     * @param payBody
     * @param req
     * @return
     */
    @RequestMapping("/order")
    @SJson
    public Object wxPayOrder(@RequestParam("orderId") String orderId, @RequestParam("payBody")String payBody, HttpServletRequest req){
        try{
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
            order.setOut_trade_no("123123");
            //计算订单总金额
            int money =  0;
            order.setTotal_fee(money);
            order.setSpbill_create_ip(Globals.APP_IP);
            String str=Globals.APP_DOMAIN;
            order.setNotify_url("http://" + Globals.APP_DOMAIN + "/open/pay/wxPay/payOrderBack");//支付结果回调通知地址
            //交易类型
            order.setTrade_type("NATIVE");
            //商品Id
            order.setProduct_id(orderId);//NATIVE时填写
            NutMap resp = wxApi2.pay_unifiedorder(config.get("key"), order);
            String return_code = resp.getString("result_code", "");//SUCCESS
            if(!"SUCCESS".equals(return_code)){
                return Result.error(resp.getString("err_code_des", ""));
            }
            String code_url = resp.getString("code_url", "");
            log.debug("resp:::" + Json.toJson(resp));
            return Result.success("globals.result.success", code_url);
        }catch (Exception e){
            log.debug(e.getMessage(),e);
            return Result.error("globals.result.error");
        }
    }

    /**
     * 订单支付回调
     * @param reader
     * @return
     */
    @RequestMapping(value = "/payOrderBack")
    @SJson
    public Object wxPayOrderBack(Reader reader){
        NutMap res = Xmls.xmlToMap(Streams.readAndClose(reader));
        Map<String, Object> map = new HashMap<>();
        if("SUCCESS".equals(res.getString("result_code"))){
            //取传递的订单号
            String orderId = res.getString("attach");
            //查询订单列表
            //统计订单总金额
            //生成订单支付明细
            map.put("return_code", "SUCCESS");
        }else {
            map.put("return_code", "FAIL");//支付失败
        }

        log.debug("res::" + Json.toJson(res));
        return Xmls.mapToXml(map);
    }

}
