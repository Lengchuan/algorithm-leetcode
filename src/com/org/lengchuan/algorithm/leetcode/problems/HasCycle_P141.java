package com.org.lengchuan.algorithm.leetcode.problems;
//给定一个链表，判断链表中是否有环。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
//
//
//
// 示例 1：
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
//
//
// 示例 2：
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
//
//
// 示例 3：
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
//
//
//
//
//
//
// 进阶：
//
// 你能用 O(1)（即，常量）内存解决此问题吗？
// Related Topics 链表 双指针

/**
 * @author lishuijun <lishuijun@kaiyuan.net>
 * @date 2019-10-14
 * @desc
 */
public class HasCycle_P141 {

    /**
     * 使用快慢指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                //没有环，走到结束了
                return false;
            }

            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(1, null);
        ListNode n4 = new ListNode(2, n5);
        ListNode n3 = new ListNode(5, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        n5.setNext(n1);

        System.out.println(new HasCycle_P141().hasCycle(n1));
    }
}
