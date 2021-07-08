package SelectionSort;

public class SelectionSort {
    private SelectionSort() {

    }

    // 范型可比较语法：<E extends Comparable<E>>
    public static <E extends Comparable<E>> void sort(E[] arr) {

        // arr[0..i)为有序，arr[i..n)为无序
        for (int i = 0; i < arr.length; i++) {
            // ===== 找到每轮循环中当前arr最小值的索引 =====
            // 假设初始最小值index是i
            int minIndex = i;
            // 从i往后继续找，如果发现比i小的，就让它当这个minIndex
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {  //compareTo: >0前者大于后者，=0相等，<0前者小于后者
                    minIndex = j;
                }
            }
            // ===== 通过index交换当前值(i)和上面找到的最小值(arr[minIndex]) ====
            swap(arr, i, minIndex);
        }
    }

    // 选择排序法复杂度O(n^2) <- 1+2+3+..n <- 等差数列求和，省去常数项和低次项

    private static <E> void swap(E[] arr, int currentIndex, int minIndex) {
        E temp = arr[currentIndex];  // E->Any type
        arr[currentIndex] = arr[minIndex];
        arr[minIndex] = temp;
    }

    public static void main(String[] args) {

        // ========== Case 1: 普通数组(由小到大) ==========
        Integer[] arr = {1, 4, 2, 3, 6, 5, 7};
        SelectionSort.sort(arr);
        System.out.println("===== After Sort =====");
        for (int e :
                arr) {
            System.out.print(e + " ");
        }

        System.out.println(" ");

        // ========== Case 2: Student类，按照学生成绩排序(由小到大) ==========
        Test_Student[] student = {
                new Test_Student("Alice", 85),
                new Test_Student("Bob", 100),
                new Test_Student("Cart", 62)
        };
        // sort()会调用Test_Student中的compareTo方法
        SelectionSort.sort(student);
        for (Test_Student s : student
        ) {
            System.out.print(s + " ");
        }

        System.out.println(" ");

        // ========== Case 3: 算法性能测试 ==========
        Integer[] test_arr = ArrayGenerator.generateRandomArray(10000, 10000);
        SortingHelper.sortTest("SelectionSort", test_arr);
    }
}
