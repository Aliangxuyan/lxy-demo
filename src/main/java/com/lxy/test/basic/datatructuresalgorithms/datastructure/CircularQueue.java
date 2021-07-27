package com.lxy.test.basic.datatructuresalgorithms.datastructure;

/**
 * @author lxy
 * @date 2020-04-21
 * <p>
 * 数组实现循环队列，
 * <p>
 * 队列为空 head == tail
 * 队列已满 tail == (head + 1)%n
 */
public class CircularQueue {

    private String[] items;
    private int n = 0;
    private int tail = 0;
    private int head = 0;

    /**
     * 申请一个大小为capacity的数组
     *
     * @param capacity
     */
    public CircularQueue(int capacity) {
        items = new String[capacity];
        this.n = capacity;
    }

    /**
     * 入队列
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        // 队列满了
        if (tail == (head + 1) % n) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue(){
        // 如果head == tail 表示队列为空
        if (tail == head) return null;
        String result = items[head];
        head  = (head + 1) % n;
        return  result;
    }


}
