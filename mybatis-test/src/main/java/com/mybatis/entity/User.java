package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author 211145187
 * @Date 2023/4/17 22:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    //id
    private Integer id;
    //用户名称
    private String username;
    //用户密码
    private String password;
    //用户手机号码
    private String mobile;
    //性别
    private Integer gender;
    //最近一次登录IP地址
    private String lastLoginIp;

    private Address address;

    public User(String username, String password, String mobile, Integer gender) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
    }

    public User(String username, Integer gender) {
        this.username = username;
        this.gender = gender;
    }

    public User(Integer id, String username, Integer gender) {
        this.id = id;
        this.username = username;
        this.gender = gender;
    }
}
