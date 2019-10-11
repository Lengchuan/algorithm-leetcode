package com.org.lengchuan.algorithm.leetcode.problems;

//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
// 说明:
//1 ≤ m ≤ n ≤ 链表长度。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL
// Related Topics 链表

/**
 * @author lishuijun <lishuijun@kaiyuan.net>
 * @date 2019-10-09
 * @desc
 */
public class ReverseBetween_P92 {

    /**
     * 分三步操作
     * 1->2->3->4->5->null m = 2, n = 4
     * 1. 先找到m的上一个节点,即 1 需要遍历链表到m - 1 ，记为pre
     * 2. 拆分出m到n之间的一段链表，对这一段链表进行反转
     * 3. 拼接pre 和 m到n这一段反转后的链表以及n之后的链表
     * <p>
     * 对于2，就是普通的链表反转
     *
     * @param head
     * @return
     * @see ReverseList_P206
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n || m <= 0 || n <= 0 || m > n) {
            return head;
        }

        ListNode tmp = new ListNode(-1, head);

        //先遍历找到pre
        ListNode pre = tmp;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        //反转m到n之间的链表
        ListNode curr = pre.next;
        ListNode prevT = null;
        ListNode next;
        for (int i = m; i <= n; i++) {
            //断开curr和curr.next 的连接，curr和prevT建立连接，记录curr.next,以便后续使用
            next = curr.next;
            curr.next = prevT;
            //pre 记录当前节点，对于下一次来说，prevT就是上一个节点
            prevT = curr;
            //指针后移
            curr = next;
        }

        //连接 pre反转链表前面的部分 curr反转链表后面的部分 prevT 反转的部分
        pre.next.next = curr;
        pre.next = prevT;

        return tmp.next;
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

        print(new ReverseBetween_P92().reverseBetween(n1, 3, 4));
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



