package com.lxy.test.interview.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lxy
 * @date 2019/4/21
 * <p>
 * 1、extends Thread
 * 2、implements Runnable
 * 3、implements callable
 * <p>
 * callable VS Runnable
 * 1、实现的方法，带返回值（高并发情况下有返回值更容易解决问题）
 * 2、有异常
 * 3、接口实现的方法不一样 call & run
 * <p>
 * FutureTask 只能被一个 一个Thread 对应，即是对应多个也是执行一次，
 * 底层怕有返回相同结果的情况，底层做了优化，
 * <p>
 * so 如果有多个线程想拿到不同的返回结果就应该有 多个 FutureTask 对应
 */
class MyThread implements Runnable {
    @Override
    public void run() {
    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("****************** come in callable ");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //两个线程，一个主线程，一个是 FutureTask，
        // 没有直接参数为 Callable 的构造方法，但是有 runnable 接口的 ，
        // 而FutureTask 是runnable 的实现类，所以可以用下面的方法构造
        // public FutureTask(Callable<V> callable) {}
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread2());
        new Thread(futureTask, "AAA").start();

        System.out.println(Thread.currentThread().getName() + "\t ****************");

        // while 也可以判断，直到结束，后面再进行取值，但是可能会一直阻塞，（类似有自旋锁），
//        while (!futureTask.isDone()){
//
//        }
        int result01 = 100;
        // 后面有依赖返回值时，尽量将get  尽量放到后面，给FutureTask线程更多的时间取计算，不然会有阻塞
        int result02 = futureTask.get();
        System.out.println("返回值：" + (result01 + result02));
    }
}
