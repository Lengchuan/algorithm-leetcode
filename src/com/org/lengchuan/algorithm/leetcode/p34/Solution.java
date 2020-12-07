package com.org.lengchuan.algorithm.leetcode.p34;
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums 是一个非递减数组
// -109 <= target <= 109
//
// Related Topics 数组 二分查找
// 👍 758 👎 0

import java.util.Arrays;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/7
 * @desc
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 因为数组本身有序，所以相同数字都是相连的
        // 二分查找找到其中任意一个target，根据坐标进行左右进行扩展
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            return new int[]{-1, -1};
        }

        // 往左
        int l = index;
        while (l >= 0 && nums[l] == target) {
            l--;
        }

        // 往右
        int r = index;
        while (r < nums.length && nums[r] == target) {
            r++;
        }

        l = l < index ? l : index;
        r = r > index ? r : index;

        return new int[]{l + 1, r - 1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}

