package Recursive;

public class LC203 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        // 使用一个数组构建一个链表
        public ListNode(int[] arr) {
            // 判断一下数组是否符合要求
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException(" Wrong! ");
            }
            // 让数组的第一个值为第一个节点的值
            this.val = arr[0];
            // 设置“当前节点”为数组第一个值的节点
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

    public static ListNode removeElements(ListNode head, int val) {
        // PART01:解决最基本问题，该问题不能自动求解
        // 如果链表本身时空的，那么直接return null
        if (head == null) {
            return null;
        }

        // PART02
        ListNode result = removeElements(head.next, val);
        if (head.val == val) {
            return result;
        } else {
            head.next = result;
            return head;
        }

    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        removeElements(head, 6);
        System.out.println(head);
    }
}
