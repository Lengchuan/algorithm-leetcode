package com.org.lengchuan.algorithm.leetcode.p493;
//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
// 你需要返回给定数组中的重要翻转对的数量。
//
// 示例 1:
//
//
//输入: [1,3,2,3,1]
//输出: 2
//
//
// 示例 2:
//
//
//输入: [2,4,3,5,1]
//输出: 3
//
//
// 注意:
//
//
// 给定数组的长度不会超过50000。
// 输入数组中的所有数字都在32位整数的表示范围内。
//
// Related Topics 排序 树状数组 线段树 二分查找 分治算法
// 👍 226 👎 0

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/11/28
 * @desc
 */
public class Solution {

    public int reversePairs(int[] nums) {
        // 暴力解，时间复杂度O(n^2),会超时
        // 归并排序
        // 我们先把问题简单化，如果 i < j 且 nums[i] > nums[j]就满足条件
        // 在归并排序的过程中，数组的左右两部分A和B在merge过程中需要交换位置
        // 只要满足nums[i] > nums[j]就需要交换一次位置
        // 对于A[i,mid]和B[mid+1,j]这个区间来说，因为区间有序
        // 只要nums[i] > nums[j]，则A[i,mid]>B[j],所有满足条件的总共有
        // mid-i+1个
        // 同理，我们在merge前，先统计nums[i] > 2 * nums[j]的数量，在进行归并，就可以在归并排序的
        // 过程中统计出翻转对的个数
        // 对于归并排序我们需要分sort merge两步进行
        if (nums == null || nums.length < 2) {
            return 0;
        }

        return sort(0, nums.length - 1, nums);
    }

    private int sort(int low, int high, int[] nums) {
        if (low >= high) {
            return 0;
        }
        int mid = (low + high) / 2;
        // 1. 左半部分 sort
        int lRes = sort(low, mid, nums);
        // 2. 右半部分 sort
        int rRes = sort(mid + 1, high, nums);
        // 3. merge
        int mRes = merge(low, high, mid, nums);

        return lRes + rRes + mRes;
    }

    private int merge(int low, int high, int mid, int[] nums) {
        // 1.先计算翻转对
        int[] temp = new int[high - low + 1];
        int i = low;// 左边数组开始位置
        int j = mid + 1;// 右边数组开始位置
        int res = 0;
        while (i <= mid && j <= high) {
            if (nums[i] > (long) 2 * nums[j]) {
                res += mid - i + 1;
                j++;// 右边数组继续右移
            } else {
                i++; // 左边数组继续右移
            }
        }

        // merge
        i = low;
        j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                // 交换
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 如果左边有剩余,说明左边的数比较大，加到数组后面
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 拷贝回原数组
        System.arraycopy(temp, 0, nums, low, temp.length);

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{1, 3, 2, 3, 1}));
    }


}
