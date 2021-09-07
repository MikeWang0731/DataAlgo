package LinkedList;

import Recursive.LC203;

public class LC206 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        // 使用一个数组构建一个列表
        public ListNode(int[] arr) {
            // 如果数组为空则无效
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("Wrong Array");
            }
            // 第一个节点的值为数组的第一个数的值
            this.val = arr[0];
            // this(第一个节点的值)为current
            ListNode current = this;
            // 遍历数组的元素，因为数组的第一个值在上面赋值了，所以i从1开始！
            for (int i = 1; i < arr.length; i++) {
                // 当前节点的下一个节点为arr[i]
                current.next = new ListNode(arr[i]);
                // 设置完之后让current向后走一步
                current = current.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                result.append(current.val).append("->");
                current = current.next;
            }
            result.append("NULL/END");
            return result.toString();
        }
    }

    // 翻转链表:1->2->3->4->5->null 变为 null<-1<-2<-3<-4<-5
    // 不可以将val的值提取出来再重新赋给链表，我们需要直接操作链表！

    public static ListNode reverseList(ListNode head) {
        // 这里我们需要三个额外的“指针”来帮助我们完成任务: prev,cur,next
        // prev用来指向当前节点的前一个节点(反转链表要把指针指向前一个节点)
        // cur指向当前节点，next指向下一个待操作的节点(不同于cur.next，它是“当前节点的下一连接的”节点)
        ListNode pre = null;  // 一开始cur在头节点，头节点前面没东西，所以是null
        ListNode cur = head;

        while (cur != null) {
            // current不为空的话，next就不会报空指针异常
            ListNode next = cur.next;
            cur.next = pre;  // 反转节点：1->2 to 1<-2
            pre = cur;  // 操作下一个节点
            cur = next; // 操作下一个节点
        }
        // 当循环结束的时候，current指向原链表的最后一个节点的next(它是个null)，
        // 所以，如果我们想要返回反转后的链表的头节点，就需要找到“此时指向原链表的最后一个
        // 节点的指针“，也就是pre！
        return pre;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        System.out.println("Original LL: " + head);

        ListNode revHead = reverseList(head);
        System.out.println("After LL: " + revHead);
    }

}
