package java基础.查漏补缺;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Robbie
 * @since 2024/09/29
 */

public class p40_文件相关 {
    @Test
    // 创建删除文件
    public void test1() throws IOException {
        // 方式1：直接写文件的路径
        File file1 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流\\test1.txt"); // 绝对路径
        File file2 = new File("java基础\\查漏补缺\\IO流\\test2.txt"); // 相对路径（相对于当前项目的根目录）
        file1.createNewFile();
        file2.createNewFile();

        // 方式2：父目录路径+文件名
        File file3 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流", "test3.txt");
        File file4 = new File("java基础\\查漏补缺\\IO流", "test4.txt");
        file3.createNewFile();
        file4.createNewFile();

        // 方式3：父目录文件对象+文件名
        File parent5 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流");
        File file5 = new File(parent5, "test5.txt");
        File parent6 = new File("java基础\\查漏补缺\\IO流");
        File file6 = new File(parent6, "test6.txt");
        file5.createNewFile();
        file6.createNewFile();

        File file7 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流\\test_del.txt");
        file7.createNewFile();
        System.out.println(file7.delete());;
    }

    @Test
    // 获取文件信息
    public void test2() {
        File file1 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流\\test1.txt");
        System.out.println("文件名：" + file1.getName());
        System.out.println("绝对路径：" + file1.getAbsolutePath());
        System.out.println("父目录：" + file1.getParent());
        System.out.println("是否存在：" + file1.exists());
        System.out.println("是否是文件：" + file1.isFile());
        System.out.println("是否是目录：" + file1.isDirectory());
        System.out.println("最后修改时间：" + file1.lastModified());
        System.out.println("文件大小：" + file1.length());

        File file2 = new File("E:");
        System.out.println("文件名：" + file2.getName());
        System.out.println("绝对路径：" + file2.getAbsolutePath());
        System.out.println("父目录：" + file2.getParent());
        System.out.println("是否存在：" + file2.exists());
        System.out.println("是否是文件：" + file2.isFile());
        System.out.println("是否是目录：" + file2.isDirectory());
        System.out.println("最后修改时间：" + file2.lastModified());
        System.out.println("文件大小：" + file2.length());
    }

    @Test
    // 创建目录。目录也是一种文件
    // 如果目录已存在，或用mkdir创建多级目录，返回false
    public void test3() {
        // 单级目录
        File dir1 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流\\test_dir1");
        System.out.println(dir1.mkdir());

        // 多级目录
        File dir2 = new File("E:\\GitCode\\Java\\java基础\\查漏补缺\\IO流\\test_dir2\\test_dir3");
//        System.out.println(dir2.mkdir());
        System.out.println(dir2.mkdirs());
    }

    public static void main(String[] args) {

    }
}
