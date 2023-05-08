package com.design.designMode.StructuralPatterns.ProxyPattern;

/**
 * @Author 211145187
 * @Date 2022/7/4 17:12
 **/
public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
