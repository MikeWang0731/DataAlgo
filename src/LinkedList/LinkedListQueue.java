package LinkedList;

public class LinkedListQueue<E> implements OurQueue<E> {

    // 设计为内部类，因为用户不需要知道链表的底层实现
    private class Node {
        public E e;  // 当前“节点”储存的数值
        public Node next;  // 指向下个"节点的"连接线

        public Node(E e, Node next) {
            // this可以理解为"这个节点Node"
            this.e = e;  // 将用户传来的e赋值给这个节点的e
            this.next = next;  // 将用户传来的next赋值给这个节点的next
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 没有dummyHead，尾tail指向链表的最后一个元素
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = tail = null;
        size = 0;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // 如果tail为空的话，那么说明链表没有元素
        if (tail == null) {
            tail = new Node(e);  // 那就直接在tail新建一个节点
            head = tail;  // 此时链表里有一个元素，head和tail都在一起
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty!");
        }
        // 队列前出后进，所以出队的位置在head
        Node delNode = head;
        head = head.next;  // 让head挪到下一个元素的位置
        delNode.next = null;  // 让原来的head(即delNode)置空
        // 当表里只有一个元素，我们删除了唯一的这个，此时表里就没有元素了，即head为null
        if (head == null) {
            // 没有元素 -> head和tail都为null
            tail = null;
        }
        size--;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty!");
        }
        return head.e;  // 头元素的值就是head节点所对应的值
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Queue: Front[");

        Node current = head;
        while (current != null) {
            result.append(current).append("->");
            current = current.next;
        }
        result.append("]Tail");
        return result.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
