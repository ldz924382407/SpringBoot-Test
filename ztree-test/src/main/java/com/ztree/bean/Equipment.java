package com.ztree.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 装备
 * @Author 211145187
 * @Date 2022/3/26 10:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    //id
    private int id;
    //父id
    private int parentId;
    //名称
    private String name;


}
