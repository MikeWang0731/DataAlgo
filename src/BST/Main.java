package BST;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        Random random = new Random();

        // 为测试用例添加元素
        int N_TIMES = 1000;
        for (int i = 0; i < N_TIMES; i++) {
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums = new ArrayList<>();
        // 如果bst不为空，那么就不断的删除最小值，并将最小值保存在nums
        int t = 10;
        while (t >= 0) {
            nums.add(bst.removeMin());
            t--;
        }
        // 那么这个nums应该是一个由小到大排序的数组
        System.out.println(nums);
    }
}
