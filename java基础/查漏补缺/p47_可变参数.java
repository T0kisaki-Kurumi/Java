package java基础.查漏补缺;

import java.util.stream.Stream;

/**
 * @author Robbie
 * @since 2024/11/06
 */

public class p47_可变参数 {
    // 可变参数本质上是数组
    static void print(Integer... args) {
        Stream.of(args).forEach(arg -> {
            System.out.print(arg + " ");
        });
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        // 以下两种方式完全一致，即输入多个参数，就相当于把参数组成了一个数组输入
        print(arr);
        print(1, 2, 3, 4, 5);
    }
}
