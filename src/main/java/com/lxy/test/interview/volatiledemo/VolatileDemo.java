package com.lxy.test.interview.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1、 验证 volatile 的可见性
 *      1.1 假如 int number = 0;number 变量之前根本没有添加 volatile 关键字修饰
 * 结果；因为主内存的 AAA 线程将 主线程mydata 数据number 改了，但是没有通知主线程，对main 主线程不可见，所以在while 循环一直等待
 *
 *      1.2 添加了volatile 可以解决可见性问题
 *
 * 2、验证volatile 不保证原子性
 *      2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败。
 *
 *      2.1 volatile 不保证原子性案例演示
 *
 *      2.3 为什么不能保证原子性
 *      javap 汇编编译完之后可以看到该过程执行了三条命令
 *          2: getfield      #2                  // Field number:I
 *          5：iconst_1
 *          6: iadd
 *          7: putfield      #2                  // Field number:I
 *      2.4 怎样保证原子性
 *          使用 atomicXXX
 *          因为底层原理是 CAS （从unsafe 类和 自旋锁角度理解）
 *
 *
 *
 * Created by lxy on 2019/4/20.
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上面20 个线程都全部即算完成后在用main 线程取得最终的结果值看是多少？
        /**
         * 多线程控制上面计算线程执行完时间，默认后台有两个线程一个是main 线程一个是后台 GC 线程（有其他线程时 > 2）
         * Thread.yield(); 等待上面另外20 个线程执行完
         */
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomicInteger finally number value:" + myData.atomicInteger);
    }

    /**
     * 1.volatile 可以保证可见性及时通知其他线程主物理内存的值已经被修改
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData(); // 资源类

        // 线程1
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " \t come in");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                myData.addAtomic();
                System.out.println(Thread.currentThread().getName() + " \t update number value");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        // 线程2 就是我们的主线程
        while (myData.number == 0) {
            // main 线程就一致在这等待循环，知道number  值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + " \t missing is over,main get number valeu: " + myData.number);
    }
}

class MyData {
    // ***********************可见性**************************
    // 情况一：没有可见性
//    int number = 0;
    // 情况二： volatile 关键字修饰
    volatile int number = 0;
    public void addTo60() {
        this.number = 60;
    }
    // ***********************原子性******************************
    // 请注意，此时number  前面是加了volatile 关键字修饰的 volatile  不保证原子性

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}