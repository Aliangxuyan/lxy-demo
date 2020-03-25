package com.lxy.test.basic.pattern.Factory.impl;

import com.lxy.test.basic.pattern.Factory.Human;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class BlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑人会哭");
    }

    @Override
    public void cry() {
        System.out.println("黑人会笑");
    }

    @Override
    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");
    }
}
