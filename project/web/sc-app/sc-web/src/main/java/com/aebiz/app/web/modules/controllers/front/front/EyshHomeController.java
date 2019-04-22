package com.aebiz.app.web.modules.controllers.front.front;

import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.tourist.modules.models.Tour_guest;
import com.aebiz.app.tourist.modules.models.Tour_journey;
import com.aebiz.app.tourist.modules.models.Tour_journey_txt;
import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.*;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.baseframework.base.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


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
    private TourJourneyTxtService tourJourneyTxtService;
    @Autowired
    private TourJourneyCheckService tourJourneyCheckService;
    @Autowired
    private TourJourneyCommentService tourJourneyCommentService;
    @Autowired
    private TourGuestService tourGuestService;

    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        String returnUrl = request.getParameter("returnUrl");
        request.setAttribute("uri",returnUrl);
        return "pages/front/tourist/welcome";
    }

    @RequestMapping("/ocean")
    public String ocean(HttpServletRequest request) {
        String returnUrl = request.getParameter("returnUrl");
        request.setAttribute("uri",returnUrl);
        return "pages/front/tourist/eysh/ocean";
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

    @RequestMapping("/fly")
    public String fly(HttpServletRequest request) {
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("nickname",request.getSession().getAttribute("nickname"));
        return "pages/front/tourist/eysh/fly";
    }

    @RequestMapping("/about")
    public String aboutUs(HttpServletRequest request){
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("nickname",request.getSession().getAttribute("nickname"));
        return "pages/front/tourist/eysh/about";
    }

    //判断是否为手机浏览器
    private Integer judgeIsMoblie(HttpServletRequest request) {
        int isMoblie = 0;
        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        if (request.getHeader("User-Agent") != null) {
            String agent=request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
                    isMoblie = 1;
                    break;
                }
            }
        }
        return isMoblie;
    }

    @RequestMapping("/game")
    public String game(HttpServletRequest request){
        Tour_user tour_user = (Tour_user) request.getSession().getAttribute("user");
        if (tour_user != null) {
            request.setAttribute("uid", tour_user.getId());
            request.setAttribute("nickname", tour_user.getNickname());
        } else {
            Subject subject = SecurityUtils.getSubject();
            Tour_user user = (Tour_user) subject.getPrincipal();
            if (user != null) {
                request.setAttribute("uid", user.getId());
                request.setAttribute("nickname", user.getNickname());
            } else {
               String ip = request.getRemoteAddr();
               Tour_guest guest = tourGuestService.fetch(Cnd.where("ip", "=", ip));
               if (guest != null) {
                   request.setAttribute("nickname", guest.getNickname());
               }
            }
        }
        request.setAttribute("uri",request.getRequestURI().replaceAll("/",""));
        request.setAttribute("isMobile", judgeIsMoblie(request));
        return "pages/front/tourist/eysh/game";
    }

    @RequestMapping("/score/list")
    @ResponseBody
    public Result getScoreList() {
        Cnd cnd = Cnd.NEW();
        List<Tour_user> users = tourUserService.query("id|nickname|score|rank", cnd.where("score", ">", 0).limit(1,10).desc("score"));
        List<Tour_guest> guests = tourGuestService.query("id|nickname|score|rank", Cnd.where("score", ">", 0).limit(1,10).desc("score"));
        users.forEach(user -> {
            Tour_guest guest = new Tour_guest();
            guest.setId(user.getId());
            guest.setNickname(user.getNickname());
            guest.setRank(user.getRank());
            guest.setScore(user.getScore());
            guests.add(guest);
        });
        List<Tour_guest> tour_guests = guests.stream().sorted((o1, o2) -> o2.getScore() - o1.getScore()).limit(10).collect(Collectors.toList());
        if (tour_guests != null && tour_guests.size() > 0) {
            return Result.success("game.list.ok", tour_guests);
        }
        return Result.error("game.list.null");
    }

    @RequestMapping(value = "/save/score", method = RequestMethod.POST)
    @ResponseBody
    public Result saveScore(String id, Integer score) {
        Tour_user user = tourUserService.fetch(id);
        int success = 0;
        if (user.getScore() == null) {
            success = tourUserService.updateIgnoreNull(user);
        } else {
            if (user.getScore() < score) {
                user.setScore(score);
                success = tourUserService.updateIgnoreNull(user);
            } else {
                return Result.success("game.fight");
            }
        }
        if (success < 1) {
            return Result.error("game.error");
        }
        return Result.success("game.congratulation", success);
    }

    @RequestMapping(value = "/guest/nickname", method = RequestMethod.POST)
    @ResponseBody
    public Result checkGuestNickName(String nickname) {
        Tour_guest guest = tourGuestService.fetch(Cnd.where("nickname", "=", nickname));
        if (guest != null) {
            return Result.error();
        } else {
            return Result.success();
        }
    }

    @RequestMapping(value = "/save/guest", method = RequestMethod.POST)
    @ResponseBody
    public Result saveGuest(String nickname, Integer score, HttpServletRequest request) {
        Tour_guest guest = tourGuestService.fetch(Cnd.where("nickname", "=", nickname));
        int success = 0;
        if (guest == null) {
            guest = new Tour_guest();
            guest.setNickname(nickname);
            guest.setScore(score);
            guest.setIp(request.getRemoteAddr());
            guest = tourGuestService.insert(guest);
            return Result.success("game.congratulation", guest);
        }
        if (guest.getScore() == null) {
            success = tourGuestService.updateIgnoreNull(guest);
        } else {
            if (guest.getScore() < score) {
                guest.setScore(score);
                success = tourGuestService.updateIgnoreNull(guest);
            } else {
                return Result.success("game.fight");
            }
        }
        if (success < 1) {
            return Result.error("game.error");
        }
        return Result.success("game.congratulation", success);
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
        Subject subject = SecurityUtils.getSubject();
        Tour_user user = (Tour_user) subject.getPrincipal();
        Tour_journey tour_journey = new Tour_journey();
        tour_journey.setAuthor(user.getId());
        tour_journey.setTitle(title);
        Trans.exec(new Atom(){
            @Override
            public void run() {
                final Tour_journey journey = tourJourneyService.insert(tour_journey);
                Tour_journey_txt tour_journey_txt = new Tour_journey_txt();
                tour_journey_txt.setContent(content);
                tour_journey_txt.setJourney_id(journey.getId());
                tourJourneyTxtService.insert(tour_journey_txt);
            }
        });
    }

    @RequestMapping("/toUpload")
    public String toUpload(){

        return "pages/front/tourist/eysh/upload";
    }

}
