package com.org.lengchuan.algorithm.leetcode.p221;
//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
//
// 示例:
//
// 输入:
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4
// Related Topics 动态规划

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/1
 * @desc
 */
public class Solution {

    public int maximalSquare(char[][] matrix) {
        // matrix[i][j]作为正方形的左上角，向右向下求最大边长
        // 需要验证中间空的部分是否也是1
        if (matrix.length == 0) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    int right = j;
                    int bottom = i;
                    int maxLength = 1;
                    while (right + 1 < matrix[i].length && matrix[i][right + 1] == '1'
                            && bottom + 1 < matrix.length && matrix[bottom + 1][j] == '1') {
                        boolean flag = true;
                        for (int m = j; m <= right + 1 && flag; m++) {
                            for (int n = i; n <= bottom + 1; n++) {
                                if (matrix[n][m] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        if (!flag) {
                            break;
                        }
                        maxLength++;
                        bottom++;
                        right++;
                    }

                    max = Math.max(max, maxLength * maxLength);
                }

            }
        }
        return max;
    }

    public static void main(String[] args) {
        //1 0 1 0 0
        //1 0 1 1 1
        //1 1 1 1 1
        //1 0 0 1 0
//        char[][] matrix = new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };

        char[][] matrix = new char[][]{{'1'}};
        System.out.println(new Solution().maximalSquare(matrix));
    }
}
