package com.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.excel.bean.Score;
import com.excel.bean.Teacher;
import com.excel.bean.TeacherListener;
import com.excel.service.ImportService;
import com.excel.utils.ExportUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 测试3.17版本操作Excel
 * @Author 211145187
 * @Date 2022/2/22 19:43
 **/
@RequestMapping("/excel")
@Controller
public class OperateExcelController {
    private static Logger logger = LoggerFactory.getLogger(OperateExcelController.class);
    @Autowired
    private ImportService importService;
    @Value("${export.num:10000}")
    private Integer exportLimitNum;

    //首页
    @GetMapping(value = "")
    public String index() {
        //使用@RestController不能直接返回return "index"，否则不会跳转页面，只会再页面显示index文本而已
        return "excelIndex";
    }

    //构建教师集合数据
    public List<Teacher> buildTeacherList1() {
        List<Teacher> teacherList = new ArrayList<>();
        Teacher teacher1 = new Teacher();
        teacher1.setName("周杰伦");
        teacher1.setClasses("三年二班");
        teacher1.setCollege("魔法学院");
        teacher1.setAlias("Jay Chou");
        teacherList.add(teacher1);
        Teacher teacher2 = new Teacher();
        teacher2.setName("陈奕迅");
        teacher2.setClasses("三年二班");
        teacher2.setCollege("魔法学院");
        teacher2.setAlias("Eason");
        teacherList.add(teacher2);
        Teacher teacher3 = new Teacher();
        teacher3.setName("林俊杰");
        teacher3.setClasses("三年二班");
        teacher3.setCollege("魔法学院");
        teacher3.setAlias("Eason");
        teacherList.add(teacher3);
        Teacher teacher4 = new Teacher();
        teacher4.setName("张杰");
        teacher4.setClasses("三年二班");
        teacher4.setCollege("魔法学院");
        teacher4.setAlias("Eason");
        teacherList.add(teacher4);
        return teacherList;
    }
    //构建分数集合数据
    public List<Score> buildScoreList1() {
        List<Score> scoreList = new ArrayList<>();
        Score score1 = new Score();
        score1.setName("流川枫");
        score1.setClasses("三年二班");
        score1.setWriteScore("6");
        score1.setComputerScore("4");
        scoreList.add(score1);
        Score score2 = new Score();
        score2.setName("樱木花道");
        score2.setClasses("三年二班");
        score2.setWriteScore("6");
        score2.setComputerScore("4");
        scoreList.add(score2);
        Score score3 = new Score();
        score3.setName("大猩猩");
        score3.setClasses("三年二班");
        score3.setWriteScore("6");
        score3.setComputerScore("4");
        scoreList.add(score3);
        Score score4 = new Score();
        score4.setName("三井");
        score4.setClasses("三年二班");
        score4.setWriteScore("6");
        score4.setComputerScore("4");
        scoreList.add(score4);
        return scoreList;
    }
    /**
     * 构建分数表excel的标头
     * @Author 211145187
     * @Date 2022/2/22 20:20
     * @Param wb wb
     **/
    private void buildScoreSheetTitle(XSSFWorkbook wb) {
        //建立新的sheet对象（excel的表单）
        XSSFSheet sheet=wb.createSheet("成绩表");
        XSSFRow row=sheet.createRow(0);
        //创建单元格并设置单元格内容
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("姓名");
        cell0.setCellStyle(getHeadStyle(wb));
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("班级");
        cell1.setCellStyle(getHeadStyle(wb));
        XSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("笔试成绩");
        cell2.setCellStyle(getHeadStyle(wb));
        XSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("机试成绩");
        cell3.setCellStyle(getHeadStyle(wb));
    }
    /**
     * 构建教师表excel的标头
     * @Author 211145187
     * @Date 2022/2/22 20:20
     * @Param wb wb
     **/
    private void buildTeacherSheetTitle(XSSFWorkbook wb) {
        //建立新的sheet对象（excel的表单）
        XSSFSheet sheet=wb.createSheet("教师表");
        XSSFRow row=sheet.createRow(0);
        //创建单元格并设置单元格内容
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("姓名");
        cell0.setCellStyle(getHeadStyle(wb));
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("班级");
        cell1.setCellStyle(getHeadStyle(wb));
        XSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("所属学院");
        cell2.setCellStyle(getHeadStyle(wb));
        XSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("别名");
        cell3.setCellStyle(getHeadStyle(wb));
    }

