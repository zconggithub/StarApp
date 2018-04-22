package quartzDemo.quartzDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.SchedulerException;

import quartzDemo.quartzDemo.MyScheduler.MyScheduler1;
import quartzDemo.quartzDemo.MyScheduler.MyScheduler2;
import quartzDemo.quartzDemo.MyScheduler.MyScheduler3;

/**
 * 要测试RAMdemo的代码
 *,请先删除demo中这个quartz.properties文件，或者重命名！否则会测试不成功
 */
public class App 
{
    public static void main( String[] args ) throws SchedulerException
    {
/*        MyScheduler1 myScheduler1=new MyScheduler1();
        myScheduler1.startScheduler();*/
    	
    	/*MyScheduler2 myScheduler2=new MyScheduler2();
    	myScheduler2.startScheduler();*/
    	
    	
    	Date startDates=new Date();
    	Date endDates=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("CurrentTime is "+sf.format(startDates));
    	MyScheduler3 myScheduler3=new MyScheduler3();
    	myScheduler3.startScheduler(startDates,endDates);
    	
    }
}
