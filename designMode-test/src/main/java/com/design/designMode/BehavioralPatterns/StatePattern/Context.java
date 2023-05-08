package com.design.designMode.BehavioralPatterns.StatePattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 15:44
 **/
public class Context {
    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
