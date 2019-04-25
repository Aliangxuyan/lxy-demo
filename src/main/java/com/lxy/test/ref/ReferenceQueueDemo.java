package com.lxy.test.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author lxy
 * @date 2019/4/24
 *
 * 引用队列
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<Object>(obj1, referenceQueue);

        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("*************************************");

        obj1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
