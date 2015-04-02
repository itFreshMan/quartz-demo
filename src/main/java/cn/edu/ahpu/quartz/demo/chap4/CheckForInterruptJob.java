package cn.edu.ahpu.quartz.demo.chap4;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

public class CheckForInterruptJob implements InterruptableJob {
	static Log logger = LogFactory.getLog(CheckForInterruptJob.class);

	private boolean jobInterrupted = false;

	private int counter = 5;

	private boolean jobFinished = false;

	public void interrupt() throws UnableToInterruptJobException {
		jobInterrupted = true;
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		while (!jobInterrupted && !jobFinished) {

			// Perform a small amount of processing
			logger.info(Thread.currentThread().getName()+" -- Processing the job");
			counter--;

			if (counter <= 0) {
				jobFinished = true;
			}

			// Sleep and wait for 3 seconds
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		if (jobFinished) {
			logger.info("Job finished without interrupt");
		} else if (jobInterrupted) {
			logger.info("Job was interrupted");
		}
	}
}
