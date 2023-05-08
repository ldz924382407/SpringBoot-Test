package com.design.designMode.CreationalPatterns.AbstractFactoryPattern;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
