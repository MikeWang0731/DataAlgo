package Recursive;

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

    // 使用递归完成
    public static ListNode reverseList(ListNode head) {
        // 最基本(小)问题：当链表为空或只有一个节点时，不需要翻转
        if (head == null || head.next == null) {
            return head;
        }
        // 递归
        ListNode rev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        System.out.println("Original: " + head);

        ListNode revHead = reverseList(head);
        System.out.println("After: " + revHead);
    }
}
