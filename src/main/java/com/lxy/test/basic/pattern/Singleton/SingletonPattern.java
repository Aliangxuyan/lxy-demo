package com.lxy.test.basic.pattern.Singleton;

/**
 * @author lxy
 * @date 2020-03-25
 */
@SuppressWarnings("all")
public class SingletonPattern {
    private static final SingletonPattern singletonPattern = new SingletonPattern();

    //限制住不能直接产生一个实例
    private SingletonPattern() {
    }

    public synchronized static SingletonPattern getInstance() {
        return singletonPattern;
    }
}