    //方法1：读取指定的Excel
    @RequestMapping(value = "/readExcel")
    @ResponseBody
    public List<Teacher> readExcel() {
        String fileName = "C:\\Users\\211145187\\Desktop\\fsdownload\\details.xls";
        TeacherListener teacherListener = new TeacherListener();
        EasyExcel.read(fileName, Teacher.class, teacherListener).sheet().doRead();
        return teacherListener.getTeacherList();
    }

    //方法2：读取上传的Excel里面的内容
    @RequestMapping(value = "/readUploadExcel")
    @ResponseBody
    public String readUploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("fileExcel");
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();
        list.forEach(System.out::println);
        return "上传成功并输出内容";
    }

    //方法3：java单文件导入Excel,保存到target的目录下
    @PostMapping(value = "/uploadExcel")
    public String uploadExcel(@RequestParam("file00") MultipartFile file, Model model) throws IOException {
        try {
            if(file.isEmpty()){
                model.addAttribute("msg","上传失败，请选择文件！");
                return "excelIndex";
            }
            String filename = file.getOriginalFilename();
            //filePath获取的是编译后的路径，而不是项目看到的路径，filePath=/E:/WorkSpace/demo/target/classes/
            String filePath = ResourceUtils.getURL("classpath:").getPath()+"static/oneFile/";
            //避免文件重复覆盖
            String uuid= UUID.randomUUID().toString().replaceAll("-", "");
            //时间戳分类文件
            String time = new SimpleDateFormat("YYYY-MM").format(new Date());
            String realPath = filePath + time + "/" + uuid + "-" + filename;
            System.out.println("realPath:" + realPath);
            //最后保存的路径在这里：target/classes/static/oneFile/2022-02/548881060e3d417a91d87b0a10959077-sop.sql
            File dest = new File(realPath);
            //检测是否存在目录，无，则创建
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();//新建文件夹 多级目录
            }
            file.transferTo(dest);//文件写入
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("msg","文件上传成功！");
        return "hello";
    }

    //方法4：java多文件导入Excel,保存到target的目录下
    @PostMapping(value = "/uploadBatchExcel")
    public String uploadBatchExcel(HttpServletRequest request, Model model) throws IOException {
        MultipartRequest request1 = (MultipartRequest)request;
        //猜测 file为 input 类型为 file
        List<MultipartFile> fileList = request1.getFiles("file");
        List<String> msgList = new ArrayList<>();
        try {
            String filePath = ResourceUtils.getURL("classpath:").getPath()+"static/multiFile/";
            for (int i = 1; i <= fileList.size(); i++){
                MultipartFile file = fileList.get(i - 1);
                if (file.isEmpty()){
                    msgList.add("上传第"+i+"个文件失败");
                    model.addAttribute("msgList",msgList);
                    continue;
                }
                String filename = file.getOriginalFilename();
                //避免文件重复覆盖
                String uuid= UUID.randomUUID().toString().replaceAll("-", "");
                //时间戳分类文件
                String time = new SimpleDateFormat("YYYY-MM").format(new Date());
                String realPath = filePath + time + "/" + uuid + "-" + filename;
                System.out.println("realPath:" + realPath);
                File dest = new File(realPath);
                //System.out.println("realPath"+realPath);
                //检测是否存在目录，无，则创建
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdirs();//新建文件夹 多级目录
                }
                msgList.add("第"+i+"个文件，上传成功!");
                file.transferTo(dest);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("msgList",msgList);
        return "hello";
    }


    //方法5：java导出Excel
    @RequestMapping("/exportExcel")
    public void createExcel(HttpServletResponse response) throws IOException {

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        buildScoreSheet(wb);
        buildTeacherSheet(wb);
        //.....省略部分代码

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details100.xls");
        response.setContentType("application/octet-stream;charset=iso8859-1");
        wb.write(output);
        output.close();
    }
    /**
     * 设置样式
     * @Author 211145187
     * @Date 2022/2/22 20:15
     * @Param wb wb
     * @Return CellStyle
     **/
    private CellStyle getHeadStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        //用于设置前景颜色
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        /**
         * setFillPattern用于设置单元格填充样式
         * 注意：
         *      1）setFillPattern必须设置否则光设置setFillForegroundColor无效
         *      2）3.10.1版本支持short类型参数，而3.17版本支持FillPatternType类型参数
         */
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }
    /**
     * 构建参数
     * @Author 211145187
     * @Date 2022/2/22 20:20
     * @Param wb wb
     **/
    private void buildScoreSheet(HSSFWorkbook wb) {
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("成绩表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
        cell.setCellStyle(getHeadStyle(wb));
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        //在sheet里创建第三行
        HSSFRow row3=sheet.createRow(2);
        row3.createCell(0).setCellValue("李明");
        row3.createCell(1).setCellValue("As178");
        row3.createCell(2).setCellValue(87);
        row3.createCell(3).setCellValue(78);
    }
    /**
     * 构建参数
     * @Author 211145187
     * @Date 2022/2/22 20:20
     * @Param wb wb
     **/
    private void buildTeacherSheet(HSSFWorkbook wb) {
        String[] titleData = new String[4];
        titleData[0] = "姓名";
        titleData[1] = "班级";
        titleData[2] = "所属学院";
        titleData[3] = "别名";
        List<Teacher> teacherList = new ArrayList<>();
        Teacher teacher1 = new Teacher();
        teacher1.setName("周杰伦");
        teacher1.setClasses("三年二班");
        teacher1.setCollege("魔法学院");
        teacher1.setAlias("Jay Chou");
        teacherList.add(teacher1);
        Teacher teacher2 = new Teacher();
        teacher2.setName("陈奕迅");
        teacher2.setClasses("三年二班");
        teacher2.setCollege("魔法学院");
        teacher2.setAlias("Eason");
        teacherList.add(teacher2);

        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("教师表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("教师一览表");
        cell.setCellStyle(getHeadStyle(wb));
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        for (int i = 0; i < titleData.length; i++) {
            HSSFCell cell2 = row2.createCell(i);
            cell2.setCellValue(titleData[i]);
            cell2.setCellStyle(getHeadStyle(wb));
        }
        //在sheet里创建第三行
        for (int j = 0; j < teacherList.size(); j++) {
            Teacher teacher = teacherList.get(j);
            HSSFRow row3 = sheet.createRow(j + 2);
            for (int k = 0; k < titleData.length; k++) {
                HSSFCell cell3 = row3.createCell(k);
                if (k == 0) {
                    cell3.setCellValue(teacher.getName());
                } else if (k == 1) {
                    cell3.setCellValue(teacher.getClasses());
                } else if (k == 2) {
                    cell3.setCellValue(teacher.getCollege());
                } else if (k == 3) {
                    cell3.setCellValue(teacher.getAlias());
                }
            }
        }
    }

    //方法5：java导出多个Excel为zip包
    @RequestMapping("/exportMultipleExcelToZip")
    public void exportMultipleExcelToZip(HttpServletResponse response) throws IOException {
        response.setHeader("Content-disposition", "attachment; filename=" + "test.zip");
        response.setContentType("application/zip; charset=utf-8");

        //创建HSSFWorkbook对象(excel的文档对象)
        XSSFWorkbook wb = null;
        List<Teacher> teacherList = new ArrayList<>();
        //构建sheet页集合
        List<Score> scoreList = new ArrayList<>();
        File templateFile = new File("C:\\Users\\211145187\\Desktop\\data模板.xlsx");

        //.....省略部分代码
        List<Teacher> buildTeacherList = buildTeacherList1();
        List<Score> buildScoreList = buildScoreList1();

        Integer pageLimitSize = exportLimitNum;
        //计算list的分页数量
        Integer pageCount = ExportUtil.getPageCount(buildTeacherList.size(), pageLimitSize);
        List<XSSFWorkbook> excels = new ArrayList<>();
        try {
            for(Integer pageNum = 1; pageNum < pageCount + 1; pageNum++) {
                //注意：每次循环遍历前都需要初始化新的wb对象
                //注意情况1：如果是初始化wb空对象然后手动添加title，下方三行代码不会报错
//                wb = new XSSFWorkbook();
//                buildScoreSheetTitle(wb);
//                buildTeacherSheetTitle(wb);

                //注意情况2：如果是初始化wb对象，并且以流的形式初始化，那么io流必须放在里面才行，如果放在for循环外面会报“Stream Closed”错误
                InputStream io = new FileInputStream(templateFile);
                wb = new XSSFWorkbook(io);
                teacherList = ExportUtil.getPageList(buildTeacherList, pageNum, pageLimitSize, pageCount);
                scoreList = ExportUtil.getPageList(buildScoreList, pageNum, pageLimitSize, pageCount);
                buildScoreSheetParams(wb, scoreList);
                buildTeacherSheetParams(wb, teacherList);
                excels.add(wb);
            }
            //最后统一封装zip压缩包并导出
            ExportUtil.downFileByStream(response, excels);
        } catch (Exception e) {
            logger.error("IOException:", e);
        }
    }

    /**
     * 填充教师页信息
     * @param wb wb
     * @param bodyData bodyData
     */
    private void buildTeacherSheetParams(Workbook wb, List<Teacher> bodyData){
        int teacherColumnCount = wb.getSheetAt(1).getRow(0).getPhysicalNumberOfCells();
        Sheet sheet = wb.getSheetAt(1);
        // build data
        for(int j=0; j<bodyData.size(); j++){
            Teacher itm = bodyData.get(j);
            Row rowData = sheet.createRow(j+1);
            for(int k=0;k<teacherColumnCount; k++){
                Cell cell = rowData.createCell(k);
                cell.setCellValue(getValueByTeacher(k, itm));
            }
        }
    }
    private String getValueByTeacher(int columnIndex,Teacher itm){
        String cellValue;
        switch (columnIndex){
            case 0:cellValue = itm.getName(); break;
            case 1:cellValue = itm.getClasses()+""; break;
            case 2:cellValue = itm.getCollege(); break;
            case 3:cellValue = itm.getAlias(); break;
            default:cellValue=""; break;
        }
        return cellValue;
    }
    /**
     * 填充分数页信息
     * @param wb wb
     * @param bodyData bodyData
     */
    private void buildScoreSheetParams(Workbook wb, List<Score> bodyData){
        int scoreColumnCount = wb.getSheetAt(0).getRow(0).getPhysicalNumberOfCells();
        Sheet sheet = wb.getSheetAt(0);
        // build data
        for(int j=0; j<bodyData.size(); j++){
            Score itm = bodyData.get(j);
            Row rowData = sheet.createRow(j+1);
            for(int k=0;k<scoreColumnCount; k++){
                Cell cell = rowData.createCell(k);
                cell.setCellValue(getValueByScore(k, itm));
            }
        }
    }
    private String getValueByScore(int columnIndex,Score itm){
        String cellValue;
        switch (columnIndex){
            case 0:cellValue = itm.getName(); break;
            case 1:cellValue = itm.getClasses()+""; break;
            case 2:cellValue = itm.getWriteScore(); break;
            case 3:cellValue = itm.getComputerScore(); break;
            default:cellValue=""; break;
        }
        return cellValue;
    }
}
