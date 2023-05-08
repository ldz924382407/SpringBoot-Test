package com.design.designMode.StructuralPatterns.FacadePattern;

/**
 * 创建一个外观类
 * @Author 211145187
 * @Date 2022/7/4 16:16
 **/
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
