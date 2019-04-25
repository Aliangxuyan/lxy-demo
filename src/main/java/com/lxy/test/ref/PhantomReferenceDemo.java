package com.lxy.test.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author lxy
 * @date 2019/4/24
 * <p>
 * 虚引用
 *
 * java 提供了4中引用类型，在垃圾回收的时候，都有自己各自的特点。
 * ReferenceQueue 是用来配合引用工作的，没有 ReferenceQueue 一样可以运行
 *
 * 创建引用的时候可以指定关联的队列，当GC 释放对象内存的时候，会将引用加入到引用队列，
 * 如果程序发现某个引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动，
 * 这相当于是一种通知机制（和 Spring aop 的后置通知很像）
 *
 * 当关联的引用队列中有数据的时候，意味着引用指向的d堆内存中的对象被回收，通过这种方式，
 * JVM 允许 我们在对象被销毁后，做一些我们自己想做的事情。
 *
 * 一般是用在监控对象的回收情况，99.99% 在日常工作中用不到
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj1,referenceQueue);
        System.out.println(obj1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("*******************************");
        obj1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(obj1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
