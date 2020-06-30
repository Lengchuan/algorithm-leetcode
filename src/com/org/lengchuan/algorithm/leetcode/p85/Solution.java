package com.org.lengchuan.algorithm.leetcode.p85;
//给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
//
// 示例:
//
// 输入:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//输出: 6
// Related Topics 栈 数组 哈希表 动态规划

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/6/19
 * @desc
 */
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        // M X N 的矩阵
        // 参考84题，计算所有matrix[i][j]的高度，映射成柱形的高度
        if (matrix.length == 0) {
            return 0;
        }

        int max = 0;
        List<int[]> heigths = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] rows = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                int bottom = i;
                int height = 0;
                if (matrix[i][j] == '1') {
                    height = 1;
                    while (bottom - 1 >= 0 && matrix[bottom - 1][j] == '1') {
                        height++;
                        bottom--;
                    }
                }

                rows[j] = height;
            }

            heigths.add(rows);
        }

        for (int[] rows : heigths) {
            max = Math.max(max, largestRectangleArea(rows));
        }

        return max;
    }

    private int largestRectangleArea(int[] heights) {
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
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(new Solution().maximalRectangle(matrix));
    }
}
