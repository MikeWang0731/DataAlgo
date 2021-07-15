package LinkedList;

public class Main {
    public static void main(String[] args) {
        OurLinkedList<Integer> linkedList = new OurLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        // 4 -> 3 -> 2 -> 1 -> 0 -> NULL/END

        linkedList.add(666, 2);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
