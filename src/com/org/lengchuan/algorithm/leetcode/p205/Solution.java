package com.org.lengchuan.algorithm.leetcode.p205;
//给定两个字符串 s 和 t，判断它们是否是同构的。
//
// 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
//
// 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
//
// 示例 1:
//
// 输入: s = "egg", t = "add"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "foo", t = "bar"
//输出: false
//
// 示例 3:
//
// 输入: s = "paper", t = "title"
//输出: true
//
// 说明:
//你可以假设 s 和 t 具有相同的长度。
// Related Topics 哈希表

import java.util.HashMap;
import java.util.Map;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/27
 * @desc
 */
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        // 假设s 和 t是同构的
        // 做一个映射关系s[i]—>g[i] g[i]->s[i]
        // 如果说全是单个字符，那肯定是同构；
        // 如果存在重复字符,只要s[i]—>g[i] g[i]->s[i]的映射关系不成立，就可以判断非同构
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if ((sMap.containsKey(c1) && sMap.get(c1) != c2)
                    || (tMap.containsKey(c2) && tMap.get(c2) != c1)) {
                return false;
            }
            sMap.put(c1, c2);
            tMap.put(c2, c1);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isIsomorphic("add", "egg"));
        System.out.println(new Solution().isIsomorphic("add", "frf"));
    }
}
