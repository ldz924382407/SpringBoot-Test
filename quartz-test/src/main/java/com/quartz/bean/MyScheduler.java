package com.quartz.bean;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 练习使用Quartz
 * 创建Schedule，执行任务
 * @Author 211145187
 * @Date 2022/4/6 17:07
 **/
public class MyScheduler {
    //这里使用的是ThreadPoolExecutor的完整版构造函数
    private static final ThreadPoolExecutor singlePool = new ThreadPoolExecutor(10,10,100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100),
            new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()
            ,new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //案例1：触发器类型为SimpleTrigger的简单任务实例，执行main方法，执行一次/每间隔1s，5s后结束执行
        SimpleTrigger1();
        //案例2：触发器类型为SimpleTrigger的简单任务实例，下面的程序就实现了程序运行5s后开始执行Job，执行Job 5s后结束执行
//        SimpleTrigger2();
        //案例3：触发器类型为CronTrigger 的简单任务实例，执行main方法，执行一次/每间隔1s，5s后结束执行
//        CronTrigger1();
        //案例4：使用阿里线程池，模拟定时执行任务途中停止任务，休息几秒后再激活任务，输出查看任务状态等信息
//        singlePool.execute(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                //执行暂停方法
//                pauseJob();
//                //睡眠2秒，让暂停方法和激活方法中间有点时间间隔
//                Thread.sleep(2000);
//                //激活任务
//                resumeJob();
//            }
//        });
    }

    /**
     * 案例1：触发器类型为SimpleTrigger的简单任务实例，执行main方法，执行一次/每间隔1s，5s后结束执行
     * @Author 211145187
     * @Date 2022/4/6 17:23
     **/
    public static void SimpleTrigger1() throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1") //使用具有给定名称和组来标识 JobDetail的身份
                .build();
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()    //设置调度器类型：构建SimpleTrigger简单触发器
                    .withIntervalInSeconds(1)//每隔1s执行一次
                    .repeatForever()//一直执行
                ).build();

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------SimpleTrigger1 scheduler start ! ------------");
        scheduler.start();

        //睡眠
        Thread.sleep(5000);
        scheduler.shutdown();
        System.out.println("--------SimpleTrigger1 scheduler shutdown ! ------------");
    }
    /**
     * 案例2：触发器类型为SimpleTrigger的简单任务实例，可以实现在一个指定时间段内执行一次作业任务或一个时间段内多次执行作业任务
     *      下面的程序就实现了程序运行5s后开始执行Job，执行Job 5s后结束执行
     * 注意：
     *      1.JobDetail和Trigger设置同名参数会被覆盖
     *      2.JobDetail实例设置的参数使用jobExecutionContext.getJobDetail().getJobDataMap()和jobExecutionContext.getMergedJobDataMap()都可以获取到，而Trigger实例设置的参数只能使用jobExecutionContext.getMergedJobDataMap()才可以获取到
     *      3.参数设置有两种方式：
     *          设置参数方式1，直接使用usingJobData设置，用于执行execute()中获取
     *          设置参数方式2，使用jobDetail.getJobDataMap().put(key, value)设置，用于执行execute()中获取
     *      4.可以封装TriggerKey传入Trigger的withIdentity(形参中)，也可以直接设置值  =》 既.withIdentity("trigger1", "triggerGroup1") 和.withIdentity(triggerKey)等效
     * @Author 211145187
     * @Date 2022/4/6 17:23
     **/
    public static void SimpleTrigger2() throws SchedulerException, InterruptedException {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 5000);
        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 5000);

        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")     //使用具有给定名称和组来标识 JobDetail的身份
                .usingJobData("name1", "孙悟空")   //设置参数方式1，直接使用usingJobData设置，用于执行execute()中获取
                .build();
        jobDetail.getJobDataMap().put("name3", "猪八戒");  //设置参数方式2，使用jobDetail.getJobDataMap().put(key, value)设置，用于执行execute()中获取
        //注意：可以封装TriggerKey传入Trigger的withIdentity(形参中)，也可以直接设置值  =》 既.withIdentity("trigger1", "triggerGroup1") 和.withIdentity(triggerKey)等效
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "triggerGroup1");
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")   //使用具有给定名称和组来标识 Trigger的身份
                .startNow()//立即生效
                .usingJobData("name2", "这是jobDetail1的trigger")   //设置参数，用于执行execute()中获取
                .startAt(startDate) //设置开始时间
                .endAt(endDate) //设置结束时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()    //设置调度器类型：构建SimpleTrigger简单触发器
                    .withIntervalInSeconds(1)//每隔1s执行一次
                    .withRepeatCount(1)//定义重复执行次数
                    .repeatForever()//一直执行
                ).build();

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------SimpleTrigger2 scheduler start ! ------------");
        scheduler.start();

        //睡眠
        Thread.sleep(10000);
        scheduler.shutdown();
        System.out.println("--------SimpleTrigger2 scheduler shutdown ! ------------");
    }

    /**
     * 案例3：触发器类型为CronTrigger 的简单任务实例，执行main方法，执行一次/每间隔1s，5s后结束执行
     * 注意：
     *      cron表达式6位：秒分时日月周
     *      cron表达式7位：秒分时日月周年
     * @Author 211145187
     * @Date 2022/4/6 17:23
     **/
    public static void CronTrigger1() throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")
                .build();    //使用具有给定名称和组来标识 JobDetail的身份
        // 3、构建Trigger实例,每隔1s执行一次,执行10s后停止
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger")
                .startNow()//立即生效
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))     //设置调度器类型：构建CronTrigger简单触发器
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        System.out.println("--------CronTrigger1 scheduler start ! ------------");
        scheduler.start();

//        //睡眠
//        Thread.sleep(10000);
//        scheduler.shutdown();
//        System.out.println("--------CronTrigger1 scheduler shutdown ! ------------");
    }

    /**
     * 暂停任务
     * 注意：
     *      1.这里传的是job的name和group名字，千万不要写成trigger的名字和组名
     *      2.name和group都要跟任务job的一致，负责执行无效
     **/
    public static void pauseJob() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        JobKey jobKey = new JobKey("job1", Scheduler.DEFAULT_GROUP);   //无效。组名不一致
        JobKey jobKey = new JobKey("job1", "group1");
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.pauseJob(jobKey);
        System.out.println("--------CronTrigger1 pauseJob ! ------------");
    }
    //激活任务
    public static void resumeJob() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        JobKey jobKey = new JobKey("job1", "group1");
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.resumeJob(jobKey);
        System.out.println("--------CronTrigger1 resumeJob ! ------------");
        System.out.println("输出下次执行之间:" +scheduler.getTriggersOfJob(jobKey).get(0).getFireTimeAfter(new Date()));    //输出下次执行时间
    }
}
