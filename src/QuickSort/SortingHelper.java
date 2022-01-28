package QuickSort;

import MergeSort.MergeSort;

public class SortingHelper {
    private SortingHelper() {

    }

    public static <E extends Comparable<E>> boolean isSorted(E[] arrayInput) {
        // 将第二个元素和第一个元素比较，看一看第二个是不是比第一个大，所以i从1开始，不是0开始
        for (int i = 1; i < arrayInput.length; i++) {
            // >0意味着第二个比第一个小，即排序错误，返回false。因为我们是由小到大排序!
            if (arrayInput[i - 1].compareTo(arrayInput[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String algoName, E[] arrayInput) {
        long startTime = System.nanoTime();

        if (algoName.equals("QuickSort")) {
            MergeSort.sort(arrayInput);
        } else {
            System.out.println("None.");
        }

        long endTime = System.nanoTime();

        // 进行排序后验证，若验证成功，显示结果；验证失败，报错
        if (SortingHelper.isSorted(arrayInput)) {
            System.out.println("Time Usage for " + algoName + ": " + (endTime - startTime) / 1000000000.0 + " second.");
        } else {
            throw new RuntimeException("Selection Failed!");
        }
    }
}
