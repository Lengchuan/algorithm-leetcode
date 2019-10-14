package com.org.lengchuan.algorithm.leetcode.problems;
//请判断一个链表是否为回文链表。
//
// 示例 1:
//
// 输入: 1->2
//输出: false
//
// 示例 2:
//
// 输入: 1->2->2->1
//输出: true
//
//
// 进阶：
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 链表 双指针

/**
 * @author lishuijun <lishuijun@kaiyuan.net>
 * @date 2019-10-14
 * @desc
 */
public class IsPalindrome_P234 {

    /**
     * 使用快慢指针
     * 1. 使用fast slow 两个指针，找到链表的中间节点为slow
     * 2. 反转前面部分的链表
     * 3. 比较前后两部分的链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        //1. 找到链表中间节点
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //2. 反转链表
        ListNode pre = null;
        ListNode curr = head;
        ListNode next;
        //链表反转时条件用curr ！= null,这里需要用curr != slow，因为不需要反转整个链表
        while (curr != slow) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        //3. 比较
        //链表长度为偶数时， 2 * slow = fast
        //链表长度为奇数时， 2 * slow  = fast 还多一个节点，比较时需要去掉后部分的第一个节点
        if (fast != null) {
            slow = slow.next;//slow后半部分 pre反转后的前半部分
        }
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }

            pre = pre.next;
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
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(1, null);
        ListNode n4 = new ListNode(2, n5);
        ListNode n3 = new ListNode(5, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        System.out.println(new IsPalindrome_P234().isPalindrome(n1));
    }
}
