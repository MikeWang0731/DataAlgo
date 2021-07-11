package StackAndQueue;

public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        System.out.println("====== Queue ======");

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);

        queue.enqueue(5);
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue.getFront());
    }
}
