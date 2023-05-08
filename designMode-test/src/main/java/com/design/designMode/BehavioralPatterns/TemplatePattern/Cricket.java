package com.design.designMode.BehavioralPatterns.TemplatePattern;

/**
 * @Author 211145187
 * @Date 2022/7/7 16:57
 **/
public class Cricket extends Game {

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }
}
