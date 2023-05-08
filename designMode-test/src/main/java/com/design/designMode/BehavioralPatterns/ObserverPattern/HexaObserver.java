package com.design.designMode.BehavioralPatterns.ObserverPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 15:28
 **/
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
