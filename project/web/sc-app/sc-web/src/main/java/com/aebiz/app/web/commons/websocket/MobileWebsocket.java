package com.aebiz.app.web.commons.websocket;

import com.aebiz.app.web.commons.websocket.handler.MobileWsHandler;
import com.aebiz.app.web.commons.websocket.room.JedisRoomProvider;
import com.aebiz.baseframework.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ Autowired
 * protect MyWebsocket myWebsocket;

 public void sayhi(String room) {
     myWebsocket.each(room, new Each<Session>() {
     public void invoke(int index, Session ele, int length) {
        myWebsocket.sendJson(ele.getId(), new NutMap("action", "layer").setv("msg", "hi"));
     }
     });
 }

 public void sayhiJson(String wsid) {
     myWebsocket.sendJson(wsid, new NutMap("action", "layer").setv("msg", "hi"));
 }
 */
@ServerEndpoint(value = "/mobile/websocket", configurator = MyWebSocketConfigurator.class)
@Component
public class MobileWebsocket extends AbstractWsEndpoint {

    @Autowired
    protected RedisService redisService;

    @PostConstruct
    public void init() {
        super.setRoomPrefix("wsroom:");
        super.setRoomProvider(new JedisRoomProvider(redisService.getJedisAgent()));
    }

    @PreDestroy
    public void depose() {

    }

    /**
     * 覆盖父类中createHandler方法，定制消息处理
     * @param session
     * @param config
     * @return
     */
    @Override
    public WsHandler createHandler(Session session, EndpointConfig config) {
        MobileWsHandler handler = new MobileWsHandler(roomPrefix);
        handler.setRoomProvider(roomProvider);
        handler.setSession(session);
        return handler;
    }
}
