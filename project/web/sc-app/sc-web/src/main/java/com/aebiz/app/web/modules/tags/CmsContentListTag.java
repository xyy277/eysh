package com.aebiz.app.web.modules.tags;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.models.Cms_content_file;
import com.aebiz.app.cms.modules.models.Cms_content_txt;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsContentFileService;
import com.aebiz.app.cms.modules.services.CmsContentService;
import com.aebiz.app.cms.modules.services.CmsContentTxtService;
import com.aebiz.app.sys.modules.services.SysAreaService;
import com.aebiz.app.sys.modules.services.SysDictService;
import com.aebiz.app.web.commons.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取文章列表的自定义标签
 */
@Component
@Scope("prototype")
public class CmsContentListTag extends GeneralVarTagBinding {
    @Autowired
    private CmsContentService cmsContentService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsContentTxtService cmsContentTxtService;
    @Autowired
    private CmsContentFileService cmsContentFileService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysAreaService sysAreaService;
    @Override
    public void render() {
        String siteId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("siteId")));
        String channelId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("channelId")));
        String excludeId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("excludeId")));
        String channelName = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("channelName")));
        String topicId = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("topicId")));
        String count = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("count")));
        String page = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("page")));
        String orderBy = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("orderBy")));
        String hasImg = StringUtils.trimToEmpty(Strings.sNull(this.getAttributeValue("hasImg")));
        Cnd cnd = Cnd.NEW();
        cnd.and("status", "=", 3).and("is_static", "=", 1);
        if (!StringUtils.isEmpty(channelId)) {
            String[] ids = StringUtils.split(channelId, ",");
            cnd.and("channel_id", "in", ids);
        }
        if (!StringUtils.isEmpty(channelName)) {
            List<Cms_channel> list = cmsChannelService.query(Cnd.where("name", "=", channelName));
            List ids = new ArrayList();
            for (Cms_channel c : list) {
                ids.add(c.getId());
            }
            if (!ids.isEmpty())
                cnd.and("channel_id", "in", ids);
            else
                cnd.and("id", "is", null);
        }
        if (!StringUtils.isEmpty(excludeId)) {
            String[] ids = StringUtils.split(excludeId, ",");
            if (ids.length > 0) cnd.and("id", "NOT IN", ids);
        }
        if (!StringUtils.isEmpty(siteId)) {
            cnd.and("site_id", "=", siteId);
        }
        if (StringUtils.isEmpty(topicId)) {
            cnd.and(Cnd.exps("topic_id", "=", topicId).or("topic_id", "=", null));
        } else {
            cnd.and("topic_id", "=", topicId);
        }
        if (!StringUtils.isEmpty(hasImg)) {
            if (Boolean.parseBoolean(hasImg)) {
                cnd.andNot(Cnd.exps("has_title_img", "is", null).and("has_title_img", "=", ""));
            } else {
                cnd.and(Cnd.exps("has_title_img", "is", null).or("has_title_img", "=", ""));
            }
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
            cnd.orderBy(order, mode);
        }
        // 分页

        List<Cms_content> list = (List<Cms_content>) cmsContentService.listPage(StringUtil.str2int(page, 1), StringUtil.str2int(count, 10), cnd).getList();
        for (Cms_content content : list){
            if(content.getDepartment()!=null){
                String de=sysDictService.getNameById(content.getDepartment());
                if(de!=null){
                    content.setDepartment(de);
                }
            }
            if(content.getArea()!=null){
                String ar=sysAreaService.getNameByCode(content.getArea());
                if(ar!=null){
                    content.setArea(ar);
                }
            }
            Cms_content_txt contentTxt = cmsContentTxtService.fetch(Cnd.where("content_id", "=", content.getId()));
            content.setContent_txt(contentTxt.getTxt());
            List<Cms_content_file> files = cmsContentFileService.query(Cnd.where("content_id","=",content.getId()));
            if (files!=null&&files.size()>0){
                content.setFilePath(files.get(0).getFile_path());
            }
        }

        cmsContentService.fetchLinks(list, "channel");
        this.binds(list);
        this.doBodyRender();
    }
}