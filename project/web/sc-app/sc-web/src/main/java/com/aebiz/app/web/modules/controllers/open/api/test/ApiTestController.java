package com.aebiz.app.web.modules.controllers.open.api.test;

import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import org.nutz.http.Request;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wizzer on 2017/4/4.
 */
@Controller
@RequestMapping("/test/api/data")
public class ApiTestController {
    private static final Log log = Logs.get();
    private static final String TESTurl = "localhost:8080/";
    /**
     * @api {post} /open/api/test/hi?appid=appid&token=token 测试API
     * @apiGroup Test
     * @apiVersion 1.0.0
     * @apiPermission token
     * @apiParam {Object}	data 				    数据对象
     * @apiParamExample {json} 示例
     * POST /open/api/test/hi?appid=appid&token=token
     * {
     * "txt": "你好，大鲨鱼"
     * }
     * @apiSuccess {number} code 			         code
     * @apiSuccess {String} msg 			         msg
     * @apiSuccessExample {json} 示例
     * HTTP/1.1 200 OK
     * {
     * "code": 0,
     * "msg": "ok"
     * }
     * @apiError (失败) {number} code 不等于0
     * @apiError (失败) {string} msg 错误文字描述
     * @apiErrorExample {json} 示例
     * HTTP/1.1 200 OK
     * {
     * "code": 1,
     * "msg": "fail"
     * }
     */
    @RequestMapping(value = "/hi", method = RequestMethod.POST)
    @SJson
    public Object hi(@RequestBody NutMap map, HttpServletRequest req) {
        try {
            log.debug("map::" + Json.toJson(map));
            return Result.success("ok");
        } catch (Exception e) {
            return Result.error("fail");
        }
    }





    @RequestMapping(value = "/flat_info")
    @SJson
    public Object testExFlatInfo() {
        try {
            Request req = Request.create(TESTurl + "/api/pro/data/flat_info", Request.METHOD.POST);
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("flat_type", "1");
           params.put("flat_name", "合肥市公共平台2008");
            params.put("org_property", "1");
            params.put("org_code", "3401820310283");
            params.put("org_name", "测试备案");
            params.put("area_county", "340102");
            params.put("linkman", "珠珠");
            params.put("tel", "15209833950");
            params.put("build_start", "2017-12-12");
            params.put("build_end", "2017-12-22");
            params.put("total_money_", "10");
            params.put("self_money_", "8");
            params.put("gov_money_", "2");

            req.setParams(params);
            Response resp = Sender.create(req).send();
            if (resp.isOK()) {
                NutMap newmap = Json.fromJson(NutMap.class, resp.getContent());
                log.debug("api:::" + Json.toJson(newmap));
                return newmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error("操作过程发生异常");
    }

    @RequestMapping(value = "/flat_progress")
    @SJson
    public Object testExFlatProgress() {
        try {
            Request req = Request.create(TESTurl + "/api/pro/data/flat_progress", Request.METHOD.POST);
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("id", "800129cf07cb40db821e1869c3decb97");
            params.put("flat_id", "0200c297826e46138505e8d2a257a10d");
            params.put("finish_num", "88");
            params.put("send_person", "珠珠");
            params.put("sendDate", "2017-12-22");
            params.put("sendTel", "15209833950");
            params.put("f_money", "340102");

            req.setParams(params);
            Response resp = Sender.create(req).send();
            if (resp.isOK()) {
                NutMap newmap = Json.fromJson(NutMap.class, resp.getContent());
                log.debug("api:::" + Json.toJson(newmap));
                return newmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error("操作过程发生异常");
    }

}
