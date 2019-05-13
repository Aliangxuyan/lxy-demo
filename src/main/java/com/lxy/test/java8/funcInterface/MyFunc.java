package com.lxy.test.java8.funcInterface;

/**
 * @author lxy
 * @date 2019/4/30
 * 函数式接口中使用泛型
 */
@FunctionalInterface
public interface MyFunc<T> {
    public T getValue(T t);
    // 可以包含默认的方法
    default int getDefalut(){
        return 0;
    }
    // 可以包含静态方法
    static int getStatic(){
        return 1;
    }
}
