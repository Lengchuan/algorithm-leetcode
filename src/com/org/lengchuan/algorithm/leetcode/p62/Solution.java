package com.org.lengchuan.algorithm.leetcode.p62;
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
//机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
//问总共有多少条不同的路径？
//

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/3
 * @desc
 */
public class Solution {

    public int uniquePaths(int m, int n) {
        // 动态规划
        // dp[i][j] 表示 走到(i,j)的路径总量
        // 走到(i,j) 我们可以从左边走一步(i-1,j) 或者从上边往下走一步(i,j-1)
        // 可以得到转态转移方程 dp[i][j] = dp[i-1][j] + dp[i][j-1]
        int[][] dp = new int[m][n];
        // 处理边界
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(3, 7));
    }
}
