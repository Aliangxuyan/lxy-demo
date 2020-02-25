package com.lxy.test.basic.ref;

import java.lang.ref.SoftReference;

/**
 * @author lxy
 * @date 2019/4/24
 * <p>
 * 软引用
 */
public class SoftReferenceDemo {

    // 内存够用的时候就保留，不够用就回收
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());

    }

    /**
     * JVM 配置。故易产生大对象并配置小的内存，让内存不够用导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    // 内存够用的时候就保留，不够用就回收
    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
        softRef_Memory_NotEnough();
    }
}
