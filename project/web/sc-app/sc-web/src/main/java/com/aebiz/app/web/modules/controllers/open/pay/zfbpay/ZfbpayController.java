package com.aebiz.app.web.modules.controllers.open.pay.zfbpay;

import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.modules.controllers.open.pay.zfbpay.been.ZfbPayUnifiedOrder;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.MoneyUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付
 * @author: jxx
 * @date: 2017/9/19 9:08
 */
@Controller
@RequestMapping("/open/pay/zfbpay")
public class ZfbpayController {

    private static final Log log = Logs.get();
    @Autowired
    private PropertiesProxy config;
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    //public String alipay_public_key =config.get("alipay_public_key");
    // 商户私钥，您的PKCS8格式RSA2私钥
    //public String merchant_private_key =config.get("merchant_private_key");
    /**
     * 订单微信支付
     * @param orderId
     * @param payBody
     * @param httpResponse
     * @return
     */
    @RequestMapping("/order")
    public void zfbPayOrder(@RequestParam("orderId") String orderId, @RequestParam("payBody")String payBody, HttpServletResponse httpResponse){
        try{
            //计算订单总金额
            int money =  0;
            ZfbPayUnifiedOrder order = new ZfbPayUnifiedOrder();
            order.setAppid(config.get("ZfbAppId"));
            order.setOutTradeno(orderId);
            order.setTotalAmount(MoneyUtil.fenToYuan(money));
            order.setSubject(payBody);
            order.setBody(payBody);
            order.setUid("");
            order.setReturnurl("http://" + Globals.APP_DOMAIN + "/front/order/paySuccess");
            order.setNotifyurl("http://" + Globals.APP_DOMAIN + "/open/pay/zfbpay/payOrderBack");
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request

            AlipayClient alipayClient = new DefaultAlipayClient(
                    "https://openapi.alipay.com/gateway.do",
                    order.getAppid(),
                    config.get("merchant_private_key"),
                    "json",
                    charset,config.get("alipay_public_key"),
                    sign_type); //获得初始化的AlipayClient
            alipayRequest.setBizContent("{" +
                    "    \"out_trade_no\":\""+order.getOutTradeno()+"\"," +
                    "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                    "    \"total_amount\":"+order.getTotalAmount()+"," +
                    "    \"subject\":\""+order.getSubject()+"\"," +
                    "    \"body\":\""+order.getBody()+"\"," +
                /*"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +*/
                    "    \"extend_params\":{" +
                    "    \"sys_service_provider_id\":\""+order.getUid()+"\"" +
                    "    }"+
                    "  }");//填充业务参数
            alipayRequest.setReturnUrl(order.getReturnurl());
            alipayRequest.setNotifyUrl(order.getNotifyurl());//在公共参数中设置回跳和通知地址
            String form="";
            try {
                form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            httpResponse.setContentType("text/html;charset=" + "UTF-8");
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        }catch (Exception e){
            log.debug(e.getMessage(),e);
        }
    }

    /**
     * 订单支付回调
     * @return
     */
    @RequestMapping(value = "/payOrderBack")
    @SJson
    public Object zfbPayOrderBack(HttpServletRequest httpRequest){
        try {
            Map<String,String> paramsMap = new HashMap<String,String>();
            Map<String,String[]> requestParams = httpRequest.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                paramsMap.put(name, valueStr);
            }
            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, config.get("alipay_public_key"), charset, sign_type); //调用SDK验证签名
            if(signVerified){//验证签名
                if("TRADE_SUCCESS".equals(paramsMap.get("trade_status"))){
                    //取传递的订单号
                    String orderId = paramsMap.get("out_trade_no");
                    //查询订单列表
                    //生成订单支付明细
                }
                return "success";
            }else{
                log.debug("res::fail");
                return "fail";
            }
        } catch (Exception e) {
            log.debug("ZfbpayController::"+ e.getMessage(),e);
            return "fail";
        }
    }

}
