package Array;

public class JavaArray {

    public static void main(String[] args) {
        int[] arr = new int[10]; // 生成一个容纳10个元素的数组

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;  // 填充元素
        }

        int[] scores = new int[]{100, 99, 66}; // 生成一个有三个值的数组
        // for-each语句打印数组元素
        for (int s :scores) {
            System.out.println(s);
        }

        // 索引可以有语义也可以没有，数组最好应用于有语义的情况！
        // index: 0 1 2 3 4 5
        // ele  : 2 4 6 8 0 1
        scores[0] = 98; // 更改第一个值为98
    }
}
