package com.design.designMode.StructuralPatterns.AdapterPattern;

/**
 * @Author 211145187
 * @Date 2022/7/1 16:12
 **/
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
