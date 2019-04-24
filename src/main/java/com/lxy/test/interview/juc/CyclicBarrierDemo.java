package com.lxy.test.interview.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lxy
 * @date 2019/4/21
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // CyclicBarrier(int parties,Runnable barrierAction) 函数式接口
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("**********召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第：" + tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
