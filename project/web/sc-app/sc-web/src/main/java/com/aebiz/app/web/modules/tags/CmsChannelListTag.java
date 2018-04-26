package com.aebiz.app.web.modules.tags;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zyang on 2017/7/25.
 */
@Component
@Scope("prototype")
public class CmsChannelListTag extends GeneralVarTagBinding {
    @Autowired
    private CmsChannelService cmsChannelService;

    @Override
    public void render() {
        String channelId = Strings.sNull(this.getAttributeValue("channelId"));
        String channelName = Strings.sNull(this.getAttributeValue("channelName"));
        String siteId = Strings.sNull(this.getAttributeValue("siteId"));
        String count = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("count")));
        String parentId = Strings.sNull(this.getAttributeValue("parentId"));
        String isDisplay = Strings.sNull(this.getAttributeValue("isDisplay"));
        String orderBy = Strings.sNull(this.getAttributeValue("orderBy"));
        Cnd cnd = Cnd.NEW();
        if (channelId != null && channelId.trim() != "") {
            cnd.and("id", "=", channelId);
        } else {
            if (channelName != null && channelName.trim() != "") {
                cnd.and("name", "=", channelName);
            }
            if (siteId != null && !"".equals(siteId)) {
                cnd.and("site_id", "=", siteId);
            }
            if (isDisplay != null && isDisplay.trim() != "") {
                cnd.and("is_display", "=", Boolean.parseBoolean(isDisplay) ? "Y" : "N");
            }
            if (parentId != null && parentId.trim() != "") {
                cnd.and("parentId", "=", parentId);
            } else {
                cnd.and(Cnd.exps("parentId", "=", "").or("parentId", "is", null));
            }
        }
        // 排序
        if (!StringUtils.isEmpty(orderBy)) {
            orderBy = orderBy.trim();
            String mode = "";
            String order = orderBy;
            if (StringUtils.contains(orderBy, ",")) {
                mode = StringUtils.substringAfterLast(orderBy, ",");
                order = StringUtils.substringBeforeLast(orderBy, ",");
            }
            if (StringUtils.isEmpty(mode)) {
                mode = "asc";
            }
            cnd.orderBy(order, mode);
        }

        List<Cms_channel> list = cmsChannelService.query(cnd);
        if(Strings.isNotBlank(count)){
            Integer cou = Integer.valueOf(count);
            for (int i=0;i<cou;i++){
                this.binds(list.get(i));
                this.doBodyRender();
            }
        }else{
            for (Cms_channel channel : list) {
                this.binds(channel);
                this.doBodyRender();
            }
        }
    }
}
