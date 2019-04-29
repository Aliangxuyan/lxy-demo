package com.lxy.test.jvm.gc;

/**
 * Created by lxy on 2018/12/19.
 *
 * 查看GC 回收日志 启动参数可以加 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:./gclogs
 *
 * 从 在运行结果中可以看到GC日志中包含 9349K->978K ",老年代从9349KK(大约8M,其实就是objA与objB)变为了978K,
 * 意味着虚拟并没有因为这两个对象相互引用就不回收它们,这也证明虚拟并不是通过通过引用计数算法来判断对象是否存活的
 *
 * 判断对象是否存活算法
 * 1、引用计数算法(Reference Counting)
 *
 *
 *
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int int_1MB = 1024 * 1024;

    // 这个成员属性的唯一意义就是占内存，方便在GC  日志中清楚看见被回收
    private byte[] bigSize = new byte[2*int_1MB];

    // 方法执行后objA  和 objB  不会被GC 回收
    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
