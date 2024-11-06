package java基础.java8_17;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Robbie
 * @since 2024/11/06
 */

public class p2_Stream {
    public static List<String> list = new ArrayList<>();
    public static Integer[] arr = {1, 2, 3, 4, 5};
    static {
        list.add("apple");
        list.add("banana");
        list.add("orange");
        list.add("grape");
    }

    @Test
    // Stream的实例化
    public void test() {
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
}
