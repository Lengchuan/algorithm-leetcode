package com.org.lengchuan.algorithm.leetcode.p323;
//给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。
//
//示例 1:
//
//输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]
//
//     0          3
//     |          |
//     1 --- 2    4
//
//输出: 2
//示例 2:
//
//输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
//
//     0           4
//     |           |
//     1 --- 2 --- 3
//
//输出:  1
//注意:
//你可以假设在 edges 中不会出现重复的边。而且由于所以的边都是无向边，[0, 1] 与 [1, 0]  相同，所以它们不会同时在 edges 中出现。
//

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/10
 * @desc
 */
public class Solution {

    public int countComponents(int n, int[][] edges) {
        // 构建邻接矩阵
        // 如示例1 递归遍历顶点0->1->2 可以得到计算出一次连通分量
        // 递归遍历3->4 可以计算出一次连通分量
        // 使用递归或者dfs

        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }

        // bfs
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {// 当前顶点被访问过
                continue;
            }
            count++;
            dfs(visited, i, i, graph);
        }
        return count;
    }

    private void dfs(boolean[] visited, int k, int index, int[][] graph) {
        visited[index] = true;
        graph[k][index] = 0;
        graph[index][k] = 0;
        for (int i = 0; i < graph[index].length; i++) {
            if ((graph[index][i] == 1)) {
                dfs(visited, index, i, graph);
            }
        }
    }

    public static void main(String[] args) {
        // [[0,1], [0,2], [0,3], [1,4]]
        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 4}
        };

        System.out.println(new Solution().countComponents(5, edges));

        // n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]
        edges = new int[][]{
                {0, 1},
                {1, 2},
                {3, 4}
        };

        System.out.println(new Solution().countComponents(5, edges));
    }
}

