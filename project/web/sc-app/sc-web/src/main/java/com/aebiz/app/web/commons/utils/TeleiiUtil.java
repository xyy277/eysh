package com.aebiz.app.web.commons.utils;

import com.aebiz.app.web.commons.base.Globals;
import org.apache.commons.lang3.StringUtils;
import org.nutz.http.Http;
import org.nutz.json.Json;
import org.nutz.json.JsonException;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.HashMap;
import java.util.Map;

/**
 * teleii 虚拟号码工具类
 * Created by zyang on 2017/9/8.
 */
public class TeleiiUtil {

    private static Log log = Logs.get();

    /**
     * 虚拟号的全局配置
     *
     * @param config
     * @return
     * @throws JsonException
     */
    public static Map config(TeleiiConfig config) throws JsonException {
        String url = Globals.TELEII_API_SERVER + "/setSpInfo.do";
        log.debug(String.format("url=[%s]", url));
        Map params = new HashMap();
        params.put("id", config.getId());
        // 时间戳（用户请求的北京时间）（毫秒数），时间戳作为标识防止并发读取 | YES
        params.put("timestamp", config.getTimestamp());
        params.put("notifyCallUrl", config.getNotifyCallUrl());
        params.put("notifyMtReceUrl", config.getNotifyMtReceUrl());
        params.put("notifyMoUrl", config.getNotifyMoUrl());
        params.put("notifyAudioStatusUrl", config.getNotifyAudioStatusUrl());
        params.put("notifyMediaStreamUrl", config.getNotifyMediaStreamUrl());
        params.put("notifyAuthStatusUrl", config.getNotifyAuthStatusUrl());
        params.put("notifySecretRelationshipUrl", config.getNotifySecretRelationshipUrl());
        params.put("syncRelationshipUrl", config.getSyncRelationshipUrl());
        params.put("sign", config.generateSign());
        String content = Http.post(url, params, Globals.TELEII_TIMEOUT * 1000);
        Map<String, Object> result = Json.fromJson(Map.class, content);
        log.debug(String.format("result=[%s]", result));
        return result;
    }

    /**
     * 绑定虚拟号
     *
     * @param fm       拨打者的号码
     * @param tm       要拨打至的号码
     * @param vm       虚拟号（可传空）
     * @param bindTime 绑定的时间（分钟）
     * @param scene    场景
     * @return 返回Map集合<br />
     * result 错误码 0为成功<br />
     * error 错误信息 <br />
     * virtualMobile 绑定的虚拟号（如果传入了vm就是vm）<br />
     * startTime endTime 请求的时间（时间戳）<br />
     * @throws JsonException
     */
    public static Map bind(String fm, String tm, String vm, String bindTime, String scene) throws JsonException {
        // 请求地址
        String url = Globals.TELEII_API_SERVER + "/autoCallTransferForSp.do";
        log.debug(String.format("url=[%s]", url));
        // 需求参数
        Map params = new HashMap<String, String>();
        // 接入商id，唯一，由平台方提供 | YES
        String id = Globals.TELEII_APPID;
        String key = Globals.TELEII_APPKEY;
        long timestamp = System.currentTimeMillis();
        // 接入商平台生成的标识，保证一段时间内不重复，
        // 用于异步消息通知时的对应关系 最大64字节
        String seqId = R.UU32();
        int timeout = Globals.TELEII_TIMEOUT > 0 ? Globals.TELEII_TIMEOUT * 1000 : 30 * 1000;
        // 生成规则 Md5(key+id+seqId+timestamp+fm+tm)；
        String sign = StringUtil.getMD5(key + id + seqId + timestamp + fm + tm);
        params.put("id", id);
        params.put("timestamp", timestamp + "");
        params.put("seqId", seqId);
        params.put("fm", fm);
        if (!StringUtils.isEmpty(vm)) params.put("virtualMobile", vm);
        params.put("tm", tm);
        if (!StringUtils.isEmpty(bindTime)) params.put("bindTime", bindTime);
        if (!StringUtils.isEmpty(scene)) params.put("scene", scene);
        params.put("sign", sign);
        String content = Http.post(url, params, timeout);
        Map<String, Object> result = Json.fromJson(Map.class, content);
        log.debug(String.format("result=[%s]", result));
        return result;
    }


    /**
     * 解绑
     *
     * @param fm 拨打者的号码
     * @param tm 拨打至的号码
     * @param vm 要解绑的虚拟号
     * @return 返回Map集合<br />
     * result 错误码 0为成功<br />
     * error 错误信息 <br />
     * @throws JsonException
     */
    public static Map unbind(String fm, String tm, String vm) throws JsonException {
        // 请求地址
        String url = Globals.TELEII_API_SERVER + "/unbindCallTransferForSp.do";
        log.debug(String.format("url=[%s]", url));
        // 需求参数
        Map params = new HashMap<String, String>();
        String id = Globals.TELEII_APPID;
        String key = Globals.TELEII_APPKEY;
        long timestamp = System.currentTimeMillis();
        int timeout = Globals.TELEII_TIMEOUT > 0 ? Globals.TELEII_TIMEOUT * 1000 : 30 * 1000;
        // 生成规则 Md5(key+id+seqId+timestamp+fm+tm)；
        String sign = StringUtil.getMD5(key + id + timestamp + fm + tm + vm);
        params.put("id", id);
        params.put("timestamp", timestamp + "");
        params.put("fm", fm);
        params.put("tm", tm);
        params.put("vm", vm);
        params.put("sign", sign);
        String content = Http.post(url, params, timeout);
        Map<String, Object> result = Json.fromJson(Map.class, content);
        log.debug(String.format("result=[%s]", result));
        return result;
    }
}
