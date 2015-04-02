package cn.edu.ahpu.quartz.demo.chap4;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_3 {
	static Log logger = LogFactory.getLog(Listing_4_3.class);

	public static void main(String[] args) {
		Listing_4_3 example = new Listing_4_3();
		example.startScheduler();
	}

	private void startScheduler() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			logger.info("Scheduler starting up...");
			scheduler.start();

		/*	JobDetail jobDetail = new JobDetail("CheckForInterruptJob", Scheduler.DEFAULT_GROUP, CheckForInterruptJob.class);
			
			Trigger trigger = TriggerUtils.makeSecondlyTrigger(10);
			trigger.setName("CheckForInterruptJobTrigger");
			trigger.setStartTime(new Date());
			
			scheduler.scheduleJob(jobDetail, trigger);*/
			
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}

	}
}
