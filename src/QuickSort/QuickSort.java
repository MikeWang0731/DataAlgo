package QuickSort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private QuickSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // Step 1: 取得pivot索引
        int pivot = partition(arr, l, r);
        // Step 2: 对pivot之前的元素进行sort
        sort(arr, l, pivot - 1);
        // Step 3: 对pivot之后的元素进行sort
        sort(arr, pivot + 1, r);
    }

    /**
     * 对数组实现分区，假设arr[l]为标定点，保持arr[l+1..j]<v且arr[j+1..i]>=v
     *
     * @param arr 传入的数组
     * @param l   左侧边界
     * @param r   右侧边界
     * @param <E> 泛型
     * @return 返回pivot标定点
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        // 为了解决在对“有序”数组的排序过程中的性能退化问题，我们引入一个随机index，对第一个数和index进行交换
        // 随机数范围是l,r，但是random无法直接表示，所以我们转化为[0..r-l]，但又因为nextInt的参数是不包含的，所以是(r-l+1)+l
        int randomPivot = (new Random()).nextInt(r - l + 1) + l;
        swap(arr, l, randomPivot);
        // j是大于pivot和小于pivot的分界线
        int j = l;
        // 因为l是标定点，不参与分区，所以真正的第一个元素是l+1
        for (int i = l + 1; i <= r; i++) {
            // 如果当前元素arr[i]大于标定点，其实什么都不需要做
            // 我们真正需要操作的是“当前元素小于标定点”的情况，此时交换arr[i]和arr[j]
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        // 将标定点放到它应该的位置上，即j的位置(大小分界线)
        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("QuickSort", arr);

        Integer[] arr2 = {5, 3, 2, 9, 6, 8, 7, 1};
        sort(arr2);
        System.out.println("QuickSort: " + Arrays.toString(arr2));

    }
}
