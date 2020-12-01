package com.org.lengchuan.algorithm.leetcode.p322;
//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
// 示例 4：
//
//
//输入：coins = [1], amount = 1
//输出：1
//
//
// 示例 5：
//
//
//输入：coins = [1], amount = 2
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 104
//
// Related Topics 动态规划

import java.util.Arrays;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/1
 * @desc
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        // 动态规划
        // 转态:amount
        // 状态变化，amount 增加
        // 最优子问题: 每次都使用最少的硬币凑出子问题的解
        if (null == coins || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];// dp[n]保存的是凑出n需要的最少零钱数,因为是从0开始，需要+1
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    // 可以凑出来,刚好够或者还不够
                    // dp[i] 是已经求出的可以凑出当前amount的最少次数
                    // dp[i - coin] + 1 为什么需要+1,因为dp[i - coin]需要在加上本次的coin才达到amount甚至比amount还小
                    //
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        //dp[amount] == 0 金额为amount无解
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange(new int[]{1, 2, 5}, 11));
    }

}
