package com.org.lengchuan.algorithm.leetcode.p111;
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例:
//
// 给定二叉树 [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回它的最小深度 2.
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/7
 * @desc
 */
public class Solution {

    public int minDepth(TreeNode root) {
        // 广度或者深度都可以
        // 这里用深度，使用队列来辅助或者使用递归也可以
        // 需要记录下每条路径的深度
        if (root == null) {
            return 0;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.node.left == null && pair.node.right == null) {
                min = Math.min(min, pair.level);
            }

            if (pair.node.left != null) {
                queue.offer(new Pair(pair.node.left, pair.level + 1));
            }

            if (pair.node.right != null) {
                queue.offer(new Pair(pair.node.right, pair.level + 1));
            }
        }

        return min;
    }

    private static class Pair {
        private TreeNode node;
        private int level;

        public Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
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

        System.out.println(new Solution().minDepth(root));
    }
}
