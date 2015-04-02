package cn.edu.ahpu.quartz.demo.chap5;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import cn.edu.ahpu.quartz.demo.chap4.CheckForInterruptJob;

public class Listing_5_4 {
	static Log logger = LogFactory.getLog(Listing_5_4.class);

	public static void main(String[] args) {
		Listing_5_4 example = new Listing_5_4();
		example.startScheduler();
	}

	private void startScheduler() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			logger.info("Scheduler starting up...");
			scheduler.start();

			JobDetail jobDetail = new JobDetail("CheckForInterruptJob", Scheduler.DEFAULT_GROUP, CheckForInterruptJob.class);
			
			String cronExpression = "";
			//每天的从下午 2 点到下午的 7:59 间的每分钟触发一次
			cronExpression = "0/15 * 14-20 * * ? ";
			CronTrigger trigger = new CronTrigger("CheckForInterruptJobTrigger", Scheduler.DEFAULT_GROUP,cronExpression );
			trigger.setStartTime(new Date());
			
			scheduler.scheduleJob(jobDetail, trigger);
			
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
