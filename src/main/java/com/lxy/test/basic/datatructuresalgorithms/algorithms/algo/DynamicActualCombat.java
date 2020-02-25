package com.lxy.test.basic.datatructuresalgorithms.algorithms.algo;

/**
 * @author lxy
 * @date 2019-06-14
 * <p>
 * 动态规划实战
 * <p>
 * 如何实现搜索引擎中的拼写纠错功能？
 * <p>
 * 换种思路：如何量化两个字符串的相似度？
 * 1）莱文斯坦距离
 * 2）最长公共子长串
 */
public class DynamicActualCombat {

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private int minDist = Integer.MAX_VALUE; // 存储结果

    //**************************************二、最长公共字串**********************************************

    /**
     * 状态转移方程
     * 如果：a[i]==b[j]，那么：max_lcs(i, j) 就等于：
     * max(max_lcs(i-1,j-1)+1, max_lcs(i-1, j), max_lcs(i, j-1))；
     * <p>
     * 如果：a[i]!=b[j]，那么：max_lcs(i, j) 就等于：
     * max(max_lcs(i-1,j-1), max_lcs(i-1, j), max_lcs(i, j-1))；
     * <p>
     * 其中 max 表示求三数中的最大值。
     *
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        for (int j = 0; j < m; ++j) {// 初始化第 0 行：a[0..0] 与 b[0..j] 的 maxlcs
            if (a[0] == b[j]) maxlcs[0][j] = 1;
            else if (j != 0) maxlcs[0][j] = maxlcs[0][j - 1];
            else maxlcs[0][j] = 0;
        }
        for (int i = 0; i < n; ++i) {// 初始化第 0 列：a[0..i] 与 b[0..0] 的 maxlcs
            if (a[i] == b[0]) maxlcs[i][0] = 1;
            else if (i != 0) maxlcs[i][0] = maxlcs[i - 1][0];
            else maxlcs[i][0] = 0;
        }
        for (int i = 1; i < n; ++i) { // 填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) maxlcs[i][j] = max(
                        maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1] + 1);
                else maxlcs[i][j] = max(
                        maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1]);
            }
        }
        return maxlcs[n - 1][m - 1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) maxv = x;
        if (y > maxv) maxv = y;
        if (z > maxv) maxv = z;
        return maxv;
    }


    //********************************一、计算莱文斯坦距离***************************************************

    /**
     * 一、计算莱文斯坦距离：
     * 1、回溯算法
     *
     * @param i
     * @param j
     * @param edist
     */
    // 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += (n - i);
            if (j < m) edist += (m - j);
            if (edist < minDist) minDist = edist;
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i + 1, j + 1, edist);
        } else { // 两个字符不匹配
            lwstBT(i + 1, j, edist + 1); // 删除 a[i] 或者 b[j] 前添加一个字符
            lwstBT(i, j + 1, edist + 1); // 删除 b[j] 或者 a[i] 前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1); // 将 a[i] 和 b[j] 替换为相同字符
        }
    }

    /**
     * 计算莱文斯坦距离：
     * 2、动态转移的方式实现
     * <p>
     * 动态转移方程：
     * 如果：a[i]!=b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
     * <p>
     * 如果：a[i]==b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
     * <p>
     * 其中，min 表示求三数中的最小值。
     *
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第 0 行:a[0..0] 与 b[0..j] 的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第 0 列:a[0..i] 与 b[0..0] 的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                else minDist[i][j] = min(
                        minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
            }
        }
        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }


}
