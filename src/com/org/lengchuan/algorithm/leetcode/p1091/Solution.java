package com.org.lengchuan.algorithm.leetcode.p1091;
//给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
//
// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求
//：
//
//
// 路径途经的所有单元格都的值都是 0 。
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
//
//
// 畅通路径的长度 是该路径途经的单元格总数。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,1],[1,0]]
//输出：2
//
//
// 示例 2：
//
//
//输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
//输出：4
//
//
// 示例 3：
//
//
//输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
//输出：-1
//
//
//
//
// 提示：
//
//
// n == grid.length
// n == grid[i].length
// 1 <= n <= 100
// grid[i][j] 为 0 或 1
//
// Related Topics 广度优先搜索
// 👍 102 👎 0

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/1
 * @desc
 */
public class Solution {

    public int shortestPathBinaryMatrix(int[][] grid) {
        // 求最短路径，使用bfs
        // 八个方向，使用数组表示
        // 左 左上 上 右上 右 右下 下 左下
        // (-1,0) (-1,-1) (0,-1) (1,-1) (1,0) (1,1) (0,1) (-1,1)
        int[][] direct = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0));
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair pair = queue.poll();
                // 如果当前位置是1，继续走下一个
                if (grid[pair.x][pair.y] == 1) {
                    continue;
                }
                // 如果已经到达终点，则返回结果
                if (pair.x == m - 1 && pair.y == n - 1) {
                    return res + 1;
                }
                // 往其它八个方向上走
                for (int[] d : direct) {
                    int x = pair.x + d[0];
                    int y = pair.y + d[1];
                    if (x >= 0 && y >= 0 && x < m && y < n && !visited[x][y]) {
                        // 可以继续往这个方向上走
                        queue.offer(new Pair(x, y));
                        visited[x][y] = true;
                    }
                }

            }

            res++;
        }

        return -1;
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // [0,1,1,0,0,0],[0,1,0,1,1,0],[0,1,1,0,1,0],[0,0,0,1,1,0],[1,1,1,1,1,0],[1,1,1,1,1,0]]
        int[][] grid = new int[][]{
                {0, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0}
        };

        System.out.println(new Solution().shortestPathBinaryMatrix(grid));
    }
}
