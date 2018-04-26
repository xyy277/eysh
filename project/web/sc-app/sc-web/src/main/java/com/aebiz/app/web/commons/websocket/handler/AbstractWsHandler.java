package com.aebiz.app.web.commons.websocket.handler;

import com.aebiz.app.web.commons.websocket.WsHandler;
import com.aebiz.app.web.commons.websocket.WsRoomProvider;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wizzer on 2017/3/8.
 */
public abstract class AbstractWsHandler implements WsHandler {

    private static final Log log = Logs.get();

    protected Set<String> rooms;

    protected WsRoomProvider roomProvider;

    protected Session session;
    protected String prefix;

    public AbstractWsHandler(String prefix) {
        rooms = new HashSet<>();
        this.prefix = prefix;
    }

    public void join(String room) {
        if (!Strings.isBlank(room)) {
            rooms.add(room);
            room = prefix + room;
            log.debugf("session(id=%s) join room(name=%s)", session.getId(), room);
            roomProvider.join(room, session.getId());
        }
    }

    public void left(String room) {
        if (!Strings.isBlank(room)) {
            rooms.remove(room);
            room = prefix + room;
            log.debugf("session(id=%s) left room(name=%s)", session.getId(), room);
            roomProvider.left(room, session.getId());
        }
    }

    public void depose() {
        for (String room : rooms) {
            left(room);
        }
    }

    public void setRoomProvider(WsRoomProvider roomProvider) {
        this.roomProvider = roomProvider;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
