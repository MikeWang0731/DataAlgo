package LinkedList;

import java.util.LinkedList;

public class LinkedListStack<E> implements OurStack<E> {

    private OurLinkedList<E> list;  // 创建一个OurLinkedList类型的变量叫做list

    public LinkedListStack() {
        list = new OurLinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);  // 从头部添加元素 = 压栈操作
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Stack: TOP->");
        result.append(list);
        return result.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }
}
