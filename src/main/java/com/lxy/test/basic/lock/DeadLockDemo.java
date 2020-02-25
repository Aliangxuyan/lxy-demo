package com.lxy.test.basic.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author lxy
 * @date 2019/4/22
 * <p>
 * 死锁是指两个或者两个以上进程在执行过程中，
 * 因争夺资源而造成的一种相互等待的现象，
 * 若无外力干涉那他将无法推进下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), ("ThreadAAAAs")).start();
        new Thread(new HoldLockThread(lockB, lockA), ("ThreadABBBs")).start();


    }
}

class HoldLockThread implements Runnable {
    private String LockA;
    private String LockB;

    public HoldLockThread(String lockA, String lockB) {
        this.LockA = lockA;
        this.LockB = lockB;
    }

    @Override
    public void run() {
        synchronized (LockA) {
            System.out.println(Thread.currentThread().getName() + "自己持有" + LockA + "\t 尝试获取" + LockB);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LockB) {
                System.out.println(Thread.currentThread().getName() + "自己持有" + LockA + "\t 尝试获取" + LockB);
            }
        }
    }
}