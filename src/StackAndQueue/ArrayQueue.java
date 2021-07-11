package StackAndQueue;

public class ArrayQueue<E> implements OurQueue<E> {
    OurArray<E> array;

    public ArrayQueue(int capacity) {
        array = new OurArray<>(capacity);
    }

    public ArrayQueue() {
        array = new OurArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: head - [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            // last index = size-1
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] - Tail");
        return res.toString();
    }
}
