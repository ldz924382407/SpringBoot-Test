package com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: liudz
 * @Date: 2020/6/22
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum BusinessDomainEnum {
    /**
     * 类型1
     */
    ONE("MCS.disable", "Disable"),
    /**
     * 类型2
     */
    TWO("MCS.enable", "Enable");
    private String category;
    private String value;

    /**
     * @author      liudz
     * @param       category category
     * @return      ture or false
     **/
    public static String transfer(String category) {
        BusinessDomainEnum[] values = BusinessDomainEnum.values();
        for (BusinessDomainEnum subEnum : values) {
            if (category.equals(subEnum.getCategory())) {
                return subEnum.getValue();
            }
        }
        return "";
    }
}

