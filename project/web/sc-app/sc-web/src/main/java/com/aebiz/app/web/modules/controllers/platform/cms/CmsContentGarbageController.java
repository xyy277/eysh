package com.aebiz.app.web.modules.controllers.platform.cms;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.models.Cms_site;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsContentService;
import com.aebiz.app.cms.modules.services.CmsSiteService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 回收站
 */
@Controller
@RequestMapping("/platform/cms/content/garbage")
public class CmsContentGarbageController {
    private static final Log log = Logs.get();
    @Autowired
	private CmsContentService cmsContentService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @RequestMapping("")
    @RequiresPermissions("platform.cms.content.garbage")
	public String index(HttpServletRequest req) {
		return "pages/platform/cms/content/garbage/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.cms.content.garbage")
    public Object data(DataTable dataTable,HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        //去除回收站
        cnd.and("status","=","-1");
        NutMap map=cmsContentService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);;
        List<Cms_content> list=(List<Cms_content>)map.get("data");
        if(list!=null && list.size()>0){
            for(Cms_content cms_content:list){
                Cms_site cms_site=cmsSiteService.fetch(cms_content.getSite_id());
                cms_content.setSite_name(cms_site==null?"":cms_site.getSite_name());
                Cms_channel cms_channel=cmsChannelService.fetch(cms_content.getChannel_id());
                cms_content.setChannel_name(cms_channel==null?"":cms_channel.getName());
            }
        }
        return map;
    }

    /**
     * 彻底删除
     * @param ids1
     * @param req
     * @return
     */
    @RequestMapping(value = {"/delete"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.garbage.delete")
    public Object delete(@RequestParam("ids") String ids1, HttpServletRequest req) {
        try {
            String[] ids = StringUtils.split(ids1, ",");
            if(ids!=null&&ids.length>0){
                cmsContentService.clear(Cnd.where("id", "IN", ids).and("status","=","-1"));
                req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
            }
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    /**
     * 转为草稿
     * @param id
     * @param req
     * @return
     */
    @RequestMapping(value = {"/draft"})
    @SJson
    @SLog(description = "Cms_content")
    @RequiresPermissions("platform.cms.content.garbage.draft")
    public Object draft(@RequestParam("id") String id, HttpServletRequest req) {
        try {
            cmsContentService.update(org.nutz.dao.Chain.make("status", "0"),Cnd.where("id", "=", id));
            req.setAttribute("id", id);
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }
}
