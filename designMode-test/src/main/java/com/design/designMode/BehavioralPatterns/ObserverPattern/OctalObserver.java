package com.design.designMode.BehavioralPatterns.ObserverPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 15:28
 **/
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) );
    }
}
