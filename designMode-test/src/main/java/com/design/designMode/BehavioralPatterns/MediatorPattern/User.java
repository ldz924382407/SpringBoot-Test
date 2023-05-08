package com.design.designMode.BehavioralPatterns.MediatorPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 14:30
 **/
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name){
        this.name  = name;
    }

    public void sendMessage(String message){
        ChatRoom.showMessage(this,message);
    }
}
