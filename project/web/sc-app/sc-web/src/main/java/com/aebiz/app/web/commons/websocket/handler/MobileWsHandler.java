package com.aebiz.app.web.commons.websocket.handler;


import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.websocket.MessageHandler;

/**
 * 手机移动端专家咨询websocket信息处理类
 *
 * @author tony
 * @date 2017-10-26
 */
public class MobileWsHandler extends AbstractWsHandler implements MessageHandler.Whole<String> {
    private static final Log log = Logs.get();

    public MobileWsHandler() {
        this("wsroom:");
    }

    public MobileWsHandler(String prefix) {
        super(prefix);
    }

    /**
     * 定制消息处理
     *
     * @param message
     */
    public void onMessage(String message) {
        try {
            System.out.println("========================");
            System.out.println("来自websocket客户端的消息:" + message);
            System.out.println("========================");
            log.info("websocket已经接收到信息:: " + message);

            NutMap msg = Json.fromJson(NutMap.class, message);
            String action = msg.getString("action");
            if (Strings.isBlank(action)) {
                return;
            }

            String room = msg.getString("room");
            switch (action) {
                case "join":
                    join(room);
                    break;
                case "left":
                    left(room);
                    break;
                case "chat":
                    sendMsg(msg);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 发送咨询信息
    private void sendMsg(NutMap msg) {

    }
}