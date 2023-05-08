package com.design.designMode.BehavioralPatterns.MediatorPattern;

import java.util.Date;

/**
 * 创建中介类
 * @Author 211145187
 * @Date 2022/7/7 14:29
 **/
public class ChatRoom {
    public static void showMessage(User user, String message){
        System.out.println(new Date().toString() + " [" + user.getName() +"] : " + message);
    }
}
