package cn.edu.ahpu.quartz.demo.z_log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;


/**
 * 
 *必须在< log4j.version> 1.2.16</ log4j.version >版本以及之后的版本，
 *否则可能 rootLogger设置的level会不起作用;
 */
public class TestLog4j {
	static Log logger = LogFactory.getLog(TestLog4j.class); 
	public static void main(String[] args) {
		PropertyConfigurator
		.configure("src\\main\\resources\\log4j.properties");
//		 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL 对应 5，4，3，2，1
		logger.debug( " debug " );
		logger.info( " info " );
		logger.warn(" warn ");
        logger.error( " error " );
	}
}
