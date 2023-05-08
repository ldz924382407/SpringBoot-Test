package com.design.designMode.BehavioralPatterns.CommandPattern;

/**
 * 创建一个请求类。
 * @Author 211145187
 * @Date 2022/7/7 11:28
 **/
public class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] sold");
    }
}
