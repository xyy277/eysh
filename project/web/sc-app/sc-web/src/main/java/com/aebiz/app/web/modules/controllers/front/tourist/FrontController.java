package com.aebiz.app.web.modules.controllers.front.tourist;


import com.aebiz.app.tourist.modules.models.Tour_journey;
import com.aebiz.app.tourist.modules.models.Tour_journey_txt;
import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.*;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import org.apache.shiro.SecurityUtils;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aj907 on 2018/3/25.
 */
@Controller
public class FrontController {

    private final Log log= Logs.get();

    @Autowired
    private TourJourneyService tourJourneyService;
    @Autowired
    private TourJourneyCheckService tourJourneyCheckService;
    @Autowired
    private TourVerseService tourVerseService;
    @Autowired
    private TourJourneyTxtService tourJourneyTxtService;
    @Autowired
    private TourJourneyPictureService tourJourneyPictureService;
    @Autowired
    private TourJourneyCommentService tourJourneyCommentService;

    @RequestMapping(value = "/saveJourney", method = RequestMethod.POST)
    @SJson
    public Object saveJourney(HttpServletRequest request){
        Object object = SecurityUtils.getSubject().getPrincipal();
        if(object==null){
            return Result.error("你还没登录哦");
        }
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        Tour_journey tour_journey = new Tour_journey();
        tour_journey.setTitle(title);
        tour_journey.setAuthor(author);
        tour_journey.setReadnum(0);
        tour_journey.setUp_number(0);
        tour_journey.setComment_no(0);
        tour_journey.setStatus(0);
        try {
            log.debug(tour_journey);
            String journey_id="";
            if(tour_journey!=null){
                journey_id=tourJourneyService.insert(tour_journey).getId(); //保存游记
            }
            Tour_journey_txt txt = new Tour_journey_txt();
            String content = request.getParameter("content");
            txt.setJourney_id(journey_id);
            if(Strings.isNotBlank(content))txt.setContent(content); //保存内容
            tourJourneyTxtService.insert(txt);
        }catch (Exception e) {
            return Result.error("提交失败咯");
        }
        return Result.success("操作成功");
    }

    @RequestMapping("/upload")
    @SJson
    public Object upload(){

        return Result.success("");
    }
}
