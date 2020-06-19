package com.org.lengchuan.algorithm.leetcode.p103;
//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回锯齿形层次遍历如下：
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
//
// Related Topics 栈 树 广度优先搜索

import java.util.*;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/6/19
 * @desc
 */
public class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 广度优先 使用队列辅助
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    list.add(i, node.val);
                } else {
                    stack.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            res.add(list);
            flag = !flag;
        }

        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n17 = new TreeNode(17);
        root.left = n9;
        root.right = n20;
        n20.left = n15;
        n20.right = n17;

        System.out.println(new Solution().zigzagLevelOrder(root));
    }
}
