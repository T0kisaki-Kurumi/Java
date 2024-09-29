package java基础.查漏补缺;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Robbie
 * @since 2024/09/29
 */

public class p42_字符流 {
    public static void main(String[] args) {
    }

    @Test
    // 字符流读取
    public void test1() throws IOException {
        // 读取单个字符
        try (FileReader fr = new FileReader("java基础/查漏补缺/p40_文件相关/test4.txt")) {
            System.out.println((char) fr.read());
        }

        // 读取多个字符，借助char[]
        char[] chars = new char[1024];
        int len;
        try (FileReader fr = new FileReader("java基础/查漏补缺/p40_文件相关/test4.txt")) {
            while ((len = fr.read(chars)) != -1) {
                System.out.print(new String(chars, 0, len));
            }
        }
    }

    @Test
    // 字符流写入
    public void test2() throws IOException {
        /*
            注意FileWriter如果不调用flush或close，数据不会被写入文件
            flush和close会调用writeBytes方法，将缓冲区中的数据写入文件，底层实际上调用的是FileOutputStream的write方法
            try with resources可以自动调用close方法
         */
//        FileWriter fw = new FileWriter("java基础/查漏补缺/p40_文件相关/test5.txt");
//        fw.write("只");
//        fw.flush();
//        fw.close();

        // 写入单个字符
        try (FileWriter fw = new FileWriter("java基础/查漏补缺/p40_文件相关/test5.txt")) {
            fw.write('c');
            fw.write('x');
            fw.write('k');
            fw.write('\n');
        }

        // 写入多个字符
        try (FileWriter fw = new FileWriter("java基础/查漏补缺/p40_文件相关/test5.txt", true)) { // 追加模式
            // 可以借助String
            fw.write("只因");
            fw.write("只因你太美", 2, 1);
            // 也可以借助char[]
            char[] chars1 = "太".toCharArray();
            char[] chars2 = "只因你太美".toCharArray();
            fw.write(chars1);
            fw.write(chars2, 4, 1);
        }
    }
}
