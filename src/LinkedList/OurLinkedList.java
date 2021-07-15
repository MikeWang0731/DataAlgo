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

    // 为了解决index为0时的链表操作问题，我们引入了dummyHead
    private Node dummyHead; // dummyHead为虚拟头节点
    private int size;

    public OurLinkedList() {
        // dummyHead在链表初始化时就会存在，但是没有值也没有挂接
        dummyHead = new Node(null, null);
        size = 0;
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
        /* 无DH的第一版写法
        高级写法：
        head = new Node(e, head) -> 初始化节点，e给节点，之前的head作为此点的next；然后，新head就是这个Node
        或：
        Node node = new Node(e);  // 初始化一个新的节点，用于存放数据
        node.next = head;  // 将新节点的next指向之前一步的head - 即：挂接节点至链表
        head = node;  // 将head的值更新为我们新加入的这个数值e - 即：更新head的位置

        size++;
         */
        add(e, 0);
    }

    // 在链表中间添加(插入)元素
    public void add(E e, int index) {
        // keypoint: 找到指定位置的“前一个节点”是谁
        // 0.判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Wrong Index!");
        }

        Node prev = dummyHead; // 设置一个从head开始的node叫做prev

        // 1.找到指定位置前一位的index值
        /*
        Case 1: 没有dummyHead
        当没有DH的时候，链表的第一个元素在index=0，我们需要找到指定index的前一个元素，即，prev=(index-2).next=(index-1)，
        即，我们要在index-2这个位置停下，也就是i<index-1，因为i是取不到index-1的。假设在第三位置插入一个元素：
        loop1->i=0,prev=0.next=1 (第一次只能取到1)
        loop2->i=1,prev=1.next=2 (i<3(index)-1，即i<2，i取不到2，所以i会在i=1停下。此时1.next=2，满足需求)
         */

        /*
        Case 2: 有dummyHead
        当有DH的时候，链表的第一个元素在dummyHead的后面，我们将prev从DH开始算，prev(DH).next=list[0]。我们仍然
        需要找到指定index的前一个元素(index-1)，但由于第一次能取到0，所以我们只需要i<index。假设在第三位置插入一个元素：
        loop1->i=0,prev=DH.next=0
        loop2->i=1,prev=0.next=1
        loop3->i=2,prev=1.next=2 (i<index，即i在i=2的时候停下，满足需求)
         */
        for (int i = 0; i < index; i++) {
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

    // 向末尾添加元素
    public void addLast(E e) {
        add(e, size);
    }

    // 取得指定位置的元素
    public E get(int index) {

        // 判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Wrong Index!");
        }

        Node current = dummyHead.next; // current Node初始化在index=0的地方，即链表的第一个元素
        for (int i = 0; i < index; i++) {
            //因为i最终取到的是index-1，但是(index-1).next就是index，所以for里的i是i<index
            current = current.next;
        }
        return current.e;  // current.e就是节点current的值
    }

    // 获得第一个 或 最后一个元素
    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    } // 因为dummyHead也算一个size，即真正的最后在size-1

    // 修改链表的指定位置的元素
    public void set(E e, int index) {
        // 判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Wrong Index!");
        }
        // 找到第index个元素
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // 更改current元素的值(cur.e)为用户传来的值(e)
        current.e = e;
    }

    // 查找是否存在指定元素
    public boolean contains(E e) {
        Node current = dummyHead.next;
        // 当current不为空，即为有效节点时，while循环运行
        while (current != null) {
            // 如果节点值和用户需要的一样，那么就true
            if (current.e.equals(e)) {
                return true;
            }
            // 如果不一样，就进入到下一个节点
            current = current.next;
        }
        // 全都结束了还找不到，就false
        return false;
    }

    // 删除指定位置的元素
    public E remove(int index) {
        // 判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Wrong Index!");
        }
        // 1.找到指定位置的前一个节点
        Node prev = dummyHead;
        // i=0,prev=dm.next=0,即for的i为几，prev就能取到index为几。但因为i<index即i最多取到index-1，
        // 这刚好满足找到指定index的前一个节点的需求
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 2.找到要删除的节点prev.next，因为prev是前一个，所以p.next就是我们想要的
        Node delNode = prev.next;
        // 3. 将前一个节点的next直接指向要移除的节点的next，即越过要删除的节点进行挂接
        // 注意！此处不等于delNode = delNode.next!因为这是节点挂接处的变更，是引用的变更！
        prev.next = delNode.next;
        // 4.将要删除节点的挂接置空，即脱离原链表
        delNode.next = null;
        size--;
        return delNode.e;
    }

    // 删除第一个 或 最后一个元素
    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);  // 因为dummyHead也算一个size，即真正的最后在size-1
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = dummyHead.next;

        while (current != null) {
            result.append(current).append(" -> ");
            current = current.next;
        }
        result.append("NULL/END");

        return result.toString();
    }
}
