package java基础.查漏补缺;

import java.util.Objects;

/**
 * @author Robbie
 * @since 2024/09/12
 */

class Person1 {
    int age;
    String name;
    String gender;

    public Person1(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, gender);
    }
}

class Person2 {
    int age;
    String name;
    String gender;

    public Person2(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }
}

public class p4_hashCode {
    public static void main(String[] args) {
        String s1 = "hello";
        s1.hashCode();

        Person1 p1 = new Person1(20, "Robbie", "male");
        Person2 p2 = new Person2(20, "Robbie", "male");
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

    }
}
