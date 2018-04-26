package com.aebiz.app.web.modules.tags;

import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.models.Cms_content_txt;
import com.aebiz.app.cms.modules.services.CmsContentService;
import com.aebiz.app.cms.modules.services.CmsContentTxtService;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by CZX on 2017/9/13.
 */
@Component
@Scope("prototype")
public class CmsFlatContentListTag extends GeneralVarTagBinding {
    @Autowired
    private CmsContentService cmsContentService;

    @Autowired
    private CmsContentTxtService cmsContentTxtService;;

    @Override
    public void render() {
        String siteId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("siteId")));
        String channelId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("channelId")));
        String count = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("count")));
        String page = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("page")));
        String orderBy = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("orderBy")));
        SimpleCriteria cri = Cnd.cri();
        cri.where().and("status", "=", 3);

        if (!StringUtils.isEmpty(siteId)) {
            cri.where().and("site_id", "=", siteId);
        }
        if (!StringUtils.isEmpty(channelId)) {
            String[] ids = StringUtils.split(channelId, ",");
            cri.where().and("channel_id", "in", ids);
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
        int size =0;
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
        List<Cms_content> list = cmsContentService.query(cri);
        for (Cms_content content : list) {
            List<Cms_content_txt> txts=cmsContentTxtService.query(Cnd.where("content_id","=",content.getId()));
            if(txts!=null && txts.size()>0){
                content.setTpl_content(txts.get(0).getTxt());
            }
            if (content.getDescription() != null) {
                try {
                    JSONArray des = new JSONArray(content.getDescription());

                    for (int i = 0; i < des.length(); i++) {
                        JSONObject myjObject = des.getJSONObject(i);
                        content.setAuthor(myjObject.getString("org_name"));
                        content.setShort_title(myjObject.getString("address"));
                        content.setTitle_color(myjObject.getString("tel"));
                    }
                } catch (Exception e) {
                }
            }
            this.binds(content);
            this.doBodyRender();
        }

    }
}
