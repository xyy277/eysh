package com.aebiz.app.web.modules.controllers.front.front;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.tourist.modules.models.Tour_journey;
import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.*;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.SSOUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by aj907 on 2018/3/21.
 */
@Controller
public class EyshHomeController {

    private final Log log= Logs.get();

    @Autowired
    private TourUserService tourUserService;
    @Autowired
    private TourJourneyService tourJourneyService;
    @Autowired
    private TourJourneyPictureService tourJourneyPictureService;
    @Autowired
    private TourJourneyTxtService tourJourneyTxtServicel;
    @Autowired
    private TourJourneyCheckService tourJourneyCheckService;
    @Autowired
    private TourJourneyCommentService tourJourneyCommentService;

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        String returnUrl = request.getParameter("returnUrl");
        request.setAttribute("uri",returnUrl);
        return "pages/front/tourist/welcome";
    }

    @RequestMapping(value = {"", "/index"})
    public String index(HttpServletRequest request) {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal != null) {
            if (principal.getClass().isAssignableFrom(Tour_user.class)) {
                Tour_user user = (Tour_user) principal;
                request.setAttribute("nickname", user.getNickname());
            } else if (principal.getClass().isAssignableFrom(Sys_user.class)) {
                 Sys_user user = (Sys_user) principal;
                request.setAttribute("username", user.getUsername());
            }
        }else{
            String nickname = (String) request.getSession().getAttribute("nickname");
            if(Strings.isNotBlank(nickname)){
                request.setAttribute("nickname",nickname);
            }
        }
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        return "pages/front/tourist/index";
    }

    @RequestMapping("/gallery")
    public String gallery(HttpServletRequest request){
        Cnd cnd = Cnd.NEW();
        cnd.and("status","=",0);
        List<Tour_journey> list = tourJourneyService.query(cnd);
        request.setAttribute("list",list);
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("nickname",request.getSession().getAttribute("nickname"));
        return "pages/front/tourist/eysh/gallery";
    }

    @RequestMapping("/contact")
    public String contract(HttpServletRequest request){
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("nickname",request.getSession().getAttribute("nickname"));
        return "pages/front/tourist/eysh/contact";
    }

    @RequestMapping("/about")
    public String aboutUs(HttpServletRequest request){
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("nickname",request.getSession().getAttribute("nickname"));
        return "pages/front/tourist/eysh/about";
    }

    @RequestMapping("/game")
    public String game(HttpServletRequest request){
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("nickname",request.getSession().getAttribute("nickname"));
        return "pages/front/tourist/eysh/game";
    }

    @RequestMapping("/front/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        try {
            Subject subject = SecurityUtils.getSubject();
            Tour_user user = (Tour_user) subject.getPrincipal();
            if (user != null) {
                subject.logout();
            }
            String returnUrl = request.getParameter("returnUrl");
            // 开启了单点登录的情况
            if (SSOUtil.isSSO()) {
                response.sendRedirect(Globals.APP_BASE + "/sso/logout?returnUrl=" + returnUrl);
            } else {
                // 跳回首页
                response.sendRedirect(Globals.APP_BASE +returnUrl);
            }
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        } catch (Exception e) {
            log.debug("Logout error", e);
        }
    }

    @RequestMapping("/verse")
    public void verse(HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");

    }

    @RequestMapping("/toUpload")
    public String toUpload(){

        return "pages/front/tourist/eysh/upload";
    }

}
