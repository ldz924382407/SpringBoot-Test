package com.log.bean;

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
public class User {
    //姓名
    private String name;
    //密码
    private String password;
}
