package com.lxy.test.java8;

import com.lxy.test.java8.funcInterface.MyFunc;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author lxy
 * @date 2019/4/30
 *
 * 作为参数传递 lambda  表达式，为了 将lambda  表达式作为参数传递，
 * 接受lambda 表达式的参数类型必须是与该lambda 表达式兼容的 函数式接口的类型
 * */
public class LambdaTest {
    public static void main(String[] args) {
        method();
    }
    public static void method() {
        // 作为参数传递lambda  表达式
        String newStr = toUpperString(str ->
           str.toUpperCase(),"asasas"
        );
        System.out.println(newStr);
    }

    public  static String toUpperString(MyFunc<String> myFunc, String str) {
        return myFunc.getValue(str);
    }

    /**
     * 简单对比
     */
    private static void simple() {
        // 匿名内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        };

        TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        // *************************************************************************************

        // lambda 表达式
        Runnable runnable1 = () -> {
            System.out.println("lambda 表达式");
        };

        TreeSet<String> set1 = new TreeSet<>((o1, o2) ->
            Integer.compare(o1.length(), o2.length())
        );
    }


}
