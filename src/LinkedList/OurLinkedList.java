package LinkedList;

public class OurLinkedList<E> {

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

    private Node head;
    private int size;

    public OurLinkedList(Node head, int size) {
        this.head = null;
        this.size = 0;
    }

    // 获取链表中元素的个数
    public int getSize() {
        return size;
    }

    // 链表是否为空？
    public boolean isEmpty() {
        return size == 0;
    }

    // 对于数组来说，在数组尾部添加元素非常方便，因为我们有size来跟踪下一个元素应该存放的位置
    // 但是，对于链表来说，我们只有一个head变量跟踪链表头部，所以，我们在头部加入元素非常方便
    // 链表头部添加新元素
    public void addFirst(E e) {
        /*
        高级写法：
        head = new Node(e, head) -> 初始化节点，e给节点，之前的head作为此点的next；然后，新head就是这个Node
         */
        Node node = new Node(e);  // 初始化一个新的节点，用于存放数据
        node.next = head;  // 将新节点的next指向之前一步的head - 即：挂接节点至链表
        head = node;  // 将head的值更新为我们新加入的这个数值e - 即：更新head的位置

        size++;
    }

    // 在链表中间添加(插入)元素
    public void add(E e, int index) {
        // keypoint: 找到指定位置的“前一个节点”是谁
        // 0.判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Wrong Index!");
        }
        // 0-1.在头部(index为0的地方)添加元素
        if (index == 0) {
            addFirst(e);
        } else {

            Node prev = head; // 设置一个从head开始的node叫做prev

            // 1.找到指定位置前一位的index值
            // 因为链表本身不存在index，所以我们需要用for循环数个数的方式数到(index-1)的那个位置
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next; // 不断地寻找并刷新prev的值，直到我们找到了
            }
            // 2.创建包含新元素的节点
            Node node = new Node(e);
            // 3.将新节点连接至指定位置前一位的后面那个节点上 (3和4不能倒过来！)
            node.next = prev.next;
            // 4.将指定位置前面那个节点的next指向这个新节点
            prev.next = node;

            size++;
        }
    }

    // 向末尾添加元素
    public void addLast(E e) {
        add(e, size);
    }
}
