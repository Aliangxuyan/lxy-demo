package com.lxy.test.basic.pattern.strategy.impl;

import com.lxy.test.basic.pattern.strategy.IStrategy;

/**
 * @author lxy
 * @date 2020-03-25
 * 孙夫人断后，挡住追兵
 */
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
