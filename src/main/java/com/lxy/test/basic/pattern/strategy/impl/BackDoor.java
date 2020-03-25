package com.lxy.test.basic.pattern.strategy.impl;

import com.lxy.test.basic.pattern.strategy.IStrategy;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class BackDoor implements IStrategy {
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
