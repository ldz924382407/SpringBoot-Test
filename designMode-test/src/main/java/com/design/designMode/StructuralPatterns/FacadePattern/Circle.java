package com.design.designMode.StructuralPatterns.FacadePattern;

/**
 * @Author 211145187
 * @Date 2022/7/4 16:19
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
