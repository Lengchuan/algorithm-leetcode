package com.org.lengchuan.algorithm.leetcode.p314;
//给定一个二叉树，返回其结点 垂直方向（从上到下，逐列）遍历的值。
//
//如果两个结点在同一行和列，那么顺序则为 从左到右。
//
//示例 1：
//输入: [3,9,20,null,null,15,7]
//
//   3
//  /\
// /  \
//9   20
//    /\
//   /  \
//  15   7
//
//输出:
//
//[
//  [9],
//  [3,15],
//  [20],
//  [7]
//]
//
//示例 2:
//输入: [3,9,8,4,0,1,7]
//
//     3
//    /\
//   /  \
//  9    8
//  /\   /\
// /  \ /  \
//4   0 1   7
//
//输出:
//
//[
//  [4],
//  [9],
//  [3,0,1],
//  [8],
//  [7]
//]
//
//示例 2:
//输入: [3,9,8,4,0,1,7,null,null,null,2,5]（注意：0 的右侧子节点为 2，1 的左侧子节点为 5）
//
//     3
//    /\
//   /  \
//   9   8
//  /\  /\
// /  \/  \
// 4  01   7
//    /\
//   /  \
//   5   2
//
//输出:
//
//[
//  [4],
//  [9,5],
//  [3,0,1],
//  [8,2],
//  [7]
//]
//

import java.util.*;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/7
 * @desc
 */
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 按层次进行遍历 广度
        // 垂直方向即横坐标相等的点都放到一个list里面
        // 维护一个treemap保存list，因为treemap 有序，可以根据横坐标进行排序，保证最终结果是从左到右
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 从小到大排
                return o1 - o2;
            }
        });

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0, 1));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            // map 中是否存在
            List<Integer> list;
            if (map.containsKey(pair.x)) {
                list = map.get(pair.x);
            } else {
                list = new ArrayList<>();
                map.put(pair.x, list);
            }
            list.add(pair.node.val);

            if (pair.node.left != null) {
                queue.offer(new Pair(pair.node.left, pair.x - 1, pair.level + 1));
            }

            if (pair.node.right != null) {
                queue.offer(new Pair(pair.node.right, pair.x + 1, pair.level + 1));
            }
        }

        for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
            res.add(m.getValue());
        }

        return res;
    }

    private static class Pair {
        TreeNode node;
        int x;
        int level;

        public Pair(TreeNode node, int x, int level) {
            this.node = node;
            this.x = x;
            this.level = level;
        }
    }

    private static class TreeNode {
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

        System.out.println(new Solution().verticalOrder(root));
    }
}
