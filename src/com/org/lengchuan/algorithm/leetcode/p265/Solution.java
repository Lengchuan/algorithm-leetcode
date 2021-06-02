package com.org.lengchuan.algorithm.leetcode.p265;
//假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
//
//当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
//
//例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
//
//注意：
//
//所有花费均为正整数。
//
//示例：
//
//输入: [[1,5,3],[2,9,4]]
//输出: 5
//解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
//     或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
//

import java.util.Arrays;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/2
 * @desc
 */
public class Solution {

    public int minCostII(int[][] costs) {
        // 动态规划,与256类似，区别在于颜色有K种，我们只需枚举K中颜色对应的转态转移方程即可
        // 定义dp[i][k] 表示第i个房子,用第k个颜色，房子0~i花费的最小成本
        // 一共有k中颜色
        // 我们可以得到状态转移方程
        // dp[i][颜色A] = min(dp[i-1][颜色B],dp[i-1][颜色C]) + costs[i][颜色A]

        int n = costs.length;
        int k = costs[0].length;

        int[][] dp = new int[n][k];
        dp[0] = costs[0];
        for (int i = 1; i < costs.length; i++) { // 第i个房子
            for (int j = 0; j < k; j++) { // 第 i个房子的颜色
                int min = Integer.MAX_VALUE;
                for (int kk = 0; kk < k; kk++) { // 第i-1个房子可选的颜色
                    if (j != kk) { // 第i-1个房子不能和第i个颜色一样
                        min = Math.min(dp[i - 1][kk], min);
                    }
                }

                dp[i][j] = min + costs[i][j];
            }
        }

        Arrays.sort(dp[n - 1]);
        return dp[n - 1][0];
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        };

        System.out.println(new Solution().minCostII(costs));
    }
}
