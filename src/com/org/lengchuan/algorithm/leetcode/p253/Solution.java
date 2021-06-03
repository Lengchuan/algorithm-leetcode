package com.org.lengchuan.algorithm.leetcode.p253;
//给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
//
// 
//
//示例 1：
//
//输入：intervals = [[0,30],[5,10],[15,20]]
//输出：2
//示例 2：
//
//输入：intervals = [[7,10],[2,4]]
//输出：1
// 
//
//提示：
//
//1 <= intervals.length <= 104
//0 <= starti < endi <= 106
//

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2021/6/3
 * @desc
 */
public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        // 我们需要遵守的一个原则,即越早开始的会议要先分配会议室,我们需要按照开始时间进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // 怎么共用会议室?
        // 单有一个会议结束之后，也就是结束时间小于另一个会议的开始时间时，我们可以复用会议室
        // 这里我们需要遵循的一个原则是优先使用最早结束会议的会议室
        // 我们使用优先队列来辅助，优先队列记录会议的结束时间，如果某个会议已经结束，另一个会议正好可以使用
        // 当前会议室，就把之前的记录删除，因为我们复用了会议室，如果没有可以复用的会议室，就使用新的会议室
        PriorityQueue<Integer> queue = new PriorityQueue<>(intervals.length, Comparator.comparingInt(o -> o));
        queue.add(intervals[0][1]); //结束时间
        for (int i = 1; i < intervals.length; i++) {
            int[] m = intervals[i];

            // 如果顶部的会议已经结束
            if (queue.peek() <= m[0]) {
                // 复用这个会议室
                queue.poll();
                queue.add(m[1]);
                continue;
            }

            // 顶部的会议还没结束，新开一个会议室
            queue.add(m[1]);
        }

        return queue.size();

    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println(new Solution().minMeetingRooms(intervals));
    }
}
