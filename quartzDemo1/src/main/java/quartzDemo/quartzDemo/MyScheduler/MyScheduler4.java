package quartzDemo.quartzDemo.MyScheduler;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
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
import quartzDemo.quartzDemo.job.MyJob4;

import org.quartz.SchedulerException;
/**
 * 
 * @author 周聪	
 * CronTrigger的学习
 */
 
//Scheduler:任务调度器
public class MyScheduler4 {
	
	private static Logger _log = LoggerFactory.getLogger(MyScheduler4.class);
	
	
	public  void startScheduler(Date dates,Date endDate) throws SchedulerException{
		
		
	//创建一个JobDetail的实例,将该实例与MyJob1 Class绑定
	JobDetail jobDetail4 = JobBuilder.newJob(MyJob4.class)
            .withIdentity("MyJob4", "group4").build();
	//每秒钟触发一次
	CronTrigger trigger4 =(CronTrigger)TriggerBuilder
			.newTrigger()
			.withIdentity("trigger4", "group4")
			.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
			.build();
	//1.创建Scheduler的工厂
    SchedulerFactory sf4 = new StdSchedulerFactory();
    //2.从工厂中获取调度器实例
    Scheduler scheduler = sf4.getScheduler();
  //.注册任务和定时器
    scheduler.scheduleJob(jobDetail4, trigger4);
    
    //.启动 调度器
    scheduler.start();
  
    
    
	}
	
}
