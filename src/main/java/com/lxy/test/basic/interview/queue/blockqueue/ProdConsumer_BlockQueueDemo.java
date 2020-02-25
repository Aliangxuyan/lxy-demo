package com.lxy.test.basic.interview.queue.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lxy
 * @date 2019/4/21
 * <p>
 * <p>
 * 只是点串起来
 * volatile-->CAS ---> atomicInteger ---> BlockQueue ---> 线程交互 ----> 原子引用
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("production")).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, String.valueOf("consumer")).start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("s 秒时间到，大老板叫停");

        myResource.stop();

    }


}

class MyResource {
    private volatile boolean FLAG = true;    // 默认开启，进行生产 + 消费

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2l, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "插入队列成功：" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列失败：" + data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "大老板叫停：标示flag = false ,生产动作结束" + data);
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "消费超过 2s 没有取到蛋糕，消费退出：" + result);
                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列成功：" + result);

        }
    }

    public void stop() {
        this.FLAG = false;
    }

}