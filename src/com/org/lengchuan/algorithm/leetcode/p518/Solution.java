package com.org.lengchuan.algorithm.leetcode.p518;
//给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
//
//
//
//
//
//
// 示例 1:
//
// 输入: amount = 5, coins = [1, 2, 5]
//输出: 4
//解释: 有四种方式可以凑成总金额:
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
//
// 示例 2:
//
// 输入: amount = 3, coins = [2]
//输出: 0
//解释: 只用面额2的硬币不能凑成总金额3。
//
//
// 示例 3:
//
// 输入: amount = 10, coins = [10]
//输出: 1
//
//
//
//
// 注意:
//
// 你可以假设：
//
//
// 0 <= amount (总金额) <= 5000
// 1 <= coin (硬币面额) <= 5000
// 硬币种类不超过 500 种
// 结果符合 32 位符号整数
//
// 👍 399 👎 0

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/4/26
 * @desc
 */
public class Solution {

    public int change(int amount, int[] coins) {
        // 动态规划
        // 完全背包问题 无顺序组合
        // 两层循环，外层nums，内层target target正序且target >= nums[i]
        // 动态规划方程 dp[i]+=dp[i-num]
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        return dp[amount];

    }

    public static void main(String[] args) {
        System.out.println(new Solution().change(5, new int[]{1, 2, 5}));
    }
}
