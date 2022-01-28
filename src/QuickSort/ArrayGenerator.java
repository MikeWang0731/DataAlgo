package QuickSort;

import java.util.Random;

public class ArrayGenerator {
    private ArrayGenerator() {
    }

    public static Integer[] generatorOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // 生成一个长度为n的随机数组，每个数字的范围是[0,randNumberBound)
    public static Integer[] generateRandomArray(int n, int randNumberBound) {
        Integer[] arr = new Integer[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(randNumberBound);
        }
        return arr;
    }
}
