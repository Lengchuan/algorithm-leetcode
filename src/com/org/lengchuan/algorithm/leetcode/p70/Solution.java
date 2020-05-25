package com.org.lengchuan.algorithm.leetcode.p70;
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/5/25
 * @desc
 */
public class Solution {

    public int climbStairs(int n) {
        // 到底i阶有以下两种走法
        // 1. 从i-1走一步
        // 2. 从i-2走两步
        // 动态规划方程: dp[i] = dp[i-1]+dp[i-2]
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(4));
    }
}
