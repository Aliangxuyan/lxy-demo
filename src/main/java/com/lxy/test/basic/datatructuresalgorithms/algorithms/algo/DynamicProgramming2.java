package com.lxy.test.basic.datatructuresalgorithms.algorithms.algo;

/**
 * @author lxy
 * @date 2019-06-14
 * <p>
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存矩阵存储的都是正整数。棋子起始位置在左上角，终止位置在右下角
 * 求最短路径
 */
public class DynamicProgramming2 {
    private static int[][] array = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

    public static void main(String[] args) {

        int result = minDistDP(array, 4);
        System.out.println(result);

    }

    /**
     * 1、回溯算法解决（递归树，存在重复元素）
     */
    private int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量

    // 调用方式：minDistBacktracing(0, 0, 0, w, n);
    // i：行，j:列  dist: 走到每行的路径和  w 二维数组，n 维度
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达了 n-1, n-1 这个位置了，这里看着有点奇怪哈，你自己举个例子看下
        if (i == n && j == n) {
            // 对走到头的每一种情况判断总的路径长度，选最短路径
            if (dist < minDist) minDist = dist;
            return;
        }
        // 构建递归树，走到每一种情况
        if (i < n) { // 往下走，更新 i=i+1, j=j
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) { // 往右走，更新 i=i, j=j+1
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }

    /**
     * 2、动态规划算法解决（状态转移表法）
     * <p>
     * 状态转移方程：min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     *
     * @param matrix
     * @param n
     * @return
     */
    public static int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化 states 的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化 states 的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }


    /**
     * 递归加"备忘录"的方式
     * <p>
     * <p>
     * min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     */
    private int[][] mem = new int[4][4];

    public int minDist(int i, int j) { // 调用 minDist(n-1, n-1);
        if (i == 0 && j == 0) return array[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;

        // 像上递归，
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = array[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }


}
