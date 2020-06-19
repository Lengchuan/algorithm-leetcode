package com.org.lengchuan.algorithm.leetcode.p69;
//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
// 示例 1:
//
// 输入: 4
//输出: 2
//
//
// 示例 2:
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
//
// Related Topics 数学 二分查找

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/6/19
 * @desc
 */
public class Solution {
    public int mySqrt(int x) {
        // N 的平方根应该小于等于N/2
        // 枚举所有的0-N/2的数，如果存在 (k-1)^2 < N < k^2,则N的平方根的整数部分为k-1
        int n = 0;
        while (n <= x / 2 + 1) {
            double sqr = Math.pow(n, 2);
            if (sqr == x) {
                return n;
            }
            if (Math.pow(n - 1, 2) < x && sqr > x) {
                return n - 1;
            }

            n++;

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().mySqrt(3));
    }
}
