package com.design.designMode.BehavioralPatterns.ObserverPattern;

/**
 * 创建实体观察者类
 * @Author 211145187
 * @Date 2022/7/7 15:28
 **/
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) );
    }
}
