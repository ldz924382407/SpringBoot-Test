package com.design.designMode.BehavioralPatterns.ChainOfResponsibilityPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 09:37
 **/
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
