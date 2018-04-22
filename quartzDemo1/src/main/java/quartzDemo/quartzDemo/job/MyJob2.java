package quartzDemo.quartzDemo.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 周聪
 *
 *参数传递JobDataMap,及JobExecutionContext
 *获取JobDataMap的第二种方式，参考文档详见ReadMe.md文件
 */
//Job主要编写的是我们的业务逻辑部分
public class MyJob2 implements Job {

	//通过setter方法传递参数
	private  String ParameMessage1;
	private  Float ParameMessage2;
	
	
	public String getParameMessage1() {
		return ParameMessage1;
	}
	public void setParameMessage1(String parameMessage1) {
		ParameMessage1 = parameMessage1;
	}
	public Float getParameMessage2() {
		return ParameMessage2;
	}
	public void setParameMessage2(Float parameMessage2) {
		ParameMessage2 = parameMessage2;
	}
	private static Logger _log = LoggerFactory.getLogger(MyJob2.class);
	//JobExecutionContext包含了job执行的上下文，详细见ReadMe.md文件
	public void execute(JobExecutionContext ags) throws JobExecutionException {
		// TODO Auto-generated method stub
		//编写我们的业务逻辑部分
		System.out.println("================jobDetail和Trigger直接合并参数获取================================");
		JobDataMap jobdataMap=ags.getMergedJobDataMap();
		System.out.println("JobDataMap's param1 is 【"+jobdataMap.getString("ParameMessage1")+"】");
		System.out.println("JobDataMap's param2 is 【"+jobdataMap.getFloat("ParameMessage2")+"】");
		System.out.println("JobDataMap's param2 is 【"+jobdataMap.getFloat("ParameMessage3")+"】");
		  
		
	}

}
