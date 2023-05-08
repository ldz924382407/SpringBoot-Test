package com.design.designMode.BehavioralPatterns.MementoPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 211145187
 * @Date 2022/7/7 14:37
 **/
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
