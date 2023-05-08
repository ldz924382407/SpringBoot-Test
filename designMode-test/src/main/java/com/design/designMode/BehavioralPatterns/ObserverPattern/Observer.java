package com.design.designMode.BehavioralPatterns.ObserverPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 15:27
 **/
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
