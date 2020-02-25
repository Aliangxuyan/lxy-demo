package com.lxy.test.basic.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 * @date 2019/4/25
 *
 * JVM 参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC 回收时间过长时会抛出OutOfMemoryError,过长的定义是：超过98% 的时间用来做GC，并且回收不到2%的堆内存
 * 连续多次GC都只回收了不到2% 的极端情况下才会抛出，假如不抛出 GC OverHead Limit 错误会发生什么呢？
 *
 * CPU 使用率一直是100%，而GC 确没有任何成果
 *
 */
public class GCOverHeadLimitExceededDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("******************* i:" + i);
            e.printStackTrace();
        } finally {
        }
    }
}
