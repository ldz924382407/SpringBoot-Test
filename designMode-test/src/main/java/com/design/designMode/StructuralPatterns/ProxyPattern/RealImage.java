package com.design.designMode.StructuralPatterns.ProxyPattern;

/**
 * 创建实现接口的实体类
 * @Author 211145187
 * @Date 2022/7/4 17:11
 **/
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}
