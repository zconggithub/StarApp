							Quzrtz学习

1、Job实例在Quartz中的生命周期
	Job就是实现逻辑业务的。
	每次调度器执行Job时，它在调用Execute方法前会创建一个新的Job实例。
当调用完成后，关联的Job对象实例会被释放，释放的实例会被垃圾回收机制回收
2、JobDetail	
JobDetail为Job实例提供了很多设置属性，以及JobDataMap成员变量属性，它用来存储特定Job实例的状态信息
调度器需要借助JobDetail对象来添加Job实例。	
	重要属性：
	name:任务的名称			
	group:任务所在的组，默认值是Default	
	JobClass:任务的实现类	【本demo中的MyJob】
	JobDataMap：用来传参数的作用及其他
譬如（JobDataMap参数传递）：可以在jobDetail中如下写到：可参考Demo中的MyJob1及对应的MyScheduler1
		JobDetail jobDetail1 = JobBuilder.newJob(MyJob1.class)
	            .withIdentity("MyJob1", "group1").usingJobData("ParameMessage1","hello world")
	            .usingJobData("ParameMessage2",3.14F).build();//主要便于理解jobDataMap的参数传递
当然也可以参数通过Trigger中写。方法同上

3、JobExecutionContext
	当Scheduler调用一个job,就会将JobExecutionContext传递给Job的execute()方法；
	Job能通过JobExecutionContext对象访问到Quzrtz运行时候的环境以及Job本身的明细数据

4、JobDataMap
	在进行任务调度时JobDataMap存储在JobExecutionContext中，非常方便获取；
	JobDataMap可以用来装载任何可序列化的数据对象，当job实例对象被执行时这些参数对象会传递给它。
	JobDataMap实现了JDK的Map接口，并添加了一些非常方便的方法用来存取基本数据类型

获取JobDataMap的两种方式
	从Map中直接获取【如MyJob1，MyScheduler1中的方式】
	 Job实现类中添加setter方法对应JobDataMap的键值（Quartz框架默认的JobFactory实现类在初始化Job实例对象时候会自动地调用这些setter方法）【如MyJob2，MyScheduler2中的方式】

5、Trigger
	Trigger就是Quartz中的触发器，触发器来告诉调度程序作业什么时候触发。即Trigger对象是用来触发执行Job的
	Trigger是通过TriggerBuilder来创建的
	Trigger是有两个实现类：CroTriggerImpl和SimpleTriggerImpl
触发器Trigger通用属性：
	JobKey:表示job实例的标识，触发器被触发，该指定的job实例会执行
	StartTime:表示触发器的时间表首次被触发的时间.它的值类型是Java.util.Date
	EndTime:指定触发器的不再被触发的时间。它的值类型是Java.util.Date
譬如实例：【Demo中MyJob3，MyScheduler3中的方式】
	5.1SimpleTrigger的作用（定时定频率）【可以跟Java中的Timer来实现】
	在指定时间段内执行一次作业任务。或是在指定的时间间隔内多次执行任务
	
6、CronTrigger【Myjob4,Scheduler4】
CronTrigger的作用：它是基于日历的作业调度器，而不是像SimpleTrigger那样精确指定间隔时间，比SimpleTrigger更常用
【Cron表达式】用于配置CronTrigger实例，描述了时间的详细信息
格式：[秒] [分][小时][日][月][周][年]
字段		是否必填			允许值			允许的特殊字符
秒		是				0-59			，- * /
分		是				0-59			，- * /
小时		是				0-59			，- * /	
日		是				0-23				, - * ? /L W C
月		是				1-31				, - * /
周		是				1-12或者JAN-DEC		, - * ?/L C	# 
年		否				empty,1970-2099		, - * /
字符意义：
，逗号：表示或的关系
-减号 	：表示“至”的意思
*		表示“每”的意思
/ 除号：表示“每”的意思
举例：0 15 10 ？ * * ：每天10点15分触发
0 0/5 14 * * ？ ：每天下午的2点到2点59分（整点开始，每隔5分触发）【0/5表示从0开始每隔5钟开始】

【Cron表达式生成器,浏览器可直接搜索在线生成工具】

7、Scheduler-工厂模式
所有的Scheduler实例是由SchedulerFactory来创建
有两个实现类：SchedulerFactory【最常用】和DirectSchedulerFactory
	Scheduler的创建方式
①		SchedulerFactory sfact= new SchedulerFactoty();
		Scheduler scheduler=sfact.getScheduler();
②		DirectSchedulerFactoty factory=DirectSchedulerFactoty.getInstache();
		Scheduler scheduler=factory.getScheduler();
Scheduler的主要函数
void start() :开始
void startBy():暂时挂起【可以重新启动，即scheduler.start()】
void shutdown():将scheduler关闭。不能重启。支持传入参数：
shutdown(true)表示等待所有正在执行的job执行完毕后，再关闭Scheduler
shutdown(false)表示直接关闭scheduler

Quzrtz.properties
尽可能使用声明式，简化代码实现，方便配置统一改变和维护：
Quzrtz.properties文档的位置和加载顺序
如果工程中没有此配置文件，它会自动去读取相关jar包中的配置文件
Quzrtz.properties文档组成部分：【详见配置文件】
调度器属性：
			org.quartz.scheduler.instanceName/org.quartz.scheduler.instanceid
线程池属性：【关系到后台处理线程的性能】
threadCount很重要：决定quartz线程的创建，至少为1，一般设置为1-100，无默认值
threadPriority:线程的优先级，最大为java.lang.Thread.Max_PRIORITY 10,
作业存储设置：
		描述了在调度器实例的生命周期中，Job和Trigger信息是如何被存储的【内存中还是DB中】
插件配置：
	满足特定需求用到的Quartz插件的配置。
