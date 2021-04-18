
public class MyStudent {

    private String name;

    public MyStudent() {

    }

    public MyStudent(String name) {
        this.name = name;
    }

    public static MyStudent test(String name, MyStudent s) {
        System.out.println(name);
        System.out.println(s.name);
        return new MyStudent("sss");
    }

}
