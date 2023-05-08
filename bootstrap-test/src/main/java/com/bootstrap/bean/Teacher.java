package com.bootstrap.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 211145187
 * @Date 2022/2/23 09:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    //id
    private Integer id;
    //姓名
    private String name;
    //班级
    private String classes;
    //所属学院
    private String college;
    //别名
    private String alias;
    //性别【0：女    1：男】
    private Integer sex;
}
