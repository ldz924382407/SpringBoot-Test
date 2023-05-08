package com.quartz.bean;

import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.TriggerKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 练习使用Quartz
 * 新建一个能够打印任意内容的Job
 * @Author 211145187
 * @Date 2022/4/6 17:06
 **/
public class PrintWordsJob implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "triggerGroup1");
        //获取任务状态
        System.out.println("任务状态:" + jobExecutionContext.getScheduler().getTriggerState(triggerKey));

        //获取job标识
        System.out.println("getJobKey():" + jobExecutionContext.getTrigger().getJobKey());  //输出job1，group1
        //获取trigger标识
        System.out.println("getKey():" + jobExecutionContext.getTrigger().getKey());    //输出trigger1，triggerGroup1

        //get(key)和getString(key)等效
        System.out.println("get(\"name1\")：" + jobExecutionContext.getMergedJobDataMap().get("name1"));
        System.out.println("getString(\"name2\")：" + jobExecutionContext.getMergedJobDataMap().getString("name2"));
        System.out.println("get(\"name1\")：" + jobExecutionContext.getJobDetail().getJobDataMap().get("name1"));
        System.out.println("getString(\"name2\")：" + jobExecutionContext.getJobDetail().getJobDataMap().getString("name2"));

        //输出打印时间
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));
    }
}
