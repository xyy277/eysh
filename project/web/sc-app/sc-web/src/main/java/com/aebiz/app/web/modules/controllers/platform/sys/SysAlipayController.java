package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sys.modules.models.Sys_alipay;
import com.aebiz.app.sys.modules.services.SysAlipayService;
import com.aebiz.baseframework.view.annotation.SJson;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alipay/sys/alipay")
public class SysAlipayController {
    private static final Log log = Logs.get();
    @Autowired
	private SysAlipayService sysAlipayService;

    @RequestMapping("")
    @RequiresPermissions("platform.sys.alipay")
	public String index() {
		return "pages/platform/sys/alipay/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.sys.alipay")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return sysAlipayService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.sys.alipay")
    public String add() {
    	return "pages/platform/sys/alipay/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Sys_alipay")
    @RequiresPermissions("platform.sys.alipay.add")
    public Object addDo(Sys_alipay sysAlipay, HttpServletRequest req) {
		try {
			sysAlipayService.insert(sysAlipay);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.sys.alipay")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", sysAlipayService.fetch(id));
		return "pages/platform/sys/alipay/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Sys_alipay")
    @RequiresPermissions("platform.sys.alipay.edit")
    public Object editDo(Sys_alipay sysAlipay, HttpServletRequest req) {
		try {
            sysAlipay.setOpBy(StringUtil.getUid());
			sysAlipay.setOpAt((int) (System.currentTimeMillis() / 1000));
			sysAlipayService.updateIgnoreNull(sysAlipay);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Sys_alipay")
    @RequiresPermissions("platform.sys.alipay.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				sysAlipayService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				sysAlipayService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.sys.alipay")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysAlipayService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/sys/alipay/detail";
    }

    @RequestMapping(value = {"/test"})
    public void test(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws ServletException, IOException {
        Sys_alipay alipay = new Sys_alipay ();

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        alipay.setAppid("2017062007526229");
        alipay.setOutTradeno(uuid);
        alipay.setTotalAmount("0.01");
        alipay.setSubject("Iphone6 16G");
        alipay.setBody("Iphone6 16G");
        alipay.setSuid("");
        alipay.setReturnurl("http://218.22.2.28:19990/aebizcms/alipay/sys/alipay");
        alipay.setNotifyurl("http://218.22.2.28:19990/aebizcms/alipay/sys/alipay/aplipayresponse");//外网地址，权限过滤去掉，session和cookie无效（注意）
        doPost(httpRequest,httpResponse,alipay);
    }

    @RequestMapping(value = {"/aplipayresponse"})
    public void aplipayresponse(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws ServletException, IOException, AlipayApiException {
        Cnd cnd = Cnd.NEW();
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
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            paramsMap.put(name, valueStr);
        }


        Sys_alipay alipay = new Sys_alipay ();
        PrintWriter pw = httpResponse.getWriter();

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnaT7muSOzSjFbfK+6Wazr2ZNH+R0uvOMQ/N29YEp+T+nqU4a2CWtnV56D8T1V2gh7QAsIpjNFR4YDPAraSvDBe99mICRBkQRd6XqXzJMyWvu6QFvW8R53OZQhpdWnTtBAX2hAyVsY1E55uts6aykGbqYt0TNdnKhNnEKZa8TbktIgVtOEatTMkBfXHnYNKLOPaEUclpGf3pynsHcs3DlLvJaywuXPAVJsBaTgMHD8z9vS9HB7/9pH7qBi3rSR/WhdlbzEPkAv4c9z/s97HzM6AqfFvsJSd1hmnuO6aLrjmSYIyAIdyI0XOPnCaDQbwwBtMYf1K5jtNKKMCD1xK/jNQIDAQAB",
                "UTF-8",
                "RSA2"); //调用SDK验证签名


        if(signVerified){
            try {
                cnd.where().and("outTradeno", "=", "%"+paramsMap.get("out_trade_no")+"%");
                int count = sysAlipayService.count(cnd);
                if(count<1) {
                    alipay.setAppid(paramsMap.get("app_id"));
                    alipay.setSuid(paramsMap.get("seller_id"));
                    alipay.setTradeNo(paramsMap.get("trade_no"));
                    alipay.setOutTradeno(paramsMap.get("out_trade_no"));
                    alipay.setTotalAmount(paramsMap.get("buyer_pay_amount"));
                    alipay.setRecieveAmount(paramsMap.get("recript_amount"));
                    alipay.setTradestatus(paramsMap.get("trade_status"));
                    alipay.setSubject(paramsMap.get("subject"));
                    alipay.setBody(paramsMap.get("body"));
                    alipay.setCj_date(paramsMap.get("gmt_create"));
                    alipay.setFukuan_date(paramsMap.get("gmt_payment"));
                    alipay.setFanhui_date(paramsMap.get("notify_time"));

                    sysAlipayService.insert(alipay);
                }
            } catch (Exception e) {
            }
            pw.write("success");

        }else{
            pw.write("fail");
        }
    }

    public void doPost(HttpServletRequest httpRequest,HttpServletResponse httpResponse, Sys_alipay alipay) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                alipay.getAppid(),
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHKHJAD8h6oOWNm8MJw27B0E+xTfavVYf6uSSeDUD+MUmGoTI6EqjI17kfnMIZNI8bho0VPdtcEZDE9y3+5YzE7tC/mI9nb+qvc3DOfFVxq7WyWr5HzlXHLOSjOAiHTV63rJG/3pPNvxyuuDe4UheqKnWEI5NvkGzwHP88jTZYM/6IYEF6x34+Oyj+QMak9NXbk5T9bk3QMTJ5PereiVehliKrDbZpXUi86F1I+ChvD4gysXYm1/635qS+9QrIxxi35pmvnAkID6ldvFMi+3/N4LSK3D5W/kAEfiUhD2HuFXTQcYMp7QrAn5HgVxo56CPtBZB3GV9foW03/rbP1zN9AgMBAAECggEATQE7TspvQ4bGIs866SYixWQE85Ti1nSZu7yeYzpT7D9yj7Tpo/KbnYpi2+L5J1ktq9YWLaMHhsYvQDlASHjKoi0aW/70vU8jXAEepVZrbA8Tv2E6BjlF3+vw/BlhqoznSSiWBcYvWcrn8ZfdjzOebgIw4C9QwgB8QLWUqhAehVOExJWRAHjOZd+RkICwgTZXOEv41dXSk4QSHxGDdWQY9tVcyQm1vFpY9u720t3lMMY5/PJMM0gqftCzInDSmHOyS9iW4jeVkh1jn2E8HUUPOXv5HkzWjeCMVX8C6vk3UiyLOIFLwj6CTzcOXsosYzcGKNeiqxFgEZHC+j9QT8s54QKBgQD7B4SSTBvJxnnHzbdgEOaJZet+LmhVWrt6UIrEXDZdXmpiQ+LGUu8NevW5pc1ddy1Hiry3u8QLKAMrEKnQYJD3gJhjzn9EvgstpKhpinB/WLX80jNj8tGrU/3tznK/RrKNYcHnYs21onc5I3MY0lJKLqSxGBfRe9MqePWK/On1yQKBgQCJ1ZHhlo5CRlY6vzeigFh6jj+E1/WMH4xm30C9MXIb1JDm+sMzIEvDgAmDSNqI8H82ACFiBX7Ta7inBrCb7Sf3fjEIYjIjgHuP1yEJ3TSlUpjX1VwJz2Ad33Z4grhfheivOAaMhrGah+dTgfjxW9MGZfvtOPRT8UWyd2M3JIK6FQKBgQCwEsXaF6gmDyC8FH7a0v/gv6w+BS8JL2rwkC3FLSi0tNTJt/QIR4OFYQmZ/cHeHQmVdKqJ9Rm8+YO/YSC7GACWmYxJ9T0val1M1oGKBaCCsiZz/rwtDtQh5/fkJTo3D8+Ne1QWiCbhj+MwIL1KW7t8bNr3nH6YYNHsqIsRwfQYgQKBgGKrW4IVKANznJjzd91BaZVaLUho3KORlg2ydgK52cjoc06WZpQ742CxUJ4F0kNDfp1mnH8yFec9bvNKi7UjoX+/e1RYDzJw8IM46rLIAr7ykO25E+kF5fWcmY+NbHUdREPipHWGmq9X6JZULaSCKl+jNCROqEFLL6yhYIoB/f89AoGAKEDLkIcBFcaLbigta3Oops+p8EQx9MebXC4edF5oAhVAHL9DmVyIiNLY9wXXp2xBGQUwMmywH/XUuh4JsBrjk0XJYoqimwQ1u3eV4og8oLNSZbZDnn1L0otTH0y6diR32zQZGVxj/4ta065V9OqwI0l4FBIDAikgtNBzioS8bKE=",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhyhyQA/IeqDljZvDCcNuwdBPsU32r1WH+rkkng1A/jFJhqEyOhKoyNe5H5zCGTSPG4aNFT3bXBGQxPct/uWMxO7Qv5iPZ2/qr3NwznxVcau1slq+R85VxyzkozgIh01et6yRv96Tzb8crrg3uFIXqip1hCOTb5Bs8Bz/PI02WDP+iGBBesd+Pjso/kDGpPTV25OU/W5N0DEyeT3q3olXoZYiqw22aV1IvOhdSPgobw+IMrF2Jtf+t+akvvUKyMcYt+aZr5wJCA+pXbxTIvt/zeC0itw+Vv5ABH4lIQ9h7hV00HGDKe0KwJ+R4FcaOegj7QWQdxlfX6FtN/62z9czfQIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+alipay.getOutTradeno()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+alipay.getTotalAmount()+"," +
                "    \"subject\":\""+alipay.getSubject()+"\"," +
                "    \"body\":\""+alipay.getBody()+"\"," +
                /*"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +*/
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipay.getSuid()+"\"" +
                "    }"+
                "  }");//填充业务参数
        alipayRequest.setReturnUrl(alipay.getReturnurl());
        alipayRequest.setNotifyUrl(alipay.getNotifyurl());//在公共参数中设置回跳和通知地址
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
    }
}
