package com.aebiz.app.web.modules.tags;

import com.aebiz.app.cms.modules.models.Cms_topic;
import com.aebiz.app.cms.modules.services.CmsTopicService;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zzhu on 2017/9/13.
 */
@Component
@Scope("prototype")
public class CmsTopicListTag extends GeneralVarTagBinding {
    @Autowired
    private CmsTopicService cmsTopicService;

    @Override
    public void render() {
        String channelId = Strings.sNull(this.getAttributeValue("channelId"));
        String siteId = Strings.sNull(this.getAttributeValue("siteId"));
        String excludeId = Strings.sNull(this.getAttributeValue("excludeId"));
        String count = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("count")));
        String page = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("page")));
        String orderBy = Strings.sNull(this.getAttributeValue("orderBy"));
        SimpleCriteria cri = Cnd.cri();
        if (!StringUtils.isEmpty(channelId)) {
            cri.where().and("channelIds", "like", "%"+channelId+"%");
        }
            if (siteId != null && siteId.trim() != "") {
                cri.where().and("site_id", "=", siteId);
            }
        if (excludeId != null && excludeId.trim() != "") {
            String[] ids = StringUtils.split(excludeId, ",");
            if (ids.length > 0) cri.where().and("id", "NOT in", ids);
        }
        // 排序
        if (!StringUtils.isEmpty(orderBy)) {
            String mode = "";
            String order = orderBy;
            if (StringUtils.contains(orderBy, ",")) {
                mode = StringUtils.substringAfterLast(orderBy, ",");
                order = StringUtils.substringBeforeLast(orderBy, ",");
            }
            if (StringUtils.isEmpty(mode)) {
                mode = "asc";
            }
            cri.getOrderBy().orderBy(order, mode);
        }
        // 分页
        int size = 0;
        int num = 0;
        try {
            size = Integer.parseInt(count);
            num = Integer.parseInt(page);
        } catch (NumberFormatException e) {

        }
        if (num <= 0) {
            num = 1;
        }
        if (size > 0) {
            cri.setPager(num, size);
        }
        List<Cms_topic> list = cmsTopicService.query(cri);
        this.binds(list);
        this.doBodyRender();
    }
}
