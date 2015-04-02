package cn.edu.ahpu.quartz.demo.chap3;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScanDirectoryJob implements Job {

	static Log logger = LogFactory.getLog(ScanDirectoryJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		// Every job has its own job detail
		JobDetail jobDetail = context.getJobDetail();
		
		// The name is defined in the job definition
		String jobName = jobDetail.getName(); // 任务名称

		// Log the time the job started
		logger.info(jobName + " fired at " + new Date());

		// The directory to scan is stored in the job map
		JobDataMap dataMap = jobDetail.getJobDataMap();
		String dirName = dataMap.getString("SCAN_DIR");

		// Validate the required input
		if (dirName == null) {
			logger.warn("SCAN_DIR参数不存在!");
		}

		// Make sure the directory exists
		File dir = new File(dirName);
		if (!dir.exists()) {
			logger.warn("不存在文件夹["+dirName+"]");
		/*	try {
				logger.warn("不存在文件夹["+dirName+"],scheduler执行shutdown().......!");
				context.getScheduler().deleteJob(jobName, jobDetail.getGroup());
				logger.warn("scheduler关闭!");
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		// Use FileFilter to get only XML files
		FileFilter filter = new FileExtensionFileFilter(".xml");

		File[] files = dir.listFiles(filter);

		if (files == null || files.length <= 0) {
			logger.info("No XML files found in " + dir);

			// Return since there were no files
			return;
		}

		// The number of XML files
		int size = files.length;

		// Iterate through the files found
		for (int i = 0; i < size; i++) {

			File file = files[i];

			// Log something interesting about each file.
			File aFile = file.getAbsoluteFile();
			long fileSize = file.length();
			String msg = aFile + " - Size: " + fileSize;
			logger.info(msg);
		}

	}

}
