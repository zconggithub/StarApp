package quartzDemo.quartzDemo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quartzDemo.quartzDemo.MyScheduler.MyScheduler1;


//Job主要编写的是我们的业务逻辑部分
public class MyJob1 implements Job {

	private static Logger _log = LoggerFactory.getLogger(MyJob1.class);
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		//编写我们的业务逻辑部分
		System.out.println("Hello World!"+"/t 现在的时间是"+System.currentTimeMillis());

	}

}
