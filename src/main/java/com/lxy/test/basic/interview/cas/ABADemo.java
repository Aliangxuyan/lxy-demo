package com.lxy.test.basic.interview.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author lxy
 * @date 2019/4/20
 * <p>
 * ABA 问题的解决 AtomicStampedReference
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("************************以下是ABA 问题的解决*****************************");
        new Thread(() -> {
            // ABA 问题
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号：" + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp + 2);
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, stamp, stamp + 2);
            System.out.println(Thread.currentThread().getName() + "\t 第3次版本号：" + atomicStampedReference.getStamp());
        }, String.valueOf("t3")).start();

        new Thread(() -> {
            // ABA 问题
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号：" + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 101, stamp, ++stamp);
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号：" + atomicStampedReference.getStamp() + "修改成功否：" + result);
            System.out.println(Thread.currentThread().getName() + "\t 当前实际最新值：" + atomicStampedReference.getReference());

        }, String.valueOf("t4")).start();


        System.out.println("************************以下是ABA 问题的产生*****************************");
        new Thread(() -> {
            // ABA 问题
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, String.valueOf("t1")).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("t2")).start();
    }
}
