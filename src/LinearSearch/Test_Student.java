package LinearSearch;

public class Test_Student {

    public String name;

    public Test_Student(String name) {
        this.name = name;
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
}
