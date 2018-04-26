package com.aebiz.app.web.modules.controllers.platform.sys;

import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.models.em.ContentButtomEnum;
import com.aebiz.app.cms.modules.services.CmsChannelRoleService;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.app.sys.modules.models.Sys_menu;
import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.app.sys.modules.models.Sys_unit;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysMenuService;
import com.aebiz.app.sys.modules.services.SysRoleService;
import com.aebiz.app.sys.modules.services.SysUnitService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.page.datatable.DataTableColumn;
import com.aebiz.baseframework.page.datatable.DataTableOrder;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/platform/sys/role")
public class SysRoleController {
    private static final Log log = Logs.get();
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUnitService sysUnitService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsChannelRoleService cmsChannelRoleService;
    @Autowired
    private ShiroUtil shiroUtil;

    @RequestMapping("")
    @RequiresPermissions("sys.manager.role")
    public String index() {
        return "pages/platform/sys/role/index";
    }

    @RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("sys.manager.role")
    public Object data(@RequestParam(required = false) String unitid, DataTable dataTable) {
        Cnd cnd = Cnd.NEW();
        if (!Strings.isBlank(unitid) && !"root".equals(unitid)) {
            cnd.and("unitid", "=", unitid);
        }

        return sysRoleService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping(value = {"/add/{id}", "/add"})
    @RequiresPermissions("sys.manager.role")
    public Object add(@PathVariable(required = false) String id, HttpServletRequest req) {
        List<Sys_menu> list = new ArrayList<>();
        if (shiroUtil.hasRole("sysadmin")) {
            list = sysMenuService.query(Cnd.orderBy().asc("location").asc("path"));
        } else {
            Sql sql = Sqls.create("SELECT roleId FROM sys_user_role WHERE userId=@userId");
            sql.setParam("userId", StringUtil.getUid());
            sql.setCallback(Sqls.callback.strs());
            sysMenuService.dao().execute(sql);
            List<String> ids = sql.getList(String.class);
            Sql sql1 = Sqls.create("SELECT a.* FROM sys_menu a,sys_role_menu b WHERE a.id=b.menuId AND b.roleId in (@ids)");
            sql1.setParam("ids", ids.toArray());
            sql1.setEntity(sysMenuService.dao().getEntity(Sys_menu.class));
            sql1.setCallback(Sqls.callback.entities());
            list = sysMenuService.dao().execute(sql1).getList(Sys_menu.class);
        }
        List<Sys_menu> datas = sysRoleService.getDatas("");
        List<NutMap> menus = new ArrayList<>();
        for (Sys_menu menu : list) {
            NutMap map = new NutMap();
            for (Sys_menu bt : datas) {
                if (menu.getPath().equals(bt.getPath().substring(0, bt.getPath().length() - 4))) {
                    menu.setHasChildren(true);
                    break;
                }
            }

            map.put("id", menu.getId());
            map.put("text", menu.getName());
            map.put("icon", Strings.sBlank(menu.getIcon()));
            map.put("parent", "".equals(Strings.sNull(menu.getParentId())) ? "#" : menu.getParentId());
            map.put("data", menu.getHref());
            if (menu.getSystem().equals("platform")) {
                menus.add(map);

            }
        }

        req.setAttribute("menus", Json.toJson(menus));
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysUnitService.fetch(id));
        }

