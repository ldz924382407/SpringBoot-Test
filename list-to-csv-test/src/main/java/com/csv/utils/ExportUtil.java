package com.csv.utils;

import java.util.List;

public class ExportUtil {

    /***
     * 自定义获取分页总页数的方法
     * 传参：list总数、页容量
     * 返回：总页数
     */
    public static Integer getPageCount(Integer count, Integer pageSize){

        Integer pageCount = 0;

        if(count.equals(0)){
            return pageCount;
        }

        pageCount = count/pageSize;
        if(count % pageSize != 0){
            pageCount++;
        }

        return pageCount;
    }

    /**
     * 自定义List分页工具
     * 传参：待分页的list数据、页码、页容量
     * 返回值：分页后的list数据
     */
    public static <T> List<T> getPageList(List<T> list, Integer pageNum, Integer pageSize, Integer pageCount){
        /**开始索引*/
        int beginIndex = 0;

        /**结束索引*/
        int endIndex = 0;

        Integer compare = pageNum.compareTo(pageCount);
        if(!compare.equals(0)){
            beginIndex = (pageNum - 1) * pageSize;
            endIndex = beginIndex + pageSize;
        }else{
            beginIndex = (pageNum - 1) * pageSize;
            endIndex = list.size();
        }
        List pageList = list.subList(beginIndex, endIndex);
        return pageList;
    }
}