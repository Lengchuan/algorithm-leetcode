package com.org.lengchuan.algorithm.leetcode.problems;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 2019-09-10
 * @desc 两数相加
 */
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
// Related Topics 链表 数学
//1. 链表对应结点相加时增加前一个结点的进位，并保存下一个结点的进位；除法得进位，模得结果。
//2. 两个链表长度不一致时，要处理较长链表剩余的高位和进位计算的值；
//3. 如果最高位计算时还产生进位，则还需要添加一个额外结点。
public class AddTwoNumbers_P2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode tmp = null;
        int sum = 0;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            //每位相加的和
            sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            //每次的进位
            carry = sum / 10;
            //每次的数值
            ListNode node = new ListNode(sum % 10);
            if (null == tmp) {
                tmp = node;
                result = tmp;
            } else {
                tmp.next = node;
                tmp = tmp.next;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return result;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l12 = new ListNode(3);
        l1.next = l11;
        l11.next = l12;

        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(6);
        ListNode l22 = new ListNode(4);
        l2.next = l21;
        l21.next = l22;

        AddTwoNumbers_P2 a2 = new AddTwoNumbers_P2();
        ListNode l3 = a2.addTwoNumbers(l1, l2);
        System.out.println(l3);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(val);
        ListNode tmp = next;
        while (tmp != null) {
            stringBuilder.append("-->").append(tmp.val);
            tmp = tmp.next;
        }
        return stringBuilder.toString();
    }
}
