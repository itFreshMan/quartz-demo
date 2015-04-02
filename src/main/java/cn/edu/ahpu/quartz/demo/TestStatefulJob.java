package cn.edu.ahpu.quartz.demo;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class TestStatefulJob {
	static Log logger = LogFactory.getLog(TestStatefulJob.class);

	public static void main(String[] args) {
		TestStatefulJob example = new TestStatefulJob();
		example.startScheduler();
	}

	private void startScheduler() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			logger.info("Scheduler starting up...");
			
			JobDetail jobDetail = new JobDetail("PrintStatefulJob",Scheduler.DEFAULT_GROUP,PrintStatefulJob.class);
			JobDataMap dataMap = jobDetail.getJobDataMap();
			dataMap.put("name", "蒋怀双");
			dataMap.put("age", "26岁");
			
			Trigger trigger = TriggerUtils.makeSecondlyTrigger(10);
			trigger.setName("PrintStatefulJobTrigger");
			trigger.setStartTime(new Date());
			
			scheduler.scheduleJob(jobDetail, trigger);
			
			scheduler.start();

		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}

	}
}
