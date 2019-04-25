package com.lxy.test.jvm.oom;

/**
 * @author lxy
 * @date 2019/4/25
 * <p>
 * -Xms10m -Xmx10m
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        byte[] bytes = new byte[80 * 1024 * 1024];
//        String str = "big string";
//        while (true){
//            str +=str+new Random().nextInt(1111) + new Random().nextInt(2222);
//            str.intern();
//        }
    }
}
