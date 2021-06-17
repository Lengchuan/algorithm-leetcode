package com.org.lengchuan.algorithm.leetcode.p743;
//有 n 个网络节点，标记为 1 到 n。
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
//
//
//
// 示例 1：
//
//
//
//
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
//
//
// 示例 2：
//
//
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
//
//
// 示例 3：
//
//
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= k <= n <= 100
// 1 <= times.length <= 6000
// times[i].length == 3
// 1 <= ui, vi <= n
// ui != vi
// 0 <= wi <= 100
// 所有 (ui, vi) 对都 互不相同（即，不含重复边）
//
// Related Topics 堆 深度优先搜索 广度优先搜索 图

import java.util.*;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/17
 * @desc
 */
public class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 迪杰斯特拉最短路径
        // 初始化邻接矩阵
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            if (graph.containsKey(time[0])) {
                graph.get(time[0]).add(new int[]{time[1], time[2]});
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{time[1], time[2]});
                graph.put(time[0], list);
            }
        }
        //
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        //
        dist[k] = 0;
        dist[0] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> dist[o]));
        queue.offer(k);

        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (visited[num]) {
                continue;
            }
            visited[num] = true;
            // 遍历当前节点的邻接点
            List<int[]> list = graph.get(num);
            if (list == null) {
                continue;
            }
            for (int[] ints : list) {
                int i = ints[0];
                int d = ints[1];
                if (visited[i]) {
                    continue;
                }
                // 更新当前节点的最短距离
                dist[i] = Math.min(dist[i], dist[num] + d);

                // 当前节点发生了改变，那么他的邻接节点也可能会发生改变
                queue.offer(i);
            }
        }

        // 如果数组中还存在最大值，说明有未能到达的节点
        int max = Arrays.stream(dist).max().getAsInt();
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    public static void main(String[] args) {
        // [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
        int[][] times = new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        System.out.println(new Solution().networkDelayTime(times, 4, 2));
    }
}

