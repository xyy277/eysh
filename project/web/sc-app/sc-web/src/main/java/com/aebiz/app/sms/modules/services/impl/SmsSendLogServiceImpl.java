package com.aebiz.app.sms.modules.services.impl;

import com.aebiz.app.sms.modules.models.Sms_mould_info;
import com.aebiz.app.sms.modules.services.SmsMouldInfoService;
import com.aebiz.app.web.commons.utils.SmsUtil;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.app.sms.modules.models.Sms_send_log;
import com.aebiz.app.sms.modules.services.SmsSendLogService;
import com.aebiz.baseframework.page.OffsetPager;
import com.aebiz.baseframework.page.datatable.DataTableColumn;
import com.aebiz.baseframework.page.datatable.DataTableOrder;
import com.aebiz.commons.utils.DateUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SmsSendLogServiceImpl extends BaseServiceImpl<Sms_send_log> implements SmsSendLogService {

    @Autowired
    private SmsMouldInfoService smsMouldInfoService;

    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    /**
     * 发送成功后插入到记录表中
     */
    @Override
    @Transactional
    public void insertLog(String phone, String tempcode, Map<String,Object> map, String accessKeyId) {

        String tableName = DateUtil.format(new Date(), "yyyyMM");
        Dao logDao = Daos.ext(dao(), tableName);

        Sms_send_log sms_send_log=new Sms_send_log();
        Cnd cnd=Cnd.NEW();
        cnd.and("code","=",tempcode);
        Sms_mould_info sms_mould_info=smsMouldInfoService.fetch(cnd);
        String str=sms_mould_info.getContent();
        Set set=map.keySet();
        Iterator it=set.iterator();
        while(it.hasNext()){
            Object key=it.next();
            Object value=map.get(key);
            str=str.replace("${"+ key + "}",value.toString());
            sms_send_log.setSend_msg(str);
        }
        sms_send_log.setMould_code(tempcode);
        sms_send_log.setReceive_person(phone);
        sms_send_log.setSend_person(accessKeyId);
        logDao.insert(sms_send_log);
    }

    public String sendSms(String phone, String tempcode, Map<String,Object> map,String accessKeyId){
        try {
            Cnd cnd=Cnd.NEW();
            cnd.and("code","=",tempcode);
            Sms_mould_info sms_mould_info=smsMouldInfoService.fetch(cnd);
            SendSmsResponse response = SmsUtil.sendSms(phone,sms_mould_info.getAli_code(),map);
            this.insertLog(phone,tempcode,map,accessKeyId);
            return response.getMessage();
        }catch (Exception e){
            e.printStackTrace();
            return "发送失败";
        }
    }

    public NutMap getData(int length, int start, int draw, List<DataTableOrder> orders, List<DataTableColumn> columns, Cnd cnd, String linkName, String beginDate, String endDate) {
        beginDate += "000000";
        endDate += "235959";

        try {
            String sql = "select * from (SELECT * FROM sms_send_log_ ";
            String where = " WHERE 1 = 1 ";//若有查询条件 在后面拼and

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            if (theSameMonth(beginDate, endDate)) {//判断是否为同一个月
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sdf.parse(beginDate));

                String tableName = "sms_send_log_" + DateUtil.format(calendar.getTime(), "yyyyMM");
                where += "and opAt between " + sdf.parse(beginDate).getTime() / 1000 + " and " + sdf.parse(endDate).getTime() / 1000;
                sql += "UNION ALL SELECT * FROM " + tableName + where;

                sql +=" ) ORDER BY opAt DESC";

            } else {
                List<String> monthList = getMonthList(beginDate, endDate);
                if (monthList != null && monthList.size() > 0) {
                    for (String tempStr : monthList) {
                        sql += " UNION ALL SELECT * FROM " + tempStr + where;
                        if (tempStr.equals(monthList.get(0))) {
                            sql += " AND opAt >= " + sdf.parse(beginDate).getTime() / 1000;
                        } else if (tempStr.equals(monthList.get(monthList.size() - 1))) {
                            sql += " AND opAt <= " + sdf.parse(endDate).getTime() / 1000;
                        }

                        sql +=" ) ORDER BY opAt DESC";
                    }

                }

            }

            Pager pager = new OffsetPager(start, length);
            List list = this.list((Sql) Sqls.create(sql).setPager(pager));

            NutMap result = new NutMap();
            if (orders != null && orders.size() > 0) {
                for (DataTableOrder order : orders) {
                    DataTableColumn col = columns.get(order.getColumn());
                    cnd.orderBy(Sqls.escapeSqlFieldValue(col.getData()).toString(), order.getDir());
                }
            }

            result.put("recordsFiltered", this.count(Sqls.create("SELECT COUNT(*) FROM (" + sql + ") sms_send_log ")));
//        if (!Strings.isBlank(linkName)) {
//            logDao.fetchLinks(list, linkName);
//        }
            result.put("data", list);
            result.put("draw", draw);
            result.put("recordsTotal", length);

            return result;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据时间 返回这两个时间之间的月份List
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    private List<String> getMonthList(String date1, String date2)
            throws ParseException {
        List<String> tempList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        //为什么要搞这么多日历类型的参数呢？
        //因为要解决一个特殊情况，也不算特殊情况啦，
        //就是当data1的日大于data2的日的 时候data1月份一直加的话 就会在月份相同的情况下，整体时间比data2大 导致data2那个月份的表漏掉
        //肯定有更便捷的写法啦~ 但是我现在有点懵逼 暂时就不优化啦。啦啦啦
        c3.setTime(sdf2.parse(DateUtil.format(c1.getTime(), "yyyyMM")));
        c4.setTime(sdf2.parse(DateUtil.format(c2.getTime(), "yyyyMM")));
        c4.add(Calendar.DAY_OF_MONTH,1);//为毛c4.set(Calendar.DAY_OF_MONTH,1);不好使？要不然省的上面定义一大堆。

        while (c4.after(c3)) {
            //若时间超过当前时间则跳出
            if (c3.getTime().getTime() > new Date().getTime()) {
                break;
            }

            tempList.add("sms_send_log_" + DateUtil.format(c3.getTime(), "yyyyMM"));
            c3.add(Calendar.MONTH, 1);
        }

        //根据现有的表进行过滤
        List<Record> existTableList = getTableList();
        List<String> resList = new ArrayList<>();

        for (Record table : existTableList) {
            String tempTableName = table.get("table_name").toString();
            for (String tempTableName2 : tempList) {
                if (tempTableName2.equals(tempTableName)) {
                    resList.add(tempTableName);
                }

            }

        }

        return resList;

    }

    private boolean theSameMonth(String date1, String date2)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
            return true;
        }

        return false;
    }

    private List<Record> getTableList() {
        return this.list(Sqls.create("SELECT table_name FROM information_schema.TABLES WHERE table_name like 'sms_send_log_2%';"));
    }

}
