package com.org.lengchuan.algorithm.leetcode.p650;
//最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
//
//
// Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
// Paste (粘贴) : 你可以粘贴你上一次复制的字符。
//
//
// 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
//
// 示例 1:
//
//
//输入: 3
//输出: 3
//解释:
//最初, 我们只有一个字符 'A'。
//第 1 步, 我们使用 Copy All 操作。
//第 2 步, 我们使用 Paste 操作来获得 'AA'。
//第 3 步, 我们使用 Paste 操作来获得 'AAA'。
//
//
// 说明:
//
//
// n 的取值范围是 [1, 1000] 。
//
// Related Topics 动态规划
// 👍 233 👎 0

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/7
 * @desc
 */
class Solution {
    public int minSteps(int n) {
        if (n <= 1) {
            return 0;
        }
        // 1. n=1 -> 0
        // 2. n=2 -> C P -> 2 -> 1*2
        // 3. n=3 -> C P P -> 3-> 1*3
        // 4. n=4 -> C P C P -> 4-> 2*2
        // 5. n=5 -> C P P P P -> 5-> 1*5
        // 6. n=6 -> C P P C P -> 5-> 2*3
        // 7. n=7 -> C P P P P P P -> 7-> 1*7
        // 8. n=8 -> C P C P C P -> 6-> 2*2*2
        // ...
        // 把n 分解成 x*y*z*..的形式，其中x y z都是质数,所需要的最少步数为x+y+z+...
        // 只需要把n进行质因数分解
        int res = 0;
        int d = 2;
        while (n > 1) {
            while (n % d == 0) {
                res += d;
                n /= d;
            }
            d += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minSteps(8));
        System.out.println(new Solution().minSteps(7));
    }
}

