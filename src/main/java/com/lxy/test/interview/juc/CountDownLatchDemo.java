package com.lxy.test.interview.juc;

import com.lxy.test.interview.enums.CountTryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author lxy
 * @date 2019/4/21
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                countDownLatch.countDown();
            }, String.valueOf(CountTryEnum.forEach_CountryEnum(i).getCountTryName())).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t ************ 秦帝国一统华夏");

        System.out.println();
        System.out.println();

    }
    /**
     * CountDownLatch 关门实例
     *
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t ************ 班长最后关门走人");
    }
}
