package com.cacheable.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {
    private int id;
    private String name;
    private int age;
    private String address;
}