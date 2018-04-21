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
import org.quartz.SchedulerException;

//Scheduler:任务调度器
public class MyScheduler1 {
	
	private static Logger _log = LoggerFactory.getLogger(MyScheduler1.class);
	
	
	public  void startScheduler() throws SchedulerException{

	//创建一个JobDetail的实例,将该实例与MyJob1 Class绑定
	JobDetail jobDetail1 = JobBuilder.newJob(MyJob1.class)
            .withIdentity("MyJob1", "group1").build();
	//创建一个trigger实例,定义一个job立即执行，并隔2秒钟重复执行一次，直到永远
	Trigger trigger1=TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
			.startNow()//立即执行
			.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(2).repeatForever())
			.build();
	//1.创建Scheduler的工厂
    SchedulerFactory sf = new StdSchedulerFactory();
    //2.从工厂中获取调度器实例
    Scheduler scheduler = sf.getScheduler();
  //.注册任务和定时器
    scheduler.scheduleJob(jobDetail1, trigger1);
    
    //.启动 调度器
    scheduler.start();
    _log.info("启动时间 ： " + new Date());
    
    
	}
	
}
