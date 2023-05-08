package com.design.designMode.CreationalPatterns.AbstractFactoryPattern;

public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
