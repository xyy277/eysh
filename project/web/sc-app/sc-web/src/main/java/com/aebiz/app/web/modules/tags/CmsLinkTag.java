package com.aebiz.app.web.modules.tags;

import com.aebiz.app.cms.modules.models.Cms_link_class;
import com.aebiz.app.cms.modules.services.CmsLinkClassService;
import com.aebiz.app.cms.modules.services.CmsLinkService;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.page.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyang on 2017/9/25.
 */
@Component
@Scope("prototype")
public class CmsLinkTag extends GeneralVarTagBinding {
    @Autowired
    private CmsLinkService cmsLinkService;
    @Autowired
    private CmsLinkClassService cmsLinkClassService;
    @Autowired
    private PropertiesProxy config;

    @Override
    public void render() {
        String id = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("id")));
        String name = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("name")));
        String categoryId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("categoryId")));
        String categoryName = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("categoryName")));
        String type = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("type")));
        String count = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("count")));
        String page = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("page")));
        String orderBy = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("orderBy")));
        Cnd cnd = Cnd.NEW();

        if (!"".equals(id)) {
            cnd.and("id", "=", id);
        } else {
            if (!"".equals(name)) {
                cnd.and("name", "=", name);
            }

            if (!"".equals(categoryId)) {
                String key = StringUtils.trimToEmpty(config.get(categoryId));
                if (StringUtils.isNotBlank(key)) {
                    Cms_link_class linkClass = cmsLinkClassService.fetch(Cnd.where("key_word", "=", key));
                    if (linkClass != null) {
                        cnd.and("classId", "=", linkClass.getId());
                    } else {
                        cnd.and("classId", "=", categoryId);
                    }
                } else {
                    cnd.and("classId", "=", categoryId);
                }
            }
            if (!"".equals(categoryName)) {
                List<Cms_link_class> list = cmsLinkClassService.query(Cnd.where("name", "=", categoryName));
                List<String> ids = new ArrayList();
                for (Cms_link_class lc : list) {
                    ids.add(lc.getId());
                }
                cnd.and("classId", "in", ids);
            }
            if (!"".equals(type)) {
                cnd.and("type", "=", type);
            }

            if (!"".equals(type)) {
                cnd.and("type", "=", type);
            }

            // 排序
            if (!"".equals(orderBy)) {
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
        }
        Pagination pages = cmsLinkService.listPage(StringUtil.str2int(page, 1), StringUtil.str2int(count, 10), cnd);
        this.binds(pages.getList());
        this.doBodyRender();
    }

}