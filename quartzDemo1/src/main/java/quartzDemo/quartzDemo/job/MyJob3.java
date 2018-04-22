package quartzDemo.quartzDemo.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 周聪
 * 触发器Trigger学习
 * 触发器Trigger通用属性：
	JobKey:表示job实例的标识，触发器被触发，该指定的job实例会执行
	StartTime:表示触发器的时间表首次被触发的时间.它的值类型是Java.util.Date
	EndTime:指定触发器的不再被触发的时间。它的值类型是Java.util.Date
	
 */
//Job主要编写的是我们的业务逻辑部分
public class MyJob3 implements Job {


	private static Logger _log = LoggerFactory.getLogger(MyJob3.class);
	//JobExecutionContext包含了job执行的上下文，详细见ReadMe.md文件
	public void execute(JobExecutionContext ags3) throws JobExecutionException {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("CurrentTime Execute is "+sdf.format(date));
		Trigger currentTrigger=ags3.getTrigger();
		System.out.println("Trigger's StartTime is " +sdf.format(currentTrigger.getStartTime()));
		System.out.println("Trigger's EndTime is " +sdf.format(currentTrigger.getEndTime()));
		JobKey triggerKey3=currentTrigger.getJobKey();
		System.out.println("My Trigger name and Gourp are :【"+triggerKey3.getName()+"\t"+triggerKey3.getGroup()+"】");
		
				
	}

}
