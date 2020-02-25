package com.lxy.test.basic.datatructuresalgorithms.algorithms.search;

import java.util.Arrays;

/**
 * 二分法查找及其变种查询（查重复元素第几次出现）
 *
 * @author lxy
 * @date 2019-05-27
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {0, 2, 2, 1, 6, 2, 4, 5};
        Arrays.sort(a);
        int i = bsearch(a, a.length, 2);
        int first = bsearchFirst1(a, a.length, 2);
        int last = bsearchLast(a, a.length, 2);
        System.out.println(first);
        System.out.println(last);
        System.out.println(bsearchLast2(a, a.length, 4));

    }

    private static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;

    }

    /**
     * 查询第一个大于等于给定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchFirst2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || a[mid - 1] < value) return mid;
                else
                    high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 升级版，可以二分查找含重复元素的第一次或者最后一次出现的情况
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchFirst(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && a[low] == value) return low;
        else return -1;
    }

    /**
     * 优化，更容易理解（查询某个元素是第一次出现）
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchFirst1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                // 确定value  元素是第一次出现
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 最后一次出现
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchLast(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                // 确定value  元素是最后一次出现
                if ((mid == 0) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查询最后一个小于等于指定值的元素
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearchLast2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == 0) || a[mid + 1] > value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

}
