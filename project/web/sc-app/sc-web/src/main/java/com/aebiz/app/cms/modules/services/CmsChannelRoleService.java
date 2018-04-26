package com.aebiz.app.cms.modules.services;

import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.cms.modules.models.Cms_channel_role;

import java.util.HashMap;
import java.util.Map;

public interface CmsChannelRoleService extends BaseService<Cms_channel_role>{
    public static Map<String,String> zhName=new HashMap<>();
    public static void getButtonZH(){
      //  obj.put("res_button", "新增/BtnAdd;修改/BtnUpdate;删除/BtnDel;移动/BtnMove;审核/BtnCheck;发布/BtnStatic;复制/BtnPush;引用/BtnShare;");

    }
}
