package quartzDemo.quartzDemo.MyScheduler;
import java.text.SimpleDateFormat;
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
 * @author 周聪	
 * 触发器Trigger学习
 * 触发器Trigger通用属性：
	JobKey:表示job实例的标识，触发器被触发，该指定的job实例会执行
	StartTime:表示触发器的时间表首次被触发的时间.它的值类型是Java.util.Date
	EndTime:指定触发器的不再被触发的时间。它的值类型是Java.util.Date

 */
 
//Scheduler:任务调度器
public class MyScheduler3 {
	
	private static Logger _log = LoggerFactory.getLogger(MyScheduler3.class);
	
	
	public  void startScheduler(Date dates,Date endDate) throws SchedulerException{
		
		
	//创建一个JobDetail的实例,将该实例与MyJob1 Class绑定
	JobDetail jobDetail3 = JobBuilder.newJob(MyJob3.class)
            .withIdentity("MyJob3", "group3").build();
	
	//获取距离当前时间3秒后的时间
	dates.setTime(dates.getTime()+3000);
		//获取距离当前时间6秒后的时间为截至时间
		endDate.setTime(endDate.getTime()+6000);
	//创建一个trigger实例,定义一个job立即执行，并隔2秒钟重复执行一次，直到永远
	Trigger trigger3=TriggerBuilder.newTrigger()
			.withIdentity("trigger3", "group3")
			.startAt(dates)//triggerStartTime//触发的首次时间
			.endAt(endDate)//triggerEndTime//触发失效时间
			.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(2).repeatForever())
			.build();
	//1.创建Scheduler的工厂
    SchedulerFactory sf3 = new StdSchedulerFactory();
    //2.从工厂中获取调度器实例
    Scheduler scheduler = sf3.getScheduler();
  //.注册任务和定时器
    scheduler.scheduleJob(jobDetail3, trigger3);
    
    //.启动 调度器
    scheduler.start();
  
    
    
	}
	
}
