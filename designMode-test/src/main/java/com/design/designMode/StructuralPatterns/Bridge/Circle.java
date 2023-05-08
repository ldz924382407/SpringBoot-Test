package com.design.designMode.StructuralPatterns.Bridge;

/**
 * 创建实现了 Shape 抽象类的实体类。
 * @Author 211145187
 * @Date 2022/7/2 09:23
 **/
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
