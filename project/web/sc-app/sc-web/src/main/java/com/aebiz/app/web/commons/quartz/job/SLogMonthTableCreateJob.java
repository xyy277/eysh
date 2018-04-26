package com.aebiz.app.web.commons.quartz.job;

import com.aebiz.app.sms.modules.models.Sms_send_log;
import com.aebiz.app.sms.modules.models.Sms_tele_log;
import com.aebiz.app.sys.modules.services.impl.SysTaskServiceImpl;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.aebiz.app.sys.modules.models.Sys_log;
import com.aebiz.app.sys.modules.services.SysTaskService;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.SpringUtil;

/**
 * Created by wizzer on 2017/1/3.
 */
public class SLogMonthTableCreateJob implements Job {
    private static final Log log = Logs.get();
    private SysTaskService sysTaskService = SpringUtil.getBean("sysTaskServiceImpl", SysTaskServiceImpl.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String taskId = context.getJobDetail().getKey().getName();
        try {
            String tabelName = DateUtil.format(DateUtil.getFirstDayOfNextMonth(), "yyyyMM");
            Dao logDao = Daos.ext(sysTaskService.dao(), tabelName);
            //生成下个月日志表
            if (!logDao.exists("sys_log_" + tabelName)) {
                logDao.create(Sys_log.class, false);
            }
            //生成下个月短信发送日志表
            if (!logDao.exists("sms_send_log_" + tabelName)) {
                logDao.create(Sms_send_log.class, false);
            }
            //生成下个月虚拟号码转移日志表
            if (!logDao.exists("sms_tele_log_" + tabelName)) {
                logDao.create(Sms_tele_log.class, false);
            }

            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行成功").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        } catch (Exception e) {
            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行失败").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        }
    }
}
