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

        String res = "";
        char[] arr = s.toCharArray();
        // 1. 中心向两边扩展
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(arr[i]);
            int l = i - 1;
            int r = i + 1;
            while (true) {
                if (l >= 0 && r < arr.length && arr[l] == arr[r]) { // 两边扩展
                    sb.insert(0, arr[l]);
                    sb.append(arr[r]);
                    l--;
                    r++;
                    continue;
                }
                // 1. 尝试向左边扩展
                if (l >= 0 && isPalindrome(String.valueOf(arr[l]), sb.toString())) {
                    sb.insert(0, arr[l]);
                    l--;
                    continue;
                }

                // 2. 尝试向右边扩展
                if (r < arr.length && isPalindrome(sb.toString(), String.valueOf(arr[r]))) {
                    sb.append(arr[r]);
                    r++;
                    continue;
                }
                break;
            }
            res = res.length() < sb.length() ? sb.toString() : res;
        }

        return res;
    }

    private boolean isPalindrome(String s1, String s2) {
        String s = s1 + s2;
        if (s.length() <= 1) {
            return true;
        }
        int low = 0;
        int high = s.length() - 1;
        while (low <= high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("aaaa"));
        System.out.println(new Solution().longestPalindrome("bb"));
//        System.out.println(new Solution().longestPalindrome("babad"));
//        System.out.println(new Solution().longestPalindrome("cbbd"));
//        System.out.println(new Solution().longestPalindrome("tattarrattat"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
