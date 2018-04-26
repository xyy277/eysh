package com.aebiz.app.web.commons.base;

import com.aebiz.app.sys.modules.models.Sys_config;
import com.aebiz.app.sys.modules.models.Sys_route;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.weixin.impl.WxApi2Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wizzer on 2016/12/19.
 */
public class Globals {
    //项目路径
    public static String APP_ROOT = "";
    //项目目录
    public static String APP_BASE = "";
    //项目名称
    public static String APP_NAME = "系统01后台管理平台";
    //版权信息
    public static String APP_COPYRIGHT = "<www.sys01.com>";
    //框架版本
    public static String APP_VERSION = "1.0";
    //项目短名称
    public static String APP_SHORT_NAME = "Eysh";
    //项目域名
    public static String APP_DOMAIN = "127.0.0.1:8080";
    //项目IP
    public static String APP_IP = "127.0.0.1";
    //是否开启单点登陆
    public static String SSO_FLAG = "off";
    //前台网站title
    public static String SITE_NAME="Eysh";
    //静态页
    public static String STATIC_URL = "http://127.0.0.1:8080";
    //动态地址
    public static String SITE_URL = "http://127.0.0.1:8080";
    //文件上传路径
    public static String APP_UPLOAD_PATH = "/upload";
    //网站首页底部QQ在线咨询
    public static String SITE_INDEX_QQ = "907507646";
    //系统自定义参数
    public static Map<String, String> MyConfig=new HashMap<>();
    //自定义路由
    public static Map<String, Sys_route> RouteMap=new HashMap<>();
    //微信map
    public static Map<String, WxApi2Impl> WxMap=new HashMap<>();
    //生成二维码宽
    public static Integer QRCODE_WIDTH=200;
    //生成二维码高
    public static Integer QRCODE_HEIGHT=200;
    //阿里大于
    public static String ALIDAYU_APPKEY="LTAIsFytGmKa3uxb";
    public static String ALIDAYU_SECRET="ll3j1fetnMtUO8IcuXEkfdVhWqgfyL";
    public static String ALIDAYU_SIGNNAME="双创合肥";
    public static String ALIDAYU_PRODUCT = "Dysmsapi";    //产品名称:云通信短信API产品,开发者无需替换
    public static String ALIDAYU_DOMAIN = "dysmsapi.aliyuncs.com";//产品域名,开发者无需替换
    public static String ALIDAYU_SEVER = "cn-hangzhou";//根据服务器所在区域进行选择,可选的有cn-beijing, cn-qingdao, cn-hangzhou, cn-hongkong, cn-shenzhen, us-west-1
    // TELEII 虚拟关系号
    public static String TELEII_API_SERVER = "http://sandbox.teleii.com";
    public static String TELEII_APPID = "484";
    public static String TELEII_APPKEY = "DZI7SHK7LWQKTOHO035MLI1Z";
    public static Integer TELEII_TIMEOUT = 30;// 超时时间秒
    // 咨询电话
    public static String ADVISORY_TEL = "18949464266";

    public static void initSysConfig(Dao dao) {
        Globals.MyConfig.clear();
        List<Sys_config> configList = dao.query(Sys_config.class, Cnd.NEW());
        for (Sys_config sysConfig : configList) {
            switch (sysConfig.getConfigKey()) {
                case "APP_NAME":
                    Globals.APP_NAME = sysConfig.getConfigValue();
                    break;
                case "APP_SHORT_NAME":
                    Globals.APP_SHORT_NAME = sysConfig.getConfigValue();
                    break;
                case "APP_DOMAIN":
                    Globals.APP_DOMAIN = sysConfig.getConfigValue();
                    break;
                case "APP_UPLOAD_PATH":
                    Globals.APP_UPLOAD_PATH = sysConfig.getConfigValue();
                    break;
                case "SITE_INDEX_QQ":
                    Globals.SITE_INDEX_QQ = sysConfig.getConfigValue();
                    break;
                case "ADVISORY_TEL":
                    Globals.ADVISORY_TEL = sysConfig.getConfigValue();
                    break;
                case "SSO_FLAG":
                    Globals.SSO_FLAG = sysConfig.getConfigValue();
                    break;
                case "SITE_URL":
                    Globals.SITE_URL = sysConfig.getConfigValue();
                    break;
                case "STATIC_URL":
                    Globals.STATIC_URL = sysConfig.getConfigValue();
                    break;
                case "SITE_NAME":
                    Globals.SITE_NAME = sysConfig.getConfigValue();
                    break;
                default:
                    Globals.MyConfig.put(sysConfig.getConfigKey(), sysConfig.getConfigValue());
                    break;
            }
        }
    }

    public static void initRoute(Dao dao) {
        Globals.RouteMap.clear();
        List<Sys_route> routeList = dao.query(Sys_route.class, Cnd.where("disabled", "=", 0));
        for (Sys_route route : routeList) {
            Globals.RouteMap.put(route.getUrl(), route);
        }
    }
}
