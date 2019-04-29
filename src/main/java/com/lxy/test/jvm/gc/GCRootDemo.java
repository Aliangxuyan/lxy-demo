package com.lxy.test.jvm.gc;

/**
 * @author lxy
 * @date 2019/4/22
 * <p>
 * Java 中可以作为 GC Roots 的对象
 * <p>
 * 1、虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象。
 * 2、方法区中类静态属性引用的对象。
 * 3、方法区中常量引用的对象
 * 4、本地方法栈中 JNI（native 方法）引用的对象。
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100 * 1024 * 1024];

    // private static GCRootDemo2 t2;   // 情况2
    // private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1() {
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC 完成");
    }

    public static void main(String[] args) {
        m1();// 情况1 : t1 就是虚拟机栈中的引用的对象

    }

}
