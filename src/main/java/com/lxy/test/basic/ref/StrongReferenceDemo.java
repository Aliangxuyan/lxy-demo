package com.lxy.test.basic.ref;

/**
 * @author lxy
 * @date 2019/4/24
 * 强引用
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        // 这样定义的默认就是强引用
        Object obj1 = new Object();
        Object obj2 = obj1;
        // 置空
        System.out.println(obj2);
        obj1 = null;
        System.gc();
        // java.lang.Object@6f539caf
        System.out.println(obj2);

    }
}
