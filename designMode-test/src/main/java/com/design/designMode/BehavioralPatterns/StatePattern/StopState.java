package com.design.designMode.BehavioralPatterns.StatePattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 15:44
 **/
public class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    //具体的行为
    public String toString(){
        return "Stop State";
    }
}