package java基础.查漏补缺;

import java.util.Arrays;

/**
 * @author Robbie
 * @since 2024/09/16
 */

public class p17_String {
    public static void main(String[] args) {
        // 两种创建String的方法
        // 方式一:先从常量池查看是否有"hsp”数据空间，如果有，直接指向;如果没有则重新创建,然后指向。s最终指向的是常量池的空间地址
        String str1 = "hsp";
        // 方式二:先在堆中创建空间，里面维护了value属性，指向常量池的hsp空间。如果常量池没有"hsp",重新创建,如果有，直接通过value指向。最终指向的是堆中的空间地址。
        String str2 = new String("hsp");

        // 无论什么情况，equals方法都是安全的
        System.out.println(str1.equals(str2)); // true

        System.out.println(str1 == str2); // false,因为str1和str2指向的是不同的数据空间
        System.out.println(str1.hashCode() == str2.hashCode()); // true, 因为String的hashCode方法是根据字符串内容计算的，相同的字符串内容，hashCode也相同。
        System.out.println(str1 == str2.intern()); // true,intern方法是将字符串放入常量池，如果常量池中已经有该字符串，则直接返回常量池中的地址，否则，将字符串放入常量池，并返回常量池中的地址。
        System.out.println(str2 == str2.intern()); // intern方法返回的是常量池中的地址，而str2是堆中的地址

        System.out.println("----------------------------------------------------");

        // 字符串拼接
        String str3 = "hello" + "world"; // 编译器会直接优化为 String str3 = "helloworld";
        System.out.println(str3 == "helloworld"); // true

        String str4 = "hello";
        String str5 = "world";
        String str6 = str4 + str5;
        // String str6 = str4 + str5 底层操作：
        // StringBuilder sb = new StringBuilder();
        // sb.append("hello");
        // sb.append("world");
        // String str6 = sb.toString();
        System.out.println(str6 == "helloworld"); // false，底层操作的最后一步toString方法，会new一个String对象，返回的是堆地址

        System.out.println("----------------------------------------------------");

        // 一些常用方法
        // substring：字符串截取
        System.out.println(str6.substring(3)); // 截取第3个字符（包括）后面所有字符
        System.out.println(str6.substring(3, 6)); // 截取[3, 6)区间的字符，注意是左闭右开区间
        // indexOf：查找子串的位置
        System.out.println(str6.indexOf("wo"));  // 底层是BM算法，而不是KMP算法。Boyer-Moore 算法在处理大文本和小模式时表现良好，而 KMP 更适合处理模式长度较长的情况。
        // concat：字符串拼接
        String str7 = str6.concat("!");
        // split：字符串分割
        String str8 = "a\\b\\c";
        String[] strArr = str8.split("\\\\"); // 为什么要4个\ ： 首先String中需要转义，然后正则表达式也需要
        System.out.println(Arrays.toString(strArr));
        // compareTo：字符串比较
        System.out.println("hel".compareTo("hello")); // 如果是前缀关系，返回长度的差值
        System.out.println("hello123".compareTo("hello")); // 如果是前缀关系，返回长度的差值
        System.out.println("helloa".compareTo("hello1")); //如果不是，返回第一个不相同字母的ASCII码的差值
    }
}
