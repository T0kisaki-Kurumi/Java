package java基础.java8_17;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Robbie
 * @since 2024/10/16
 */

//abstract class p1_Father {
////    public void Hello(String s) {
////        System.out.println("Hello " + s);
////    }
//    abstract void Hello(String s);
//}

// lambda表达式替换的对象必须是接口
// 如果接口中只有一个抽象方法，这个接口就叫做函数式接口
@FunctionalInterface
interface p1_Father {
    // 如果要用lambda表达式，只能含有一个抽象方法
    String Hello(String s);
//    void Hello2(String s, int i);
}

public class p1_Lambda表达式 {
    public static void main(String[] args) {
        p1_Father father1 = s -> "你好 " + s; // 形参数据类型可以省略，如果只有一个参数小括号可以省略，如果只有一个语句大括号可以省略，return关键字可以省略
//        p1_Father father2 = (s, i) -> System.out.println("你好 " + s + " " + i);
        System.out.println(father1.Hello("Robbie"));

        // 几个常用的函数式接口
        Consumer<String> consumer = System.out::println;
        Supplier<String> supplier = () -> "Hello World2";
        Function<String, Integer> function = String::length;
        Predicate<String> predicate = s -> s.length() > 5;

        consumer.accept("Hello World1");
        System.out.println(supplier.get());
        System.out.println(function.apply("Hello World3"));
        System.out.println(predicate.test("Hello World4"));
    }
}
