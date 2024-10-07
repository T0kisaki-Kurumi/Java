package java基础.查漏补缺;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Robbie
 * @since 2024/10/05
 */

class Dog implements Serializable {
    // 基于类的描述符（包括类名、父类名、实现的接口、类的成员变量等）生成一个哈希值。默认用computeDefaultSUID方法生成。
    private static final long serialVersionUID = -2213683754941922578L;

    // 声明为transient和static的成员变量不会被序列化
    static String nationality;
    transient String color;
    int age;
    String name;

    public Dog(int age, String name, String color, String nationality) {
        this.age = age;
        this.name = name;
        this.color = color;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}

public class p44_序列化测试 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    }

    @Test
    // 序列化
    public void test1() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("java基础/查漏补缺/IO流/序列化2.txt"))) {
            oos.writeObject(new Dog(2, "Rufus", "brown", "Japanese"));
        }
    }

    @Test
    // 反序列化
    public void test2() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("java基础/查漏补缺/IO流/序列化2.txt"))) {
            Dog dog2 = (Dog) ois.readObject();
            System.out.println(dog2);
        }
    }
}
