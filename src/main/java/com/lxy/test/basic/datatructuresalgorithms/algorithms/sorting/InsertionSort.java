package com.lxy.test.basic.datatructuresalgorithms.algorithms.sorting;

import java.util.Arrays;

/**
 * @author lxy
 * @date 2019-05-22
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {2, 3, 4, 1, 6, 2, 4, 5};
        int n = a.length;
//        insertionSort(a, n);
        System.out.println(Arrays.toString(a));

        for (int i = 1; i < n; i++) {
            int val = a[i];
            int j = i - 1;
            for (; j >=0; j--) {
                // 如果j > i 下标值
                if (a[j] > val) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = val;
        }
        System.out.println(Arrays.toString(a));
    }

    private static void insertionSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) { // 移动数据
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }
}
