package com.lxy.test.jvm.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author lxy
 * @date 2019-05-17
 * MXBean 监控一些信息
 * <p>
 * MXbean库对JVM信息进行采集
 */
public class MXBeanDemo {
    public static void main(String[] args) {
        getThreadInfo();
    }

    /**
     * 使用Java提供的MXBean来监控jvm创建了哪些线程
     */
    private static void getThreadInfo() {
        System.out.println("below is thread info:");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ": " + threadInfo.getThreadName());
        }
    }
}
