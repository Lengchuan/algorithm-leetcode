package com.org.lengchuan.algorithm.leetcode.p118;
//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
//
//
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。
//
// 示例:
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]
// Related Topics 数组
// 👍 417 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/6
 * @desc
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0) {
            return res;
        }
        // 1
        // 1 1
        // 1 2 1
        // 1 3 3 1
        // 1 4 6 4 1
        // 第行有i+1个数,我们记为j
        // 我们可以得出规律
        // 1. i!=j时, (i,j) = (i-1,j-1)+(i-1,j)
        // 2. i==j时, (i,j) = 1 // 最后一列
        // 3. j==0时, (i,0) = 1 // 第一列
        for (int i = 0; i < numRows; i++) {
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (i == j || j == 0) {
                    nums.add(1);
                } else {
                    List<Integer> last = res.get(i - 1);
                    nums.add(last.get(j - 1) + last.get(j));
                }
            }
            res.add(nums);
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new Solution().generate(1);
        for (List<Integer> nums : res) {
            System.out.println(Arrays.toString(nums.toArray()));
        }
    }
}
