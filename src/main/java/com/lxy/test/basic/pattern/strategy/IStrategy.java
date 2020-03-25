package com.lxy.test.basic.pattern.strategy;

/**
 * @author lxy
 * @date 2020-03-25
 * 首先定一个策略接口，这是诸葛亮老人家给赵云的三个锦囊妙计的接口
 */
public interface IStrategy {
    //每个锦囊妙计都是一个可执行的算法
    public void operate();
}
