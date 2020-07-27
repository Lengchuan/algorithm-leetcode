package com.org.lengchuan.algorithm.leetcode.p1102;
//给你一个 R 行 C 列的整数矩阵 A。矩阵上的路径从 [0,0] 开始，在 [R-1,C-1] 结束。
//
//路径沿四个基本方向（上、下、左、右）展开，从一个已访问单元格移动到任一相邻的未访问单元格。
//
//路径的得分是该路径上的 最小 值。例如，路径 8 →  4 →  5 →  9 的值为 4 。
//
//找出所有路径中得分 最高 的那条路径，返回其 得分。
//
//示例 1：
//
//
//
//输入：[[5,4,5],[1,2,6],[7,4,6]]
//输出：4
//解释：
//得分最高的路径用黄色突出显示。
//示例 2：
//
//
//
//输入：[[2,2,1,2,2,2],[1,2,2,2,1,2]]
//输出：2
//示例 3：
//
//
//
//输入：[[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
//输出：3
// 
//
//提示：
//
//1 <= R, C <= 100
//0 <= A[i][j] <= 10^9
//

import java.util.PriorityQueue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/14
 * @desc
 */
public class Solution {

    int R, C;

    public int maximumMinimumPath(int[][] A) {
        // bfs+优先队列，每次优先走最小值越大的路径，如果到达终点，则直接返回，直到所有路径遍历完成
        R = A.length;
        if (R == 0) {
            return 0;
        }
        C = A[0].length;
        boolean[][] visited = new boolean[R][C];
        // 上 下 左 右
        int[][] direct = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> {
            return o2.min - o1.min; // 从小到大
        });
        queue.add(new Pair(0, 0, A[0][0]));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.x == R - 1 && pair.y == C - 1) {
                // 到达终点
                return pair.min;
            }

            // 标记当前点已经被访问过
            visited[pair.x][pair.y] = true;
            // 未到达终点，继续遍历相邻四个方向的点
            for (int[] d : direct) {
                int nextX = pair.x + d[0];
                int nextY = pair.y + d[1];
                if (nextX >= 0 && nextX <= R - 1 && nextY >= 0 && nextY <= C - 1 && !visited[nextX][nextY]) {
                    queue.offer(new Pair(nextX, nextY, Math.min(pair.min, A[nextX][nextY])));
                }
            }
        }

        return -1;
    }

    private static class Pair {
        int x;
        int y;
        int min;

        public Pair(int x, int y, int min) {
            this.x = x;
            this.y = y;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        // [[5,4,5],[1,2,6],[7,4,6]]
        int[][] A = new int[][]{
                {5, 4, 5},
                {1, 2, 6},
                {7, 4, 6}
        };

        System.out.println(new Solution().maximumMinimumPath(A));

    }
}
