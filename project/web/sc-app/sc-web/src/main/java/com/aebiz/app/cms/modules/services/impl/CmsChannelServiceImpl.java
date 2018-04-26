package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.models.Cms_channel_model;
import com.aebiz.app.cms.modules.services.CmsChannelModelService;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsLogService;
import com.aebiz.app.cms.modules.services.StaticPageService;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CmsChannelServiceImpl extends BaseServiceImpl<Cms_channel> implements CmsChannelService {
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }
    @Autowired
    private CmsChannelModelService cmsChannelModelService;
    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private StaticPageService staticPageService;
    @Override
    @Transactional
    public void addDo(Cms_channel cmsChannel, List<Cms_channel_model> models, Sys_user user, String ip, String resUrl) {
        cmsChannel=dao().insert(cmsChannel);
        if (models!=null && models.size()>0) {
            for(Cms_channel_model m:models){
                m.setSite_id(cmsChannel.getSite_id());
                m.setChannel_id(cmsChannel.getId());
            }
            cmsChannelModelService.insert(models);
        }
        if (!Strings.isEmpty(cmsChannel.getParentId())) {
            this.update(Chain.make("hasChildren", true), Cnd.where("id", "=", cmsChannel.getParentId()));
        }
        cmsLogService.info("0", 2, cmsChannel.getSite_id(),"添加栏目", "channel_id:" + cmsChannel.getId() + ";channel_name:" + cmsChannel.getName(), user, ip,resUrl);
    }

    @Override
    @Transactional
    public void editDo(Cms_channel cmsChannel, List<Cms_channel_model> models, Sys_user user, String ip, String resUrl) throws Exception {
        cmsChannelModelService.deleteByCmsChannelId(cmsChannel.getId());
        this.updateIgnoreNull(cmsChannel);
        if (models!=null && models.size()>0) {
            cmsChannelModelService.insert(models);
        }
        if (!"".equals(cmsChannel.getLinkurl()) || "N".equals(cmsChannel.getIs_display())) {
            staticPageService.delChannelPage(cmsChannel);
        }
        cmsLogService.info("0", 2, cmsChannel.getSite_id(),"修改栏目", "channel_id:" + cmsChannel.getId() + ";channel_name:" + cmsChannel.getName(), user, ip,resUrl);
    }

    @Override
    @Transactional
    public void deleteAndChild(Cms_channel cms_channel,Sys_user user, String ip, String resUrl)throws Exception {
        cmsChannelModelService.deleteByCmsChannelId(cms_channel.getId());
        dao().execute(Sqls.create("delete from cms_channel where parentId = @parentId or id= @id")
                .setParam("parentId", cms_channel.getId())
                .setParam("id",cms_channel.getId()));
        if (!Strings.isEmpty(cms_channel.getParentId())) {
            int count = count(Cnd.where("parentId", "=", cms_channel.getParentId()));
            if (count < 1) {
                dao().execute(Sqls.create("update cms_channel set hasChildren=0 where id=@pid").setParam("pid",
                        cms_channel.getParentId()));
            }
        }
        staticPageService.delChannelPage(cms_channel);
        cmsLogService.info("0", 2, cms_channel.getSite_id(),"删除栏目", "channel_id:" + cms_channel.getId() + ";channel_name:" + cms_channel.getName(), user, ip,resUrl);
    }
}
