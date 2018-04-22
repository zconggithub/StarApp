package quartzDemo.quartzDemo.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 周聪
 * CronTrigger的学习
 */
//Job主要编写的是我们的业务逻辑部分
public class MyJob4 implements Job {


	private static Logger _log = LoggerFactory.getLogger(MyJob4.class);
	//JobExecutionContext包含了job执行的上下文，详细见ReadMe.md文件
	public void execute(JobExecutionContext ags4) throws JobExecutionException {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("CurrentTime Execute is "+sdf.format(date));
		System.out.println("Hello world!");
				
	}

}
