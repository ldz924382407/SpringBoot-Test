package com.aop.custom.annotation.bean;

import lombok.Data;

/**
 * @Author 211145187
 * @Date 2022/2/23 09:32
 **/
@Data
public class OperateTeacherReq {
    //id
    private Integer id;
    //姓名
    private String name;
    //开关【0:Disable、1：Enable】
    private Integer pstnFlag;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", pstnFlag:" + pstnFlag +
                '}';
    }
}
