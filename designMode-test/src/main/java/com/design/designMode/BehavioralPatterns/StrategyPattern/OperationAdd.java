package com.design.designMode.BehavioralPatterns.StrategyPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 16:50
 **/
public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
