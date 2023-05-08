package com.design.designMode.BehavioralPatterns.CommandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建命令调用类
 * @Author 211145187
 * @Date 2022/7/7 11:29
 **/
public class Broker {
    private List<com.design.designMode.BehavioralPatterns.CommandPattern.Order> orderList = new ArrayList<com.design.designMode.BehavioralPatterns.CommandPattern.Order>();

    public void takeOrder(com.design.designMode.BehavioralPatterns.CommandPattern.Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (com.design.designMode.BehavioralPatterns.CommandPattern.Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