        return "pages/platform/sys/role/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @RequiresPermissions("sys.manager.role.add")//sys.manager.role.add
    @SLog(description = "Sys_role")
    public Object addDo(Sys_role sysRole, HttpServletRequest req) {
        String menuIds = req.getParameter("menuIds");
        if (Strings.isBlank(menuIds)) {
            return Result.error("菜单权限不能为空");
        }

        try {
            int num = sysRoleService.count(Cnd.where("code", "=", sysRole.getCode().trim()));
            if (num > 0) {
                return Result.error("sys.role.code");
            }

            String[] ids = StringUtils.split(menuIds, ",");
            if ("root".equals(sysRole.getUnitid())) {
                sysRole.setUnitid("");
            }

            Sys_role r = sysRoleService.insert(sysRole);
            for (String s : ids) {
                if (!Strings.isEmpty(s)) {
                    sysRoleService.insert("sys_role_menu", org.nutz.dao.Chain.make("roleId", r.getId()).add("menuId", s));
                }
            }

            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("sys.manager.role")
    public String edit(@PathVariable String id, HttpServletRequest req) {
        Sys_role role = sysRoleService.fetch(id);
        req.setAttribute("unit", sysUnitService.fetch(role.getUnitid()));
        req.setAttribute("obj", role);
        return "pages/platform/sys/role/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @RequiresPermissions("sys.manager.role.edit")
    @SLog(description = "Sys_role")
    public Object editDo(Sys_role sysRole, HttpServletRequest req) {
        String oldCode = req.getParameter("oldCode");
        try {
            if (!Strings.sBlank(oldCode).equals(sysRole.getCode())) {
                int num = sysRoleService.count(Cnd.where("code", "=", sysRole.getCode().trim()));
                if (num > 0) {
                    return Result.error("sys.role.code");
                }
            }

            if ("root".equals(sysRole.getUnitid()))
                sysRole.setUnitid("");
            sysRole.setOpBy(Strings.sNull(StringUtil.getUid()));
            sysRole.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysRoleService.updateIgnoreNull(sysRole);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @RequiresPermissions("sys.manager.role.delete")
    @SLog(description = "Sys_role")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids", required = false) String[] ids, HttpServletRequest req) {
        try {
            if (ids != null && ids.length > 0) {
                sysRoleService.del(ids);
            } else {
                sysRoleService.del(id);
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("sys.manager.role")
    public String detail(@PathVariable String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", sysRoleService.fetch(id));
        } else {
            req.setAttribute("obj", null);
        }
        return "pages/platform/sys/role/detail";
    }


    @RequestMapping(value = {"/menu", "/menu/{id}"})
    @RequiresPermissions("sys.manager.role")
    public Object menu(@PathVariable(required = false) String id, HttpServletRequest req) {
        Sys_role role = sysRoleService.fetch(id);
        List<Sys_menu> menus = sysRoleService.getMenusAndButtons(id);
        List<Sys_menu> datas = sysRoleService.getDatas(role.getId());
        List<Sys_menu> firstMenus = new ArrayList<>();
        List<Sys_menu> secondMenus = new ArrayList<>();
        for (Sys_menu menu : menus) {
            for (Sys_menu bt : datas) {
                if (menu.getPath().equals(bt.getPath().substring(0, bt.getPath().length() - 4))) {
                    menu.setHasChildren(true);
                    break;
                }
            }
            if (menu.getPath().length() == 4) {
                firstMenus.add(menu);
            } else {
                secondMenus.add(menu);
            }
        }

        req.setAttribute("userFirstMenus", firstMenus);
        req.setAttribute("userSecondMenus", secondMenus);
        req.setAttribute("jsonSecondMenus", Json.toJson(secondMenus));
        req.setAttribute("obj", role);
        return "pages/platform/sys/role/menu";
    }

    @RequestMapping(value = {"/tree", "/tree/{pid}"})
    @SJson
    @RequiresPermissions("sys.manager.role")
    public Object tree(@PathVariable(required = false) String pid) {

        List<Map<String, Object>> tree = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        List<Sys_unit> list = new ArrayList<>();
        if (shiroUtil.hasRole("sysadmin")) {
            Cnd cnd = Cnd.NEW();
            if (Strings.isBlank(pid)) {
                cnd.and("parentId", "=", "").or("parentId", "is", null);
            } else {
                cnd.and("parentId", "=", pid);
            }
            cnd.asc("path");
            list = sysUnitService.query(cnd);
            if (Strings.isBlank(pid)) {
                obj.put("id", "root");
                obj.put("text", "系统角色");
                obj.put("children", false);
                tree.add(obj);
            }
        } else {
            Sys_user user = (Sys_user) shiroUtil.getPrincipal();
            if (user != null && Strings.isBlank(pid)) {
                list = sysUnitService.query(Cnd.where("id", "=", user.getUnitid()).asc("path"));
            } else {
                Cnd cnd = Cnd.NEW();
                if (Strings.isBlank(pid)) {
                    cnd.and("parentId", "=", "").or("parentId", "is", null);
                } else {
                    cnd.and("parentId", "=", pid);
                }
                cnd.asc("path");
                list = sysUnitService.query(cnd);
            }
        }
        for (Sys_unit unit : list) {
            obj = new HashMap<>();
            obj.put("id", unit.getId());
            obj.put("text", unit.getName());
            if (!unit.isHasChildren()) {
                obj.put("icon", Globals.APP_BASE + "/assets/platform/images/icons/filetype/txt.gif");
            }
            obj.put("children", unit.isHasChildren());
            tree.add(obj);
        }

        return tree;
    }

    @RequestMapping("/editMenu/{roleId}")
    @RequiresPermissions("sys.manager.role")
    public Object editMenu(@PathVariable String roleId, HttpServletRequest req) {
        StringBuilder roleMenuIds = new StringBuilder();
        List<Sys_menu> list = new ArrayList<>();
        if (shiroUtil.hasRole("sysadmin")) {
            list = sysMenuService.query(Cnd.orderBy().asc("location").asc("path"));
        } else {
            Sql sql = Sqls.create("SELECT roleId FROM sys_user_role WHERE userId=@userId");
            sql.setParam("userId", StringUtil.getUid());
            sql.setCallback(Sqls.callback.strs());
            sysMenuService.dao().execute(sql);
            List<String> ids = sql.getList(String.class);
            Sql sql1 = Sqls.create("SELECT a.* FROM sys_menu a,sys_role_menu b WHERE a.id=b.menuId AND b.roleId in (@ids)");
            sql1.setParam("ids", ids.toArray());
            sql1.setEntity(sysMenuService.dao().getEntity(Sys_menu.class));
            sql1.setCallback(Sqls.callback.entities());
            list = sysMenuService.dao().execute(sql1).getList(Sys_menu.class);
        }
        List<Sys_menu> datas = sysRoleService.getDatas("");
        List<Sys_menu> roleMenu = sysRoleService.getMenusAndButtons(roleId);
        for (Sys_menu m : roleMenu) {
            roleMenuIds.append(m.getId() + "#");
        }

        String roleMenuId = roleMenuIds.toString();
        log.debug(roleMenuId);
        List<NutMap> menus = new ArrayList<>();
        for (Sys_menu menu : list) {
            NutMap map = new NutMap();
            for (Sys_menu bt : datas) {
                if (menu.getPath().equals(bt.getPath().substring(0, bt.getPath().length() - 4))) {
                    menu.setHasChildren(true);
                    break;
                }
            }
            map.put("id", menu.getId());
            map.put("text", menu.getName());
            map.put("icon", Strings.sBlank(menu.getIcon()));
            map.put("parent", "".equals(Strings.sNull(menu.getParentId())) ? "#" : menu.getParentId());
            map.put("data", menu.getHref());
            if ((menu.getPath().length() >= 16 || !menu.isHasChildren()) && roleMenuId.contains(menu.getId() + "#")) {
                map.put("state", NutMap.NEW().addv("selected", true));
            } else {
                map.put("state", NutMap.NEW().addv("selected", false));
            }

            if (menu.getSystem().equals("platform")) {
                menus.add(map);

            }
        }
        req.setAttribute("menus", Json.toJson(menus));
        if (!Strings.isBlank(roleId)) {
            req.setAttribute("obj", sysRoleService.fetch(roleId));
        }

        return "pages/platform/sys/role/editMenu";
    }

    @RequestMapping("/editMenuDo/{roleid}")
    @SJson
    @RequiresPermissions("sys.manager.role.menu")
    public Object editMenuDo(@RequestParam(required = false) String ids, @PathVariable String roleid, HttpServletRequest req) {
        try {
            Sys_role role = sysRoleService.fetch(roleid);
            if ("access".equalsIgnoreCase(role.getCode())) {
                return Result.error("您无法给" + role.getName() + "分配权限");
            }
            String[] idss = StringUtils.split(ids, ",");
            sysRoleService.dao().clear("sys_role_menu", Cnd.where("roleid", "=", roleid));
            for (String s : idss) {
                if (!Strings.isEmpty(s)) {
                    sysRoleService.insert("sys_role_menu", org.nutz.dao.Chain.make("roleId", roleid).add("menuId", s));
                }
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/editUser/{roleid}")
    @RequiresAuthentication
    @RequiresPermissions("sys.manager.role.user")
    public Object editUser(@PathVariable String roleid, HttpServletRequest req) {
        req.setAttribute("obj", sysRoleService.fetch(roleid));
        return "pages/platform/sys/role/editUser";
    }

    @RequestMapping("/userData")
    @SJson("full")
    @RequiresPermissions("sys.manager.role")
    public Object userData(@RequestParam("roleid") String roleid, @RequestParam("loginname") String loginname,
                           @RequestParam("username") String username, DataTable dataTable) {
        String sql = "SELECT a.* FROM sys_user a,sys_user_role b WHERE a.id=b.userId ";
        if (!Strings.isBlank(roleid)) {
            sql += " and b.roleId='" + roleid + "'";
        }

        if (!Strings.isBlank(loginname)) {
            sql += " and a.loginname like '%" + loginname + "%'";
        }

        if (!Strings.isBlank(username)) {
            sql += " and a.username like '%" + username + "%'";
        }

        String s = sql;
        if (dataTable.getOrders() != null && dataTable.getOrders().size() > 0 && dataTable.getColumns() != null && dataTable.getColumns().size() > 0) {
            for (DataTableOrder o : dataTable.getOrders()) {
                DataTableColumn col = dataTable.getColumns().get(o.getColumn());
                s += " order by a." + Sqls.escapeSqlFieldValue(col.getData()).toString() + " " + o.getDir();
            }
        }

        return sysRoleService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), Sqls.create(sql), Sqls.create(s));
    }

    @RequestMapping("/selectUser")
    @RequiresPermissions("sys.manager.role")
    public Object selectUser(HttpServletRequest req) {
        return "pages/platform/sys/role/selectUser";
    }

    @RequestMapping("/selectData")
    @SJson("full")
    @RequiresPermissions("sys.manager.role")
    public Object selectData(@RequestParam("roleid") String roleid,
                             @RequestParam("name") String name, @RequestParam("length") int length,
                             @RequestParam("start") int start, @RequestParam("draw") int draw,
                             ArrayList<DataTableOrder> order, ArrayList<DataTableColumn> columns) {
        String sql = "SELECT a.* FROM sys_user a WHERE 1=1 ";
        if (!Strings.isBlank(roleid)) {
            sql += " and a.id NOT IN(SELECT b.userId FROM sys_user_role b WHERE b.roleId='" + roleid + "')";
        }
        if (!Strings.isBlank(name)) {
            sql += " and (a.loginname like '%" + name + "%' or a.username like '%" + name + "%') ";
        }
        String s = sql;
        if (order != null && order.size() > 0) {
            for (DataTableOrder o : order) {
                DataTableColumn col = columns.get(o.getColumn());
                s += " order by a." + Sqls.escapeSqlFieldValue(col.getData()).toString() + " " + o.getDir();
            }
        }
        return sysRoleService.data(length, start, draw, Sqls.create(sql), Sqls.create(s));
    }

    @RequestMapping("/enable/{roleid}")
    @SJson
    @SLog(description = "系统角色")
    @RequiresPermissions("sys.manager.role.edit")
    public Object enable(@PathVariable String roleid, HttpServletRequest req) {
        try {
            sysRoleService.update(org.nutz.dao.Chain.make("disabled", false), Cnd.where("id", "=", roleid));
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/disable/{roleid}")
    @SJson
    @SLog(description = "系统角色")
    @RequiresPermissions("sys.manager.role.edit")
    public Object disable(@PathVariable String roleid, HttpServletRequest req) {
        try {
            sysRoleService.update(org.nutz.dao.Chain.make("disabled", true), Cnd.where("id", "=", roleid));
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/pushUser")
    @SJson
    @SLog(description = "添加用户到角色")
    @RequiresPermissions("sys.manager.role")
    public Object pushUser(@RequestParam("menuIds") String menuIds, @RequestParam("roleid") String roleid, HttpServletRequest req) {
        try {
            String[] ids = StringUtils.split(menuIds, ",");
            for (String s : ids) {
                if (!Strings.isEmpty(s)) {
                    sysRoleService.insert("sys_user_role", org.nutz.dao.Chain.make("roleId", roleid).add("userId", s));
                }
            }
            return Result.success("globals.result.success");

        } catch (Exception e) {
            return Result.error("globals.result.error");

        }
    }


    @RequestMapping("/delUser")
    @SJson
    @SLog(description = "从角色中删除用户")
    @RequiresPermissions("sys.manager.role")
    public Object delUser(@RequestParam("menuIds") String menuIds, @RequestParam("roleid") String roleid, HttpServletRequest req) {
        try {
            String[] ids = StringUtils.split(menuIds, ",");
            sysRoleService.dao().clear("sys_user_role", Cnd.where("userId", "in", ids).and("roleId", "=", roleid));
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("sglobals.result.error");
        }
    }

    /**
     * CMS权限分配页面
     *
     * @param roleId 角色id
     * @param req
     * @return
     */
    @RequestMapping("/editCmsMenu/{roleId}")
    @RequiresPermissions("sys.manager.role")
    public Object editCmsMenu(@PathVariable String roleId, HttpServletRequest req) {
        //查询所有的一级目录
        List<Cms_site> list = cmsSiteService.query(Cnd.where("1", "=", 1).asc("location"));
        List<String> siteIds = new ArrayList<>();
        for (Cms_site cms_site : list) {
            siteIds.add(cms_site.getId());
        }
        Map<String, List<String>> channelids = new HashMap<>();
        List<Record> cms_channels = new ArrayList<>();
        if (siteIds != null && siteIds.size() > 0) {
            Sql sql = Sqls.create("select id,site_id,name,hasChildren,parentId from cms_channel a ");
            cms_channels = cmsChannelService.list(sql);
        }
        List<NutMap> menus = new ArrayList<>();
        for (Cms_site menu : list) {
            NutMap map = new NutMap();
            String id = menu.getId();
            map.put("id", id);
            map.put("text", menu.getSite_name());
            map.put("data", "");
            map.put("parent", "#");
            map.put("state", NutMap.NEW().addv("selected", false));
            menus.add(map);
        }
        if (cms_channels != null && cms_channels.size() > 0) {
            for (Record record : cms_channels) {
                NutMap map = new NutMap();
                String id = record.getString("id");
                String site_id = record.getString("site_id");
                map.put("id", id);
                map.put("text", record.getString("name"));
                map.put("data", "");
                map.put("state", NutMap.NEW().addv("selected", false));
                map.put("parent", Strings.sNull(record.get("parentId")).length() == 0 ? site_id : record.get("parentId"));
                menus.add(map);

                Sql sql = Sqls.create("select distinct b.channel_id,b.site_id,b.role_id,b.HAS_ADD,b.HAS_UPDATE,b.HAS_DEL,b.HAS_MOVE,b.HAS_CHECK,b.HAS_STATIC,b.HAS_PUSH,b.HAS_SHARE from  " +
                        "   cms_channel a,cms_channel_role b where a.site_id=b.site_id and a.id=b.channel_id and b.role_id =@roleid and a.site_id=@siteID");
                sql.params().set("roleid", roleId);
                sql.params().set("siteID", site_id);
                List<Record> roleList = cmsChannelRoleService.list(sql);
                boolean has_add = false;
                boolean getHas_update = false;
                boolean has_del = false;
                boolean has_move = false;
                boolean has_check = false;
                boolean has_static = false;
                boolean has_push = false;
                // 详细权限中的 checked 标记位
                for (int j = 0, sum = roleList.size(); j < sum; j++) {
                    if (roleList.get(j).getString("channel_id").equals(id)) {
                        if (checkObj(roleList.get(j).getString("has_add"))) has_add = true;
                        if (checkObj(roleList.get(j).getString("has_update"))) getHas_update = true;
                        if (checkObj(roleList.get(j).getString("has_del"))) has_del = true;
                        if (checkObj(roleList.get(j).getString("has_move"))) has_move = true;
                        if (checkObj(roleList.get(j).getString("has_check"))) has_check = true;
                        if (checkObj(roleList.get(j).getString("has_static"))) has_static = true;
                        if (checkObj(roleList.get(j).getString("has_push"))) has_push = true;
                    }
                }
                //新增
                this.getContentButtom(menus, ContentButtomEnum.BTNADD.getKey(), ContentButtomEnum.BTNADD.getValue(), id, site_id, has_add);
                //修改
                this.getContentButtom(menus, ContentButtomEnum.BTNUPDATE.getKey(), ContentButtomEnum.BTNUPDATE.getValue(), id, site_id, getHas_update);
                //删除
                this.getContentButtom(menus, ContentButtomEnum.BTNDEL.getKey(), ContentButtomEnum.BTNDEL.getValue(), id, site_id, has_del);
                //移动
                this.getContentButtom(menus, ContentButtomEnum.BTNMOVE.getKey(), ContentButtomEnum.BTNMOVE.getValue(), id, site_id, has_move);
                //审核
                this.getContentButtom(menus, ContentButtomEnum.BTNCHECK.getKey(), ContentButtomEnum.BTNCHECK.getValue(), id, site_id, has_check);
                //发布
                this.getContentButtom(menus, ContentButtomEnum.BTNSTATIC.getKey(), ContentButtomEnum.BTNSTATIC.getValue(), id, site_id, has_static);
                //推送
                this.getContentButtom(menus, ContentButtomEnum.BTNPUSH.getKey(), ContentButtomEnum.BTNPUSH.getValue(), id, site_id, has_push);

            }
        }
        req.setAttribute("menus", Json.toJson(menus));
        if (!Strings.isBlank(roleId)) {
            req.setAttribute("obj", sysRoleService.fetch(roleId));
        }
        return "pages/platform/sys/role/roleCmsMenu";
    }

    /**
     * CMS权限分配处理
     *
     * @param ids    选中的id
     * @param roleid 角色id
     * @param req
     * @return
     */
    @RequestMapping("/editCmsMenuDo/{roleid}")
    @SJson
    @RequiresPermissions("sys.manager.role.cmsmenu")
    public Object editCmsMenuDo(@RequestParam(required = false) String ids, @PathVariable String roleid, HttpServletRequest req) {
        try {
            String[] newIds = new String[ids.length()];
            List<String> channel_ids = new ArrayList<>();
            String[] idss = StringUtils.split(ids, ",");
            Map<String, String> btnMap = new HashMap<>();
            Map<String, String> siteIdMap = new HashMap<>();
            for (int i = 0; i < idss.length; i++) {
                if (idss[i].contains(ContentButtomEnum.BTNADD.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNADD.getKey());
                } else if (idss[i].contains(ContentButtomEnum.BTNUPDATE.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNUPDATE.getKey());
                } else if (idss[i].contains(ContentButtomEnum.BTNDEL.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNDEL.getKey());
                } else if (idss[i].contains(ContentButtomEnum.BTNMOVE.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNMOVE.getKey());
                } else if (idss[i].contains(ContentButtomEnum.BTNCHECK.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNCHECK.getKey());
                } else if (idss[i].contains(ContentButtomEnum.BTNSTATIC.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNSTATIC.getKey());
                } else if (idss[i].contains(ContentButtomEnum.BTNPUSH.getKey())) {
                    this.getBtnDate(idss, channel_ids, btnMap, siteIdMap, i, ContentButtomEnum.BTNPUSH.getKey());
                } else {
                    newIds[i] = idss[i];
                }
            }
            sysRoleService.editCmsMenuDo(roleid, channel_ids, siteIdMap, btnMap, newIds);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 用于判断用户权限,为"Y"则返回true.
     *
     * @param roleStr
     * @return
     */
    private boolean checkObj(String roleStr) {
        return "Y".equals(roleStr);
    }

    /**
     * 组装数据，CMS解析按钮权限
     *
     * @param idss
     * @param channel_ids
     * @param btnMap
     * @param siteIdMap
     * @param i
     * @param btnType
     */
    private void getBtnDate(String[] idss, List<String> channel_ids, Map<String, String> btnMap, Map<String, String> siteIdMap, Integer i, String btnType) {
        String channelIdAndSiteId = idss[i].replace(btnType, "");
        String channelId = channelIdAndSiteId.split(":")[0];
        if (!channel_ids.contains(channelId)) {
            channel_ids.add(channelId);
        }
        btnMap.put(btnType + channelId, btnType);
        String siteId = channelIdAndSiteId.split(":")[1];
        siteIdMap.put(channelId, siteId);
    }

    /**
     * 组装文章按钮权限
     *
     * @param menus       组装后的list
     * @param buttomType  按钮key
     * @param buttomValue 按钮值
     * @param id          栏目id
     * @param site_id     站点id
     * @param hasType     按钮是否选中
     */
    private void getContentButtom(List<NutMap> menus, String buttomType, String buttomValue, String id, String site_id, boolean hasType) {
        NutMap mapButton = new NutMap();
        mapButton.put("id", buttomType + id + ":" + site_id);
        mapButton.put("text", buttomValue);
        mapButton.put("data", "");
        mapButton.put("state", NutMap.NEW().addv("selected", hasType ? true : false));
        mapButton.put("parent", id);
        menus.add(mapButton);
    }
}
