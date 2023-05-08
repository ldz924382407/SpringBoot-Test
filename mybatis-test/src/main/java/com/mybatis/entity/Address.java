package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 211145187
 * @Date 2023/4/17 22:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address implements Serializable {
    //id
    private Integer id;
    //用户名称
    private String name;
    //用户ID
    private Integer userId;

    private List<User> userList = new ArrayList<>();
}
