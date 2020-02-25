package com.lxy.test.basic.jvm.oom;

/**
 * @author lxy
 * @date 2019/4/25
 *
 * 不能两次start ，底层源码发现状态编码，抛异常
 */
public class T {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();
    }
}
