package Leetcode;

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

        // 假设头节点就是我们要删除的那个
        // 用while不用if是因为，有可能第一个和后面的节点都是要删除的值
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 如果全删除完了，head就为null了
        if (head == null) {
            return null;
        }

        // 当链表中间有我们需要删除的时，我们需要找到待删除节点的前一个节点
        ListNode preNode = head;  // 我们设定一个preNode从head开始
        // 我们从head开始不断地向后搜索，只要preNode的下一个不是空(即，还有节点)，我们就继续
        while (preNode.next != null) {
            // 如果说“前一个节点”的“下一个节点”的值(即，待操作节点)等于我们要删除的值
            if (preNode.next.val == val) {
                // 那么delNode就是prev.next
                ListNode delNode = preNode.next;
                // 将前一个节点的next挂接到目标节点的next(即，绕过目标节点重新挂接)
                preNode.next = delNode.next;
                // 将目标节点的next置空，即，脱离
                delNode.next = null;
            } else {
                // while循环的递增遍历需要手动操作！这不是for循环！
                preNode = preNode.next;  // 如果不是要删除的，那么preNode就往后走一个
            }
        }
        return head;
    }

    public static ListNode removeElements2(ListNode head, int val) {

        // 因为dummyHead我们永远不会访问它，所以只是随便赋一个值
        ListNode dummyHead = new ListNode(-1);
        // dummyHead的下一个节点为真正的head
        dummyHead.next = head;

        // 因为有虚拟头节点的存在，我们就不需要考虑真实头节点的特殊情况
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        removeElements2(head, 6);
        System.out.println(head);
    }
}
