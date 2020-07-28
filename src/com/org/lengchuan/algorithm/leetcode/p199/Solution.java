package com.org.lengchuan.algorithm.leetcode.p199;
//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
// 示例:
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
//
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/28
 * @desc
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // bfs
        // 使用TreeMap来存储每一层的结果，每一次只保留最后一次结果
        // TreeMap key 从大到小排序，保证从上到下有序
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, Integer> map = new TreeMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            // 遍历每一层
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                map.put(level, node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getValue());
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
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.right = n5;
        n3.right = n4;

        System.out.println(new Solution().rightSideView(n1));
    }
}
