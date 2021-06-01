package com.org.lengchuan.algorithm.leetcode.p416;
//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。
//
// 示例 2：
//
//
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
//

/**
 * @author
 * @since 2021/6/1
 */
public class Solution {
    public boolean canPartition(int[] nums) {
        // 背包问题
        // 0 1 背包存在性问题
        // target = sum/2
        // 外循环nums,内循环target,target倒序且target>=nums[i];
        // 动态规划方程 dp[i] || dp[i-num]
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canPartition(new int[] {1, 5, 11, 5}));
        System.out.println(new Solution().canPartition(new int[] {1, 2, 3, 5}));
    }
}
