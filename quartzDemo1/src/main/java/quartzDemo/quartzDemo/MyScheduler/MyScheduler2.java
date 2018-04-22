package quartzDemo.quartzDemo.MyScheduler;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quartzDemo.quartzDemo.job.MyJob1;
import quartzDemo.quartzDemo.job.MyJob3;

import org.quartz.SchedulerException;
/**
 * 
 * @author 周聪	对应MyJob1参靠
 * *参数传递JobDataMap,及JobExecutionContext
 *获取JobDataMap的第二种方式，参考文档详见ReadMe.md文件
 */
 
//Scheduler:任务调度器
public class MyScheduler2 {
	
	private static Logger _log = LoggerFactory.getLogger(MyScheduler2.class);
	
	
	public  void startScheduler() throws SchedulerException{

	//创建一个JobDetail的实例,将该实例与MyJob1 Class绑定
	/*JobDetail jobDetail1 = JobBuilder.newJob(MyJob1.class)
            .withIdentity("MyJob1", "group1").build();*/
		//下面是理解jobDetail中的参数结合JobExecutionContext中的实例
		JobDetail jobDetail2 = JobBuilder.newJob(MyJob3.class)
	            .withIdentity("MyJob2", "group2").usingJobData("ParameMessage1","hello world")
	            .usingJobData("ParameMessage2",3.14F).build();//主要便于理解jobDataMap的参数传递
/*	System.out.println("JobDetail's name:"+jobDetail1.getKey().getName());//获取任务的名
	System.out.println("JobDetail's group:"+jobDetail1.getKey().getGroup());//获取任务所在的组
	System.out.println("JobDetail's jobClass:"+jobDetail1.getJobClass().getName());//获取任务的名类
*/
	
	//创建一个trigger实例,定义一个job立即执行，并隔2秒钟重复执行一次，直到永远
	Trigger trigger2=TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
			.startNow()//立即执行
			.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(2).repeatForever())
			.build();
	//1.创建Scheduler的工厂
    SchedulerFactory sf2 = new StdSchedulerFactory();
    //2.从工厂中获取调度器实例
    Scheduler scheduler = sf2.getScheduler();
  //.注册任务和定时器
    scheduler.scheduleJob(jobDetail2, trigger2);
    
    //.启动 调度器
    scheduler.start();
    _log.info("启动时间 ： " + new Date());
    
    
	}
	
}
