package Array;

public class OurArray {

    private int[] data;  // 数组叫做data
    private int size;  // 数组的元素个数size(数组中第一个没有元素的位置)

    // 构造函数，传入数组的容量capacity，即用户想要开辟多大的数组？
    public OurArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 无参构造：用户有时候也不知道想要多少，我们默认为10
    public OurArray() {
        this(10);
    }

    // 获取元素个数
    public int getSize() {
        return size;
    }

    // 获取容量
    public int getCapacity() {
        return data.length;
    }

    // 是否为空？
    public boolean isEmpty() {
        return size == 0;
    }

    // 向数组的最后添加一个元素
    public void addLast(int element) {
        // 因为我们数组的长度是固定的，如果满了则不能再加入
        // 假设数组的长度是5，则data.length=>5，此时最后一个元素的index为4；size作为第一个没有元素的位置，此时等于5
        // 所以说，当size=data.length的时候，数组就存满了
        if (size == data.length) {
            throw new IllegalArgumentException("Array is FULL!");
        } else {
            data[size] = element;
            size++;
        }
    }

    // 向指定位置添加元素
    public void add(int index, int element) {
        // 判断是否有空余位置
        if (size == data.length) {
            throw new IllegalArgumentException("Array is FULL!");
        }
        // 判断index是否合法（不能为负数，不能大于size，即数组必须是连续的）
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        // 将index之后的所有元素向后复制一位，将element赋值给index
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }  // System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    // 向数组头部添加元素(index=0)
    public void addFirst(int element) {
        add(0,element); // 在index=0的地方加入元素
    }
}
