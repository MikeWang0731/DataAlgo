package LinkedList;

public interface OurStack<E> {
    public int getSize();

    public boolean isEmpty();

    public void push(E e);

    public E pop();

    public E peek();
}
