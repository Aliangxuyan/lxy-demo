package com.lxy.test.basic.pattern.strategy.impl;

import com.lxy.test.basic.pattern.strategy.IStrategy;

/**
 * @author lxy
 * @date 2020-03-25
 * 求吴国太开个绿灯
 */
public class GivenGreenLight implements IStrategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯,放行!");
    }
}
