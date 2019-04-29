package com.lxy.test.jvm.gc;

import java.util.Random;

/**
 * @author lxy
 * @date 2019/4/28
 * <p>
 * 1：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew + Tenured)
 * 2：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC    (ParNew + Tenured)
 * <p>
 * 备注：
 *  -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails        -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParNewGC
 *
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *
 *3：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC    (PSYoungGen + ParOldGen)
 *
 *4：
 * 4.1:-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen +ParOldGen)
 * 4.2: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 *5: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (par new generation + concurrent mark-sweep)
 *
 *6: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 *7:(理论知道即可，在JAVA8 中已经被优化掉了，没有了)
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
 *
 *
 *
 *以下是故意繁琐配置，主要为了学习，生产环境不会这么配置
 * 以下是故意繁琐配置，主要为了学习，生产环境不会这么配置
 * 以下是故意繁琐配置，主要为了学习，生产环境不会这么配置
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  -XX:+UseParallelOldGC (PSYoungGen +ParOldGen)
 *
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (par new generation + concurrent mark-sweep)
 */
public class GCDemo {
    public static void main(String[] args) {

        // 一下操作会产生GC
        System.out.println("*****************GCDemo Hello");
        try {
            String str = "googogoog";
            while (true) {
                str += str + new Random().nextInt(1111111) + new Random().nextInt(222222);
                str.intern();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
