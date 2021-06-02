package com.org.lengchuan.algorithm.leetcode.p542;
//给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
//
// 两个相邻元素间的距离为 1 。
//
//
//
// 示例 1：
//
//
//输入：
//[[0,0,0],
// [0,1,0],
// [0,0,0]]
//
//输出：
//[[0,0,0],
// [0,1,0],
// [0,0,0]]
//
//
// 示例 2：
//
//
//输入：
//[[0,0,0],
// [0,1,0],
// [1,1,1]]
//
//输出：
//[[0,0,0],
// [0,1,0],
// [1,2,1]]
//
//
//
//
// 提示：
//
//
// 给定矩阵的元素个数不超过 10000。
// 给定矩阵中至少有一个元素是 0。
// 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
//

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/1
 * @desc
 */
public class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // 最短距离 广度优先搜索
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = helper(mat, i, j);
            }
        }

        return res;
    }

    private static int helper(int[][] mat, int x, int y) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] direct = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        boolean visited[][] = new boolean[m][n];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair pair = queue.poll();

                if (mat[pair.x][pair.y] == 0) {
                    return res;
                }

                for (int[] d : direct) {
                    int xx = pair.x + d[0];
                    int yy = pair.y + d[1];
                    if (xx >= 0 && yy >= 0 && xx < m && yy < n && !visited[xx][yy]) {
                        queue.offer(new Pair(xx, yy));
                        visited[xx][yy] = true;
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
        int[][] mat = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        int[][] res = new Solution().updateMatrix(mat);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }

    }
}
