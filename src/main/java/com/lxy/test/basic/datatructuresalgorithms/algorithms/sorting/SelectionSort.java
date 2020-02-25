package com.lxy.test.basic.datatructuresalgorithms.algorithms.sorting;

import java.util.Arrays;

/**
 * @author lxy
 * @date 2019-05-22
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] a = {2, 3, 4, 1, 6, 2, 4, 5};
        selectionSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

    private static void selectionSort(int[] a, int n) {
        if (n < 1) return;
        for (int i = 0; i < n; i++) {
            int j = i;
            int min = i;
            for (; j < n; j++) { // 查找剩余无需数组中最小的下标
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            if (a[min] != a[i]) { // a[i] 下标和 最小下标交换
                int tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;
            }
        }

    }
}
