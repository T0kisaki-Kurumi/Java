package java基础.查漏补缺;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Robbie
 * @since 2024/10/01
 */

/**
 * 节点流是比较低级的流，每一个节点流类只能处理单一类型的流
 * 处理流是对节点流的包装，可以传入各种类型的节点流，并提供统一的接口，使得流的读写更加方便
 */

// 如果一个类想要序列化，必须实现Serializable接口或Externalizable接口
// Serializable是一个标记接口，不含有方法；Externalizable接口包含writeExternal和readExternal方法。通常使用Serializable接口，更加简单。
// 注意如果其中包含其他类，这些类都需要实现接口
class p43_Dog implements Serializable {
    // 序列化时会默认生成一个serialVersionUID，用于标识类的版本，UID不同则认为是不同的类
    // 如果手动加了serialVersionUID，即使修改了也会认为是一个类的不同版本，反序列化不会报错
    private final static long serialVersionUID = 114514L;

    int age;
    String name;
//    String color;

    public p43_Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "p43_Dog{" + "age=" + age + ", name=" + name + '}';
    }
}

public class p43_处理流 {
    public static void main(String[] args) {
    }

    @Test
    // BufferReader和BufferWriter。带缓冲的字符处理流
    public void test1() throws IOException {
        FileReader fr = new FileReader("java基础/查漏补缺/IO流/test4.txt");
        // 关闭时只要关闭外层的处理流即可，会自动调用内层流的close方法
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            // 读取一行
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
//        // 不能接着再来一次，因为fr已经关闭了
//        try (BufferedReader br = new BufferedReader(fr)) {
//            String line;
//            // 读取一行
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        }

        FileWriter fw = new FileWriter("java基础/查漏补缺/IO流/test6.txt");
//        try (BufferedWriter bw = new BufferedWriter(fw)) {
//            bw.write("hello world");
//            bw.newLine(); // 换行
//            bw.write("你好，世界");
//            bw.newLine();
//        }
        BufferedWriter bw = new BufferedWriter(fw);
        StringBuffer sb = new StringBuffer();
        // 如果不close或flush，缓冲区满时也会自动刷新到文件中
        for (int i = 0; i < 8192 + 8192; i++) {
            sb.append("7");
        }
        bw.write(sb.toString());
    }

    @Test
    // BufferedInputStream和BufferedOutputStream。带缓冲的字节处理流
    public void test2() throws IOException {
        FileInputStream fis = new FileInputStream("图片/java基础/查漏补缺/IO流/IO流体系图.png");
        FileOutputStream fos = new FileOutputStream("图片/java基础/查漏补缺/IO流/IO流体系图拷贝43.png");
        byte[] buffer = new byte[1024];
        int len;
        try (BufferedInputStream bis = new BufferedInputStream(fis);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        }
    }

    @Test
    // ObjectInputStream和ObjectOutputStream。对象处理流，用来对对象进行序列化和反序列化
    public void test3() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("java基础/查漏补缺/IO流/序列化.txt"); // 可以是任意文件类型
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeInt(100);
            oos.writeBoolean(false);
            oos.writeFloat(3.14f);
            oos.writeUTF("hello world");
            oos.writeObject(new p43_Dog(2, "小白")); // 自定义类必须实现Serializable接口
        }

        FileInputStream fis = new FileInputStream("java基础/查漏补缺/IO流/序列化.txt");
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            // 读取时需要按照写入的顺序读取
            System.out.println(ois.readInt());
            System.out.println(ois.readBoolean());
            System.out.println(ois.readFloat());
            System.out.println(ois.readUTF());
            System.out.println(ois.readObject());
        }
    }
}
