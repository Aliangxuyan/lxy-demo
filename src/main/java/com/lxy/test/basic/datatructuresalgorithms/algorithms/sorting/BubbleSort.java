package com.lxy.test.basic.datatructuresalgorithms.algorithms.sorting;

import java.util.Arrays;

/**
 * @author lxy
 * @date 2019-05-22
 * 冒泡排序，a 表示数组，n 表示数组大小
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {2, 3, 4, 1, 6, 2, 4, 5};
        bubbleSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

    public static void bubbleSort(int[] a, int n) {
        // 提前退出冒泡循环的标志位
        boolean flag = false;
        if (n < 0) return;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) { // 交换
                    int tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                    flag = true; // 表示还有数据交换不退出
                }
            }
            if (!flag) break; // 没有数据交换，提前退出
        }
    }
}
