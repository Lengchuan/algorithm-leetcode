package com.org.lengchuan.algorithm.leetcode.p392;
//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
//
// 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"ae
//c"不是）。
//
// 示例 1:
//s = "abc", t = "ahbgdc"
//
// 返回 true.
//
// 示例 2:
//s = "axc", t = "ahbgdc"
//
// 返回 false.
//
// 后续挑战 :
//
// 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
//？
//
// 致谢:
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
// Related Topics 贪心算法 二分查找 动态规划

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/5/26
 * @desc
 */
public class Solution {
    public boolean isSubsequence(String s, String t) {
        // 前后同时进行比较
        int i = t.length() - 1; //长字符串右边
        int j = s.length() - 1; //短字符串右边
        int m = 0; //长字符串左边
        int n = 0; //短字符串左边

        while (i >= m && j >= n) {
            // 左边
            if (t.charAt(m) == s.charAt(n)) {
                m++;
                n++;
            } else {
                m++;
            }

            // 右边
            if (t.charAt(i) == s.charAt(j)) {
                i--;
                j--;
            } else {
                i--;
            }
        }

        // 如果是子序列，则结束比较时短字符串j和n应该相遇，n = j+1(如果是奇数长度，左边多计算一次自增；如果是偶数，右边多计算一次自减)

        return n == j + 1;
    }

    public static void main(String[] args) {
        String t = "ahbgdc";
        String s = "abc";
        System.out.println(new Solution().isSubsequence(s, t));
    }
}
