package com.design.designMode.BehavioralPatterns.NullObjectPattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 16:34
 **/
public class NullCustomer extends AbstractCustomer {

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}
