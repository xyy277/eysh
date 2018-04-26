package com.aebiz.app.sms.modules.services;

import com.aebiz.baseframework.base.service.BaseService;
import com.aebiz.app.sms.modules.models.Sms_tele_log;
import com.aebiz.baseframework.page.datatable.DataTableColumn;
import com.aebiz.baseframework.page.datatable.DataTableOrder;
import org.nutz.dao.Cnd;
import org.nutz.lang.util.NutMap;

import java.util.List;

public interface SmsTeleLogService extends BaseService<Sms_tele_log>{
    public void insertLog(Sms_tele_log info);
    NutMap getData(int length, int start, int draw, List<DataTableOrder> orders, List<DataTableColumn> columns, Cnd cnd, String linkName, String beginDate, String endDate);
}
