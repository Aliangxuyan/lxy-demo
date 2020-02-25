package com.lxy.test.basic.datatructuresalgorithms.datastructure;

/**
 * Created by lxy on 2019/4/19.
 *
 * 栈既可以用数组实现也可以用链表实现
 */
public class ArrayStack {
    private String[] items;     // 数组
    private int count;          // 栈中元素个数
    private int n;              // 栈的大小

    public ArrayStack(int n){
        items = new String[n];
        this.count = n;
        this.n = 0;
    }

    public boolean push(String item){
        if (count == n){
            return false;
        }
        items[count]=item;
        ++count;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        --count;
        return items[count-1];

    }
}
