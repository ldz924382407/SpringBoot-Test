package com.design.designMode.StructuralPatterns.Bridge;

/**
 * 使用 DrawAPI 接口创建抽象类 Shape。
 * @Author 211145187
 * @Date 2022/7/2 09:23
 **/
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
