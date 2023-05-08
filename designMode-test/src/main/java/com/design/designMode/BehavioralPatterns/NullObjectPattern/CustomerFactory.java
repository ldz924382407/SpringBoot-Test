package com.design.designMode.BehavioralPatterns.NullObjectPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 16:34
 **/
public class CustomerFactory {

    public static final String[] names = {"Rob", "Joe", "Julie"};

    public static AbstractCustomer getCustomer(String name){
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)){
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}
