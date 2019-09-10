package com.org.lengchuan.algorithm.leetcode.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2019-09-10
 * @desc 两数之和
 */
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
// Related Topics 数组 哈希表

//一、暴力解法，遍历两次数组，找到符合条件的数据,时间复杂度O(n^2),空间复杂度O(1)
//二、两次hash表，第一次遍历把索引数据存入hash表，第二次遍历与hash表数据对比,找到符合条件的数据，
//    时间复杂度O(n)，空间复杂度O(n)-->取决于hash表大小
//三、一次hash表，基于(二),在插入hash表的同时进行比较，找到符合条件的数据,时间复杂度O(n)，空间复杂度O(n)
public class A1 {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0, l = nums.length; i < l; i++) {
            int result = target - nums[i];
            if (numsMap.containsKey(result)) {
                return new int[]{numsMap.get(result), i};
            }
            numsMap.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        A1 a1 = new A1();
        System.out.println(Arrays.toString(a1.twoSum(nums, 9)));
    }
}
