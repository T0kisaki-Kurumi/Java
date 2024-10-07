package java基础.查漏补缺;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Robbie
 * @since 2024/09/29
 */

public class p41_字节流 {
    public static void main(String[] args) {

    }

    @Test
    // 文件流读取单个字符
    public void test1() throws IOException {
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream("java基础/查漏补缺/IO流/test1.txt");
//            int b;
//            while ((b = fis.read())!= -1) {
//                System.out.print((char) b + " ");
//            }
//            System.out.println();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        // 可以使用try-with-resources语句自动关闭资源，需要流对象实现了Closeable接口
        try (FileInputStream fis = new FileInputStream("java基础/查漏补缺/IO流/test1.txt")) {
            int b;
            while ((b = fis.read()) != -1) {
                System.out.print((char) b + " ");
            }
            System.out.println();
        }
    }

    @Test
    // 文件流读取多个字符
    public void test2() throws IOException {
        int size = 8;
        byte[] bytes = new byte[size];
        try (FileInputStream fis = new FileInputStream("java基础/查漏补缺/IO流/test1.txt")) {
            int len;
            while ((len = fis.read(bytes)) != -1) {
                // 注意这边new String里面要写len不能写size，如果是size则最后一次可能会输出未被覆盖的上一次的内容
                System.out.println("读取了" + len + "字节：" + new String(bytes, 0, len));
            }
        }
    }

    @Test
    // 文件流写入单个字符
    public void test3() throws IOException {
        // 没有文件的话会自动创建，但不会创建多级目录
        try (FileOutputStream fos = new FileOutputStream("java基础/查漏补缺/IO流/test2.txt")) {
            fos.write('a');
            fos.write('b');
        }
        // 创建IO流对象时可以指定第二个参数true，表示追加模式。默认为false，每次写入都会清空文件内容
        try (FileOutputStream fos = new FileOutputStream("java基础/查漏补缺/IO流/test2.txt", true)) {
            fos.write('c');
        }
    }

    @Test
    // 文件流写入多个字符
    public void test4() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("java基础/查漏补缺/IO流/test3.txt")) {
            fos.write("hello world啊啊啊".getBytes());
            fos.write("0123456789".getBytes(), 3, 4); // 从索引3开始写入4个字符
        }
    }

    @Test
    // 文件流实现文件拷贝
    public void test5() throws IOException {
        try (FileInputStream fis = new FileInputStream("图片/java基础/查漏补缺/IO流/IO流体系图.png");
             FileOutputStream fos = new FileOutputStream("图片/java基础/查漏补缺/IO流/IO流体系图拷贝.png")) {
             byte[] bytes = new byte[1024];
             int len;
             while ((len = fis.read(bytes)) != -1) {
//                 fos.write(bytes); // 不能直接写入bytes，原理同test2
                 fos.write(bytes, 0, len);
             }
        }
    }

    @Test
    // 标准输入输出流也是字节流
    public void test6() {
        System.out.println(System.in.getClass()); // System.in的运行类型是BufferedInputStream
        System.out.println(System.out.getClass()); // System.out的运行类型是PrintStream
    }
}
