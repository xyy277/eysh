package com.aebiz.app.web.commons.utils;


import com.aebiz.app.web.commons.base.Globals;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created on 17/6/7. 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可) 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar 2:aliyun-java-sdk-dysmsapi.jar
 * 
 * 备注:Demo工程编码采用UTF-8 国际短信发送请勿参照此DEMO
 */
@Component
public class SmsUtil {

	public static SendSmsResponse sendSms(String phone, String tempcode, Map<String,Object> map) throws ClientException {
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		//regionId,根据服务器所在区域进行选择,可选的有cn-beijing, cn-qingdao, cn-hangzhou, cn-hongkong, cn-shenzhen, us-west-1
		IClientProfile profile = DefaultProfile.getProfile(Globals.ALIDAYU_SEVER, Globals.ALIDAYU_APPKEY, Globals.ALIDAYU_SECRET);
		DefaultProfile.addEndpoint(Globals.ALIDAYU_SEVER, Globals.ALIDAYU_SEVER, Globals.ALIDAYU_PRODUCT, Globals.ALIDAYU_DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(Globals.ALIDAYU_SIGNNAME);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(tempcode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${number}"时,此处的值为
		Set set=map.keySet();
		Iterator it=set.iterator();
		while(it.hasNext()){
			Object key=it.next();
			Object value=map.get(key);
			request.setTemplateParam("{\"" + key + "\":\"" + value + "\"}");
		}
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		return sendSmsResponse;
	}


	public static void main(String[] args) throws ClientException,
			InterruptedException {

		// 发短信
		Map<String,Object> map=new HashMap<>();
		map.put("number","八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述八项规定，激浊扬清之剑——党的十八大以来以习近平同志为核心的党中央贯彻执行八项规定、推动作风建设综述");
        SendSmsResponse response = sendSms("15155518671", "SMS_100760048", map);
        //根据返回的response.getCode() != null && response.getCode().equals("OK"),则发送成功
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());

		Thread.sleep(3000L);

		// 查明细
		/*if (response.getCode() != null && response.getCode().equals("OK")) {
			QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response
					.getBizId());
			System.out.println("短信明细查询接口返回数据----------------");
			System.out.println("Code=" + querySendDetailsResponse.getCode());
			System.out.println("Message="
					+ querySendDetailsResponse.getMessage());
			int i = 0;
			for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse
					.getSmsSendDetailDTOs()) {
				System.out.println("SmsSendDetailDTO[" + i + "]:");
				System.out.println("Content=" + smsSendDetailDTO.getContent());
				System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
				System.out.println("OutId=" + smsSendDetailDTO.getOutId());
				System.out
						.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
				System.out.println("ReceiveDate="
						+ smsSendDetailDTO.getReceiveDate());
				System.out
						.println("SendDate=" + smsSendDetailDTO.getSendDate());
				System.out.println("SendStatus="
						+ smsSendDetailDTO.getSendStatus());
				System.out.println("Template="
						+ smsSendDetailDTO.getTemplateCode());
			}
			System.out.println("TotalCount="
					+ querySendDetailsResponse.getTotalCount());
			System.out.println("RequestId="
					+ querySendDetailsResponse.getRequestId());
		}*/

	}

}
