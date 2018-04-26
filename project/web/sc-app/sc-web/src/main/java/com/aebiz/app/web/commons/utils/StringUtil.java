package com.aebiz.app.web.commons.utils;

import org.nutz.lang.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: jxx
 * @date: 2017/7/10 10:23
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil extends com.aebiz.commons.utils.StringUtil {
    /**
     * 将对象转换为字符型
     *
     * @param s
     */
    public static String null2String(Object s) {
        return s == null || s.equals("null") || s.equals("NULL") ? "" : s.toString();
    }

    /**
     * 返回文件大小，单位MB
     *
     * @param filesize
     * @param scale
     * @return
     */
    public static double getFileSizeKB(long filesize, int scale) {
        BigDecimal bd1 = new BigDecimal(Long.toString(filesize));
        BigDecimal bd2 = new BigDecimal(Long.toString(1024));
        return bd1.divide
                (bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 从发的request请求的头信息里获取客户端IP地址
     *
     * @param request
     * @return ip 客户端IP地址
     */

    public static String getClientIPAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        /*
         * 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length() = 15
         */
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 获取本地机器IP地址
     *
     * @return 本地IP地址
     */
    public static String getLocalIPAddress() {
        InetAddress inet = null;
        try {
            inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inet.getHostAddress();
    }

    /**
     * 获得MD5加密字符串
     *
     * @param source 源字符串
     * @return 加密后的字符串
     */
    public static String getMD5(String source) {
        String mdString = null;
        if (source != null) {
            try {
                //关键是这句
                mdString = getMD5(source.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return mdString.toLowerCase();
    }

    /**
     * 获得MD5加密字符串
     *
     * @param source 源字节数组
     * @return 加密后的字符串
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        final int temp = 0xf;
        final int arraySize = 32;
        final int strLen = 16;
        final int offset = 4;
        try {
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte[] tmp = md.digest();
            char[] str = new char[arraySize];
            int k = 0;
            for (int i = 0; i < strLen; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> offset & temp];
                str[k++] = hexDigits[byte0 & temp];
            }
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @see StringUtil#str2int(java.lang.String, int)
     */
    public static int str2int(String str) {
        return str2int(str, 0);
    }

    /**
     * 字符串转int
     *
     * @param str        要转换的字符串
     * @param defaultVal 默认值
     * @return
     */
    public static int str2int(String str, int defaultVal) {
        int r = 0;
        try {
            r = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            r = defaultVal;
        }
        return r;
    }

    public static List<String> getMonthBetween(String minDate, String maxDate) {
        try {
            ArrayList<String> result = new ArrayList<String>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 判断字符串是否代表指定精度的小数
     *
     * @param str       待判断字符串
     * @param precision 小数点位数
     * @return
     */
    public static boolean isDecimal(String str, int precision) {
        if (Strings.isBlank(str) || precision <= 0) {
            return false;
        }
        String regStr = "^(([1-9]\\d*|0)\\.\\d{" + precision + "})$";
        Pattern p = Pattern.compile(regStr);
        return Strings.isMatch(p, str);
    }

}
