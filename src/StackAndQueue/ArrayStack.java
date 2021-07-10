package StackAndQueue;

public class ArrayStack<E> implements OurStack<E> {

    OurArray<E> array;

    public ArrayStack() {
        array = new OurArray<>();
    }

    public ArrayStack(int capacity) {
        array = new OurArray<>(capacity);
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E element) {
        array.addLast(element);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            // last index = size-1
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] - Top");
        return res.toString();
    }
}
