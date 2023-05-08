package com.design.designMode.BehavioralPatterns.MementoPattern;

/**
 * 包含了要被恢复的对象的状态
 * @Author 211145187
 * @Date 2022/7/7 14:37
 **/
public class Memento {
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}
