package com.design.designMode.StructuralPatterns.DecoratorPattern;

/**
 * 创建实现了 Shape 接口的抽象装饰类。
 * @Author 211145187
 * @Date 2022/7/4 14:50
 **/
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

}
