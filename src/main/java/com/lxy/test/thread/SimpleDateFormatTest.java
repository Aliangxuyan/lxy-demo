package com.lxy.test.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lxy
 * @date 2019-05-13
 */
public class SimpleDateFormatTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) throws ParseException {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }

    // *************************************synchronized***********************************************
    public static String formatDateSync(Date date) throws ParseException {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    public static Date parseSync(String strDate) throws ParseException {
        synchronized (sdf) {
            return sdf.parse(strDate);
        }
    }

    //****************************************ThreadLocal************************************************
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date parseThreadLocal(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String formatThreadLocal(Date date) {
        return threadLocal.get().format(date);
    }

    //******************************************LocalDateTime **********************************************
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatDate2(LocalDateTime date) {
        return formatter.format(date);
    }

    public static LocalDateTime parse2(String dateNow) {
        return LocalDateTime.parse(dateNow, formatter);
    }


    public static void main(String[] args) throws InterruptedException, ParseException {

//        singleThread();
//        multiThread();
//        multiThreadSync();
//        multiThreadThreadLocal();
        DateTimeFormatter1_8();
    }

    /**
     * 基于JDK1.8的DateTimeFormatter
     *  * This class is immutable and thread-safe. 源码会发现的DateTimeFormatter 是安全的
     * 也是《阿里巴巴开发手册》给我们的解决方案，对之前的代码进行改造
     * jdk 1.8 DateTimeFormatter  方法支持
     *
     * @throws InterruptedException
     */
    public static void DateTimeFormatter1_8() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int j = 0; j < 20; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println(parse2("2018-01-02 09:45:59"));
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    /**
     * ThreadLocal可以确保每个线程都可以得到单独的一个SimpleDateFormat的对象，那么自然也就不存在竞争问题了。
     *
     * @throws InterruptedException
     */
    public static void multiThreadThreadLocal() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int j = 0; j < 20; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(parseThreadLocal("2018-01-02 09:45:59"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }


    /**
     * 简单粗暴
     * synchronized往上一套也可以解决线程安全问题，缺点自然就是并发量大的时候会对性能有影响，线程阻塞。
     *
     * @throws InterruptedException
     */
    public static void multiThreadSync() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int j = 0; j < 20; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(parseSync("2018-01-02 09:45:59"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    /**
     * 多线程情况,SimpleDateFormat 线程不安全
     * <p>
     * Exception in thread "pool-1-thread-1" java.lang.NumberFormatException: multiple points
     */
    public static void multiThread() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int j = 0; j < 20; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(parse("2018-01-02 09:45:59"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    // 单线程情况下是安全的
    private static void singleThread() {
        System.out.println(sdf.format(new Date()));
    }
}

