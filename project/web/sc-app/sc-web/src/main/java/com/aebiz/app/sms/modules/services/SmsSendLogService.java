package com.aebiz.app.sms.modules.services;

import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.sms.modules.models.Sms_send_log;
import com.aebiz.baseframework.page.datatable.DataTableColumn;
import com.aebiz.baseframework.page.datatable.DataTableOrder;
import org.nutz.dao.Cnd;
import org.nutz.lang.util.NutMap;

import java.util.List;
import java.util.Map;

public interface SmsSendLogService extends BaseService<Sms_send_log>{


        /**
         * 短信发送，并保存发送记录
         * @param phone
         * @param tempcode
         * @param map
         * @param accessKeyId
         */
        public String sendSms(String phone, String tempcode, Map<String,Object> map,String accessKeyId);

        /**
         * 短信发送成功后，添加数据到记录表中
         * @param phone
         * @param tempcode
         * @param map
         * @param accessKeyId
         */
        public void insertLog(String phone, String tempcode, Map<String,Object> map,String accessKeyId);

        NutMap getData(int length, int start, int draw, List<DataTableOrder> orders, List<DataTableColumn> columns, Cnd cnd, String linkName, String beginDate, String endDate);
}
