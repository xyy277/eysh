package com.aebiz.app.web.commons.quartz.job;

import com.aebiz.app.cms.modules.services.StaticPageService;
import com.aebiz.app.sys.modules.services.SysTaskService;
import com.aebiz.app.sys.modules.services.impl.SysTaskServiceImpl;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.SpringUtil;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时生成静态页
 * Created by zyang on 2017/9/18.
 */
public class GenerateStaticPageJob implements Job {
    private StaticPageService staticPageService = SpringUtil.getBean(StaticPageService.class);
    private SysTaskService sysTaskService = SpringUtil.getBean("sysTaskServiceImpl", SysTaskServiceImpl.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String taskId = context.getJobDetail().getKey().getName();
        JobDataMap data = context.getJobDetail().getJobDataMap();
        try {
            String siteId = data.getString("siteId");
            if (siteId != null && !StringUtils.isBlank(siteId)) {
                staticPageService.updateIndex(siteId, false);
            } else {
                staticPageService.updateIndex("", false);
            }
            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行成功").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        } catch (Exception e) {
            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行失败").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        }
    }
}
