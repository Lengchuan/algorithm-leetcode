package com.org.lengchuan.algorithm.leetcode.p528;
//给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i
//] 成正比。
//
//
//
//
// 例如，给定一个值 [1，9] 的输入列表，当我们从中选择一个数字时，很有可能 10 次中有 9 次应该选择数字 9 作为答案。
//
//
//
// 示例 1：
//
// 输入：
//["Solution","pickIndex"]
//[[[1]],[]]
//输出：[null,0]
//
//
// 示例 2：
//
// 输入：
//["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
//[[[1,3]],[],[],[],[],[]]
//输出：[null,0,1,1,1,0]
//
//
//
// 输入语法说明：
//
// 输入是两个列表：调用成员函数名和调用的参数。Solution 的构造函数有一个参数，即数组 w。pickIndex 没有参数。输入参数是一个列表，即使参数
//为空，也会输入一个 [] 空列表。
//
//
//
// 提示：
//
//
// 1 <= w.length <= 10000
// 1 <= w[i] <= 10^5
// pickIndex 将被调用不超过 10000 次
//
// Related Topics 二分查找 Random

import java.util.Random;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/28
 * @desc
 */
public class Solution {
    // w = [1,3] 代表w[0]的权重为1 w[1]的权重为3
    // 则w[0]的概率应该为1/(1+3) w[1]的概率应该为3/(1+3)
    //
    //   0------1---------------------------------4
    //
    // 如图，如果我们从w[0]+w[1] = 4 中随机一个数字，则落在0-1之间的概率为0.25，落在1-4之间的概率为0.75
    // 用一个数组sum[] 来保存各个区间，sum = {1,4}
    // 我们只需要找到随机数落在哪个区间即可，返回其索引
    // 比如x = 1 时，我们找到第一个x<sum[i]的位置，也就是sum[0]，返回索引0
    // 比如x = 3 时，我们找到第一个x<sum[i]的位置，也就是sum[1]，返回索引1

    int[] sum;

    int S;

    Random random = new Random();

    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
        S = sum[w.length - 1];
    }

    public int pickIndex() {
        int r = random.nextInt(S);
        int left = 0;
        int right = 0;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (mid == 0 || r >= sum[mid - 1]) {
                return mid;
            }
        }

        return -1;
    }
}
