package com.lxy.test.basic.interview.queue.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lxy
 * @date 2019/4/21
 * <p>
 * ArrayBlockingQueue:
 * 是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序
 * <p>
 * LinkedBlockingDeque
 * 是一个基于链表结构的阻塞队列，此队列按 FIFO (先进先出) 排序元素，吞吐量要高于ArrayBlockingQueue
 * <p>
 * SynchronousQueue
 * 是一个 不存储元素的阻塞队列，每一个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态
 * 吞吐量一般要高
 * <p>
 * <p>
 * 1 队列
 * 2 阻塞队列
 * 2.1 阻塞队列有没有好的一面
 * <p>
 * 2.2 不得不阻塞，你如果管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        timeout();
    }

    /**
     * 超时组
     * @throws InterruptedException
     */
    private static void timeout() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }

    /**
     * 没有或者队列已满，一直会阻塞，比较危险慎用，不过用好，特别棒
     * @throws InterruptedException
     */
    private static void blockType() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("*****************************");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
    }

    /**
     * 特殊类型
     */
    private static void specialType() {
        // List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 异常型
     */
    private static void exceptionType() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.add("x"));
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
