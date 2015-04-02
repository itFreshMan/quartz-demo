package cn.edu.ahpu.quartz.demo.chap3;

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

public class Listing_3_5 {
	static Log logger = LogFactory.getLog(Listing_3_5.class);

	public static void main(String[] args) {
		Listing_3_5 example = new Listing_3_5();

		try {
			Scheduler scheduler = example.createScheduler();// 创建一个调度器scheduler
			example.scheduleJob(scheduler);//给调度器安排任务;

			// Start the Scheduler running
			scheduler.start();

			logger.info("Scheduler started at " + new Date());

		} catch (SchedulerException ex) {
			logger.error(ex);
		}
	}
	
	/*
	 * return an instance of the Scheduler from the factory
	 */
	public Scheduler createScheduler() throws SchedulerException {
		return StdSchedulerFactory.getDefaultScheduler();
	}

	// Create and Schedule a ScanDirectoryJob with the Scheduler
	private void scheduleJob(Scheduler scheduler) throws SchedulerException {

		/**
		 *  定义一个job
		 *  注意：Job通过名称和组名能唯一被标识
		 */
		JobDetail jobDetail = new JobDetail("ScanDirectory",Scheduler.DEFAULT_GROUP, ScanDirectoryJob.class);
		JobDataMap jobDataMap = jobDetail.getJobDataMap();//给job设置必要的参数;
		jobDataMap.put("SCAN_DIR", "c:\\quartz-book\\input");

		// Create a trigger that fires every 10 seconds, forever
		Trigger trigger = TriggerUtils.makeSecondlyTrigger(10);//触发器,每隔10s执行一次
		trigger.setName("scanTrigger");
		// Start the trigger firing from now
		trigger.setStartTime(new Date());

		// Associate the trigger with the job in the scheduler
		scheduler.scheduleJob(jobDetail, trigger);
		
	}
}
