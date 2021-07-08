package SelectionSort;

public class InsertionSort {
    private InsertionSort() {

    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 将当前元素arr[i]插入到合适的位置
            // 比较当前元素和它之前的元素，即:用j=i确定当前元素位置，j>=1确保它前面有元素，j--确保不断地往前去比较
            for (int j = i; j >= 1; j--) {  //此时 [i] 就是 [j]，前面一个就是 [j-1]
                // 如果这个元素比前面的元素小，就要插入到它前面去
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else break;
            }
        }
    }

    // 当arr[currIndex]比arr[prevIndex]要小的时候，将curr和prev换位置，因为prev的应该比curr的小
    private static <E> void swap(E[] targetArr, int currIndex, int prevIndex) {
        E temp = targetArr[prevIndex];
        targetArr[prevIndex] = targetArr[currIndex];
        targetArr[currIndex] = temp;
    }

    // 第二种不涉及swap方法
    public static <E extends Comparable<E>> void sortV2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 设立一个临时变量暂存本轮选定的值 (tempValue=arr[i]=arr[jIndex])
            E tempValue = arr[i];
            // 确定这个临时值应该在数组中出现的index
            int jIndex;  // Index of tempValue
            // 从i(jIndex)开始向前看，每次都是本轮j的位置和j-1比较。
            for (jIndex = i; jIndex >= 1; jIndex--) {
                // 如果前面比选定值大：那就让前面一个的值向当前复制(相当于向后平移一位)，不用怕当前值(tempValue)丢失，因为前面暂存了
                if (arr[jIndex - 1].compareTo(tempValue) > 0) {
                    arr[jIndex] = arr[jIndex - 1];
                } else break;  // 直到前面的值[j-1]比这个值[j]小，那么[j]就是tempValue应该在的地方
                // 原来这个地方的值已经被向后复制一位了，并不用怕丢失，现在这个地方就等着被tempVal覆盖
            }
            arr[jIndex] = tempValue;
        }
    }

    // 插入排序法时间复杂度O(n^2)，但插入排序法对于有序数据的复杂度为O(n)，然而选择排序永远是O(n^2)
    // 即：当数据为近似有序数据时，插入排序远快与选择排序

    public static void main(String[] args) {
        // ========== Case 1: 普通数组 ==========
        Integer[] arr = {9, 7, 6, 5, 2, 4, 8};
        InsertionSort.sortV2(arr);
        System.out.println("After insert sort: ");
        for (int e : arr
        ) {
            System.out.print(" " + e);
        }
        System.out.println(" ");

        // ========== Case 2: 性能测试 ==========
        Integer[] testArr = ArrayGenerator.generateRandomArray(10000, 10000);
        SortingHelper.sortTest("InsertionSort", testArr);

    }
}