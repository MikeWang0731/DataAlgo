package ShellSort;

import java.util.Arrays;

public class ShellSort {

    private ShellSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 初始间隔
        int gap = arr.length / 2;
        // 主循环
        while (gap >= 1) {
            // 只要间隔>=1就保持运行
            // i代表每一个子数组的开始的索引：arr[i,i+h,i+2h,i+3h...] -> 对这个数组进行插入排序
            for (int i = 0; i < gap; i++) {
                // 大循环控制子数组起始位置，小循环控制这个子数组的取值/比较/交换
                for (int j = i + gap; j < arr.length; j += gap) {
                    E temp = arr[j];
                    int insertIndex;
                    // 如果元素需要排序，那么我们就找到它应该去的位置（从当前位置不断地往前看，和前面的比较，因为是升序数组）
                    // 此时我们操作的子数组依然是：arr[i,i+h,i+2h,i+3h...]
                    for (insertIndex = j; insertIndex - gap >= 0 && temp.compareTo(arr[insertIndex - gap]) < 0; insertIndex -= gap) {
                        arr[insertIndex] = arr[insertIndex - gap];
                    }
                    arr[insertIndex] = temp;
                }

            }
            // 更新间隔
            gap = gap / 2;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 3, 2, 6, 7, 7, 5};
        sort(arr);
        System.out.println("Shell Sort: " + Arrays.toString(arr));
    }
}
