package LinearSearch;

import java.security.AlgorithmParameterGenerator;

public class LinearSearch {

    private LinearSearch() {
    }

    // 使用"范型"来兼容多种类型的数据：E是Element的缩写，代指任何类型的数据
    // 线性查找法
    public static <E> int search(E[] data, E target) {
        // 遍历整个数组，找到对应值，并返回它的index，如果找不到就返回-1
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {  //.equals()->兼容字符串
                return i;
            }
        }
        return -1;
    }

    /*
    算法复杂度分析需要看最差的情况
    假设我们运行到最后一次才找到需要的结果(最差情况)，即运行n次才找到答案，复杂度就是O(n) -> complexity = n * data_scalar (y=kx，线性正比)
     */

    public static void main(String[] args) {

        //=================== Case 1: 普通数组 ====================
        Integer[] data = {24, 18, 12, 9, 16, 27, 30};  //int->Integer(适应范型)

        // 范型只能接受类对象，不能接受基本数据类型，如int；对于单个数字，可以自动转换
        // 但每个基本数据类型都有自己的包装类 int->Integer
        int res = LinearSearch.search(data, 16);  // Search()私有构造，外部文件只允许调用，不允许new对象
        System.out.println("Index of 16 is: " + res);

        //=================== Case 2: Student类 ==================
        // 初始化自定义数据：这里是对象类型->这里将三个对象存为一个数组
        Test_Student[] students = {new Test_Student("Alice"), new Test_Student("Tom"), new Test_Student("Bob")};
        // 我们想要查找Tom
        Test_Student wanted = new Test_Student("TOM");
        int res_stu = LinearSearch.search(students, wanted);  //.equals会自动找到对象所属类的.equals()方法
        System.out.println("Index of Tom is: " + res_stu);

        //=================== 测试算法性能 ==================
        Integer[] arr_test = ArrayGenerator.generatorOrderedArray(1000000);  // 生成一个一百万的数据用于测试

        long startTime = System.nanoTime();  // 开始时间
        int res_test = LinearSearch.search(arr_test, 1000000);
        long endTime = System.nanoTime();  // 停止时间

        double timeUsage = (endTime - startTime) / 1000000000.0;  // ns -> s
        System.out.println("Time Usage for Linear Search: " + timeUsage + " second.");


    }
}
