package java基础.查漏补缺;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Robbie
 * @since 2024/10/07
 */

public class p45_Properties {
    public static void main(String[] args) throws IOException {
        // Properties的父类是Hashtable
        Properties properties = new Properties();
        properties.load(new FileReader("java基础/查漏补缺/IO流/config.properties"));
        properties.list(System.out);
        System.out.println(properties.get("user"));

        properties.setProperty("user", "Robbie"); // 可以对已有键值对进行修改
        properties.setProperty("port", "8080"); // 如果不存在键值对，会自动添加

        // store方法可以将Properties对象保存到文件中
        // 注意如果是对一个已有properties文件进行修改，则必须先load，否则会覆盖原文件
        properties.store(new FileWriter("java基础/查漏补缺/IO流/config.properties"), "update user"); // comment会以注释的形式（# ....）保存到文件开头
    }
}
