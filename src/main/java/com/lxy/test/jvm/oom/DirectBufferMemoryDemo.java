package com.lxy.test.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @author lxy
 * @date 2019/4/25
 * <p>
 * 配置参数
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * <p>
 * 故障现象：
 * Exception in thread "main" java.lang.OutOfMemoryError:Direct buffer memory
 * <p>
 * 导致原因：
 * 写 NIO 程序经常使用 ByteByffer 来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的Nio 方式
 * 他可以使用Native 韩数据直接分配堆外内存，然后通过一个存储在Java 堆里面的 DirectByteBuffer 对象作为
 * 这块内存的引用进行操作，这样能在一些场景中显著提高性能，因为避免了在Java 堆和 Native 堆中来回复制数据
 * <p>
 * ByteBuffer.allocate ：第一种凡事是分配JVM 堆内存，属于GC 管辖范围，由于需要拷贝所以速度相对较慢
 * <p>
 * ByteBuffer.allocateDirect ： 第二种方式是分配 os 本地呢村，不属于GC 管辖范围，由于不需要拷贝所以速度相对较快
 * <p>
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM 就不需要执行GC，DirectVyteBuffer 对象们就不会被回收
 * 这样堆内存充足，但本地内存可能已经是用光了，再次尝试分配本地内存就会出现
 * <p>
 * 那程序就直接崩溃了。
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置 maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double) 1024) / 1024 + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // -XX:MaxDirectMemorySize=5m 我们配置为5m ，但实际使用6m ,故意使坏
    ByteBuffer bb = ByteBuffer.allocateDirect(10 * 1024 * 1024);

}
