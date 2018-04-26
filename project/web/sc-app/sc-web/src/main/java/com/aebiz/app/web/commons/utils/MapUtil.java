package com.aebiz.app.web.commons.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: jxx
 * @date: 2017/7/12 14:26
 * map 工具类
 */
public class MapUtil {
    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
}

class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
