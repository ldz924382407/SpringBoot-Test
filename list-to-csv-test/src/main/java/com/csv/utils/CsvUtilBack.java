package com.csv.utils;

import com.github.mygreen.supercsv.annotation.CsvColumn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CsvUtilBack {

    //日期:2020-07-24
    //作者:150513701
    //描述:List<T>转List<String>
    public static <T> List<String> ListToCsv(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        boolean first = true;
        List<String> resultList = new ArrayList<>();
        for (T t : list) {
            List<String> nameList = new ArrayList<>();
            List<String> valueList = new ArrayList<>();
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);                                                      //这句话的目的是为了能够通过反射读取JAVA类中Private修饰的变量
                try {
                    Object fieldValue = field.get(t);
                    Object fieldType = field.getType();
                    if (fieldValue != null) {
                        if (first) {                                                            //第一次进入函数需要获取列名
                            CsvColumn annotation = field.getAnnotation(CsvColumn.class);
                            if (annotation == null) {
                                nameList.add(field.getName());
                            } else {
                                nameList.add(annotation.label());
                            }
                        }
                        if (((Class) fieldType).getName() == "java.util.Date") {
                            Date date = (Date) fieldValue;
                            valueList.add(DateUtilBack.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
                        } else {
                            valueList.add(fieldValue.toString());
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //添加列头
            if (first) {
                resultList.add(String.join(",", nameList));
                first = false;
            }
            //添加列值
            resultList.add(String.join(",", valueList));
        }
        return resultList;
    }

    /**
     * 描述:把list封装成CSV数据文件返回file对象
     * 使用场景：方法返回csv文件，用于上传文件服务器或者上传windows路径下
     * @param list 数据集合
     * @param fileName 文件名称
     * @return File
     */
    public static File returnListToCsvFile(List<String> list, String fileName) {
        File csvFile = null;
        BufferedWriter csvWriter = null;

        try {
            csvFile = new File(fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            //创建文件
            csvFile.createNewFile();
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
            //写入内容
            for (String str : list) {
                csvWriter.write(str);
                csvWriter.newLine();
            }
            csvWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 描述:把list封装成CSV数据文件输出到指定文件目录
     * 使用场景：直接将csv文件上传到指定目录下
     * @param list 数据集合
     * @param fileName 文件名称
     * @param filePath 保存路径
     */
    public static void listToCsvFileSaveToDirectory(List<String> list, String filePath, String fileName) {
        File csvFile = null;
        BufferedWriter csvWriter = null;

        try {
            //创建文件
            csvFile = new File(filePath + File.separator + fileName + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
            //写入内容
            for (String str : list) {
                csvWriter.write(str);   //注意点1：这里必须传string，不能传对象.toString()，否则存入的值就不对了，所以才有了先把 List<Subscriber>转换成List<String>的步骤
                csvWriter.newLine();
            }
            csvWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
