package com.design.designMode.StructuralPatterns.DecoratorPattern;

/**
 * 创建实现接口的实体类。
 * @Author 211145187
 * @Date 2022/7/4 14:50
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
