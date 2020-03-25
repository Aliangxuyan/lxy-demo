package com.lxy.test.basic.datatructuresalgorithms.algorithms.sorting;

import java.util.Arrays;

/**
 * @author lxy
 * @date 2020-03-22
 */
public class SortTest {
    public static void main(String[] args) {
        int[] a = {2, 3, 4, 1, 6, 2, 4, 5};
        //        selectSort(a, a.length );
        //        inertSort(a, a.length);
        bubbleSort(a, a.length);
        System.out.println(Arrays.toString(a));

    }

    private static void bubbleSort(int[] a, int n) {
        // 提前退出冒泡循环的标志位
        boolean flag = false;
        if (n < 0) return;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }


    private static void inertSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    // 前面假定都是排好序的元素，判断最后一个如果比新元素小前面元素不用对比
                    break;
                }
            }

            a[j + 1] = value;
        }
    }

    // 分成已排序和未排序，将即将排序的元素和为排序中的最小值进行比较，如果还要小则交换
    private static void selectSort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                // 拿到最小值
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (a[i] != a[min]) {
                int tmp = a[min];
                a[min] = a[i];
                a[i] = tmp;
            }
        }

    }
}
