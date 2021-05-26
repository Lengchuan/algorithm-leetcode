package com.org.lengchuan.algorithm.leetcode.p714;
//给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
//
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
//
// 返回获得利润的最大值。
//
// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
//
// 示例 1:
//
// 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出: 8
//解释: 能够达到的最大利润:
//在此处买入 prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
//
// 注意:
//
//
// 0 < prices.length <= 50000.
// 0 < prices[i] < 50000.
// 0 <= fee < 50000.
//
// Related Topics 贪心算法 数组 动态规划
// 👍 483 👎 0

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/5/27
 * @desc
 */
public class Solution {

    public int maxProfit(int[] prices, int fee) {
        // 贪心
        // 因为不限交易次数，所以我们只要每次可以赚到钱就卖出获取利润
        // 因为需要手续费，一次交易也就是买入+卖出才收一次手续费，我们把手续费计入买入的成本中
        int maxProfit = 0;
        int minPrice = prices[0] + fee;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] + fee < minPrice) { // 如果比之前买入的价格还少，就买入
                minPrice = prices[i] + fee;
            } else if (prices[i] > minPrice) { // 可以赚到钱就直接卖出
                // 卖出
                maxProfit += prices[i] - minPrice;
                minPrice = prices[i];
            }

        }

        return maxProfit;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

}
