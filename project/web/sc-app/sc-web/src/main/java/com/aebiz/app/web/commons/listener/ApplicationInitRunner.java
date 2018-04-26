package com.aebiz.app.web.commons.listener;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.sms.modules.models.Sms_send_log;
import com.aebiz.app.sms.modules.models.Sms_tele_log;
import com.aebiz.app.sys.modules.models.*;
import com.aebiz.app.sys.modules.services.SysUnitService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.quartz.QuartzJob;
import com.aebiz.app.web.commons.quartz.QuartzManagerImpl;
import com.aebiz.baseframework.ig.RedisIdGenerator;
import com.aebiz.baseframework.redis.RedisService;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.SpringUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.el.opt.custom.CustomMake;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ServletContextAware;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 初始化系统
 * Created by wizzer on 2016/12/28.
 */
public class ApplicationInitRunner implements ApplicationContextAware, ServletContextAware,
        InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    private Log log = Logs.get();
    @Resource(name = "nutDao", type = Dao.class)
    private Dao dao;
    @Autowired
    private QuartzManagerImpl quartzManager;
    @Autowired
    private RedisService redisService;
    @Autowired
    private PropertiesProxy conf;
    @Autowired
    private ScAccountLinkService scAccountLinkService;
    @Autowired
    private SysUnitService sysUnitService;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        //为SpringBeans工具类applicationContext赋值
        SpringUtil.applicationContext = ctx;

    }

    @Override
    public void setServletContext(ServletContext context) {
        Globals.APP_BASE = context.getContextPath();
        Globals.APP_ROOT = context.getRealPath("/");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 初始化redis实现的id生成器
        CustomMake.me().register("ig", SpringUtil.getBean(RedisIdGenerator.class));
        //初始化订单号的生成器
        initDb();
        initSysRoute();
        initRabbit();
        initRedisIg();
        EhCacheManager cacheManager = SpringUtil.getBean(EhCacheManager.class);
        log.debug("Ehcache CacheManager = " + cacheManager.getCacheManager().getName());
        log.info("\n  ______     ____    __    ____  ___      .__   __.   _______ \n" +
                " /  __  \\    \\   \\  /  \\  /   / /   \\     |  \\ |  |  /  _____|\n" +
                "|  |  |  |    \\   \\/    \\/   / /  ^  \\    |   \\|  | |  |  __  \n" +
                "|  |  |  |     \\            / /  /_\\  \\   |  . `  | |  | |_ | \n" +
                "|  `--'  '--.   \\    /\\    / /  _____  \\  |  |\\   | |  |__| | \n" +
                " \\_____\\_____\\   \\__/  \\__/ /__/     \\__\\ |__| \\__|  \\______| \n" +
                "                                                               ");
    }

    /**
     * 当项目启动的时候把表主键加载到redis缓存中
     */
    private void initRedisIg() {
        log.debug("====当项目启动的时候把表主键加载到redis缓存中====");
        long a = System.currentTimeMillis();
        try (Jedis jedis = redisService.jedis()) {
            Sql sql;
            if ("mysql".equalsIgnoreCase(dao.getJdbcExpert().getDatabaseType())) {
                sql = Sqls.create("SELECT table_name FROM information_schema.columns WHERE table_schema='" + conf.get("db.name", "") + "' AND column_name='id'");
            } else {
                //oracle mssql该怎么写呢,等你来添加...
                log.info("wait for you ...");
                return;
            }
            sql.setCallback(Sqls.callback.strs());
            dao.execute(sql);
            List<String> tableNameList = sql.getList(String.class);
            for (String tableName : tableNameList) {
                List<Record> list = dao.query(tableName, Cnd.NEW().desc("id"), new Pager().setPageSize(1).setPageNumber(1));
                if (list.size() > 0) {
                    String id = list.get(0).getString("id");
                    if (Strings.isMatch(Pattern.compile("^.*[\\d]{16}$"), id)) {
                        String ym = id.substring(id.length() - 16, id.length() - 10);
                        if (Strings.isBlank(jedis.get("aebiz-ig:" + tableName.toUpperCase() + ym))) {
                            jedis.set("aebiz-ig:" + tableName.toUpperCase() + ym, String.valueOf(NumberUtils.toLong(id.substring(id.length() - 10, id.length()), 1)));
                        }
                    }
                }
            }
        }
        long b = System.currentTimeMillis();
        log.info("init redis ig time::" + (b - a) + "ms");
    }

    /**
     * 初始化表结构及系统表数据
     */
    private void initDb() {
        try {
            log.debug("====初始化数据库====");
            //初始化表结构
            Daos.createTablesInPackage(dao, "com.aebiz.app", false);
//            Daos.migration(dao, "com.aebiz.app", true, false);
            //根据实体类修改表结构,有风险的操作,注释掉吧 Daos.migration(dao, "com.aebiz.app", true, false);
            //项目启动时创建本月日志表,为了日志写入速度,需做定时任务每日检查并创建下个月日志表
            String tabelName = DateUtil.format(new Date(), "yyyyMM");
            Dao logDao = Daos.ext(dao, tabelName);
            //按月生产日志表
            if (!logDao.exists("sys_log_" + tabelName)) {
                logDao.create(Sys_log.class, false);
            }
            //按月生产短信发送日志表
            if (!logDao.exists("sms_send_log_" + tabelName)) {
                logDao.create(Sms_send_log.class, false);
            }
            //按月生产虚拟号码转移日志表
            if (!logDao.exists("sms_tele_log_" + tabelName)) {
                logDao.create(Sms_tele_log.class, false);
            }


            //初始化定时任务表数据
            if (0 == dao.count(Sys_user.class)) {
                //初始化配置表
                Sys_config conf = new Sys_config();
                conf.setConfigKey("APP_NAME");
                conf.setConfigValue("双创枢纽后台管理平台");
                conf.setNote("系统名称");
                dao.insert(conf);
                conf = new Sys_config();
                conf.setConfigKey("APP_SHORT_NAME");
                conf.setConfigValue("Qwwk");
                conf.setNote("系统短名称");
                dao.insert(conf);
                conf = new Sys_config();
                conf.setConfigKey("APP_DOMAIN");
                conf.setConfigValue("127.0.0.1");
                conf.setNote("系统域名");
                dao.insert(conf);
                conf = new Sys_config();
                conf.setConfigKey("APP_UPLOAD_PATH");
                conf.setConfigValue("/upload");
                conf.setNote("单机部署时文件上传路径");
                dao.insert(conf);
                //初始化单位
                Sys_unit unit = new Sys_unit();
                unit.setPath("0001");
                unit.setName("系统管理");
                unit.setAliasName("System");
                unit.setLocation(0);
                unit.setAddress("银河-太阳系-地球");
                unit.setEmail("wizzer@qq.com");
                unit.setTelephone("");
                unit.setUnitcode("");
                unit.setNote("");
                unit.setHasChildren(false);
                unit.setParentId("");
                unit.setWebsite("http://www.qwang.com.cn");
                Sys_unit dbunit = dao.insert(unit);
                //初始化菜单
                List<Sys_menu> menuList = new ArrayList<Sys_menu>();
                Sys_menu menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001");
                menu.setName("系统");
                menu.setNote("系统");
                menu.setSystem("platform");
                menu.setAliasName("System");
                menu.setIcon("");
                menu.setLocation(0);
                menu.setHref("");
                menu.setTarget("");
                menu.setIsShow(true);
                menu.setHasChildren(true);
                menu.setParentId("");
                menu.setType("menu");
                menu.setPermission("sys");
                Sys_menu m0 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("00010001");
                menu.setName("系统管理");
                menu.setNote("系统管理");
                menu.setSystem("platform");
                menu.setAliasName("Manager");
                menu.setIcon("ti-settings");
                menu.setLocation(0);
                menu.setHref("");
                menu.setTarget("");
                menu.setIsShow(true);
                menu.setHasChildren(true);
                menu.setParentId(m0.getId());
                menu.setType("menu");
                menu.setPermission("sys.manager");
                Sys_menu m1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010001");
                menu.setName("单位管理");
                menu.setSystem("platform");
                menu.setAliasName("Unit");
                menu.setLocation(0);
                menu.setHref("/platform/sys/unit");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.unit");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100010001");
                menu.setName("添加单位");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(0);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.unit.add");
                menu.setParentId(m2.getId());
                menu.setType("data");
                Sys_menu m21 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100010002");
                menu.setName("修改单位");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(0);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.unit.edit");
                menu.setParentId(m2.getId());
                menu.setType("data");
                Sys_menu m22 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100010003");
                menu.setName("删除单位");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(0);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.unit.delete");
                menu.setParentId(m2.getId());
                menu.setType("data");
                Sys_menu m23 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010002");
                menu.setName("用户管理");
                menu.setSystem("platform");
                menu.setAliasName("User");
                menu.setLocation(0);
                menu.setHref("/platform/sys/user");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.user");
                menu.setHasChildren(false);
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100020001");
                menu.setName("添加用户");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(0);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.user.add");
                menu.setParentId(m3.getId());
                menu.setType("data");
                Sys_menu m31 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100020002");
                menu.setName("修改用户");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.user.edit");
                menu.setParentId(m3.getId());
                menu.setType("data");
                Sys_menu m32 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100020003");
                menu.setName("删除用户");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.user.delete");
                menu.setParentId(m3.getId());
                menu.setType("data");
                Sys_menu m33 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010003");
                menu.setName("角色管理");
                menu.setSystem("platform");
                menu.setAliasName("Role");
                menu.setLocation(0);
                menu.setHref("/platform/sys/role");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.role");
                menu.setTarget("data-pjax");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m4 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100030001");
                menu.setName("添加角色");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.role.add");
                menu.setParentId(m4.getId());
                menu.setType("data");
                Sys_menu m41 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100030002");
                menu.setName("修改角色");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.role.edit");
                menu.setParentId(m4.getId());
                menu.setType("data");
                Sys_menu m42 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100030003");
                menu.setName("删除角色");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.role.delete");
                menu.setParentId(m4.getId());
                menu.setType("data");
                Sys_menu m43 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100030004");
                menu.setName("分配菜单");
                menu.setSystem("platform");
                menu.setAliasName("SetMenu");
                menu.setLocation(4);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.role.menu");
                menu.setParentId(m4.getId());
                menu.setType("data");
                Sys_menu m44 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100030005");
                menu.setName("分配用户");
                menu.setSystem("platform");
                menu.setAliasName("SetUser");
                menu.setLocation(5);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.role.user");
                menu.setParentId(m4.getId());
                menu.setType("data");
                Sys_menu m45 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100030006");
                menu.setName("分配CMS权限");
                menu.setSystem("platform");
                menu.setAliasName("SetCmsMenu");
                menu.setLocation(6);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.role.cmsmenu");
                menu.setParentId(m4.getId());
                menu.setType("data");
                Sys_menu m46 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010004");
                menu.setName("菜单管理");
                menu.setSystem("platform");
                menu.setAliasName("Menu");
                menu.setLocation(0);
                menu.setHref("/platform/sys/menu");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.menu");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m5 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100040001");
                menu.setName("添加菜单");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.menu.add");
                menu.setParentId(m5.getId());
                menu.setType("data");
                Sys_menu m51 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100040002");
                menu.setName("修改菜单");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.menu.edit");
                menu.setParentId(m5.getId());
                menu.setType("data");
                Sys_menu m52 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100040003");
                menu.setName("删除菜单");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.menu.delete");
                menu.setParentId(m5.getId());
                menu.setType("data");
                Sys_menu m53 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010005");
                menu.setName("系统参数");
                menu.setSystem("platform");
                menu.setAliasName("Param");
                menu.setLocation(0);
                menu.setHref("/platform/sys/conf");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.conf");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m6 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100050001");
                menu.setName("添加参数");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.conf.add");
                menu.setParentId(m6.getId());
                menu.setType("data");
                Sys_menu m61 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100050002");
                menu.setName("修改参数");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.conf.edit");
                menu.setParentId(m6.getId());
                menu.setType("data");
                Sys_menu m62 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100050003");
                menu.setName("删除参数");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.conf.delete");
                menu.setParentId(m6.getId());
                menu.setType("data");
                Sys_menu m63 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010006");
                menu.setName("日志管理");
                menu.setSystem("platform");
                menu.setAliasName("Log");
                menu.setLocation(0);
                menu.setHref("/platform/sys/log");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.log");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m7 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100060001");
                menu.setName("清除日志");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.log.delete");
                menu.setParentId(m7.getId());
                menu.setType("data");
                Sys_menu m71 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010007");
                menu.setName("定时任务");
                menu.setSystem("platform");
                menu.setAliasName("Task");
                menu.setLocation(0);
                menu.setHref("/platform/sys/task");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.task");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m8 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100070001");
                menu.setName("添加任务");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.task.add");
                menu.setParentId(m8.getId());
                menu.setType("data");
                Sys_menu m81 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100070002");
                menu.setName("修改任务");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.task.edit");
                menu.setParentId(m8.getId());
                menu.setType("data");
                Sys_menu m82 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100070003");
                menu.setName("删除任务");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.task.delete");
                menu.setParentId(m8.getId());
                menu.setType("data");
                Sys_menu m83 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010008");
                menu.setName("自定义路由");
                menu.setSystem("platform");
                menu.setAliasName("Route");
                menu.setLocation(0);
                menu.setHref("/platform/sys/route");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.route");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu m9 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100080001");
                menu.setName("添加路由");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.route.add");
                menu.setParentId(m9.getId());
                menu.setType("data");
                Sys_menu m91 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100080002");
                menu.setName("修改路由");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.route.edit");
                menu.setParentId(m9.getId());
                menu.setType("data");
                Sys_menu m92 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100080003");
                menu.setName("删除路由");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.route.delete");
                menu.setParentId(m9.getId());
                menu.setType("data");
                Sys_menu m93 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010009");
                menu.setName("应用管理");
                menu.setSystem("platform");
                menu.setAliasName("App");
                menu.setLocation(0);
                menu.setHref("/platform/sys/api");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.api");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu mm1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100090001");
                menu.setName("添加应用");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.api.add");
                menu.setParentId(mm1.getId());
                menu.setType("data");
                Sys_menu mm2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100090002");
                menu.setName("修改应用");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.api.edit");
                menu.setParentId(mm1.getId());
                menu.setType("data");
                Sys_menu mm3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100090003");
                menu.setName("删除应用");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.api.delete");
                menu.setParentId(mm1.getId());
                menu.setType("data");
                Sys_menu mm4 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010010");
                menu.setName("数据字典");
                menu.setSystem("platform");
                menu.setAliasName("Dict");
                menu.setLocation(0);
                menu.setHref("/platform/sys/dict");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.manager.dict");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu d = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100100001");
                menu.setName("添加字典");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.dict.add");
                menu.setParentId(d.getId());
                menu.setType("data");
                Sys_menu d1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100100002");
                menu.setName("修改字典");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.dict.edit");
                menu.setParentId(d.getId());
                menu.setType("data");
                Sys_menu d2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100100003");
                menu.setName("删除字典");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.manager.dict.delete");
                menu.setParentId(d.getId());
                menu.setType("data");
                Sys_menu d3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100010011");
                menu.setName("地区设置");
                menu.setSystem("platform");
                menu.setAliasName("area");
                menu.setLocation(0);
                menu.setHref("/platform/sys/config/area");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sys.config.delivery.area");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu a = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100110001");
                menu.setName("添加地区");
                menu.setSystem("platform");
                menu.setAliasName("Add");
                menu.setLocation(1);
                menu.setIsShow(false);
                menu.setPermission("sys.config.delivery.area.add");
                menu.setParentId(a.getId());
                menu.setType("data");
                Sys_menu a1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100110002");
                menu.setName("修改地区");
                menu.setSystem("platform");
                menu.setAliasName("Edit");
                menu.setLocation(2);
                menu.setIsShow(false);
                menu.setPermission("sys.config.delivery.area.edit");
                menu.setParentId(a.getId());
                menu.setType("data");
                Sys_menu a2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100110003");
                menu.setName("删除地区");
                menu.setSystem("platform");
                menu.setAliasName("Delete");
                menu.setLocation(3);
                menu.setIsShow(false);
                menu.setPermission("sys.config.delivery.area.delete");
                menu.setParentId(a.getId());
                menu.setType("data");
                Sys_menu a3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("000100012");
                menu.setName("接口权限设置");
                menu.setSystem("platform");
                menu.setAliasName("ApiApp");
                menu.setLocation(0);
                menu.setHref("/platform/sys/api/unit");
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("platform.sys.api.unit");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu a4 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000120001");
                menu.setName("添加");
                menu.setSystem("platform");
                menu.setAliasName("ApiAppAdd");
                menu.setLocation(1);
                menu.setTarget("data-pjax");
                menu.setIsShow(false);
                menu.setPermission("platform.sys.api.unit.add");
                menu.setParentId(a4.getId());
                menu.setType("data");
                Sys_menu a41 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000120002");
                menu.setName("修改");
                menu.setSystem("platform");
                menu.setAliasName("ApiAppEdit");
                menu.setLocation(2);
                menu.setTarget("data-pjax");
                menu.setIsShow(false);
                menu.setPermission("platform.sys.api.unit.edit");
                menu.setParentId(a4.getId());
                menu.setType("data");
                Sys_menu a42 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000120003");
                menu.setName("删除");
                menu.setSystem("platform");
                menu.setAliasName("ApiAppDelete");
                menu.setLocation(3);
                menu.setTarget("data-pjax");
                menu.setIsShow(false);
                menu.setPermission("platform.sys.api.unit.delete");
                menu.setParentId(a4.getId());
                menu.setType("data");
                Sys_menu a43 = dao.insert(menu);

                //客户订阅管理菜单初始化
                menu = new Sys_menu();
                menu.setPath("000100010013");
                menu.setName("客户订阅管理");
                menu.setSystem("platform");
                menu.setAliasName("khdygl");
                menu.setLocation(482);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setDisabled(false);
                menu.setHasChildren(false);
                menu.setHref("/platform/sys/subscribe");
                menu.setPermission("platform.sys.subscribe");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                Sys_menu a44 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100130001");
                menu.setName("导出");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sys.subscribe.exp");
                menu.setParentId(a44.getId());
                menu.setType("data");
                Sys_menu a45 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100130002");
                menu.setName("修改");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sys.subscribe.edit");
                menu.setParentId(a44.getId());
                menu.setType("data");
                Sys_menu a46 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setDisabled(false);
                menu.setPath("0001000100130003");
                menu.setName("删除");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sys.subscribe.delete");
                menu.setParentId(a44.getId());
                menu.setType("data");
                Sys_menu a47 = dao.insert(menu);

                //短信管理菜单初始化
                menu = new Sys_menu();
                menu.setPath("00010002");
                menu.setName("短信管理");
                menu.setSystem("platform");
                menu.setAliasName("dxgl");
                menu.setLocation(106);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("sms.manager");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(true);
                Sys_menu sms1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("000100020001");
                menu.setName("短信模板管理");
                menu.setSystem("platform");
                menu.setAliasName("dxmbgl");
                menu.setLocation(107);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("platform.sms.mould.info");
                menu.setHref("/platform/sms/mould/info");
                menu.setParentId(sms1.getId());
                menu.setType("menu");
                menu.setHasChildren(false);
                menu.setDisabled(false);
                Sys_menu sms2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000200010001");
                menu.setName("新增");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sms.mould.info.add");
                menu.setParentId(sms2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu sms3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000200010002");
                menu.setName("删除");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sms.mould.info.delete");
                menu.setParentId(sms2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu sms4 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000200010003");
                menu.setName("修改");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sms.mould.info.edit");
                menu.setParentId(sms2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu sms5 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("000100020002");
                menu.setName("发送记录");
                menu.setSystem("platform");
                menu.setAliasName("fsjl");
                menu.setLocation(108);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setHasChildren(false);
                menu.setPermission("platform.sms.send.log");
                menu.setHref("/platform/sms/send/log");
                menu.setParentId(sms1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                Sys_menu sms6 = dao.insert(menu);

                //初始化虚拟号码管理菜单
                menu = new Sys_menu();
                menu.setPath("00010003");
                menu.setName("虚拟号码管理");
                menu.setSystem("platform");
                menu.setAliasName("xnhmgl");
                menu.setLocation(109);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("tele");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(true);
                Sys_menu tele1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("000100030001");
                menu.setName("虚拟号码配置");
                menu.setSystem("platform");
                menu.setAliasName("xnhmpz");
                menu.setLocation(110);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("platform.sms.tele.info");
                menu.setHref("/platform/sms/tele/info");
                menu.setParentId(tele1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(false);
                Sys_menu tele2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000300010001");
                menu.setName("新增");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sms.tele.info.add");
                menu.setParentId(tele2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu tele3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000300010002");
                menu.setName("删除");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sms.tele.info.delete");
                menu.setParentId(tele2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu tele4 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000300010003");
                menu.setName("修改");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("platform.sms.tele.info.edit");
                menu.setParentId(tele2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu tele5 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("000100030002");
                menu.setName("虚拟号码使用日志");
                menu.setSystem("platform");
                menu.setAliasName("fsjl");
                menu.setLocation(111);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("platform.sms.tele.log");
                menu.setHref("/platform/sms/tele/log");
                menu.setParentId(tele1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(false);
                Sys_menu tele6 = dao.insert(menu);

                //初始化服务机构管理菜单
                menu = new Sys_menu();
                menu.setPath("00010004");
                menu.setName("服务机构");
                menu.setSystem("platform");
                menu.setAliasName("Accuser Setting");
                menu.setLocation(113);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("accuser.config");
                menu.setParentId(m1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(true);
                Sys_menu acc1 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("000100040001");
                menu.setName("机构角色");
                menu.setSystem("platform");
                menu.setAliasName("Accuser Role");
                menu.setLocation(114);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("accuser.config.role");
                menu.setHref("/platform/accuser/config/role");
                menu.setParentId(acc1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(false);
                Sys_menu acc2 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000400010001");
                menu.setName("添加角色");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("accuser.config.role.add");
                menu.setParentId(acc2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu acc3 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000400010002");
                menu.setName("修改角色");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("accuser.config.role.edit");
                menu.setParentId(acc2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu acc4 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000400010003");
                menu.setName("删除角色");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("accuser.config.role.delete");
                menu.setParentId(acc2.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu acc5 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("000100040002");
                menu.setName("机构菜单");
                menu.setSystem("platform");
                menu.setAliasName("Accuser Menu");
                menu.setLocation(115);
                menu.setTarget("data-pjax");
                menu.setIsShow(true);
                menu.setPermission("accuser.config.menu");
                menu.setHref("/platform/accuser/config/menu");
                menu.setParentId(acc1.getId());
                menu.setType("menu");
                menu.setDisabled(false);
                menu.setHasChildren(false);
                Sys_menu acc6 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000400020001");
                menu.setName("添加菜单");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("accuser.config.menu.add");
                menu.setParentId(acc6.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu acc7 = dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000400020002");
                menu.setName("修改菜单");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("accuser.config.menu.edit");
                menu.setParentId(acc6.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu acc8= dao.insert(menu);
                menu = new Sys_menu();
                menu.setPath("0001000400020003");
                menu.setName("删除菜单");
                menu.setSystem("platform");
                menu.setIsShow(false);
                menu.setPermission("accuser.config.menu.delete");
                menu.setParentId(acc6.getId());
                menu.setType("data");
                menu.setDisabled(false);
                Sys_menu acc9 = dao.insert(menu);

                //初始化角色
                Sys_role role = new Sys_role();
                role.setName("公共角色");
                role.setCode("public");
                menu.setSystem("platform");
                role.setAliasName("Public");
                role.setNote("All user has role");
                role.setUnitid(dbunit.getId());
                role.setDisabled(false);
                dao.insert(role);
                role = new Sys_role();
                role.setName("系统管理员");
                role.setCode("sysadmin");
                menu.setSystem("platform");
                role.setAliasName("Sysadmin");
                role.setNote("System Admin");
                role.setUnitid(dbunit.getId());
                role.setMenus(menuList);
                role.setDisabled(false);
                Sys_role dbrole = dao.insert(role);
                //初始化用户
                Sys_user user = new Sys_user();
                user.setLoginname("superadmin");
                user.setUsername("超级管理员");
                user.setOpAt(DateUtil.getTime(new Date()));
                RandomNumberGenerator rng = new SecureRandomNumberGenerator();
                String salt = rng.nextBytes().toBase64();
                String hashedPasswordBase64 = new Sha256Hash("1", salt, 1024).toBase64();
                user.setSalt(salt);
                user.setPassword(hashedPasswordBase64);
                user.setLoginIp("127.0.0.1");
                user.setLoginAt(0);
                user.setLoginCount(0);
                user.setEmail("chenquanlai@qwang.com.cn");
                user.setLoginTheme("palette.css");
                user.setLoginBoxed(false);
                user.setLoginScroll(false);
                user.setLoginSidebar(false);
                user.setLoginPjax(true);
                user.setUnitid(dbunit.getId());
                Sys_user dbuser = dao.insert(user);
                dao.insert("sys_user_unit", Chain.make("userId", dbuser.getId()).add("unitId", dbunit.getId()));
                dao.insert("sys_user_role", Chain.make("userId", dbuser.getId()).add("roleId", dbrole.getId()));
                //执行Quartz SQL脚本
                String dbType = dao.getJdbcExpert().getDatabaseType();
                log.debug("dbType:::" + dbType);
                FileSqlManager fmq = new FileSqlManager("quartz/" + dbType.toLowerCase() + ".sql");
                List<Sql> sqlListq = fmq.createCombo(fmq.keys());
                Sql[] sqlsq = sqlListq.toArray(new Sql[sqlListq.size()]);
                for (Sql sql : sqlsq) {
                    dao.execute(sql);
                }
                //执行SQL脚本
                FileSqlManager fm = new FileSqlManager("sql/" + dbType.toLowerCase()+"/");
                List<Sql> sqlList = fm.createCombo(fm.keys());
                dao.execute(sqlList.toArray(new Sql[sqlList.size()]));
                Sys_task task = new Sys_task();
                task.setStatus(0);
                task.setType(Sys_task.TypeEnum.SYSTEM);
                task.setName("日志表检查");
                task.setJobClass("SLogMonthTableCreateJob");
                task.setCron("0 59 23 L * ?");
                task.setData("{}");
                task.setNote("每个月最后一天执行");
                dao.insert(task);

                //电子券配置更新定时任务
                task = new Sys_task();
                task.setStatus(0);
                task.setType(Sys_task.TypeEnum.SYSTEM);
                task.setName("电子券配置更新");
                task.setJobClass("com.aebiz.app.web.commons.quartz.job.ScCouponConfigUpdateJob");
                task.setCron("0 0 0 * * ?");
                task.setData("{}");
                task.setNote("每天0点执行");
                dao.insert(task);
                //统一将菜单与角色进行关联
                dao.execute(Sqls.create("INSERT INTO sys_role_menu(roleId,menuId) SELECT @roleId,id FROM sys_menu where system='platform'").setParam("roleId", dbrole.getId()));

            }
            if (0 == dao.count(Sc_account_user.class)) {
                Sys_unit unit = sysUnitService.fetch(Cnd.where("name", "=","系统管理"));
                //初始化用户
                Sc_account_user user = new Sc_account_user();
                user.setLoginname("superadmin");
                user.setNickname("超级管理员");
                user.setOpAt(DateUtil.getTime(new Date()));
                RandomNumberGenerator rng = new SecureRandomNumberGenerator();
                String salt = rng.nextBytes().toBase64();
                String hashedPasswordBase64 = new Sha256Hash("1", salt, 1024).toBase64();
                user.setSalt(salt);
                user.setPassword(hashedPasswordBase64);
                user.setLast_ip("127.0.0.1");
                user.setLogin_num(0);
                user.setEmail("chenquanlai@qwang.com.cn");
                Sc_account_user dbuser = dao.insert(user);
                Sc_account_link linkUser = new Sc_account_link();
                linkUser.setAccount_id(dbuser.getId());
                linkUser.setTable_id("");
                scAccountLinkService.insert(linkUser);
            }

            //初始化系统参数
            Globals.initSysConfig(dao);
            //初始化自定义路由
            Globals.initRoute(dao);
            //启动时清除任务(不影响集群任务)
            quartzManager.clear();
            List<Sys_task> taskList = dao.query(Sys_task.class, Cnd.where("status", "=", 0));
            for (Sys_task sysTask : taskList) {
                try {
                    QuartzJob qj = new QuartzJob();
                    qj.setJobName(sysTask.getId());
                    qj.setJobGroup(sysTask.getId());
                    qj.setClassName(sysTask.getJobClass());
                    qj.setCron(sysTask.getCron());
                    qj.setComment(sysTask.getNote());
                    qj.setDataMap(sysTask.getData());
                    quartzManager.add(qj);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("====初始化数据库结束====");
    }

    /**
     * 初始化自定义路由
     */
    private void initSysRoute() {
        log.debug("====初始化自定义路由====");
        if (0 == dao.count(Sys_route.class)) {
            //路由示例
            Sys_route route = new Sys_route();
            route.setDisabled(false);
            route.setUrl("/sysadmin");
            route.setToUrl("/platform/login");
            route.setType("hide");
            dao.insert(route);
        }
        Globals.initRoute(dao);
        log.debug("====初始化自定义路由结束====");
    }

    /**
     * 初始化队列,用于集群部署时的数据更新
     */
    private void initRabbit() {
        try {
            log.debug("====初始化队列====");
            if (SpringUtil.isRabbitEnabled()) {
                RabbitAdmin rabbitAdmin = SpringUtil.getBean("rabbitAdmin", RabbitAdmin.class);
                Queue queue = new Queue(R.UU32(), true, true, false);
                rabbitAdmin.declareQueue(queue);
                FanoutExchange fanoutExchange = SpringUtil.getBean("fanoutExchange", FanoutExchange.class);
                Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, fanoutExchange.getName(), "", new HashMap<>());
                rabbitAdmin.declareBinding(binding);
                SimpleMessageListenerContainer listenerContainer = SpringUtil.getBean("listenerContainer", SimpleMessageListenerContainer.class);
                listenerContainer.addQueues(queue);
                log.debug("QueueNames::" + Json.toJson(listenerContainer.getQueueNames()));
                log.debug("====初始化队列结束====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
