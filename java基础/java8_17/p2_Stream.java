package java基础.java8_17;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Robbie
 * @since 2024/11/06
 */

public class p2_Stream {
    public static List<String> list = Arrays.asList("apple ", "banana ", "orange ", "grape ", "orange ");
    public static Integer[] arr = {1, 2, 3, 4, 5};

    @Test
    // Stream的实例化
    public void test1() {
        // 通过集合
        Stream<String> stream1 = list.stream();
        Stream<String> pstream1 = list.parallelStream(); // 并行stream，提高性能，用法是一样的
        Stream<String> pstream2 = stream1.parallel(); // 普通stream转化为并行stream

        // 通过数组
        Stream<Integer> stream2 = Arrays.stream(arr);

        // 通过Stream.of()
        Stream<Integer> stream3 = Stream.of(arr); // 底层就是基于可变形参和Arrays.stream()
        Stream<Integer> stream4 = Stream.of(1, 2, 3, 4, 5);  // 等价写法（可变形参性质）
    }

    @Test
    // Stream的中间操作
    // 注意同一个stream对象只能被使用一次，多次调用会报错
    public void test2() {
        list.stream().filter(s -> s.compareTo("cat") > 0).forEach(System.out::print); // 筛选
        System.out.println();

        list.stream().limit(2).forEach(System.out::print); // 截取前几个，越界不会报错
        System.out.println();

        list.stream().skip(2).forEach(System.out::print); // 跳过前几个，越界不会报错
        System.out.println();

        list.stream().distinct().forEach(System.out::print); // 去重，底层根据hashCode()和equals()判断
        System.out.println();

        list.stream().sorted().forEach(System.out::print); // 排序，默认升序，需要类实现Comparable接口
        System.out.println();
        list.stream().sorted((s1, s2) -> s1.length() - s2.length()).forEach(System.out::print); // 可以自定义排序规则，如果没有实现Comparable接口必须提供Comparator
        System.out.println();

        list.stream().map(String::length).forEach(System.out::print); // 映射
        System.out.println();
        list.stream().map(String::toUpperCase).forEach(System.out::print);
        System.out.println();
    }

    @Test
    // Stream的终止操作
    public void test3() {
        // 匹配
        System.out.println(list.stream().allMatch(s -> s.startsWith("a"))); // 所有元素都匹配
        System.out.println(list.stream().anyMatch(s -> s.startsWith("a"))); // 存在元素匹配
        System.out.println(list.stream().noneMatch(s -> s.startsWith("a"))); // 所有元素都不匹配

        // 查找
        System.out.println(list.stream().findFirst().get()); // 第一个元素，得到的是Optional类型，需要用get()方法获取值
        System.out.println(list.stream().findAny().get()); // 任意元素，找到满足的元素就立即返回，可能是任意一个元素

        // 计数
        System.out.println(list.stream().count());

        // 最值
        System.out.println(list.stream().max(String::compareTo).get()); // 最大值，得到的是Optional类型，需要用get()方法获取值
        System.out.println(list.stream().min(String::compareTo).get()); // 最小值，得到的是Optional类型，需要用get()方法获取值

        // 遍历
        list.stream().forEach(System.out::print);
        System.out.println();
        list.forEach(System.out::print); // jdk8中，List自带了forEach()方法，但是不支持并行流
        System.out.println();

        // 归约
        System.out.println(Stream.of(arr).reduce((s1, s2) -> s1 + s2).get()); // 底层是BinaryOperator<T> extends BiFunction<T,T,T>
        System.out.println(Stream.of(arr).reduce(0, (s1, s2) -> s1 + s2)); // 可以设置初始值
        System.out.println(Stream.of(arr).reduce(0, Integer::sum)); // 可以写成方法引用形式

        // 收集
        System.out.println(list.stream().sorted().collect(Collectors.toList())); // 转化为List
        System.out.println(list.stream().collect(Collectors.toCollection(() -> new LinkedHashSet<>()))); // 转化为Set
        System.out.println(list.stream().sorted().collect(Collectors.joining())); // 转化为一整个字符串
    }
}
