package Array;

/**
 * 添加操作 -> O(n) (本身O(1)，resize是O(n)，最坏情况就是O(n))
 * resize -> O(n)
 * 删除操作 -> O(n)
 * 查找操作 -> O(n)
 * 更改操作 -> O(n)
 */
public class OurArray<E> {

    private E[] data;  // 数组叫做data
    private int size;  // 数组的元素个数size(数组中第一个没有元素的位置)

    // 构造函数，传入数组的容量capacity，即用户想要开辟多大的数组？
    public OurArray(int capacity) {
        data = (E[]) new Object[capacity];
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
    public void addLast(E element) {
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
    public void add(int index, E element) {
//        // 判断是否有空余位置
//        if (size == data.length) {
//            throw new IllegalArgumentException("Array is FULL!");
//        }
        // 判断index是否合法（不能为负数，不能大于size，即数组必须是连续的）
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        // 将index之后的所有元素向后复制一位，将element赋值给index
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }  // System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    // 向数组头部添加元素(index=0)
    public void addFirst(E element) {
        add(0, element); // 在index=0的地方加入元素
    }

    // 获取指定index的元素
    public E get(int index) {
        // 因为size是作为"第一个没有数据"的位置，所以当index取到size的时候取不到任何数据
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed! Illegal index");
        }
        return data[index];
    }

    // 改变指定index的元素
    public E set(int index, E value) {
        // 因为size是作为"第一个没有数据"的位置，所以当index取到size的时候取不到任何数据
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed! Illegal index");
        }
        return data[index] = value;
    }

    // 查找数组中是否包含指定元素
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            // int比较可以用==，类型比较用.equals()
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中是否包含指定元素并返回其index
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;  // 找不到就return -1
    }

    // 从数组中删除指定index的元素
    public E remove(int targetIndex) {

        if (targetIndex < 0 || targetIndex >= size) {
            throw new IllegalArgumentException("Remove Failed! Illegal index");
        }
        E targetElement = data[targetIndex];
        for (int i = targetIndex + 1; i < size; i++) {
            // 这里不能使用data[i] = data[i+1]，当size=length=10时，i+1=11，会out-of-bound!!!
            data[i - 1] = data[i];  // 注意错误：for里面变量是i，不是targetIndex!
        }
        // data[size - 1] = 0;  // 这一行其实不是必须，因为size--之后，用户访问不到原来的data[size]的值
        size--;
        data[size] = null;  // 将对象置空，可以让垃圾回收机制回收

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return targetElement;
    }

    // 删除指定元素
    // 缺点：若该值出现多次，只能删除第一次出现的值
    public void removeElement(E element) {
        int elementIndex = find(element);
        if (elementIndex != -1) {
            remove(elementIndex);
            System.out.println("Element " + element + " at index " + elementIndex + " has been removed");
        } else {
            throw new IllegalArgumentException("Can't find this value!");
        }
    }

    // 删除第一个元素 和 最后一个
    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        // 创建一个原数组指定倍数长的新数组，将原数组的值全部复制
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        // 再将新数组赋给data
        data = newData;
    }

    @Override
    public String toString() {
        // 使用StringBuilder来构造结果
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array Info: size = %d, capacity = %d\n", size, data.length));
        result.append('[');
        // 到size是因为，我们的数组只有size个元素，虽然我们有capacity个空间，但并不是全都用了
        // i < size 不等同于 i < data.length!!! data.length相当于打印全部capacity!
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i != size - 1) {
                // 如果不是最后一个元素，那么输出完这个元素就加个逗号
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();  // StringBuilder需要toString()才能print
    }
}
