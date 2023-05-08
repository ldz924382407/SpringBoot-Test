package com.design.designMode.CreationalPatterns.AbstractFactoryPattern;

public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
