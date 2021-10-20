package Recursive;

public class DebugRecurLList {
    public LC203.ListNode removeElements(LC203.ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call Remove " + val + " in " + head);

        if (head == null) {
            System.out.print(depthString);
            System.out.println("return: " + head);
            return head;
        }

        LC203.ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ":" + res);

        LC203.ListNode returnRes;
        if (head.val == val) {
            returnRes = res;
        } else {
            head.next = res;
            returnRes = head;
        }

        System.out.print(depthString);
        System.out.println("Return: " + returnRes);
        return head.val == val ? head.next : head;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 3, 4, 5, 6};
        LC203.ListNode head = new LC203.ListNode(nums);
        System.out.println(head);

        LC203.ListNode res = (new DebugRecurLList()).removeElements(head, 6,0);
        System.out.println(res);

    }
}
