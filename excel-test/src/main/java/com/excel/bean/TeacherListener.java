package com.excel.bean;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师读取类
 * @Author 211145187
 * @Date 2022/2/23 16:50
 **/
public class TeacherListener extends AnalysisEventListener<Teacher> {

    @Getter
    private List<Teacher> teacherList = new ArrayList<>();

    public TeacherListener() {
        super();
        teacherList.clear();
    }

    /**
     * 每一条数据解析都会调用
     */
    @Override
    public void invoke(Teacher teacher, AnalysisContext context) {
        teacherList.add(teacher);
    }

    /**
     * 所有数据解析完成都会调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        teacherList.forEach(System.out::println);
    }
}
