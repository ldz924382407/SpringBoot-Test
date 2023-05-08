package com.design.designMode.StructuralPatterns.Bridge;

/**
 * 创建实现了 DrawAPI 接口的实体桥接实现类。
 * @Author 211145187
 * @Date 2022/7/2 09:22
 **/
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
