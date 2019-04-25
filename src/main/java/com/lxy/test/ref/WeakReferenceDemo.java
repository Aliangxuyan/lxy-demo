package com.lxy.test.ref;

import java.lang.ref.WeakReference;

/**
 * @author lxy
 * @date 2019/4/24
 * 弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(obj1);
        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(weakReference.get());
    }
}
