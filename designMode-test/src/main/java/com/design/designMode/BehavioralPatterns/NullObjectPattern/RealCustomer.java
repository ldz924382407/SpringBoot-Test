package com.design.designMode.BehavioralPatterns.NullObjectPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 16:33
 **/
public class RealCustomer extends AbstractCustomer {

    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}
