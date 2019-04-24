package com.lxy.test.interview.volatiledemo;

/**
 * @author lxy
 * @date 2019/4/20
 *
 * DCL (Double check Lock 双重检锁机制) 不一定线程安全没原因是有指令重排序的存在，
 * 加上volatile 可以禁止指令重排
 *
 */
public class SingletonDenoForVolatile {
    private static volatile  SingletonDenoForVolatile instance = null;

    private SingletonDenoForVolatile() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法FIngletonDeno");

    }

    /*
    *DCL (Double check Lock 双重检锁机制)
    *
    * 改进之前的直接在改方法上加 synchronized ，效率更高，只是锁代码块
    */
    public static SingletonDenoForVolatile getInstance() {
        if (instance == null) {
            synchronized (SingletonDenoForVolatile.class) {
                if (instance == null) {
                    instance = new SingletonDenoForVolatile();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                SingletonDenoForVolatile.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
