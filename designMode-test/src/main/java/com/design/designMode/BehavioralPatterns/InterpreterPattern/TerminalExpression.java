package com.design.designMode.BehavioralPatterns.InterpreterPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 13:19
 **/
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
