package com.org.lengchuan.algorithm.leetcode.p74;
//编写一个高效的算法来判断m x n的矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
//每行中的整数从左到右按升序排列。
//每行的第一个整数大于前一行的最后一个整数。
//

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/3/30
 * @desc
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 把数组的行拼接在一起就可以得到一个升序的数组
        // 直接对新数组进行二分查找就可以得到需要的结果
        // 把新数组的下标映射到二维数组上
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
