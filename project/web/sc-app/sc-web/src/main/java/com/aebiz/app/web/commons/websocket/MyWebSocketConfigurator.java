package com.aebiz.app.web.commons.websocket;

import com.aebiz.commons.utils.SpringUtil;

import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by wizzer on 2017/3/3.
 */
public class MyWebSocketConfigurator extends ServerEndpointConfig.Configurator {

    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return SpringUtil.getBean(endpointClass);
    }
}