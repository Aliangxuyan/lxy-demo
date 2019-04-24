package com.lxy.test.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lxy
 * @date 2019/4/21
 * <p>
 * 题目：实现一个自旋锁
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait 的阻塞
 * <p>
 * 通过CAS 操作完成自旋锁，A线程先进来调用 myLock 放大自己持有锁 5秒钟，
 * B 随后进来后发现当前有线程持有锁，不是null ，所以只能通过自旋等到，直到 A 释放锁后 B 随后抢到
 */
public class SpinLockDemo {

    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in myLock");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    // 解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                // 暂停一会线程
                TimeUnit.SECONDS.sleep(5);
                spinLockDemo.myUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("AA")).start();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                // 暂停一会线程
                TimeUnit.SECONDS.sleep(2);
                spinLockDemo.myUnLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("BB")).start();

    }
}
