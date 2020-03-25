package com.lxy.test.basic.pattern.proxy.impl;

import com.lxy.test.basic.pattern.proxy.KindWomen;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class PanJinLian implements KindWomen {
    @Override
    public void makeEyesWithMan() {
        System.out.println("潘金莲.....");
    }

    @Override
    public void happyWithMan() {
        System.out.println("潘金莲抛媚眼");
    }
}
