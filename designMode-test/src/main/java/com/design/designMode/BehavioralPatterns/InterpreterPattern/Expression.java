package com.design.designMode.BehavioralPatterns.InterpreterPattern;

/**
 * 创建一个表达式接口
 * @Author 211145187
 * @Date 2022/7/7 13:19
 **/
public interface Expression {
    public boolean interpret(String context);
}
