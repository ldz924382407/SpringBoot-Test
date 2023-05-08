package com.design.designMode.BehavioralPatterns.ChainOfResponsibilityPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 09:37
 **/
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
