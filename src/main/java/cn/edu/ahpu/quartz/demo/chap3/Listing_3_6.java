package cn.edu.ahpu.quartz.demo.chap3;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_3_6 {
	static Log logger = LogFactory.getLog(Listing_3_5.class);
	public static void main(String[] args) {
		Listing_3_6 example = new Listing_3_6();

		try {
			Scheduler scheduler = example.createScheduler();// 创建一个调度器scheduler
			logger.info("Scheduler started at " + new Date());
			scheduler.start();
			
			example.scheduleJob(scheduler, "ScanDirectory1",ScanDirectoryJob.class,Scheduler.DEFAULT_GROUP,"c:\\quartz-book\\input1",10);//给调度器安排任务;
			example.scheduleJob(scheduler, "ScanDirectory2",ScanDirectoryJob.class,Scheduler.DEFAULT_GROUP,"c:\\quartz-book\\input2",15);//给调度器安排任务;

		} catch (SchedulerException ex) {
			logger.error(ex);
		}
	}
	
	private void scheduleJob(Scheduler scheduler, String jobName,
			Class<ScanDirectoryJob> jobClass, String jobGroup,
			String scanDir, int scanInterval) throws SchedulerException {
		// TODO Auto-generated method stub
		JobDetail jobDetail = new JobDetail(jobName, jobGroup, jobClass);
		jobDetail.getJobDataMap().put("SCAN_DIR", scanDir);
		
		Trigger trigger = TriggerUtils.makeSecondlyTrigger(scanInterval);  
		trigger.setName(jobName+"Trigger");
		
		 trigger.setStartTime(new Date());       
         scheduler.scheduleJob(jobDetail, trigger);      
	}

	public Scheduler createScheduler() throws SchedulerException {
		return StdSchedulerFactory.getDefaultScheduler();
	}
	

}
