package com.aebiz.app.web.commons.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import com.aebiz.app.web.commons.websocket.handler.SimpleWsHandler;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.Lang;
import org.nutz.lang.LoopException;
import org.nutz.lang.Streams;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;
/**
 * Created by wizzer on 2017/3/8.
 */
public abstract class AbstractWsEndpoint extends Endpoint {

    /**
     * 存放Websocket Session Id --> WsHandler 的映射关系
     */
    protected ConcurrentHashMap<String, WsHandler> handlers = new ConcurrentHashMap<>();
    /**
     * 存放Websocket Session Id --> Session 的映射关系
     */
    protected ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    /**
     * 房间提供者. WebSocket默认没有房间的概念
     */
    protected WsRoomProvider roomProvider;

    /**
     * 用于修改Session的Id字段
     */
    protected Field idField;

    private static final Log log = Logs.get();

    protected String roomPrefix = "wsroom:";

    public WsRoomProvider getRoomProvider() {
        return roomProvider;
    }

    public void setRoomProvider(WsRoomProvider roomProvider) {
        this.roomProvider = roomProvider;
    }

    public String getRoomPrefix() {
        return roomPrefix;
    }

    public void setRoomPrefix(String roomPrefix) {
        this.roomPrefix = roomPrefix;
    }

    /**
     * Websocket会话创建成功时调用本方法, 将创建WsHandler实例,并登记之.
     */
    public void onOpen(Session session, EndpointConfig config) {
        changeSessionId(session);
        String wsid = session.getId();
        WsHandler handler = createHandler(session, config);
        handler.setRoomProvider(roomProvider);
        handler.setSession(session);
        session.addMessageHandler(handler);
        sessions.put(wsid, session);
        handlers.put(wsid, handler);
    }

    /**
     * WebSocket会话关闭是调用本方法,通常是用户关闭浏览器. 移除session相关的资源
     */
    public void onClose(Session session, CloseReason closeReason) {
        sessions.remove(session.getId());
        WsHandler handler = handlers.remove(session.getId());
        if (handler != null)
            handler.depose();
    }

    /**
     * WebSocket会话出错时调用,默认调用onClose.
     */
    public void onError(Session session, java.lang.Throwable throwable) {
        onClose(session, null);
    }


    /**
     *  WebSocketSession只对当前JVM是唯一的,所以我们要改造一下, 变成UUID. 测试过tomcat和jetty是没有问题的,其他web服务器未测试.
     */
    protected void changeSessionId(Session session) {
        try {
            if (idField == null) {
                idField = session.getClass().getDeclaredField("id");
                idField.setAccessible(true);
            }
            idField.set(session, R.UU32());
        }
        catch (Exception e) {
            log.debug("change session id fail. " + e.getMessage());
        }
    }

    /**
     * 根据WebSocket会话创建一个WsHandler. 注意, 该实例还得实现MessageHandler.Whole或MessageHandler.Partial接口!!!
     */
    public WsHandler createHandler(Session session, EndpointConfig config) {
        SimpleWsHandler handler = new SimpleWsHandler(roomPrefix);
        handler.setRoomProvider(roomProvider);
        handler.setSession(session);
        return handler;
    }

    /**
     * 返回一个活跃的WebSocket Session对象
     * @param wsid session的id
     * @return 如果该wsid存在且处于活跃状态,返回session实例,否则返回null
     */
    public Session getSession(String wsid) {
        return getSession(wsid, true);
    }

    /**
     * 根据wsid获取一个Session对象.
     * @param wsid session的id
     * @param opened 是否检查活跃状态
     */
    public Session getSession(String wsid, boolean opened) {
        Session session = sessions.get(wsid);
        if (session == null)
            return null;
        if (opened && !session.isOpen())
            return null;
        return session;
    }

    /**
     * 根据wsid获取其WsHandler实例
     * @param wsid session的id
     */
    public WsHandler getHandler(String wsid) {
        return handlers.get(wsid);
    }

