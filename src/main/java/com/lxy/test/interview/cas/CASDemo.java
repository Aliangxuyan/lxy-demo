package com.lxy.test.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lxy
 * @date 2019/4/20
 *
 * 1 CAS  是什么？ (compareAndSet )
 *   比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // main do thing......

        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t cunrent data: " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t cunrent data: " + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
