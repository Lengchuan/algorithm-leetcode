package com.org.lengchuan.algorithm.leetcode.p861;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/7
 * @desc
 */
class Solution {
    public int matrixScore(int[][] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        // 1. 第一个数为1时肯定是最大的,所以我们需要把第一列都翻转成1
        // 2. 其后的每一列如果0的数量大于1，则进行翻转，保证1的数量最多
        // 例1翻转后的结果为
        // 1,1,1,1
        // 1,0,0,1
        // 1,1,1,1
        // 第一列的和为2^3+2^3+2^3 = m*2^n(m为行,n为列) = 24 --> k*2^(n-j-1)
        // 第二列的和为2^2+2^2*0+2^2= k*2^(n-j-1)(k为1的个数,j为所处的列) = 8
        // 第三列的和为2^1+2^1*0+2^1 =  k*2^(n-j-1)(k为1的个数,j为所处的列) =4
        // 第四列的和为2^0+2^0+2^0 =  k*2^(n-j-1)(k为1的个数,j为所处的列) =3
        // 和为39
        // k * 1 << (n-j-1)
        int sum = 0;
        int m = A.length;
        int n = A[0].length;

        // 首先对第一列进行翻转
        for (int i = 0; i < m; i++) {
            if (A[i][0] == 0) {
                // 行翻转
                for (int j = 0; j < n; j++) {
                    if (A[i][j] == 0) {
                        A[i][j] = 1;
                    } else {
                        A[i][j] = 0;
                    }
                }
            }
        }

        // 第一列的和
        sum += m * 1 << (n - 1);

        // 对后面的几列进行翻转
        for (int j = 1; j < n; j++) {
            int k = 0; //1 的数量
            for (int i = 0; i < m; i++) {
                if (A[i][j] == 1) {
                    k++;
                }
                if (i == m - 1) {
                    // 比较0跟1的数量
                    if (m - k > k) { // 列翻转
                        for (int x = 0; x < m; x++) {
                            if (A[x][j] == 0) {
                                A[x][j] = 1;
                            } else {
                                A[x][j] = 0;
                            }
                        }
                        k = m - k;
                    }

                    sum += k * 1 << (n - j - 1);
                }
            }

        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{
                {0, 0, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0}
        };
        System.out.println(new Solution().matrixScore(A));
    }
}