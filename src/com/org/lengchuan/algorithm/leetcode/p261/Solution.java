package com.org.lengchuan.algorithm.leetcode.p261;
//给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。
//
//示例 1：
//
//输入: n = 5, 边列表 edges = [[0,1], [0,2], [0,3], [1,4]]
//输出: true
//示例 2:
//
//输入: n = 5, 边列表 edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
//输出: false
//注意：你可以假定边列表 edges 中不会出现重复的边。由于所有的边是无向边，边 [0,1] 和边 [1,0] 是相同的，因此不会同时出现在边列表 edges 中。
//

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/9
 * @desc
 */
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // 一个图如果是一棵树需要满足：
        // 1. 各个顶点不成环
        // 2. 所有顶点都有连接线--连通分量为0
        // 构建图的邻接矩阵，使用dfs或者bfs遍历，标识访问过的顶点
        // 如果一个顶点被访问两次，则说明构成了环
        // 如果有顶点未被标记，则说明连通分量大于0

        // 构建连接矩阵
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1; // 无向图邻接矩阵是对称的
        }

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // 当前顶点被访问到了，标记
            visited[curr] = true;
            // 查看 curr的邻接点
            for (int i = 0; i < n; i++) {
                // 是否有连接线
                if (graph[curr][i] == 1) {
                    if (visited[i]) {// 当前顶点已经被访问过
                        return false;
                    }

                    // 标记
                    visited[i] = true;
                    graph[curr][i] = 0;
                    graph[i][curr] = 0;
                    queue.add(i);
                }
            }
        }

        // 有顶点未被访问过
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // [[0,1], [0,2], [0,3], [1,4]]
        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 4}
        };

        System.out.println(new Solution().validTree(edges.length + 1, edges));
    }
}
