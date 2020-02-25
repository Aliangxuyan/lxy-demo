package com.lxy.test.basic.interview.queue.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lxy
 * @date 2019/4/21
 *
 * 题目：多线程之前按顺序调用，实现 A->B->c 三个线程启动，要求如下：
 *
 * AA 打印 5此，BB打印10 次，CC 打印15次
 * 紧接着
 * AA 打印 5此，BB打印10 次，CC 打印15次
 * .......
 * 来10 轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
    ShareResource shareResource = new ShareResource();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
               shareResource.prints5();
            }, String.valueOf("AAA")).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                shareResource.prints10();
            }, String.valueOf("BBB")).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                shareResource.prints15();
            }, String.valueOf("CCC")).start();
        }
    }
}

class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void prints5(){
        lock.lock();
        try {
            //1 判断
            while (number != 1){
                condition1.await();
            }
            //2 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void prints10(){
        lock.lock();
        try {
            //1 判断
            while (number != 2){
                condition1.await();
            }
            //2 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void prints15(){
        lock.lock();
        try {
            //1 判断
            while (number != 3){
                condition1.await();
            }
            //2 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
