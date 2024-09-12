package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/12
 */

class Person {
    String name;
}

public class p5_toString {
    public static void main(String[] args) {
        Person p = new Person();
        // toString方法：全类名+@+哈希码的十六进制表示
        System.out.println(p);
        System.out.println(p.toString());
    }
}
