package com.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//计算日期工具
public class CalculateDateUtilsBack {

    public static void main(String[] args) {
        //当前时间为：2022/6/23 11:33

        //获取当前的开始时间
        System.out.println(getDayBegin());  //Thu Jun 23 00:00:00 CST 2022
        //获取当前的结束时间
        System.out.println(getDayEnd());    //Thu Jun 23 23:59:59 CST 2022

        //获取昨天的开始时间
        System.out.println(getYestodayBegin()); //Wed Jun 22 00:00:00 CST 2022
        //获取昨天的结束时间
        System.out.println(getYestodayEnd());   //Wed Jun 22 23:59:59 CST 2022

        //获取明天的开始时间
        System.out.println(getTomorrowBegin()); //Fri Jun 24 00:00:00 CST 2022
        //获取明天的开始时间
        System.out.println(getTomorrowEnd());   //Fri Jun 24 23:59:59 CST 2022

        //获取指定日期的第二天同一时间
        System.out.println(getTSpecifyDateBegin(new Date()));   //Fri Jun 24 11:33:54 CST 2022

        //获取指定日期0时0分0秒
        System.out.println(getDateBegin(new Date()));   //Thu Jun 23 00:00:00 CST 2022
        //获取指定日期23时59分59秒
        System.out.println(getDateEnded(new Date()));   //Thu Jun 23 23:59:59 CST 2022

        //获取上个月的开始时间
        System.out.println(getBeforeFirstMonth());  //Sun May 01 00:00:00 CST 2022
        //获取上个月的结束时间
        System.out.println(getBeforeLastMonth());   //Tue May 31 23:59:59 CST 2022

        //获取指定日期,下个月的第一天
        System.out.println(getNextMonthBegin(new Date()));  //Fri Jul 01 00:00:00 CST 2022

        //获取间隔多少天的截至时间字符串
        System.out.println(getDelDirDateStr(30));   //20220524
        //获取间隔多少天的截至时间date
        System.out.println(getDelDirDate(30));  //Tue May 24 11:33:54 CST 2022

        //获取两个日期之间的所有日期的开始时间集合
        Date beginDate = new Date();
        beginDate.setTime(1652976000000L);  //2022-05-20 00:00:00
        Date endDate = new Date();
        endDate.setTime(1655654400000L);  //2022-06-20 00:00:00
        System.out.println(getBetweenDates(beginDate, endDate));    //[Sat May 21 00:00:00 CST 2022, Sun May 22 00:00:00 CST 2022, Mon May 23 00:00:00 CST 2022, Tue May 24 00:00:00 CST 2022, Wed May 25 00:00:00 CST 2022, Thu May 26 00:00:00 CST 2022, Fri May 27 00:00:00 CST 2022, Sat May 28 00:00:00 CST 2022, Sun May 29 00:00:00 CST 2022, Mon May 30 00:00:00 CST 2022, Tue May 31 00:00:00 CST 2022, Wed Jun 01 00:00:00 CST 2022, Thu Jun 02 00:00:00 CST 2022, Fri Jun 03 00:00:00 CST 2022, Sat Jun 04 00:00:00 CST 2022, Sun Jun 05 00:00:00 CST 2022, Mon Jun 06 00:00:00 CST 2022, Tue Jun 07 00:00:00 CST 2022, Wed Jun 08 00:00:00 CST 2022, Thu Jun 09 00:00:00 CST 2022, Fri Jun 10 00:00:00 CST 2022, Sat Jun 11 00:00:00 CST 2022, Sun Jun 12 00:00:00 CST 2022, Mon Jun 13 00:00:00 CST 2022, Tue Jun 14 00:00:00 CST 2022, Wed Jun 15 00:00:00 CST 2022, Thu Jun 16 00:00:00 CST 2022, Fri Jun 17 00:00:00 CST 2022, Sat Jun 18 00:00:00 CST 2022, Sun Jun 19 00:00:00 CST 2022]
        //获取两个日期之间所有月份的集合
        System.out.println(getBetweenMonth(beginDate, endDate));    //[Sun May 01 00:00:00 CST 2022, Wed Jun 01 00:00:00 CST 2022]
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param format 日期格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (format == null || format.equals("")) {
            format = "yyyy-mm-dd";
        }
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }


    /**
     * author:Yu Yang
     * 获取当前的开始时间
     */
    public static Date getDayBegin(){
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * author:Yu Yang
     * 获取当前的结束时间
     */
    public static Date getDayEnd(){
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    /**
     * author:Yu Yang
     * 获取昨天的开始时间
     */
    public static Date getYestodayBegin(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * author:Yu Yang
     * 获取昨天的结束时间
     */
    public static Date getYestodayEnd(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);

        return cal.getTime();
    }

    /**
     * author:Yu Yang
     * 获取明天的开始时间
     */
    public static Date getTomorrowBegin(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
    /**
     * author:Yu Yang
     * 获取明天的结束时间
     */
    public static Date getTomorrowEnd(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * author:Yu Yang
     * 获取指定日期的第二天同一时间
     */
    public static Date getTSpecifyDateBegin(Date date){
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }


    /**
     * author:Cui Yang
     * 获取指定日期0时0分0秒
     */
    public static Date getDateBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    /**
     * author:Cui Yang
     * 获取指定日期23时59分59秒
     */
    public static Date getDateEnded(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }



    /**
     * author:Yu Yang
     * 获取上个月的开始时间
     */
    public static Date getBeforeFirstMonth(){

        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * author:Yu Yang
     * 获取上个月的结束时间
     */
    public static Date getBeforeLastMonth(){
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取指定日期,下个月的第一天
     */
    public static Date getNextMonthBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 获取当前月最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * author:Yu Yang
     * 获取间隔多少天的截至时间字符串
     */
    public static String getDelDirDateStr(Integer interval){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.DAY_OF_MONTH, 0 - interval);// 获取当前月最后一天
        return dateFormat.format(calendar.getTime());
    }
    /**
     * author:Yu Yang
     * 获取间隔多少天的截至时间date
     */
    public static Date getDelDirDate(Integer interval){
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.DAY_OF_MONTH, 0 - interval);// 获取当前月最后一天
        return calendar.getTime();
    }


    /**
     * 获取两个日期之间的所有日期的开始时间集合
     * 返回Date的List
     */
    public static List<Date> getBetweenDates(Date start, Date end){
        List<Date> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR,1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);

        while (tempStart.before(tempEnd)){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 获取两个日期之间所有月份的集合
     */
    public static List<Date> getBetweenMonth(Date start, Date end){
        List<Date> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.set(tempStart.get(Calendar.YEAR), tempStart.get(Calendar.MONTH), 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        tempEnd.set(tempEnd.get(Calendar.YEAR), tempEnd.get(Calendar.MONTH), 2);

        while (tempStart.before(tempEnd)){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.MONTH,1);
        }
        return result;
    }
}