package com.org.lengchuan.algorithm.leetcode.p993;
//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
//
// 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
//
// 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
//
// 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
//
//
//
// 示例 1：
//
//
// 输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
//
//
// 示例 2：
//
//
// 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
//
//
// 示例 3：
//
//
//
// 输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false
//
//
//
// 提示：
//
//
// 二叉树的节点数介于 2 到 100 之间。
// 每个节点的值都是唯一的、范围为 1 到 100 的整数。
//
//
//
// Related Topics 树 广度优先搜索
// 👍 108 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/11/25
 * @desc
 */
public class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        // 广搜，按层次遍历
        // 1. 如果是堂兄弟节点，必须在同一层
        // 2. 需要记录每一个节点的父亲
        if (root == null) {
            return false;
        }
        // 使用队列辅助
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(null, root));
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Pair> pairs = new ArrayList<>();
            while (size > 0) {
                Pair pair = queue.poll();
                if (pair.node.val == x || pair.node.val == y) {
                    pairs.add(pair);
                }

                if (pair.node.left != null) {
                    queue.offer(new Pair(pair.node, pair.node.left));
                }
                if (pair.node.right != null) {
                    queue.offer(new Pair(pair.node, pair.node.right));
                }

                if (pairs.size() == 2 && pairs.get(0).parent != pairs.get(1).parent) {
                    return true;
                }
                size--;
            }
        }

        return false;
    }

    private class Pair {
        TreeNode parent;
        TreeNode node;

        public Pair(TreeNode parent, TreeNode node) {
            this.parent = parent;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.right = n5;

        System.out.println(new Solution().isCousins(n1, 4, 5));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
