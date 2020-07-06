package com.org.lengchuan.algorithm.leetcode.p316;
//给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
//
//
// 示例 1:
//
// 输入: "bcabc"
//输出: "abc"
//
//
// 示例 2:
//
// 输入: "cbacdcbc"
//输出: "acdb"
//
//
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同
// Related Topics 栈 贪心算法

import java.util.Stack;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/1
 * @desc
 */
public class Solution {

    public String removeDuplicateLetters(String s) {
        // 只包含26个小写字母
        // 维护一个int[26]的字典表，记录每个字符出现的总次数
        // 维护一个栈，如果栈顶元素比当前字符大且字符次数大于1，则将其出栈，需要循环出栈，直到
        // 遇到栈顶字符次数为1，将当前字符入栈
        // 遍历完成，将栈中的字符转换成成字符串
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.contains(c)) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && nums[stack.peek() - 'a'] > i) {
                stack.pop();
            }

            stack.push(c);
        }


        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.reverse().toString();
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().removeDuplicateLetters("bcabc"));
        System.out.println(new Solution().removeDuplicateLetters("cbacdcbc"));
    }
}
