package com.org.lengchuan.algorithm.leetcode.p567;
//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
//
// 换句话说，第一个字符串的排列之一是第二个字符串的子串。
//
// 示例1:
//
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
//
//
//
//
// 示例2:
//
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
//
//
//
//
// 注意：
//
//
// 输入的字符串只包含小写字母
// 两个字符串的长度都在 [1, 10,000] 之间
//
// Related Topics 双指针 Sliding Window

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/5/28
 * @desc
 */
public class Solution {

    public boolean checkInclusion(String s1, String s2) {
        // 滑动窗口，窗口大小为l1，窗口移动距离为l2-l1
        // 从左到右，如果不匹配，则窗口右移一次
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0) {
            return true;
        }
        if (len2 < len1) {
            return false;
        }

        int[] s1arr = new int[26];
        int[] s2arr = new int[26];
        // 统计每个字符出现的次数
        for (int i = 0; i < len1; i++) {
            s1arr[s1.charAt(i) - 'a']++;
            s2arr[s2.charAt(i) - 'a']++;// 初始窗口大小
        }
        // s1arr 不变，s2arr每次移动窗口时，移动前的字符数减1
        for (int i = 0; i < len2 - len1; i++) {
            if (isMatch(s1arr, s2arr)) {
                return true;
            }
            // 窗口右移
            // 移动了窗口，左边字符数需要减1
            s2arr[s2.charAt(i) - 'a']--;
            // 移动了窗口，增加了一个字符，字符数需要加1
            s2arr[s2.charAt(i + len1) - 'a']++;
        }
        return isMatch(s1arr, s2arr);

    }

    private boolean isMatch(int[] a, int[] b) {
        for (int i = 0; i < 26; i++)
            if (a[i] != b[i]) return false;
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().checkInclusion("ab", "eidbaooo"));
//        System.out.println(new Solution().checkInclusion("abcb", "eidbabacbcaoo"));
//        System.out.println(new Solution().checkInclusion("hello", "ooolleoooleh"));
//        System.out.println(new Solution().checkInclusion("abc", "bbbca"));
//        System.out.println(new Solution().checkInclusion("pqzhi", "ghrqpihzybre"));
        System.out.println(new Solution().checkInclusion("adc", "dcda"));
    }
}
