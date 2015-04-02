package cn.edu.ahpu.quartz.demo.chap3;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleScheduler {
	static Log logger = LogFactory.getLog(SimpleScheduler.class); 
	
	public static void main(String[] args) {
		SimpleScheduler simple = new SimpleScheduler();       
        simple.startScheduler(); 
	}

	private void startScheduler() {
		Scheduler scheduler = null;
		 try {       
             // Get a Scheduler instance from the Factory       
             scheduler = StdSchedulerFactory.getDefaultScheduler();       
    
             // Start the scheduler       
             scheduler.start();       
             logger.info("调度起启动： " + new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));       
    
        } catch (SchedulerException ex) {       
             // deal with any exceptions       
             logger.error(ex);       
        } 
	}
	
	private void modifyScheduler(Scheduler scheduler) {       
	    try {       
	         if (!scheduler.isInStandbyMode()) {       
	              // pause the scheduler       
	              scheduler.standby();       
	         }       
	      
	         // Do something interesting here       
	      
	         // and then restart it       
	         scheduler.start();       
	      
	    } catch (SchedulerException ex) {       
	         logger.error(ex);       
	    }       
	}  
}
