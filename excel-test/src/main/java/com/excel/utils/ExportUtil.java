package com.excel.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 导出【用户、组、隶属关系】工具
 * @Author 211145187
 * @Date 2023/4/13 16:29
 **/
public class ExportUtil {
    private static Logger logger = LoggerFactory.getLogger(ExportUtil.class);

    /**
     * 自定义获取分页总页数的方法
     * @param count 查询集合数量
     * @param pageSize 配置文件中设置的单文件存储最大条数
     * @return 总页数
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
     * @param list 待分页的list数据
     * @param pageNum 页码
     * @param pageSize 页容量
     * @param pageCount 总页数
     * @return 分页后的list数据
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

    /**
     * HSSFWorkbook转file
     * @param wb wb
     * @param name 文件名称
     * @return File
     */
    public static File xssfWorkbookToFile(Workbook wb, String name) {
        File toFile = new File(name);
        try {
            OutputStream os = new FileOutputStream(toFile);
            wb.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toFile;
    }


    /**
     * 直接下载zip包
     * @param response response
     * @param excels wb集合
     */
    public static void downFileByStream(HttpServletResponse response, List<XSSFWorkbook> excels){
        try {
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            ZipOutputStream zipOutputStream = new ZipOutputStream(toClient);
            for(int i=0; i<excels.size(); i++){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 将Workbook写入内存流
                excels.get(i).write(baos);
                ZipEntry zipEntry = new ZipEntry("data" + i + ".xlsx");
                zipOutputStream.putNextEntry(zipEntry);
                // 将内存流写入Zip文件
                zipOutputStream.write(baos.toByteArray());
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.close();
        }catch (Exception e){
            logger.error("downFileByStream==========fail:{}", e.getMessage());
        }
    }
}
