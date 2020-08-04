package com.org.lengchuan.algorithm.leetcode.p490;
//由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
//
// 给定球的起始位置，目的地和迷宫，判断球能否在目的地停下。
//
// 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
//
//
//
// 示例 1:
//
// 输入 1: 迷宫由以下二维数组表示
//
//0 0 1 0 0
//0 0 0 0 0
//0 0 0 1 0
//1 1 0 1 1
//0 0 0 0 0
//
//输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
//输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)
//
//输出: true
//
//解析: 一个可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
//
//
//
// 示例 2:
//
// 输入 1: 迷宫由以下二维数组表示
//
//0 0 1 0 0
//0 0 0 0 0
//0 0 0 1 0
//1 1 0 1 1
//0 0 0 0 0
//
//输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
//输入 3: 目的地坐标 (rowDest, colDest) = (3, 2)
//
//输出: false
//
//解析: 没有能够使球停在目的地的路径。
//
//
//
//
//
// 注意:
//
//
// 迷宫中只有一个球和一个目的地。
// 球和目的地都在空地上，且初始时它们不在同一位置。
// 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。
// 迷宫至少包括2块空地，行数和列数均不超过100。
//
// Related Topics 深度优先搜索 广度优先搜索

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2020/7/31
 * @desc
 */
public class Solution {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // 广度或者深度 关键点在于每次行进时如果没有遇到墙就一直往前走，直到遍历所有路径
        // 每次遇到墙之后，看当前位置是否已经走过，如果没有走过，则从当前位置开始继续走，同时把当前位置标记为已经走过
        if (maze.length == 0) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        int w = maze.length;
        int h = maze[0].length;
        // 上 下 左 右 四个方向
        int[][] direct = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        boolean[][] visited = new boolean[w][h];
        visited[start[0]][start[1]] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(start[0], start[1]));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.x == destination[0] && pair.y == destination[1]) {
                return true;
            }

            // 四个方向遍历
            for (int[] d : direct) {
                int x = pair.x + d[0];
                int y = pair.y + d[1];
                // 没有遇到墙之前一直往前走
                while (x >= 0 && y >= 0 && x < w && y < h && maze[x][y] == 0) {
                    x = x + d[0];
                    y = y + d[1];
                }

                // 遇到墙之后，如果当前位置没有访问过，则从当前位置开始继续往下走
                if (!visited[x - d[0]][y - d[1]]) {
                    visited[x - d[0]][y - d[1]] = true;
                    queue.offer(new Pair(x - d[0], y - d[1]));
                }
            }

        }
        return false;
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
        //0 0 1 0 0
        //0 0 0 0 0
        //0 0 0 1 0
        //1 1 0 1 1
        //0 0 0 0 0

        int[][] maze = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };

        int[] statr = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        System.out.println(new Solution().hasPath(maze, statr, destination));
    }
}
