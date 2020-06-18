package com.org.lengchuan.algorithm.leetcode.p88;
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
//
//
// 说明:
//
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//
//
//
//
// 示例:
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6]
// Related Topics 数组 双指针

import java.util.Arrays;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/6/14
 * @desc
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int x = n + m - 1;
        // 合并后数组的总长度为m+n
        // 从后面的数开始比较，填充到nums1，可以不对数组进行复制
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[x] = nums1[i];
                i--;
            } else {
                nums1[x] = nums2[j];
                j--;
            }
            x--;
        }

        // 当m > n时，可能会出现i = 0 但是j>0的情况，此时还有一部分数据为复制到nums1
        System.arraycopy(nums2, 0, nums1, 0, j + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        new Solution().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
