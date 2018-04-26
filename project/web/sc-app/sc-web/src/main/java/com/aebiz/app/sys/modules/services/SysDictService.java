package com.aebiz.app.sys.modules.services;

import com.aebiz.app.sys.modules.models.Sys_dict;
import com.aebiz.baseframework.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by 王怀先 on 2017/1/19.
 */
public interface SysDictService extends BaseService<Sys_dict> {
    void clearCache();
    String getNameByCode(String code);
    String getIdByCode(String code);
    String getNameById(String id);
    List<Sys_dict> getSubListByPath(String path);
    List<Sys_dict> getSubListById(String id);
    List<Sys_dict> getSubListByCode(String code);
    List<Sys_dict> getAllSubListByCode(String code) ;
    Map getSubMapByPath(String path);
    Map getSubMapById(String id);
    Map getSubMapByCode(String code);
    void save(Sys_dict dict, String pid);
    void deleteAndChild(Sys_dict dict);

    /**
     * 获取某一code下包含所有数据字典项map
     * @param code
     * @return
     */
    Map getAllMapByCode(String code);

    /**
     * 根据数据字典id，获取字典的子集（用于三级联动或二级联动）
     * @param id
     * @return
     */
    public List<Map<String, Object>> getDictList(String id);


    /**
     * 获取某一code下包含的数据字典项code:name形式map
     * @param code
     * @return
     */
    Map getSubCodeMap(String code);
}
