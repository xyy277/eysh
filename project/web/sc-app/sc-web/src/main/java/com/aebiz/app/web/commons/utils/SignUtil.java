package com.aebiz.app.web.commons.utils;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: tony
 * @date: 2017/8/15 14:26
 * 数据校验处理
 */
public class SignUtil {
    /**
     * 根据参数生成指定的签名
     * 使用 Map按key进行排序
     *
     * @param params
     * @return
     */
    public static String createSign(String key, Map<String, Object> params) {
        Map<String, Object> map = MapUtil.sortMapByKey(params);
        StringBuffer sb = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String k = it.next();
            String v = Strings.sBlank(map.get(k));
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k) && !"fileList".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = Lang.md5(sb.toString());
        return sign;
    }
}

