package com.mini.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_反转链表 {


    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = null;
        ListNode node;
        while (head != null){
            node = head;
            head = head.next;
            node.next = newHead;
            newHead = node;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode f = new ListNode(3);
        ListNode s = new ListNode(2);
        ListNode t = new ListNode(1);

        f.next = s;
        s.next = t;

        new _206_反转链表().reverseList(f);
    }

}
