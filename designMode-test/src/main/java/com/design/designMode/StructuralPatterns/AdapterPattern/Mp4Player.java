package com.design.designMode.StructuralPatterns.AdapterPattern;

/**
 * @Author 211145187
 * @Date 2022/7/1 16:12
 **/
public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
