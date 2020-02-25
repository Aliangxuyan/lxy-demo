package com.lxy.test.basic.jvm.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author lxy
 * @date 2019/4/22
 * <p>
 * jps -l
 * jinfo -flag PrintGCDetails 31410
 */
public class HelloGC {
    public static void main(String[] args) {
//        // -Xms10m -Xmm10m
//        byte[] bigByte = new byte[50 * 1024 * 1024];
        block();
    }

    /**
     * 查看JVM 的参数通过程序
     */
    private static void queryParams() {
        // 返回java 虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("total_memoty = " + totalMemory + "字节，" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("max_memoty = " + maxMemory + "字节" + (maxMemory / (double) 1024 / 1024) + "MB");
    }

    private static void block() {
        System.out.println("**********************");
        try {
            TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
