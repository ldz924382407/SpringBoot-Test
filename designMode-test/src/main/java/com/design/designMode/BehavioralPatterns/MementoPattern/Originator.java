package com.design.designMode.BehavioralPatterns.MementoPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 14:37
 **/
public class Originator {
    private String state;

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento){
        state = Memento.getState();
    }
}
