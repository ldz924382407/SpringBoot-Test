package com.design.designMode.BehavioralPatterns.CommandPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 11:29
 **/
public class SellStock implements Order {
    private com.design.designMode.BehavioralPatterns.CommandPattern.Stock abcStock;

    public SellStock(com.design.designMode.BehavioralPatterns.CommandPattern.Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}
