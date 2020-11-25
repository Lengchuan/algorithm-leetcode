package com.org.lengchuan.algorithm.leetcode.p559;
//给定一个 N 叉树，找到其最大深度。
//
// 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
//
// 例如，给定一个 3叉树 :
//
//
//
//
//
//
//
// 我们应返回其最大深度，3。
//
// 说明:
//
//
// 树的深度不会超过 1000。
// 树的节点总不会超过 5000。
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 135 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/11/25
 * @desc
 */
public class Solution {

    public int maxDepth(Node root) {
        // 深搜
        // 递归遍历所有路径，记录路径的长度，使用一个变量保存最大路径
        if (root == null) {
            return 0;
        } else if (root.children == null || root.children.size() == 0) {
            return 1;
        } else { // 有子节点，需要找出所有子节点中深度最大的
            List<Integer> maxDepths = new ArrayList<>();
            for (Node n : root.children) {
                maxDepths.add(maxDepth(n));
            }

            return Collections.max(maxDepths) + 1;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        List<Node> c1 = new ArrayList<>();
        c1.add(n3);
        c1.add(n2);
        c1.add(n4);
        n1.children = c1;

        List<Node> c2 = new ArrayList<>();
        c2.add(n5);
        c2.add(n6);
        n3.children = c2;

        System.out.println(new Solution().maxDepth(n1));
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
