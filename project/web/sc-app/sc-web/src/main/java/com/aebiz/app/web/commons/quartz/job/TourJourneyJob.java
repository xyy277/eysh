package com.aebiz.app.web.commons.quartz.job;

import com.aebiz.app.sys.modules.services.SysTaskService;
import com.aebiz.app.sys.modules.services.impl.SysTaskServiceImpl;
import com.aebiz.app.tourist.modules.services.TourJourneyService;
import com.aebiz.app.tourist.modules.services.TourUserService;
import com.aebiz.app.tourist.modules.services.TourVerseService;
import com.aebiz.app.tourist.modules.services.impl.TourJourneyServiceImpl;
import com.aebiz.app.tourist.modules.services.impl.TourUserServiceImpl;
import com.aebiz.app.tourist.modules.services.impl.TourVerseServiceImpl;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.SpringUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by aj907 on 2018/4/13.
 */
public class TourJourneyJob implements Job {

    private TourJourneyService tourJourneyService = SpringUtil.getBean("tourJourneyServiceImpl",TourJourneyServiceImpl.class);
    private TourVerseService tourVerseService = SpringUtil.getBean("tourVerseServiceImpl",TourVerseServiceImpl.class);
    private TourUserService tourUserService = SpringUtil.getBean("tourUserServiceImpl",TourUserServiceImpl.class);
    private SysTaskService sysTaskService = SpringUtil.getBean("sysTaskServiceImpl", SysTaskServiceImpl.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String taskId = context.getJobDetail().getKey().getName();
        JobDataMap data = context.getJobDetail().getJobDataMap();
        try {
            //文章审核

            //精彩文章推荐

            //精彩短篇比较并推送首页

            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行成功").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        } catch (Exception e) {
            sysTaskService.update(Chain.make("exeAt", (int) (System.currentTimeMillis() / 1000)).add("exeResult", "执行失败").add("nextAt", DateUtil.getTime(context.getNextFireTime())), Cnd.where("id", "=", taskId));
        }
    }
}
