package quartzDemo.quartzDemo;

import org.quartz.SchedulerException;

import quartzDemo.quartzDemo.MyScheduler.MyScheduler1;

/**
 * 要测试RAMdemo的代码
 *,请先删除demo中这个quartz.properties文件，或者重命名！否则会测试不成功
 */
public class App 
{
    public static void main( String[] args ) throws SchedulerException
    {
        MyScheduler1 myScheduler1=new MyScheduler1();
        myScheduler1.startScheduler();
    	
    	
    }
}
