package cn.edu.ahpu.quartz.demo.chap4;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class Listing_4_2 {
	static Log logger = LogFactory.getLog(Listing_4_2.class);

	public static void main(String[] args) {
		Listing_4_2 example = new Listing_4_2();
		example.startScheduler();
	}

	private void startScheduler() {
		StdSchedulerFactory factory = new StdSchedulerFactory();

		Properties props = new Properties();

		// required to supply threadpool class and num of threads

		props.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS,
				"org.quartz.simpl.SimpleThreadPool");

		props.put("org.quartz.threadPool.threadCount", "10");

		try {
			factory.initialize(props);
			Scheduler scheduler = factory.getScheduler();
			logger.info("Scheduler starting up...");
			scheduler.start();

		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}

	}
}
