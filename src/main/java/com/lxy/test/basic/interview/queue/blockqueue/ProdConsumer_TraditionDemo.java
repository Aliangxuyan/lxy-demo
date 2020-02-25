package com.lxy.test.basic.interview.queue.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lxy
 * @date 2019/4/21
 *
 * 传统方式
 * <p>
 * 题目：一个初始值为0 的变量，两个线程对其交替操作，一个加1，一个减1 来5轮
 * <p>
 * 1    线程      操作(方法)      资源类
 * 2    判断      干活      通知
 * 3    防止虚假唤醒（一定用while）
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareDate = new ShareData();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf("AAA")).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf("BBB")).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf("CCC")).start();
        }
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf("DDD")).start();
        }
    }
}

/**
 * 资源类
 */
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {

        lock.lock();
        try {
            //1 判断
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t  " + number);

            //3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {

        lock.lock();
        try {
            //1 判断
            while (number == 0) {
                //等待，不能消费
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t  " + number);

            //3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}