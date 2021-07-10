package Array;

public class Test_Student implements Comparable<Test_Student> {

    public String name;

    private int score;

    public Test_Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //override父类Object的equals方法，不能写为equals(Student student)!
    @Override
    public boolean equals(Object student) {

        if (this == student) {  //判断是否为同一个类对象(即都为Test_Student new出来的)
            return true;
        }
        if (student == null) {  // 空对象就false
            return false;
        }
        if (this.getClass() != student.getClass()) {  // 同"一"
            return false;
        }

        Test_Student another = (Test_Student) student;


        // 类的比较转换为字符串的比较，因为this.name是字符串
        // .equalsIgnoreCase()->匹配字符串时忽略大小写，等同于全部.toLowerCase()
        return this.name.equalsIgnoreCase(another.name);
    }

    @Override
    // 重写compareTo方法，比较学生成绩
    public int compareTo(Test_Student another) {
        if (this.score < another.score) {
            return -1;
        } else if (this.score == another.score) {
            return 0;
        }
        return 1;
        // 或者: return this.score - another.score;  因为这样前者小于后者，那么相减也是一个负数
    }

    @Override
    public String toString() {
        return "Test_Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
