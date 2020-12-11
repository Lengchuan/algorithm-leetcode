package com.org.lengchuan.algorithm.leetcode.p1267;
//这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
//
// 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
//
// 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
//
//
//
// 示例 1：
//
//
//
// 输入：grid = [[1,0],[0,1]]
//输出：0
//解释：没有一台服务器能与其他服务器进行通信。
//
// 示例 2：
//
//
//
// 输入：grid = [[1,0],[1,1]]
//输出：3
//解释：所有这些服务器都至少可以与一台别的服务器进行通信。
//
//
// 示例 3：
//
//
//
// 输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
//输出：4
//解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m <= 250
// 1 <= n <= 250
// grid[i][j] == 0 or 1
//
// Related Topics 图 数组
// 👍 26 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countServers(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        // 如果一台服务器不能通信，那么他所在的行跟列都没有其它服务器
        // 1. 先找出一行只有一台的服务器，这是有可能无法通信的
        // 2. 对比这些可能服务通信的服务器，看列上是否有其它服务器，如果没有，表示无法参与通信
        // 3. 总的服务器数量-无法通信的 = 可参与通信的服务器数量

        // 行
        LinkedList<Integer> list = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            int rowCount = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    // 有服务器
                    count++;
                    rowCount++;
                    list.add(j); // 可能无法服务通信的
                }
            }
            if (rowCount >= 2) { // 可以通信
                for (int k = 0; k < rowCount; k++) {
                    list.removeLast();
                }
            }
        }

        // 列
        int count2 = 0;
        for (int j : list) {
            int cellCount = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    cellCount++;
                }
            }
            if (cellCount == 1) {
                count2++;
            }
        }

        return count - count2;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countServers(new int[][]{
                {1, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        }));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
