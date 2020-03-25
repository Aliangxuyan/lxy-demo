package com.lxy.test.basic.pattern.Singleton;

/**
 * @author lxy
 * @date 2020-03-25
 */
public class Emperor {
    private static Emperor emperor = null;

    private Emperor() {
    }

    public static Emperor getInstance() {
        if (emperor == null) {
            emperor = new Emperor();
        }
        return emperor;
    }

    public static void emperorInfo() {
        System.out.println("我就是皇帝某某某....");
    }
}
