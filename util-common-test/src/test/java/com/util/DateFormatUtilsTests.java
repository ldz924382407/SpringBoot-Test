package com.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//【日期类型、字符串、数值型】工具
class DateFormatUtilsTests {

    @Test
    void parseUrl()  {
        String str = "/home/ems/ems_eam/../momgr/pocTemp/ezgrmpjrz/V4.5.04.010.01.zip";
        String name = str.substring(str.lastIndexOf("/") + 1);
        System.out.println(name);

        String str1 = "E:\\home\\ems\\ems_file\\data\\fm\\alarmAutoConfirm\\V4.5.04.010.01.zip";
        String name1 = str1.substring(str1.lastIndexOf("\\") + 1);
        System.out.println(name1);
    }

    //Long转String（时间毫秒数转日期格式字符串）
    @Test
    void millisecondsTransformString()  {
        long milliSecond = 1570650412089L;
        Date date = new Date();
        date.setTime(milliSecond);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));   //2019-10-10 03:46:52
    }

    //String转Long（日期格式字符串转为时间毫秒数）
    @Test
    void stringTransformMilliseconds()  {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "2020-10-11 10:42:01";
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = date.getTime();
        System.out.println("时间毫秒数：" + time1);     //输出：时间毫秒数：1602384121000
    }

    //Date转String（日期转日期格式字符串）
    @Test
    void dateTransformString()  {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String format = sdf.format(date);
        System.out.println("当前日期格式字符串：" + format);    //当前日期格式字符串：2022-05-02 16:41:52
    }

    //String转Date（日期格式字符串转日期）
    @Test
    void stringTransformDate()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = "2020-10-14 10:10:00";
        Date date = null;
        try{
            date = sdf.parse(string);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("日期：" + date);   //日期：Wed Oct 14 10:10:00 CST 2020
    }

    //Long转Date（时间毫秒数转日期）
    @Test
    void millisecondsTransformDate()  {
        long milliSecond = 1570650412089L;
        Date date = new Date();
        date.setTime(milliSecond);
        System.out.println("日期："+ date);   //日期：Thu Oct 10 03:46:52 CST 2019
    }

    //Date转Long（日期转为时间毫秒数）
    @Test
    void dateTransformMilliseconds()  {
        Date date = new Date();
        long time = date.getTime();
        System.out.println("时间毫秒数：" + time);     //输出：时间毫秒数：1651482816418
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);

        set1.removeAll(set2);
        System.out.println(set1);
    }
}
