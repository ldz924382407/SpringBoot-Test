package com.excel.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 211145187
 * @Date 2023/4/14 09:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    //姓名
    private String name;
    //班级
    private String classes;
    //笔试成绩
    private String writeScore;
    //机试成绩
    private String computerScore;
}
