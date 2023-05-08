package com.design.designMode.BehavioralPatterns.StrategyPattern;

/**
 * Context 是一个使用了某种策略的类
 * @Author 211145187
 * @Date 2022/7/7 16:51
 **/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
