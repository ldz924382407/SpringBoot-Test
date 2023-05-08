package com.design.designMode.StructuralPatterns.Bridge;

/**
 * @Author 211145187
 * @Date 2022/7/2 09:23
 **/
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
