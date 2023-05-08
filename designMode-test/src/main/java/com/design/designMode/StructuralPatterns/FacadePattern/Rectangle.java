package com.design.designMode.StructuralPatterns.FacadePattern;

/**
 * @Author 211145187
 * @Date 2022/7/4 16:15
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
