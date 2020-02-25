package com.lxy.test.basic.interview.ThreadPool;

import java.util.concurrent.*;

/**
 * @author lxy
 * @date 2019/4/21
 * <p>
 * 第四种获得/使用 java 多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(2,
            5,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy() // 四种拒绝策略可以挨个执行看看结果
        );

        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    /**
     * 基础的 线程池
     */
    private static void threadPoolInitBase() {
        // 一池5 个处理线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 一池1线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 一池n 线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
