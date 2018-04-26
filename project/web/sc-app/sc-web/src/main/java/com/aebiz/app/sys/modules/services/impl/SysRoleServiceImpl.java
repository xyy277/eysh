package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.app.cms.modules.models.Cms_channel_role;
import com.aebiz.app.cms.modules.models.em.ContentButtomEnum;
import com.aebiz.app.cms.modules.services.CmsChannelRoleService;
import com.aebiz.app.sys.modules.models.Sys_menu;
import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.app.sys.modules.services.SysRoleService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.commons.utils.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.Dao;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wizzer on 2016/12/22.
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<Sys_role> implements SysRoleService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    @Autowired
    private CmsChannelRoleService cmsChannelRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询权限
     *
     * @param role
     * @return
     */
    public List<String> getPermissionNameList(Sys_role role) {
        dao().fetchLinks(role, "menus");
        List<String> list = new ArrayList<String>();
        for (Sys_menu menu : role.getMenus()) {
            if (!Strings.isEmpty(menu.getPermission())&&!menu.isDisabled()) {
                list.add(menu.getPermission());
            }
        }
        return list;
    }

    public List<Sys_menu> getMenusAndButtons(String roleId) {
        Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.system='platform' and a.id=b.menuId and" +
                " b.roleId=@roleId and a.disabled=0 order by a.location ASC,a.path asc");
        sql.params().set("roleId", roleId);
        Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(Sys_menu.class);
    }

    public List<Sys_menu> getDatas(String id) {
        if (!Strings.isBlank(id)) {
            Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.system='platform' and a.id=b.menuId and" +
                    " b.roleId=@roleId and a.type='data' and  a.disabled=0 order by a.location ASC,a.path asc");
            sql.params().set("roleId", id);
            Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
            sql.setEntity(entity);
            sql.setCallback(Sqls.callback.entities());
            dao().execute(sql);
            return sql.getList(Sys_menu.class);
        }

        Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.system='platform' and a.id=b.menuId and a.type='data' order by a.location ASC,a.path asc");
        Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(Sys_menu.class);

    }
    public List<Sys_menu> getMenusAndButtons(String roleIds[]) {
        if(roleIds.length>0){
            Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.system='platform' and a.id=b.menuId and" +
                    " b.roleId in (@roleId) and a.disabled=0 order by a.location ASC,a.path asc");
            sql.params().set("roleId", roleIds);
            Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
            sql.setEntity(entity);
            sql.setCallback(Sqls.callback.entities());
            dao().execute(sql);
            return sql.getList(Sys_menu.class);
        }
       return  null;
    }
   public List<Sys_menu> getDatas(String roleIds[]){
       if (roleIds.length>0) {
           Sql sql = Sqls.create("select distinct a.* from sys_menu a,sys_role_menu b where a.system='platform' and a.id=b.menuId and" +
                   " b.roleId in (@roleId) and a.type='data' and  a.disabled=0 order by a.location ASC,a.path asc");
           sql.params().set("roleId", roleIds);
           Entity<Sys_menu> entity = dao().getEntity(Sys_menu.class);
           sql.setEntity(entity);
           sql.setCallback(Sqls.callback.entities());
           dao().execute(sql);
           return sql.getList(Sys_menu.class);
       }
     return null;
   }


    @Transactional
    public void del(String roleid) {
        this.dao().clear("sys_user_role", Cnd.where("roleId", "=", roleid));
        this.dao().clear("sys_role_menu", Cnd.where("roleId", "=", roleid));
        this.delete(roleid);
    }

    @Transactional
    public void del(String[] roleids) {
        this.dao().clear("sys_user_role", Cnd.where("roleId", "in", roleids));
        this.dao().clear("sys_role_menu", Cnd.where("roleId", "in", roleids));
        this.delete(roleids);
    }

    @Override
    @Transactional
    public void editCmsMenuDo(String roleId, List<String> channel_ids, Map<String, String> siteIdMap, Map<String, String> btnMap,String [] newIds) {
        cmsChannelRoleService.dao().clear("cms_channel_role",Cnd.where("role_id","=",roleId));
        for(String channelID:channel_ids){
            //栏目按钮关联更新
            String siteID= siteIdMap.get(channelID);
            int sum = cmsChannelRoleService.count(Cnd.where("role_id","=",roleId).and("channel_id","=",channelID).and("site_id","=",siteID));
            Cms_channel_role ccr = new Cms_channel_role();
            ccr.setRole_id(roleId);
            ccr.setSite_id(siteID);
            ccr.setChannel_id(channelID);
            ccr.setHas_add(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNADD.getKey()+channelID)).equals(ContentButtomEnum.BTNADD.getKey())?"Y":"N");
            ccr.setHas_check(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNCHECK.getKey()+channelID)).equals(ContentButtomEnum.BTNCHECK.getKey())?"Y":"N");
            ccr.setHas_update(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNUPDATE.getKey()+channelID)).equals(ContentButtomEnum.BTNUPDATE.getKey())?"Y":"N");
            ccr.setHas_move(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNMOVE.getKey()+channelID)).equals(ContentButtomEnum.BTNMOVE.getKey())?"Y":"N");
            ccr.setHas_push(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNPUSH.getKey()+channelID)).equals(ContentButtomEnum.BTNPUSH.getKey())?"Y":"N");
            ccr.setHas_del(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNDEL.getKey()+channelID)).equals(ContentButtomEnum.BTNDEL.getKey())?"Y":"N");
            ccr.setHas_static(Strings.sBlank(btnMap.get(ContentButtomEnum.BTNSTATIC.getKey()+channelID)).equals(ContentButtomEnum.BTNSTATIC.getKey())?"Y":"N");
            ccr.setOpBy(StringUtil.getUid());
            ccr.setOpAt((int) (System.currentTimeMillis() / 1000));
            if(sum>0){
                cmsChannelRoleService.dao().clear("cms_channel_role",Cnd.where("channel_id","=",channelID).and("site_id","=",siteID).and("role_id","=",roleId));
            }
            cmsChannelRoleService.insert(ccr);
        }
        //站点、栏目关联更新
        sysRoleService.dao().clear("cms_site_role", Cnd.where("roleid", "=", roleId));
        for (String s : newIds) {
            if (!Strings.isEmpty(s)) {
                sysRoleService.insert("cms_site_role", org.nutz.dao.Chain.make("roleId", roleId).add("siteId", s));
            }
        }
    }
}
