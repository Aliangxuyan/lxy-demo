package com.lxy.test.basic.datatructuresalgorithms.algorithms.basics;

/**
 * @author lxy
 * @date 2019-06-02
 */
public class Heap {
    private int[] a; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
//            swap(a, i, i/2); // swap() 函数作用：交换下标为 i 和 i/2 的两个元素
            i = i / 2;
        }
    }

    public int removeMax() {
        if (count == 0) return -1; // 堆中没有数据
        a[1] = a[count];    // 移除大堆堆顶元素，和数组最后一个元素先交换，在做堆话
        --count;
        heapify(a, count, 1);
        return 1;
    }

    private void heapify(int[] a, int n, int i) { // 自上往下堆化
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;    // 左节点
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;// 右节点
            if (maxPos == i) break;
//            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    // n 表示数据的个数，数组 a 中的数据从下标 1 到 n 的位置。
    public  void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
//            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }


}
