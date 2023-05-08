package com.csv.controller;

import com.csv.bean.Subscriber;
import com.csv.utils.CsvUtilBack;
import com.csv.utils.ExportUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试使用把list封装成csv文件
 * @Author 211145187
 * @Date 2022/6/21 09:50
 **/
public class ListToCsvController {
    /**
     * 模拟使用场景描述：假设list是从数据库查出的拥有至少10w条数据的集合信息，假设pageSize是我从配置文件读出的配置信息，通过for循环就可以把10W条数据按照pageSize分成10份文件上传到服务器上，模拟的是方案2实现方式
     * 实现方案有2种：
     *      第一种：每次分页查询1W条数据 =》 封装成file上传指定服务器
     *      第二种：一次查询10W条数据 =》 通过for循环就可以把10W条数据按照pageSize分成10份文件上传到服务器上
     */
    public static void main(String[] args) {
        List<Subscriber> list = new ArrayList<>();
        list.add(new Subscriber(1000500, 123456, "dy1", 1, 1, 0));
        list.add(new Subscriber(1000501, 123456, "dy2", 1, 3, 0));
        list.add(new Subscriber(1000502, 123456, "dy3", 1, 5, 0));

        //假设pageSize是我从配置文件读出的配置信息
        Integer pageSize = 10000;
        Integer pageCount = ExportUtil.getPageCount(list.size(), pageSize);

        String filePath = "C:\\Users\\211145187\\Desktop\\fsdownload";
        String fileName = "userModelList";

        for(Integer pageNum = 1; pageNum < pageCount + 1; pageNum++){
            try {
                List<Subscriber> pageList = ExportUtil.getPageList(list, pageNum, pageSize, pageCount);
                List<String> fileStr = CsvUtilBack.ListToCsv(pageList);
                //调用方法1：把list封装成CSV数据文件输出到指定文件目录
                CsvUtilBack.listToCsvFileSaveToDirectory(fileStr, filePath, fileName);
                System.out.println("--ListToCsvFileSaveToDirectory-upload-success");
                //调用方法2：把list封装成CSV数据文件返回file对象
//                File file = CsvUtilBack.returnListToCsvFile(fileStr, fileName);
//                System.out.println("--returnListToCsvFile-upload-success" + file);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
