package com.org.lengchuan.algorithm.leetcode.p640;

//求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
//
// 如果方程没有解，请返回“No solution”。
//
// 如果方程有无限解，则返回“Infinite solutions”。
//
// 如果方程中只有一个解，要保证返回值 x 是一个整数。
//
// 示例 1：
//
// 输入: "x+5-3+x=6+x-2"
//输出: "x=2"
//
//
// 示例 2:
//
// 输入: "x=x"
//输出: "Infinite solutions"
//
//
// 示例 3:
//
// 输入: "2x=x"
//输出: "x=0"
//
//
// 示例 4:
//
// 输入: "2x+3x-6x=x+2"
//输出: "x=-1"
//
//
// 示例 5:
//
// 输入: "x=x+2"
//输出: "No solution"
//
// Related Topics 数学

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/5/27
 * @desc
 */
public class Solution {
    public String solveEquation(String equation) {
        // 1. 计算x的所有系数之和a
        // 2. 计算所有的常量之和b
        // 3. 计算x的值 （1）当a=b=0 时有无穷解 （2） 当a=0，b!=0时无解 (3)x = b/a

        char[] left = equation.split("=")[0].toCharArray();
        char[] right = equation.split("=")[1].toCharArray();
        // 计算左边a b的值
        int[] leftArr = getValue(left);
        int al = leftArr[0];
        int bl = leftArr[1];

        int[] rightArr = getValue(right);
        int ar = rightArr[0];
        int br = rightArr[1];

        // 计算x的值
        int a = al - ar;
        int b = br - bl;
        System.out.println("al=" + al + " bl=" + bl + " ar=" + ar + " br=" + br);

        if (a == 0 && b == 0) {
            return "Infinite solutions";
        }

        if (a == 0 && b != 0) {
            return "No solution";
        }

        return "x=" + b / a;
    }

    public static int[] getValue(char[] ch) {
        //如果在遍历到x之前先遇到了+或者-，说明是常数
        StringBuilder tmpB = new StringBuilder();
        int a = 0;
        int b = 0;
        boolean isX = false;
        for (char c : ch) {
            if (c == 'x') {
                //前面的系数需要相加
                int atmp = 1;
                if (tmpB.toString().equals("+")) {
                    atmp = 1;
                } else if (tmpB.toString().equals("-")) {
                    atmp = -1;
                } else if (tmpB.length() > 0) {
                    atmp = Integer.parseInt(tmpB.toString());
                }
                a += atmp;
                tmpB = new StringBuilder();
                isX = true;
            } else if (c == '+' || c == '-') {
                //说明前面的是常数
                int btmp = tmpB.length() == 0 ? 0 : Integer.parseInt(tmpB.toString());
                b += btmp;
                tmpB = new StringBuilder();
                tmpB.append(c);
            } else {
                //继续遍历
                tmpB.append(c);
                isX = false;
            }
        }

        // 最后一个值没有进行计算，需要再计算
        if (tmpB.length() > 0) {
            int value = Integer.valueOf(tmpB.toString());
            if (isX) {
                a += value;
            } else {
                b += value;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().solveEquation("x+5-3+x=6+x-2"));
//        System.out.println(new Solution().solveEquation("2x=x"));
//        System.out.println(new Solution().solveEquation("2x=2x"));
//        System.out.println(new Solution().solveEquation("x=x+2"));
        System.out.println(new Solution().solveEquation("2x+3x-6x=x+2"));
    }
}