    /**
     * 异步非阻塞发送文本信息到指定的WebSocket Session
     * @param wsid session的id
     * @param text 文本信息
     * @return session存活即返回true
     */
    public boolean sendText(String wsid, CharSequence text) {
        Session session = getSession(wsid);
        if (session == null)
            return false;
        session.getAsyncRemote().sendText(text.toString());
        return true;
    }

    /**
     * sendText的同步阻塞版本
     * @param wsid session的id
     * @param text 文本信息
     * @return session存活且发送成功,返回true,否则返回false
     */
    public boolean sendTextSync(String wsid, CharSequence text) {
        Session session = getSession(wsid, true);
        if (session == null)
            return false;
        try {
            session.getBasicRemote().sendText(text.toString());
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 异步非阻塞发送文本信息到指定的WebSocket Session
     * @param wsid session的id
     * @param msg 将转换为Json字符串的对象
     * @return session存活即返回true
     */
    public boolean sendJson(String wsid, Object msg) {
        Session session = getSession(wsid);
        if (session == null)
            return false;
        session.getAsyncRemote().sendText(Json.toJson(msg, JsonFormat.full()));
        return true;
    }

    /**
     * sendJson的同步阻塞版本
     * @param wsid session的id
     * @param msg 将转换为Json字符串的对象
     * @return session存活且发送成功,返回true,否则返回false
     */
    public boolean sendJsonSync(String wsid, Object msg) {
        Session session = getSession(wsid, true);
        if (session == null)
            return false;
        try {
            session.getBasicRemote().sendText(Json.toJson(msg, JsonFormat.full()));
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 把byte[]/InputStream/ByteBuffer转换为ByteBuffer,其他类型的实例将toString()然后转byte[]再封装为ByteBuffer
     * @param msg byte[]/InputStream/ByteBuffer等对象
     * @return
     */
    public ByteBuffer toByteBuffer(Object msg) {
        if (msg == null)
            return null;
        ByteBuffer buf;
        if (msg instanceof ByteBuffer)
            buf = (ByteBuffer)msg;
        else if (msg instanceof byte[])
            buf = ByteBuffer.wrap((byte[])msg);
        else if (msg instanceof InputStream)
            buf = ByteBuffer.wrap(Streams.readBytesAndClose((InputStream)msg));
        else
            buf = ByteBuffer.wrap(msg.toString().getBytes());
        return buf;
    }

    /**
     * 异步非阻塞发送一段二进制数据到指定的WebSocket Session
     * @param wsid session的id
     * @param msg byte[]/InputStream/ByteBuffer等
     * @return session存活且msg不是null,返回true,否则返回false
     */
    public boolean sendBinary(String wsid, Object msg) {
        Session session = getSession(wsid);
        if (session == null || msg == null)
            return false;
        ByteBuffer buf = toByteBuffer(msg);
        if (buf == null)
            return false;
        session.getAsyncRemote().sendBinary(buf);
        return true;
    }

    /**
     * sendBinary的同步阻塞版本
     */
    public boolean sendBinarySync(String wsid, Object msg) {
        Session session = getSession(wsid);
        if (session == null || msg == null)
            return false;
        ByteBuffer buf = toByteBuffer(msg);
        if (buf == null)
            return false;
        try {
            session.getBasicRemote().sendBinary(buf);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 按房间遍历活跃的session对象
     * @param room
     * @param callback
     */
    public void each(String room, final Each<Session> callback) {
        Set<String> wsids = roomProvider.wsids(room);
        if (wsids == null || wsids.isEmpty())
            return;
        // 需要先转为数组,因为Set经常增减的,直接foreach会挂.
        String[] tmp = wsids.toArray(new String[wsids.size()]);
        Lang.each(tmp, new Each<String>(){
            public void invoke(int index, String ele, int length)
                    throws ExitLoop, ContinueLoop, LoopException {
                Session session = getSession(ele);
                if (session != null)
                    callback.invoke(index, session, length);
            }
        });
    }
}