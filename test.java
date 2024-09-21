import java.lang.reflect.Field;

class Father {
    protected  transient int age;

    public Father(int age) {
        this.age = age;
    }
}

class Son extends Father {
    public Son(int age) {
        super(age);
    }
}

public class test {
    public static void main(String[] args) throws Exception {
        Son son = new Son(25);
//        System.out.println(son.age);
        Field ageField1 = son.getClass().getDeclaredField("age");
        ageField1.setAccessible(true);
        System.out.println(ageField1.get(son));
    }
}
