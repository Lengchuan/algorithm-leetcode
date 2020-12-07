package com.org.lengchuan.algorithm.leetcode.p659;
//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 :
//1, 2, 3
//3, 4, 5
//
//
// 示例 2：
//
//
//输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 :
//1, 2, 3, 4, 5
//3, 4, 5
//
//
// 示例 3：
//
//
//输入: [1,2,3,4,4,5]
//输出: False
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10000
//
// Related Topics 堆 贪心算法
// 👍 250 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/7
 * @desc
 */
class Solution {
    public boolean isPossible(int[] nums) {
        // 1. 对于一个数x,如果当前存在以x-1结尾的子序列，则把x继续加到子序列中，保证子序列最大
        // 2. 如果不存在x-1结尾的子序列，则需要找x x+1 x+2构成的最短子序列
        // 3. 如果不存在这两种情况，说明无法分割
        if (nums == null || nums.length < 3) {
            return false;
        }
        Map<Integer, Integer> countMap = new HashMap<>(); // 记录所有数字的总数
        Map<Integer, Integer> endMap = new HashMap<>();// 记录以x结尾的序列总数

        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }

        for (int x : nums) {
            // x 数量是否为0
            int count = countMap.getOrDefault(x, 0);
            if (count == 0) {
                continue;
            }

            // 是否存在以x-1结尾的序列
            if (endMap.getOrDefault(x - 1, 0) > 0) {
                endMap.put(x - 1, endMap.get(x - 1) - 1); // x-1结尾的数量要减1
                endMap.put(x, endMap.getOrDefault(x, 0) + 1); // x结尾的数量要加1
                countMap.put(x, countMap.get(x) - 1); // x的数量减1
                continue;
            }

            //不存在以x-1结尾的，只能找以x x+1 x+2组成的最短序列
            int count1 = countMap.getOrDefault(x + 1, 0);
            int count2 = countMap.getOrDefault(x + 2, 0);
            if (count1 > 0 && count2 > 0) {
                countMap.put(x, countMap.get(x) - 1);
                countMap.put(x + 1, count1 - 1);
                countMap.put(x + 2, count2 - 1);
                endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                continue;
            }

            // 不满足前面两种
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPossible(new int[]{1, 2, 3, 3, 4, 5}));
    }
}
