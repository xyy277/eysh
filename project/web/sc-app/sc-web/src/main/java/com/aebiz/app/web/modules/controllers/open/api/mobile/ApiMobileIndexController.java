package com.aebiz.app.web.modules.controllers.open.api.mobile;

import com.aebiz.app.cms.modules.models.Cms_channel;
import com.aebiz.app.cms.modules.models.Cms_content;
import com.aebiz.app.cms.modules.services.CmsAccessRecordService;
import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.web.commons.es.EsService;
import com.aebiz.app.web.commons.log.annotation.SLog;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 手机移动端首页展示数据接口
 * <p>
 * Created by tony on 2017/9/19.
 */
@Controller
@CrossOrigin
@RequestMapping("/open/api/mobile/first")
public class ApiMobileIndexController {
    private static final Log log = Logs.get();
    private static final String AREA_CODE_HEFEI = "340100";
    private static final String CMS_CHANNEL_ZXLB = "sczx/zxlb"; // 轮播图片 栏目ID
    private static final String CMS_CHANNEL_TZGG = "sczx/tzgg"; // 通知公告 栏目ID
    private static final String CMS_CHANNEL_SCDT = "sczx/scdt"; // 双创动态 栏目ID

    @Autowired
    private PropertiesProxy config;
    @Autowired
    private EsService esService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsAccessRecordService cmsAccessRecordService;

    /**
     * 首页图片轮播接口
     *
     * @param count 轮播图片数量默认为3
     * @return
     */
    @RequestMapping(value = "/swiper")
    @SJson("full")
    @SLog
    public Object getSwiperData(@RequestParam(required = false) String count) {
        try {
            int dataCount = Strings.isNumber(count) ? Integer.parseInt(count) : 3;
            Cms_channel channel = cmsChannelService.fetch(Cnd.where("path", "=", CMS_CHANNEL_ZXLB));

            BoolQueryBuilder query = QueryBuilders.boolQuery();
            query.must(QueryBuilders.matchQuery("delFlag", "0"));
            query.must(QueryBuilders.matchQuery("channel_id", channel.getId()));
            query.must(QueryBuilders.matchQuery("is_static", 1));
            query.must(QueryBuilders.matchQuery("status", 3));

            SearchRequestBuilder srb = esService.getClient().prepareSearch(config.get("es.index.name"))
                    .setSearchType(SearchType.QUERY_THEN_FETCH)
                    .setTypes("cms_content")
                    .setQuery(query)
                    //分页
                    .setFrom(0).setSize(dataCount)
                    //是否高亮
                    .setExplain(false);
            SearchResponse response = srb.execute().actionGet();
            SearchHits hits = response.getHits();
            List<Cms_content> list = new ArrayList<>();
            for (SearchHit searchHit : hits) {
                Map<String, Object> source = searchHit.getSource();
                list.add(Lang.map2Object(source, Cms_content.class));
            }

            return Result.success("ok", list);
        } catch (Exception e) {
            log.info("MobileAPI error :: " + e.getMessage());
            return Result.error("fail");
        }
    }

}
