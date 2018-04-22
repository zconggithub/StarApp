package quartzDemo.quartzDemo.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 周聪
 *MyJob1主要是简单的理解Quartz中的job、JobDetail、Trigger、Scheduler
 *参数传递JobDataMap,及JobExecutionContext
 *获取JobDataMap的第一种方式，参考文档详见ReadMe.md文件
 */
//Job主要编写的是我们的业务逻辑部分
public class MyJob1 implements Job {

	private static Logger _log = LoggerFactory.getLogger(MyJob1.class);
	//JobExecutionContext包含了job执行的上下文，详细见ReadMe.md文件
	public void execute(JobExecutionContext ags) throws JobExecutionException {
		// TODO Auto-generated method stub
		//编写我们的业务逻辑部分
		System.out.println("Hello World!"+"/t 现在的时间是"+System.currentTimeMillis());
		//获取JobDetail任务的名称和组
		JobKey JobDetailkey=ags.getJobDetail().getKey();
		System.out.println("My Job name and Gourp are :【"+JobDetailkey.getName()+"\t "+JobDetailkey.getGroup()+"】");
		//获取JobDetail下的参数
		JobDataMap jobDataMapParam=ags.getJobDetail().getJobDataMap();
		System.out.println("JobDataMap's param1 is 【"+jobDataMapParam.getString("ParameMessage1")+"】");
		System.out.println("JobDataMap's param2 is 【"+jobDataMapParam.getFloat("ParameMessage2")+"】");
		
		//获取Trigger任务的名称和组
		TriggerKey triggerKey=ags.getTrigger().getKey();
		System.out.println("My Trigger name and Gourp are :【"+triggerKey.getName()+"\t"+triggerKey.getGroup()+"】");
		//获取Trigger下设置的参数
		JobDataMap triggerDataMap=ags.getTrigger().getJobDataMap();//获取trigger下的参数
		System.out.println("tdataMap's param is 【"+triggerDataMap.getString("ParameMessage3")+"】");//此处MyScheduler1中trigger中并未设置Trigger参数
		
		
		//=====================上面参数获取分别从Trigger和JobDetail中获取，代码觉得很繁琐，下面这种方式很直接===================================
		System.out.println("================jobDetail和Trigger直接合并参数获取================================");
		JobDataMap jobdataMap=ags.getMergedJobDataMap();
		System.out.println("JobDataMap's param1 is 【"+jobdataMap.getString("ParameMessage1")+"】");
		System.out.println("JobDataMap's param2 is 【"+jobdataMap.getFloat("ParameMessage2")+"】");
		System.out.println("JobDataMap's param2 is 【"+jobdataMap.getFloat("ParameMessage3")+"】");
	}

}
