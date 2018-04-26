package com.aebiz.app.web.commons.rabbit;

import com.aebiz.app.cms.modules.services.StaticPageService;
import com.aebiz.app.web.commons.es.EsService;
import com.aebiz.baseframework.rabbit.RabbitMessage;
import org.nutz.dao.Dao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.annotation.Resource;

/**
 * Created by wizzer on 2016/12/29.
 */
public class RabbitConsumer {
    private Log log = Logs.get();
    @Resource(type = Dao.class)
    private Dao dao;
    @Resource(type = EsService.class)
    private EsService esService;
    @Resource(type =StaticPageService.class)
    private StaticPageService staticPageService;
    @Resource(type = PropertiesProxy.class)
    private PropertiesProxy config;
    public void handleMessage(Object object) {
        RabbitMessage rabbitMessage = (RabbitMessage) object;
        log.debugf("RabbitMq消息队列开始处理[%s]。。。", rabbitMessage.getRouteKey());

    }
}
