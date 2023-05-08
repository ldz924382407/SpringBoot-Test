package com.design.designMode.BehavioralPatterns.TemplatePattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 16:58
 **/
public class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}
