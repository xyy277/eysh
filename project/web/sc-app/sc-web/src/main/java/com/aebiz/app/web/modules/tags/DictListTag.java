package com.aebiz.app.web.modules.tags;

import com.aebiz.app.sys.modules.services.SysAreaService;
import com.aebiz.app.sys.modules.services.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典标签，用于查询字典项
 * Created by zyang on 2017/9/16.
 */
@Component
@Scope("prototype")
public class DictListTag extends GeneralVarTagBinding {
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysAreaService sysAreaService;

    @Override
    public void render() {
        // 字典类型 area地区 dict数据字典
        String type = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("type")));
        String id = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("id")));
        String code = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("code")));
        String isAll = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("isAll")));
        List list = new ArrayList();
        switch (type) {
            case "area":
                if (Boolean.parseBoolean(isAll)) {
                    list = sysAreaService.getShopAreaList();
                } else {
                    list = sysAreaService.getAreaNodeList(code);
                }
                break;
            case "dict":
                if (Boolean.parseBoolean(isAll)) {
                    list = sysDictService.getAllSubListByCode(code);
                } else {
                    if (!"".equals(id))
                        list = sysDictService.getSubListById(id);
                    else
                        list = sysDictService.getSubListByCode(code);
                }
                break;
        }
        this.binds(list);
        this.doBodyRender();
    }
}
