package com.aebiz.app.sys.modules.services.impl;

import com.aebiz.app.sys.modules.models.Sys_dict;
import com.aebiz.app.sys.modules.services.SysDictService;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.lang.Strings;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by mac on 2017/1/19.
 */
@Service
@CacheConfig(cacheNames = "sysCache")
public class SysDictServiceImpl extends BaseServiceImpl<Sys_dict> implements SysDictService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    @CacheEvict(key = "#root.targetClass.getName()+'*'")
    @Async
    public void clearCache() {

    }

    /**
     * 通过code获取name
     *
     * @param code
     * @return
     */
    @Cacheable
    public String getNameByCode(String code) {
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        return dict == null ? "" : dict.getName();
    }

    /**
     * 通过code获取id
     *
     * @param code
     * @return
     */
    @Cacheable
    public String getIdByCode(String code) {
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        return dict == null ? "" : dict.getId();
    }
    /**
     * 通过id获取name
     *
     * @param id
     * @return
     */
    @Cacheable
    public String getNameById(String id) {
        Sys_dict dict = this.fetch(id);
        return dict == null ? "" : dict.getName();
    }

    /**
     * 通过树path获取下级列表
     *
     * @param path
     * @return
     */
    @Cacheable
    public List<Sys_dict> getSubListByPath(String path) {
        return this.query(Cnd.where("path", "like", Strings.sNull(path) + "____").asc("location"));
    }

    /**
     * 通过父id获取下级列表
     *
     * @param id
     * @return
     */
    @Cacheable
    public List<Sys_dict> getSubListById(String id) {
        return this.query(Cnd.where("parentId", "=", Strings.sNull(id)).asc("location"));
    }

    /**
     * 通过code获取下级列表
     *
     * @param code
     * @return
     */
    @Cacheable
    public List<Sys_dict> getSubListByCode(String code) {
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        return dict == null ? new ArrayList<>() : this.query(Cnd.where("parentId", "=", Strings.sNull(dict.getId())).asc("location"));
    }

    /**
     * 通过path获取下级map
     *
     * @param path
     * @return
     */
    @Cacheable
    public Map getSubMapByPath(String path) {
        return this.getMap(Sqls.create("select code,name from sys_dict where path like @path order by location asc").setParam("path", path + "____"));
    }

    /**
     * 通过id获取下级map
     *
     * @param id
     * @return
     */
    @Cacheable
    public Map getSubMapById(String id) {
        return this.getMap(Sqls.create("select code,name from sys_dict where parentId = @id order by location asc").setParam("id", id));
    }

    /**
     * 通过code获取下级map
     *
     * @param code
     * @return
     */
    @Cacheable
    public Map getSubMapByCode(String code) {
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        return dict == null ? new HashMap() : this.getMap(Sqls.create("select code,name from sys_dict where parentId = @id order by location asc").setParam("id", dict.getId()));
    }
    /**
     * 通过树code获取所有的下级列表
     *
     * @param code
     * @return
     */
    @Cacheable
    public List<Sys_dict> getAllSubListByCode(String code) {
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        return dict == null ? new ArrayList<>() : this.query(Cnd.where("path", "like", Strings.sNull(dict.getPath()) + "____%").asc("location"));
    }
    /**
     * 新增
     *
     * @param dict
     * @param pid
     */
    @Transactional
    public void save(Sys_dict dict, String pid) {
        String path = "";
        if (!Strings.isEmpty(pid)) {
            Sys_dict pp = this.fetch(pid);
            path = pp.getPath();
        }
        dict.setPath(getSubPath("sys_dict", "path", path));
        dict.setParentId(pid);
        dao().insert(dict);
        if (!Strings.isEmpty(pid)) {
            this.update(Chain.make("hasChildren", true), Cnd.where("id", "=", pid));
        }
    }

    /**
     * 级联删除
     *
     * @param dict
     */
    @Transactional
    public void deleteAndChild(Sys_dict dict) {
        dao().execute(Sqls.create("delete from sys_dict where path like @path").setParam("path", dict.getPath() + "%"));
        if (!Strings.isEmpty(dict.getParentId())) {
            int count = count(Cnd.where("parentId", "=", dict.getParentId()));
            if (count < 1) {
                dao().execute(Sqls.create("update sys_dict set hasChildren=0 where id=@pid").setParam("pid", dict.getParentId()));
            }
        }
    }

    /**
     * 获取某一code下包含所有数据字典项map
     *
     * @param code
     * @return
     */
    @Cacheable
    @Override
    public Map getAllMapByCode(String code) {
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        return (dict == null ? new HashMap() : this.getMap(Sqls.create("select code,name from sys_dict where path like @path order by location asc").setParam("path", dict.getPath() + "_%")));
    }

    public List<Map<String, Object>> getDictList(String id){
        List<Sys_dict> list=this.query(Cnd.where("parentId","=",id));
        List<Map<String, Object>> resClaList = new ArrayList<>();
        for (Sys_dict dict : list) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", dict.getId());
            obj.put("code", dict.getCode());
            obj.put("text", dict.getName());
            obj.put("children", dict.isHasChildren());
            resClaList.add(obj);
        }
        return resClaList;
    }

    /**
     * 获取某一code包含的下级数据字典项name: code形式map
     *
     * @param code
     * @return
     */
    @Cacheable
    @Override
    public Map getSubCodeMap(String code) {
        HashMap map = new HashMap();
        Sys_dict dict = this.fetch(Cnd.where("code", "=", code));
        List<Sys_dict> list = this.query(Cnd.where("path", "like", dict.getPath() + "_%").asc("location"));

        if (list != null && list.size() > 0) {
            for (Sys_dict d : list) {
                map.put(Strings.sBlank(d.getName(), d.getId()), d.getCode());
            }
        }

        return map;
    }

}
