package com.design.designMode.StructuralPatterns.FilterPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 211145187
 * @Date 2022/7/4 13:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private String gender;
    //婚姻状况
    private String maritalStatus;

}
