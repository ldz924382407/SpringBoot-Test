package com.design.designMode.BehavioralPatterns.ChainOfResponsibilityPattern;

/**
 * 创建扩展了该记录器类的实体类。
 * @Author 211145187
 * @Date 2022/7/7 09:37
 **/
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
