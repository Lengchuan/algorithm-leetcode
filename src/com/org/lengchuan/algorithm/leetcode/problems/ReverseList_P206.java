package com.org.lengchuan.algorithm.leetcode.problems;

//反转一个单链表。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
// 进阶:
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
// Related Topics 链表

/**
 * @author lishuijun <lishuijun@kaiyuan.net>
 * @date 2019-10-09
 * @desc
 */
public class ReverseList_P206 {

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode listNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return listNode;
    }


    /**
     *
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        ListNode n5 = new ListNode(5, null);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        print(n1);

        print(new ReverseList_P206().reverseList(n1));
    }

    public static void print(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(head.val);

        while (head.next != null) {
            stringBuilder.append("-->").append(head.next.val);
            head = head.next;
        }

        System.out.println(stringBuilder.toString());
    }
}



