package com.org.lengchuan.algorithm.leetcode.p84;
//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
//
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
//
//
//
//
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
//
//
//
// 示例:
//
// 输入: [2,1,5,6,2,3]
//输出: 10
// Related Topics 栈 数组

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/6/30
 * @desc
 */
public class Solution {
    public int largestRectangleArea(int[] heights) {
        // 固定高度，然后向两边扩展，直到遇到比当前高度小的柱形，其中的差值就是当前高度可以构成最大矩形的宽
        if (heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int right = i;
            int left = i;
            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) {
                left--;
            }

            while (right + 1 < heights.length && heights[right + 1] >= heights[i]) {
                right++;
            }

            max = Math.max(max, (right - left + 1) * heights[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(new Solution().largestRectangleArea(heights));
    }
}
