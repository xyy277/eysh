package com.aebiz.app.web.modules.controllers.platform.wx;

import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.app.wx.modules.models.Wx_config;
import com.aebiz.app.wx.modules.models.Wx_mass_news;
import com.aebiz.app.wx.modules.services.WxConfigService;
import com.aebiz.app.wx.modules.services.WxMassNewsService;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.page.datatable.DataTable;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.wx.modules.models.Wx_sc_news;
import com.aebiz.app.wx.modules.services.WxScNewsService;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.filepool.NutFilePool;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.weixin.spi.WxApi2;
import org.nutz.weixin.spi.WxResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/platform/wx/sc/news")
public class WxScNewsController {
    private static final Log log = Logs.get();
    @Autowired
	private WxScNewsService wxScNewsService;

    @Autowired
    private WxMassNewsService wxMassNewsService;

    @Autowired
    private WxConfigService wxConfigService;

    @Autowired
    private NutFilePool filePool;

    @RequestMapping("")
    @RequiresPermissions("platform.wx.sc.news")
	public String index() {
		return "pages/platform/wx/sc/news/index";
	}

	@RequestMapping("/data")
    @SJson("full")
    @RequiresPermissions("platform.wx.sc.news")
    public Object data(DataTable dataTable) {
		Cnd cnd = Cnd.NEW();
    	return wxScNewsService.data(dataTable.getLength(), dataTable.getStart(), dataTable.getDraw(), dataTable.getOrders(), dataTable.getColumns(), cnd, null);
    }

    @RequestMapping("/add")
    @RequiresPermissions("platform.wx.sc.news")
    public String add() {
    	return "pages/platform/wx/sc/news/add";
    }

    @RequestMapping("/addDo")
    @SJson
    @SLog(description = "Wx_sc_news")
    @RequiresPermissions("platform.wx.sc.news.add")
    public Object addDo(Wx_sc_news wxScNews, HttpServletRequest req) {
		try {
			wxScNewsService.insert(wxScNews);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping("/edit/{id}")
    @RequiresPermissions("platform.wx.sc.news")
    public String edit(@PathVariable String id,HttpServletRequest req) {
		req.setAttribute("obj", wxScNewsService.fetch(id));
		return "pages/platform/wx/sc/news/edit";
    }

    @RequestMapping("/editDo")
    @SJson
    @SLog(description = "Wx_sc_news")
    @RequiresPermissions("platform.wx.sc.news.edit")
    public Object editDo( HttpServletRequest req) {
		try {
            String id = req.getParameter("id");
            String status = req.getParameter("status");
            Wx_sc_news sc_news = wxScNewsService.fetch(id);
            sc_news.setStatus(Integer.valueOf(status));
            sc_news.setOpBy(StringUtil.getUid());
            sc_news.setOpAt((int) (System.currentTimeMillis() / 1000));
            Wx_mass_news news = new Wx_mass_news();
           if("1".equals(status)){
               news.setThumb_media_id(sc_news.getThumb_media());
               news.setAuthor(sc_news.getAuthor());
               news.setTitle(sc_news.getTitle());
               news.setContent_source_url(sc_news.getContent_source_url());

               Wx_config config = wxConfigService.fetch(Cnd.where("is_main" ,"=","1"));
               String wxid = "";
               if (config!=null){
                   wxid = config.getId();
               }else {
                   wxid = wxConfigService.query(Cnd.where("delFlag","=","0")).get(0).getId();
               }
               //解析content
               news.setContent(getContent(sc_news.getContent(),wxid));

               //重置缩略图地址

               news.setThumb_media_id(getThumb_media_id(sc_news.getThumb_media(),wxid));

               news.setWxid(wxid);
               news.setDigest(sc_news.getDigest());
               news.setShow_cover_pic(0);
               news.setOpBy(StringUtil.getUid());
               news.setOpAt((int) (System.currentTimeMillis() / 1000));
               wxMassNewsService.insert(news);
           }
            wxScNewsService.updateIgnoreNull(sc_news);
			return Result.success("globals.result.success");
		} catch (Exception e) {
			return Result.error("globals.result.error");
		}
    }

    @RequestMapping(value = {"/delete/{id}", "/delete"})
    @SJson
    @SLog(description = "Wx_sc_news")
    @RequiresPermissions("platform.wx.sc.news.delete")
    public Object delete(@PathVariable(required = false) String id, @RequestParam(value = "ids",required = false)  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				wxScNewsService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				wxScNewsService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("globals.result.success");
        } catch (Exception e) {
            return Result.error("globals.result.error");
        }
    }

    @RequestMapping("/detail/{id}")
    @RequiresPermissions("platform.wx.sc.news")
	public String detail(@PathVariable String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", wxScNewsService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
        return "pages/platform/wx/sc/news/detail";
    }

    private String getThumb_media_id(String url,String wxid){
        Response resp = Http.get(url,  30000);
        if (resp.isOK()) {
            File file=filePool.createFile(".jpg");
            Files.write(file, resp.getStream());
            //Files.copy(file,new File("D://222.jpg"));
            WxApi2 wxApi2 = wxConfigService.getWxApi2(wxid);
            WxResp res = wxApi2.media_upload("thumb", file);
            return (String)res.get("thumb_media_id");
        } else {
            System.out.println(resp.getContent());
            return "";
        }
    }

    public String getContent(String content,String wxid) {
        String startTag="src=\"";
        String endTag="\"";
        String regxpForTag = "<\\s*img\\s+([^>]*)\\s*" ;
        String regxpForTagAttrib = "src=\\s*\"([^\"]+)\"" ;
        Pattern patternForTag = Pattern.compile (regxpForTag,Pattern. CASE_INSENSITIVE );
        Pattern patternForAttrib = Pattern.compile (regxpForTagAttrib,Pattern. CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(content);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer( "<img ");
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
            if (matcherForAttrib.find()) {
                String attributeStr = matcherForAttrib.group(1);
                String url = "";
                Response resp = Http.get(attributeStr.toString(),  30000);
                if (resp.isOK()) {
                    File file=filePool.createFile(".jpg");
                    Files.write(file, resp.getStream());
                    //Files.copy(file,new File("D://222.jpg"));
                    WxApi2 wxApi2 = wxConfigService.getWxApi2(wxid);
                    WxResp res = wxApi2.uploadimg(file);
                    url= (String)res.get("url");
                } else {
                    System.out.println(resp.getContent());
                }
                matcherForAttrib.appendReplacement(sbreplace, startTag+url+endTag);
            }
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

}
