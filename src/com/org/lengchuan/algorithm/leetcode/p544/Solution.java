package com.org.lengchuan.algorithm.leetcode.p544;
//你的面前有一堵矩形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。
//
// 砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。
//
// 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。
//
// 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
//
//
//
// 示例：
//
// 输入: [[1,2,2,1],
//      [3,1,2],
//      [1,3,2],
//      [2,4],
//      [3,1,2],
//      [1,3,1,1]]
//
//输出: 2
//
//解释:
//
//
//
//
//
// 提示：
//
//
// 每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。
// 每一行砖块的数量在 [1,10,000] 范围内， 墙的高度在 [1,10,000] 范围内， 总的砖块数量不超过 20,000。
//
// Related Topics 哈希表
// 👍 117 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        // 每一行总的宽度是一样的
        // 每一列上缝隙越多,穿过的砖就越少
        if (wall.size() == 0) {
            return 0;
        }

        // 总的列数
        int height = wall.size();
        Map<Integer, Integer> map = new HashMap<>(); // 边界位置,数量
        for (int i = 0; i < height; i++) {
            int sum = 0;
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                sum += wall.get(i).get(j);
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }

        if (map.size() == 0) {
            return height;
        }

        return height - Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();

    }

    public static void main(String[] args) {

    }
}
//leetcode submit region end(Prohibit modification and deletion)
