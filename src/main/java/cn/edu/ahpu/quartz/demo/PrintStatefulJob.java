package cn.edu.ahpu.quartz.demo;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

//public class PrintStatefulJob implements StatefulJob {
public class PrintStatefulJob implements Job {
	static Log logger = LogFactory.getLog(TestStatefulJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap dataMap = jobDetail.getJobDataMap();
		Iterator it = dataMap.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			logger.info(entry.getKey()+":"+entry.getValue());
			dataMap.put(entry.getKey(), entry.getValue()+"1");
		}
	}
}
