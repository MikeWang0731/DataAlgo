package BubbleSort;

import java.util.Arrays;

public class BubbleSort {

    private BubbleSort() {
    }

    /*
    复杂度O(n^2)
    冒泡排序就是每轮都将最大的元素放到数组的最后面，下一轮开始时的遍历范围变成arr[0..arr.length-i-1]，i从0开始
    比较过程是当前元素和它的下一个元素进行比较，如果大于，就将这两个元素交换位置
     */

    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 外层循环控制基准指针（用于找到当前元素），外层基准到数组的倒数第二个元素截止，即i = arr.length-2(i < arr.length - 1)
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环控制比较指针（找到“下一个元素”进行比较）
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 当前是arr[j]，下一个是arr[j+1]
                // 如果大于就交换，把小的放前面
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 针对极小部分无序但是绝大部分有序的情况，例如123465进行优化
    // 目标：使算法在排序完需要排序的那部分之后就退出
    public static <E extends Comparable<E>> void sortOptimize(E[] arr) {
        // 外层循环控制基准指针（用于找到当前元素），外层基准到数组的倒数第二个元素截止，即i = arr.length-2(i < arr.length - 1)
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环控制比较指针（找到“下一个元素”进行比较）
            // 当本轮没有交换发生，即(round 1)123465->(round 2)123456，意味着已经全部有序
            boolean isSwapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 当前是arr[j]，下一个是arr[j+1]
                // 如果大于就交换，把小的放前面
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    // 只要发生交换，就设置值为true
                    isSwapped = true;
                }
            }
            // 当已经全部有序时，停止运行
            if (isSwapped) break;
        }
    }

    // 针对上面的情况进一步优化，假如说只有部分有序，例如：2314576
    // 经过第一轮之后变为2134567，其中34567都是有序的，我们不需要再遍历
    // 所以我们可以记录下来最后进行交换的位置，用(数组长度-交换位置)可以直接得到在下一轮中需要被swap的index，减少操作次数
    public static <E extends Comparable<E>> void sortOptimizeTwo(E[] arr) {
        // 外层循环控制基准指针（用于找到当前元素），外层基准到数组的倒数第二个元素截止，即i = arr.length-2(i < arr.length - 1)
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环控制比较指针（找到“下一个元素”进行比较）
            // 默认的最后一个交换位置是0
            int lastSwapIndex = 0;
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 当前是arr[j]，下一个是arr[j+1]
                // 如果大于就交换，把小的放前面
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    // 如果发生交换，最后一个交换位置是j+1
                    lastSwapIndex = j + 1;
                }
            }
            // 找到下一次需要被交换的基准元素的index
            // 这里不需要额外声明(if lastIndex==0: break)是因为，如果lastIndex为0，那么i就是arr.length
            // 不满足for运行条件，直接退出
            i = arr.length - lastSwapIndex;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = {7, 5, 6, 1, 3, 1, 4, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));

        Integer[] arr2 = {1, 2, 3, 4, 6, 5};
        sortOptimize(arr2);
        System.out.println(Arrays.toString(arr2));

        Integer[] arr3 = {2, 3, 1, 4, 5, 7, 6};
        sortOptimizeTwo(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
