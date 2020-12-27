package com.org.lengchuan.algorithm.leetcode.p5;
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1：
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
//
// 示例 2：
//
// 输入: "cbbd"
//输出: "bb"
//
// Related Topics 字符串 动态规划
// 👍 2987 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        // 先选择一个字符，然后向左右两边进行扩展，如果是回文子串，记录，同时更新最大子串
        // 1. 回文串是奇数
        // 2. 回文串是偶数

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = helper(s, i, i); // 奇数
            String s2 = helper(s, i, i + 1); // 偶数
            String s3 = s1.length() > s2.length() ? s1 : s2;
            res = s3.length() > res.length() ? s3 : res;
        }

        return res;
    }

    private String helper(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }

        // left+1 是因为多减了一次
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("aaaa"));
        System.out.println(new Solution().longestPalindrome("bb"));
        System.out.println(new Solution().longestPalindrome("babad"));
        System.out.println(new Solution().longestPalindrome("cbbd"));
        System.out.println(new Solution().longestPalindrome("tattarrattat"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
