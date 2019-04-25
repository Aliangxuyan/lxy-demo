package com.lxy.test.jvm.oom;

/**
 * @author lxy
 * @date 2019/4/25
 */
public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError(); // Exception in thread "main" java.lang.StackOverflowError
    }
}
