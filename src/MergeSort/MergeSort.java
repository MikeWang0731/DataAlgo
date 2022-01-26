package MergeSort;

import java.util.Arrays;

public class MergeSort {
    // O(n*log n)级别的时间复杂度
    private MergeSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        // 没有元素或只有一个元素
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        // 当前一轮的arr的"左侧"进行归并排序
        sort(arr, l, mid);
        // 当前一轮的arr的"右侧"进行归并排序
        sort(arr, mid + 1, r);
        // 进行归并
        merge(arr, l, mid, r);
    }

    // 合并两个有序的区间: A: arr[l..mid] 和 B: arr[mid+1..r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        // 将原arr复制一份到temp，r+1是因为参数定义是前闭后开，因为归并无法原地完成，原数组会被覆盖
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        // 每轮循环为arr[k]赋值，arr[k]就是指的归并后的数组
        for (int k = l; k <= r; k++) {
            // 判断i和j是否越界
            if (i > mid) {
                // 如果i越界了(A组全部完成)，那么结果数组中的值就是B中轮到的那个值
                // 这里需要从temp中取值是因为原数组会被覆盖，此外，l对应的元素储存在temp[0]，下标有偏移，所以这里是j-l
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                // 比较arr[i] & arr[j]，如果A组里的比B组里的小，那么取A组的值放入结果数组，A指针增1
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSort", arr);
    }
}
