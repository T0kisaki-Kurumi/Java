package java基础.java8_17;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Robbie
 * @since 2024/11/10
 */

public class p3_try_with_resource {
    public static void main(String[] args) throws IOException {
        // jdk7开始，try-with-resources可以自动关闭资源，不需要手动关闭，只要流对象实现了Closeable/AutoCloseable接口即可
        try (FileReader fr1 = new FileReader("java基础/查漏补缺/IO流/test4.txt");
             FileWriter fw1 = new FileWriter("java基础/查漏补缺/IO流/test5.txt")) {
            System.out.println((char) fr1.read());
        }

        // jdk9开始，try的括号里面可以只写变量名，变量声明写在try外面，代码更加美观一点
        FileReader fr2 = new FileReader("java基础/查漏补缺/IO流/test4.txt");
        FileWriter fw2 = new FileWriter("java基础/查漏补缺/IO流/test5.txt");
        try (fr2; fw2) {
            System.out.println((char) fr2.read());
            // 资源对象是final的，不能再次赋值
//            fw2 = null;
        }
    }
}
