package com.lxy.test.basic.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lxy
 * @date 2019/4/21
 * <p>
 * 可重入锁（也叫做递归锁）
 * 指的是统一线程外层函数获得锁之后，内存递归函数仍然能获取该锁的代码
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * <p>
 * 也即是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 * case one Synchronizes 就是典型的可重入锁
 * result:
 * t1	 invoked sendSMS()
 * t1	 ###########invoked sendMail()
 * t2	 invoked sendSMS()
 * t2	 ###########invoked sendMail()
 * <p>
 * case two ReentrantLock 就是典型的可重入锁
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, String.valueOf("t1")).start();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, String.valueOf("t2")).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}

class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendMail();
    }

    public synchronized void sendMail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t ###########invoked sendMail()");
    }


    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    /**
     *
     * 阿里电话面试题：
     * 正常情况是加一把锁，面试可能碰到加多把，注意：只要锁lock & unlock 匹配就ok
     *
     * 2lock & 1unlock  程序卡死
     *
     * 1lock & 2unlock  报错  java.lang.IllegalMonitorStateException
     */
    private void get() {
        lock.lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
//            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t #######invoked set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

