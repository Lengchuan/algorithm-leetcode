package com.org.lengchuan.algorithm.leetcode.p256;
//假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
//
//当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
//
//例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
//
//注意：
//
//所有花费均为正整数。
//
//示例：
//
//输入: [[17,2,17],[16,16,5],[14,3,19]]
//输出: 10
//解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
//     最少花费: 2 + 5 + 3 = 10。

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/2
 * @desc
 */
public class Solution {
    public int minCost(int[][] costs) {
        // 动态规划
        // 定义dp[i][k] 表示第i个房子,用第k个颜色，房子0~i花费的最小成本
        // 一共有3中颜色
        // 我们可以得到状态转移方程
        // dp[i][颜色A] = min(dp[i-1][颜色B],dp[i-1][颜色C]) + costs[i][颜色A]
        // dp[i][颜色B] = min(dp[i-1][颜色A],dp[i-1][颜色C]) + costs[i][颜色B]
        // dp[i][颜色C] = min(dp[i-1][颜色A],dp[i-1][颜色B]) + costs[i][颜色C]

        int n = costs.length;

        int[][] dp = new int[n][3];
        dp[0] = new int[]{costs[0][0], costs[0][1], costs[0][2]};
        for (int i = 1; i < costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        };

        System.out.println(new Solution().minCost(costs));
    }
}
