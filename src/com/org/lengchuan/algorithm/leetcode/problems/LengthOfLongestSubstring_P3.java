package com.org.lengchuan.algorithm.leetcode.problems;
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2019-09-16
 * @desc //最长子串
 */
public class LengthOfLongestSubstring_P3 {

    /**
     * 暴力破解
     * 1. 遍历字符串，通过判断所有可能的组合，判断每种组合是否存在重复字符串，得到最大子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        //遍历所有的可能性
        int max = 0;
        for (int i = 0, l = s.length(); i < l; i++) {
            for (int j = i + 1; j <= l; j++) {
                if (isUnique(s, i, j)) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    /**
     * 判断是否存在相同的字符
     *
     * @param s
     * @param start
     * @param end
     * @return
     */
    private boolean isUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }

        return true;
    }

    /**
     * 2. 基于hash表
     * 遍历字符串，用hash表来存储记录字符a上一次出现的位置x，用y 来记录最近一次字符a出现的位置，
     * 则最大子串存在于i(当前位置)-y中间的这一段，记为max，每次出现重复字符后，更新max，直到遍历整个字符串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = 0;
        int y = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, l = s.length(); i < l; i++) {
            Integer x = map.get(s.charAt(i));
            if (x != null) {//存在重复的字符
                y = Math.max(y, x);
            }

            max = Math.max(max, i - y);
            map.put(s.charAt(i), i);
        }

        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring_P3 a = new LengthOfLongestSubstring_P3();
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "au";

        System.out.println("a1-->s1-->" + a.lengthOfLongestSubstring(s1));
        System.out.println("a1-->s2-->" + a.lengthOfLongestSubstring(s2));
        System.out.println("a1-->s3-->" + a.lengthOfLongestSubstring(s3));
        System.out.println("a1-->s4-->" + a.lengthOfLongestSubstring(s4));
    }
}
