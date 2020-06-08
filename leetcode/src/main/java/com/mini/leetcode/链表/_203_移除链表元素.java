package com.mini.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/submissions/
 */
public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode curr = head;
        while(curr != null){
            if(curr.val == val){
                prev.next = curr.next;
                curr.next = null;
            }else {
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        if(head == null){
            return head;
        }
        if(head.val == val){
            return removeElements2(head.next,val);
        }else {
            head.next = removeElements2(head.next,val);
            return head;
        }
    }
}
