package com.org.lengchuan.algorithm.leetcode.p842;
//给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
//
// 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
//
//
// 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
// F.length >= 3；
// 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
//
//
// 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
//
// 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
//
//
//
// 示例 1：
//
// 输入："123456579"
//输出：[123,456,579]
//
//
// 示例 2：
//
// 输入: "11235813"
//输出: [1,1,2,3,5,8,13]
//
//
// 示例 3：
//
// 输入: "112358130"
//输出: []
//解释: 这项任务无法完成。
//
//
// 示例 4：
//
// 输入："0123"
//输出：[]
//解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
//
//
// 示例 5：
//
// 输入: "1101111"
//输出: [110, 1, 111]
//解释: 输出 [11,0,11,11] 也同样被接受。
//
//
//
//
// 提示：
//
//
// 1 <= S.length <= 200
// 字符串 S 中只含有数字。
//
// Related Topics 贪心算法 字符串 回溯算法
// 👍 173 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/12/8
 * @desc
 */
class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return res;
        }
        // 使用回溯算法
        backtrack(S.toCharArray(), res, 0);
        return res;
    }

    private boolean backtrack(char[] arr, List<Integer> res, int index) {
        if (arr.length == index && res.size() >= 3) {
            // 说明最少是找到了一组
            return true;
        }
        for (int i = index; i < arr.length; i++) {
            if (arr[index] == '0' && i > index) {
                // 以0开头的数，不符合要求，继续往下走回溯或者直接退出
                break;
            }

            int size = res.size();
            long num = parse(arr, index, i);

            if (num > Integer.MAX_VALUE) {
                break;
            }

            // 如果已经有2个数以上，并且num > res(size-1) + res(size-2),
            // break， 因为后面如果再截取的话，数字只会更大
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) {
                break;
            }

            // 如果长度小于等于1或者num = res(size-1) + res(size-2)
            // 把当前的num加入到结果中，继续往下走
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) num);
                if (backtrack(arr, res, i + 1)) {
                    return true;
                } else {
                    // 没有找到，需要删除最后放入
                    res.remove(res.size() - 1);
                }
            }
        }
        return false;
    }

    private long parse(char[] arr, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            builder.append(arr[i]);
        }

        return Long.parseLong(builder.toString());
    }

    public static void main(String[] args) {
        List<Integer> res = new Solution().splitIntoFibonacci("123456579");
        System.out.println(Arrays.toString(res.toArray()));
    }
}
