package com.lxy.test.basic.pattern.Template.impl;

import com.lxy.test.basic.pattern.Template.HummerModel;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class HummerH2Model extends HummerModel {
    @Override
    public void start() {
        System.out.println("悍马H2发动...");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H2鸣笛...");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H2引擎声音是这样在...");
    }

}
