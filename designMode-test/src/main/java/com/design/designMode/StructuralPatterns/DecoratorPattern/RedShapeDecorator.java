package com.design.designMode.StructuralPatterns.DecoratorPattern;

/**
 * 创建扩展了 ShapeDecorator 类的实体装饰类。
 * @Author 211145187
 * @Date 2022/7/4 14:50
 **/
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
