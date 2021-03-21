package com.org.lengchuan.algorithm.leetcode.p150;

// 根据 逆波兰表示法，求表达式的值。
//
// 有效的算符包括 +、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
//
//
// 说明：
//
// 整数除法只保留整数部分。
// 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
//  
//
// 示例 1：
//
// 输入：tokens = ["2","1","+","3","*"]
// 输出：9
// 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
// 示例 2：
//
// 输入：tokens = ["4","13","5","/","+"]
// 输出：6
// 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6


import java.util.Stack;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/3/21
 * @desc
 */
public class Solution {

    public int evalRPN(String[] tokens) {
        // 使用栈来辅助
        // 如果是数字，就入栈，如果是符号，就把前面两次入栈的数拿出来计算结果，然后把结果入栈
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {

            switch (s) {
                case "+":
                    res = stack.pop() + stack.pop();
                    break;

                case "-":
                    res = -stack.pop() + stack.pop();
                    break;

                case "*":
                    res = stack.pop() * stack.pop();
                    break;

                case "/":
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    res = num2 / num1;
                    break;

                default:
                    res = Integer.valueOf(s);
            }

            stack.push(res);
        }

        return res;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[]{
                "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"
        }));
    }
}
