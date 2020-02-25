package com.lxy.test.basic.datatructuresalgorithms.algorithms.algo;

/**
 * @author lxy
 * @date 2019-06-10
 */
public class DynamicProgramming {
    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    static int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    static int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    static int n = 5; // 物品个数
    static int w = 9; // 背包承受的最大重量

    public static void main(String[] args) {
//        zero_one_packet(0, 0);
//        knapsack2(weight, n, w);
        double11advance(weight, n, w);
    }


    /**
     * 回溯 方法解决问题
     * 0-1 背包问题
     *
     * @param i
     * @param cw
     */
    public static void zero_one_packet(int i, int cw) { // 调用 zero_one_packet(0, 0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        zero_one_packet(i + 1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            zero_one_packet(i + 1, cw + weight[i]); // 选择装第 i 个物品
        }
    }

    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值 false

    /**
     * 优化之后的，将上面形成的递归树重复的信息放到备忘录中
     * 回溯 方法解决问题
     *
     * @param i
     * @param cw
     */
    public void zero_one_packet2(int i, int cw) { // 调用 zero_one_packet2(0, 0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录 (i, cw) 这个状态
        zero_one_packet2(i + 1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            zero_one_packet2(i + 1, cw + weight[i]); // 选择装第 i 个物品
        }
    }

    /**
     * 动态规划解决问题：开销需要 n * (w+1) 的数组，属于空间换时间
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    //weight: 物品重量，n: 物品个数，w: 背包可承载重量
    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值 false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第 i 个物品放入背包
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {// 把第 i 个物品放入背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] == true) {
                System.out.println(states[n - 1][i] + "&&&&&&" + (n - 1) + "&&&&&&&&" + i);
                return i;
            }
        }
        return 0;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值 false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {// 把第 i 个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) {
                System.out.println(String.format("******* %s", i));
                return i;
            }
        }
        return 0;
    }


    /**
     * 尽管动态规划的执行效率比较高，但是就刚刚的代码实现来说，我们需要额外申请一个 n 乘以 w+1 的二维数组，对空间的消耗比较多。所以，有时候，我们会说，动态规划是一种空间换时间的解决思路。你可能要问了，有什么办法可以降低空间消耗吗？
     * 实际上，我们只需要一个大小为 w+1 的一维数组就可以解决这个问题。动态规划状态转移的过程，都可以基于这个一维数组来操作。具体的代码实现我贴在这里，你可以仔细看下。
     *
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) { // 初始化 states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { // 动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第 i 个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第 i 个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }

    // items 商品价格，n 商品个数, w 表示满减条件，比如 200

    /**
     * 数量，重量，价格限制
     *
     * @param items
     * @param n
     * @param w
     */
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3 * w + 1];// 超过 3 倍就没有薅羊毛的价值了
        states[0][0] = true;  // 第一行的数据要特殊处理
        if (items[0] <= 3 * w) {
            states[0][items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3 * w; ++j) {// 不购买第 i 个商品
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= 3 * w - items[i]; ++j) {// 购买第 i 个商品
                if (states[i - 1][j] == true) states[i][j + items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 3 * w + 1; ++j) {
            if (states[n - 1][j] == true) break; // 输出结果大于等于 w 的最小值
        }
        if (j == 3 * w + 1) return; // 没有可行解
        for (int i = n - 1; i >= 1; --i) { // i 表示二维数组中的行，j 表示列
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j 不变。
        }
        if (j != 0) System.out.print(items[0]);
    }


}
