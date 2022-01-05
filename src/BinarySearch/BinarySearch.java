package BinarySearch;

public class BinarySearch {
    // 对于有序数列，才能使用二分查找法，复杂度O(log n)
    // 排序叫做二分查找法的前置条件，算上排序是O(n*log n)
    private BinarySearch() {

    }

    /**
     * 二分查找法(使用递归)
     *
     * @param data   传入的有序数组
     * @param target 想要查找的数据
     * @param <E>    泛型支持
     * @return 目标数据的index
     */
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        return search(data, 0, data.length - 1, target);
    }

    // l:左侧边界，r:右侧边界
    private static <E extends Comparable<E>> int search(E[] data, int l, int r, E target) {
        // Step 0: “终止条件”
        if (l > r) {
            // 递归到底，数组为空
            return -1;
        }
        // Step 1: 获取中间值
        int mid = l + (r - l) / 2;  // 避免整形溢出
        // Step 2: 如果中间值就是目标值
        if (data[mid].compareTo(target) == 0) {
            return mid;
        }
        // Step 3: 如果中间值小于目标值，说米目标值在右侧
        if (data[mid].compareTo(target) < 0) {
            // 我们新的搜索区间就是[中间值+1]到[右侧边界]
            return search(data, mid + 1, r, target);
        }

        // Step 4: 如果中间值大于目标值，说明目标值在左侧
        return search(data, l, mid - 1, target);
    }

    /**
     * 二分查找法(不使用递归)
     *
     * @param data   传入的有序数组
     * @param target 想要查找的数据
     * @param <E>    泛型支持
     * @return 目标数据的index
     */
    public static <E extends Comparable<E>> int searchNonRecursive(E[] data, E target) {
        int l = 0, r = data.length - 1;

        // 在[l,r]中查找target，若第一轮没找到我们会更新l和r的数值，继续在新的l和r中寻找
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) == 0) {
                return mid;
            }

            if (data[mid].compareTo(target) < 0) {
                l = mid + 1;  // 此时中间值<目标值，目标值在右侧，更新左边界以缩小范围，右侧不变
            } else {
                r = mid - 1;  // 此时中间值>目标值，目标值在左侧，更新右侧边界以缩小范围，左侧不变
            }
        }

        return -1;  // 失败条件：l > r (左边界>右边界，数组为空)
    }

    public static void main(String[] args) {
        Integer[] myArr = {-1, 0, 3, 5, 9, 12};
        // 目标查找：9 - 使用递归
        int myResult = search(myArr, 9);
        if (myResult != -1) {
            System.out.println("9出现在myArr中，并且下标(index)为" + myResult);
        } else {
            System.out.println("目标不存在！");
        }

        // 目标查找：9 - 使用非递归
        int myResult2 = searchNonRecursive(myArr, 9);
        if (myResult2 != -1) {
            System.out.println("9出现在myArr中，并且下标(index)为" + myResult2);
        } else {
            System.out.println("目标不存在！");
        }
    }
}
