package Recursive;

public class BasicRecur {
    // 递归：Recursive -> 将复杂问题不断拆分为简单问题，也可理解为不断重复的使用某个方法
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    // 计算arr[left...n]这个区间内的数字和
    private static int sum(int[] arr, int leftBound) {
        // PART01:解决最基本问题，该问题不能自动求解
        // 如果左边界和数组长度相等，即，数组为空或全部计算结束。此时这一步的和为0
        if (leftBound == arr.length) {
            return 0;
        }
        // PART02:将原有问题转换成更小一层的问题
        // 不断地去用"当前边界"和"当前边界的下一个值"相加
        return arr[leftBound] + sum(arr, leftBound + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Sum:" + sum(arr));
    }
}
