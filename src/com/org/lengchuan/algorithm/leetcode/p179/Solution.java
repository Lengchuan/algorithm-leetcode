package com.org.lengchuan.algorithm.leetcode.p179;
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
//注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
// 
//
//示例 1：
//
//输入：nums = [10,2]
//输出："210"
//示例 2：
//
//输入：nums = [3,30,34,5,9]
//输出："9534330"
//示例 3：
//
//输入：nums = [1]
//输出："1"
//示例 4：
//
//输入：nums = [10]
//输出："10"
//

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/4/12
 * @desc
 */
public class Solution {
    public String largestNumber(int[] nums) {
        // 把所有数按排序,然后组合到一起
        List<String> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                long a = Long.valueOf(o1 + o2);
                long b = Long.valueOf(o2 + o1);
                if (a > b) {
                    return 1;
                }
                if (a < b) {
                    return -1;
                }

                return 0;
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }

        if (builder.indexOf("0") == 0) {
            return "0";
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
