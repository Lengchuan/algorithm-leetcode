package com.org.lengchuan.algorithm.leetcode.p204;
//统计所有小于非负整数 n 的质数的数量。
//
//
//
// 示例 1：
//
// 输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
//
//
// 示例 2：
//
// 输入：n = 0
//输出：0
//
//
// 示例 3：
//
// 输入：n = 1
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= n <= 5 * 106
//
// Related Topics 哈希表 数学
// 👍 590 👎 0

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/7
 * @desc
 */
class Solution {
    public int countPrimes(int n) {
        // 质数，只能被自己合1整除的数
        if (n <= 2) {
            return 0;
        }

        int total = 1;

        for (int i = 3; i < n; i++) {
            double sqrt = Math.sqrt(i);
            boolean flag = true;
            for (int j = 2; j <= sqrt; j++) {
                if (i % j == 0 && sqrt != 1) { // 除了1和自身，还有其它的。不是质数
                    flag = false;
                    break;
                }
            }
            if (flag) {
                total++;
            }
        }

        return total;
    }
}

