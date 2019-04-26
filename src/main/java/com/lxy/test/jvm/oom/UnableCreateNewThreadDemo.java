package com.lxy.test.jvm.oom;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author lxy
 * @date 2019/4/25
 * <p>
 * 高并发请求服务器时，经常出现异常：java.lang.OutOfMemoryError: unable to create new native thread
 * 准确的讲该native thread 异常与对应的平台有关
 * <p>
 * 导致原因：
 * 1    你的应用创建了太多线程了，一个应用进程创建多个线程，超过系统承载极限
 * 2    你的服务器并不允许你的应用程序创建那么多线程，Linux 系统默认允许单个进程可以创建的线程数是1024个
 * 你的应用创建超过这个数量，就回报
 * <p>
 * 解决办法：
 * 1   想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建那么多线程，如果不是，改代码将代码线程数降到最低
 * 2   对于有的应用，确实需要创建很多线程，远超过Linux 系统的默认的1024 个线程的限制，可以通过修改Linux 服务配置，
 * 扩大Linux 默认限制
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {

        for (int i = 0; ; i++) {
            System.out.println("***************** i = " + i);
            new Thread(() -> {
                try {
                    System.out.println("==== APP  STARTED ====");
                    RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
                    String name = runtime.getName();
                    System.out.println(name);
                    System.out.println("Process ID: " + name.substring(0, name.indexOf("@")));
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, " " + i).start();
        }
    }
}
