package Array;

public class Main {
    public static void main(String[] args) {

        OurArray<Integer> array = new OurArray<>(10);
        for (int i = 1; i < 11; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.remove(1);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);
//
//        array.addFirst(-1);
//        System.out.println(array);
//
//        array.removeElement(-1);
//        array.removeElement(4);
//        System.out.println(array);
//
//        System.out.println("============= Test with class ===========");
//
//        OurArray<Test_Student> studentArr = new OurArray<>();
//        Test_Student student1 = new Test_Student("Adam", 100);
//        studentArr.addLast(student1);
//        studentArr.addLast(new Test_Student("Bob", 95));
//        studentArr.addLast(new Test_Student("Chars", 90));
//        System.out.println(studentArr);
//
//        studentArr.remove(2);
//        System.out.println(studentArr);
    }
}
