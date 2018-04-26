package com.aebiz.app.web.commons.activemq;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.app.sys.modules.models.Sys_unit;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysRoleService;
import com.aebiz.app.sys.modules.services.SysUnitService;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.em.SSOUserTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息监听器
 */
public class MessageReceiver implements SessionAwareMessageListener {

    private Destination destination;

    @Autowired
    private ScAccountLinkService scAccountLinkService;
    @Autowired
    private ScAccountUserService scAccountUserService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUnitService sysUnitService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private PropertiesProxy config;
    private static final Log log = Logs.get();

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        // 操作的结果
        boolean synchRes = false;

        try {
            Map<String, String> data = this.getSynchParam(message);

            log.debug("data=" + data);
            //同步类型 user表示同步用户 org表示同步组织机构 userSort用户排序 orgSort 机构排序
            String flag = message.getStringProperty("flag");
            //操作类型 1 表示增加 0 表示修改 -1表示删除
            String operType = message.getStringProperty("operType");
            data.put("operType", operType);
            String id = data.get("id");
            if (StringUtils.isBlank(flag) || StringUtils.isBlank(operType) || StringUtils.isBlank(id)) {
                log.debug("异常：参数为空！");
                throw new RuntimeException("参数为空！");
            }
            switch (flag) {
                case "user":
                    log.info("同步用户");
                    // TODO 同步用户
                    synchRes = syncUser(message);
                    break;
                case "userSort":
                    log.info("用户排序");
                    // TODO 用户排序
                    synchRes = sortUser(data);
                    break;
                case "org":
                    log.info("同步组织机构");
                    // TODO 同步组织机构
                    synchRes = syncOrg(message);
                    break;
                case "orgSort":
                    log.info("机构排序");
                    // TODO 机构排序
                    synchRes = sortOrg(data);
                    break;
                default:
                    log.debug(flag);
                    break;
            }

            // 返回应答消息
            TextMessage text = session.createTextMessage();
            text.setStringProperty("sysNo", "1");
            if (synchRes) {
                text.setBooleanProperty("flag", true);
            } else {
                text.setBooleanProperty("flag", false);
            }

            text.setJMSCorrelationID(message.getJMSCorrelationID());
            session.createProducer(message.getJMSReplyTo()).send(text);
        } catch (Exception e) {
            log.error("Exception=", e);
            log.debug("message=" + message);
        }
    }

    /**
     * 获取消息转为Map
     *
     * @param message
     * @return
     * @throws Exception
     */
    private Map<String, String> getSynchParam(Message message) throws Exception {
        StringBuffer sb = new StringBuffer();
        Map<String, String> data = new HashMap();
        if (message instanceof MapMessage) {
            MapMessage map = (MapMessage) message;
            Enumeration enumer = map.getMapNames();

            while (enumer.hasMoreElements()) {
                String name = (String) enumer.nextElement();
                data.put(name, map.getString(name));
                sb.append(name + ":" + map.getString(name)).append("\n");
            }
            log.debug("sb=" + sb.toString());
        }

        return data;
    }

    /**
     * 用户同步
     *
     * @param message
     * @return
     */
    private boolean syncUser(Message message) {
        if (Boolean.parseBoolean(config.remove("localAdd"))) {
            log.debug("不走消息队列添加用户");
            return true;
        }
        Map<String, String> data = null;
        try {
            data = this.getSynchParam(message);
            //操作类型 1 表示增加 0 表示修改 -1表示删除
            String operType = message.getStringProperty("operType");
            data.put("operType", operType);
            if ("-1".equals(operType)) {
                // TODO 删除用户
                return deleteUser(data);
            } else {
                // TODO 增加或修改用户
                return saveUser(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加或修改用户
     *
     * @param data
     * @return
     */
    private boolean saveUser(Map<String, String> data) {
        //操作类型 1 表示增加 0 表示修改 -1表示删除
        String operType = data.get("operType");
        if ("1".equals(operType)) {
            return addUser(data);
        } else {
            return updateUser(data);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    private synchronized boolean addUser(Map data) {
        // 获取数据
        String name = Strings.sNull(data.get("name"));
        String type = Strings.sNull(data.get("type"));
        String loginName = Strings.sNull(data.get("loginName"));
        String rootid = Strings.sNull(data.get("rootid"));
        String groupId = Strings.sNull(data.get("groupId"));
        String password = Strings.sNull(data.get("password"));
        String adminFlag = Strings.sNull(data.get("adminFlag"));
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();

        try {
            if (SSOUserTypeEnum.isAdmin(type)) {
                // 已有说明已通过本地添加过用户，就修改
                Sys_user user = sysUserService.fetch(Cnd.where("loginname", "=", loginName));
                if (user != null) {
                    return updateUser(data);
                } else {
                    user = new Sys_user();
                }
                user.setLoginname(loginName);
                user.setUsername(name);
                user.setPassword(hashedPasswordBase64);
                user.setSalt(salt);
                user.setUnitid(groupId);
                user = sysUserService.insert(user);
                // 标识已存入
                config.set("msuid." + loginName, user.getId());
                // 设置默认角色
                Sys_role role = sysRoleService.fetch(Cnd.where("code", "=", "default"));
                if (role != null) {
                    sysRoleService.insert("sys_user_role", Chain.make("roleId", role.getId()).add("userId", user.getId()));
                }
            } else if (SSOUserTypeEnum.isMemmber(type)) {
                // 已有就修改
                Sc_account_user accountUser = scAccountUserService.fetch(Cnd.where("loginname", "=", loginName));
                if (accountUser != null) {
                    return updateUser(data);
                } else {
                    accountUser = new Sc_account_user();
                }
                accountUser.setLoginname(loginName);
                accountUser.setNickname(name);
                accountUser.setPassword(hashedPasswordBase64);
                accountUser.setSalt(salt);
                accountUser.setDisabled("0".equals(data.get("valid")));
                accountUser = scAccountUserService.insert(accountUser);
                // 标识已存入
                config.set("msuid." + loginName, accountUser.getId());
                Sc_account_link accountLink = scAccountLinkService.fetch(Cnd.where("account_id", "=", accountUser.getId()));
                if (accountLink == null) {
                    accountLink = new Sc_account_link();
                }
                accountLink.setAccount_id(accountUser.getId());
                log.debug(String.format("从消息中添加的用户：{%s}", accountLink));
            }
            return true;
        } catch (Exception e) {
            log.error("\r\n异常：\r\n", e);
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    private boolean updateUser(Map data) {
        // 获取数据
        String name = Strings.sNull(data.get("name"));
        String loginName = Strings.sNull(data.get("loginName"));
        String rootid = Strings.sNull(data.get("rootid"));
        String password = Strings.sNull(data.get("password"));
        String type = Strings.sNull(data.get("type"));
        String adminFlag = Strings.sNull(data.get("adminFlag"));
        String groupId = Strings.sNull(data.get("groupId"));
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();
        try {
            if (SSOUserTypeEnum.isAdmin(type)) {
                Sys_user user = sysUserService.fetch(Cnd.where("loginname", "=", loginName));
                // 没有就添加
                if (user == null) {
                    return addUser(data);
                }
                user.setLoginname(loginName);
                user.setUsername(name);
                if (password != null || !"".equals(password)) {
                    user.setPassword(hashedPasswordBase64);
                    user.setSalt(salt);
                }
                user.setUnitid(groupId);
                user.setDisabled("0".equals(data.get("valid")));
                sysUserService.updateIgnoreNull(user);
            } else {
                Sc_account_user accountUser = scAccountUserService.fetch(Cnd.where("loginname", "=", loginName));
                // 没有就添加
                if (accountUser == null) {
                    return addUser(data);
                }
                accountUser.setLoginname(loginName);
                accountUser.setNickname(name);
                if (password != null || !"".equals(password)) {
                    accountUser.setPassword(hashedPasswordBase64);
                    accountUser.setSalt(salt);
                }
                accountUser.setDisabled("0".equals(data.get("valid")));
                scAccountUserService.updateIgnoreNull(accountUser);
            }
            return true;
        } catch (Exception e) {
            log.error("\r\n异常：\r\n", e);
            return false;
        }
    }

    /**
     * 删除用户
     *
     * @param data
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    private boolean deleteUser(Map<String, String> data) {
        String id = Strings.sNull(data.get("id"));
        String loginName = Strings.sNull(data.get("loginName"));
        boolean flag = false;
        try {
            Sys_user user = sysUserService.fetch(Cnd.where("loginname", "=", loginName).or("id", "=", id));
            if (user != null) {
                user.setDelFlag(true);
                sysUserService.updateIgnoreNull(user);
                flag = true;
            }
            Sc_account_user accountUser = scAccountUserService.fetch(Cnd.where("loginname", "=", loginName).or("id", "=", id));
            if (accountUser != null) {
                accountUser.setDelFlag(true);
                scAccountUserService.updateIgnoreNull(accountUser);
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 用户排序
     *
     * @param data
     * @return
     */
    private boolean sortUser(Map<String, String> data) {
        String json = data.get("data");
        List<Map> orders = Json.fromJsonAsList(Map.class, json);
        for (Map map : orders) {
            // 用户id
            String id = (String) map.get("id");
            // 排序序号
            String order_id = (String) map.get("order_id");
            // TODO 存储排序结果
            // .update(Chain.make("location","=",order_id),Cnd.where("id","=",id))
        }
        return false;
    }

    /**
     * 同步组织机构
     *
     * @param message
     * @return
     */
    private boolean syncOrg(Message message) {
        if (Boolean.parseBoolean(config.remove("localAddUnit"))) {
            log.debug("不走消息队列同步组织机构");
            return true;
        }
        Map data;
        try {
            data = this.getSynchParam(message);
            //操作类型 1 表示增加 0 表示修改 -1表示删除
            String operType = message.getStringProperty("operType");
            data.put("operType", operType);
            if ("-1".equals(operType)) {
                // TODO 删除组织机构
                return deleteOrg(data);
            } else {
                // TODO 增加或修改组织机构
                return saveOrg(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加或修改组织机构
     *
     * @param data
     * @return
     */
    private boolean saveOrg(Map<String, String> data) {
        //操作类型 1 表示增加 0 表示修改 -1表示删除
        String operType = data.get("operType");
        if ("1".equals(operType)) {
            return addUnit(data);
        } else {
            return updateUnit(data);
        }
    }

    public boolean addUnit(Map<String, String> data) {
        String name = data.get("name");
        String id = data.get("id");
        String parentId = data.get("parentId");
        String address = data.get("address");
        String telephone = data.get("telephone");
        String description = data.get("description");
        String code = data.get("code");
        Sys_unit unit = new Sys_unit();
        unit.setName(name);
        unit.setId(id);
        unit.setAddress(address);
        unit.setTelephone(telephone);
        unit.setNote(description);
        unit.setUnitcode(code);
        Sys_unit parentUnit = sysUnitService.fetch(parentId);
        if (parentUnit == null) parentId = "";
        try {
            sysUnitService.save(unit, parentId, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUnit(Map<String, String> data) {
        String name = data.get("name");
        String id = data.get("id");
        String parentId = data.get("parentId");
        String address = data.get("address");
        String telephone = data.get("telephone");
        String description = data.get("description");
        String code = data.get("code");
        Sys_unit unit = sysUnitService.fetch(Cnd.where("id", "=", id).and("delFlag", "=", false));
        try {
            if (unit == null) {
                return addUnit(data);
            }
            Sys_unit parentUnit = sysUnitService.fetch(parentId);
            if (parentUnit != null) {
                unit.setParentId(parentId);
            }
            unit.setName(name);
            unit.setAddress(address);
            unit.setTelephone(telephone);
            unit.setNote(description);
            unit.setUnitcode(code);
            unit.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysUnitService.updateIgnoreNull(unit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除单位
     *
     * @param data
     * @return
     */
    private boolean deleteOrg(Map<String, String> data) {
        String id = data.get("id");
        int r = sysUnitService.delete(id);
        return r > 0;
    }

    /**
     * 用户排序
     *
     * @param data
     * @return
     */
    private boolean sortOrg(Map<String, String> data) {
        return data.isEmpty();
    }

    public Destination getDestination() {
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
