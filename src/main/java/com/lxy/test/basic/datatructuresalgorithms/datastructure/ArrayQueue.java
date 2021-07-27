package com.lxy.test.basic.datatructuresalgorithms.datastructure;

/**
 * Created by lxy on 2019/4/19.
 * <p>
 * 数组实现队列, ——》 链表也可以实现 ——》循环队列
 *
 * 循环队列 队列可以避免数据搬移问题
 */
public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;

    }




    /**
     * 入队，有缺陷，因为即时空间够，随着出队数据会没地方插入，需要数据的移动
     */
    public boolean enequeue(String item) {
        // 表示队列已满
        if (tail == n) {
            // 整个队列都占满啦
            if(head == 0){
                return  false;
            }
            // 数据搬移
            for (int i = 0; i < head;i++){
                items[i] = items[ i + head];
            }
            // 搬完之后更新head  和tail
            tail = tail - head;
            head = 0;

            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }


    /**
     * 入队，有缺陷，因为即时空间够，随着出队数据会没地方插入，需要数据的移动
     */
    public boolean enequeue1(String item) {
        // 表示队列已满
        if (tail == n) {
            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String deQueue(){
        // 如果head  ==  tail 表示队列已空
        if (head == tail) return null;
        String result = items[head];
        head ++;
        return result;

    }
}
