package com.lxy.test.basic.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lxy
 * @date 2019/4/25
 * JVM 参数
 * -XX:MetaspaceSize=8m -XX:MaxspaceSize=8m
 * <p>
 * java8 及以后的版本使用 Metaspace 来替代永久代
 * <p>
 * Metaspace 是方法区在 HotSpot 中的实现，他与持久化的区别在于：Metaspace 并不在虚拟机内存中而是本地内存中
 * 也即在java8 中class Metaspace（the virtual machines internal presentation of Java class） 被存储在
 * Metaspace 的 native memoty
 * <p>
 * 永久代（java8 后被原空间 Metaspace 取代了） 存放一下信息。
 * <p>
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * j即时编译后的代码
 * <p>
 * 模拟Metaspace 空间已出，我们不断生成类往 原空间灌，类占据的空间总是会超过 Metaspace 指定的空间大小的
 */
public class MetaspaceOOMDemo {

    static class OOMTest {
    }

    public static void main(String[] args) {
        int i = 0; // 模拟计数多少次以后发生异常
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("************* 多少次以后发生了异常：" + i);
            e.printStackTrace();
        }
    }
}

