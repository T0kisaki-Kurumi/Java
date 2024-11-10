package java基础.java8_17;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Robbie
 * @since 2024/10/16
 */

interface p1_GrandFather {
    void grandHello();
}

// lambda表达式替换的对象必须是接口
// 如果接口中只有一个抽象方法，这个接口就叫做函数式接口
@FunctionalInterface
interface p1_Father /* extends p1_GrandFather */ {
    // 如果要用lambda表达式，只能含有一个抽象方法
    String Hello(String s);
//    void Hello2(String s, int i);

    // 可以有default方法
    default void sayHello() {
        System.out.println("Hello World");
    }

    // 可以有从Object继承的其他方法
    boolean equals(Object obj);

    // 不能有从其他接口继承的方法
}

public class p1_Lambda表达式 {
    public static void main(String[] args) {
        p1_Father father1 = s -> "你好 " + s; // 形参数据类型可以省略，如果只有一个参数小括号可以省略，如果只有一个语句大括号可以省略，return关键字可以省略
//        p1_Father father2 = (s, i) -> System.out.println("你好 " + s + " " + i);
        System.out.println(father1.Hello("Robbie"));

        // 几个常用的函数式接口
        Consumer<String> consumer = System.out::println; // 相当于 Function<T, void>
        Supplier<String> supplier = () -> "Hello World2"; // 相当于 Function<void, T>
        Function<String, Integer> function = String::length; // 当然泛型类型肯定不能写void
        Predicate<String> predicate = s -> s.length() > 5; // 相当于 Function<T, boolean>

        consumer.accept("Hello World1");
        System.out.println(supplier.get());
        System.out.println(function.apply("Hello World3"));
        System.out.println(predicate.test("Hello World4"));

        // 方法引用
        // 当方法的形参和返回值类型与函数式接口中方法的形参和返回值类型相同时，可以使用方法引用
        // 1. 对象::实例方法名
        Consumer<String> s = System.out::println;
        s.accept("Hello World5");
        // 2. 类::静态方法名
        Comparator<Integer> c = Integer::compare;
        System.out.println(c.compare(1, 3));
        // 3. 类::实例方法名
        // 函数式接口中的抽象方法a与其内部实现时调用的对象的某个方法b的返回值类型相同。
        // 同时，抽象方法a中有n个参数，方法b中有n-1个参数，且抽象方法a的第1个参数作为方法b的调用者，且抽象方法a的后n-1个参数与方法b的n-1个参数的类型相同
        BiPredicate<String, String> bp = String::equals; // 举例：BiPredicate中的test方法和String::equals方法都返回boolean类型，且test方法有两个参数，equals方法有一个参数
        System.out.println(bp.test("Hello", "World"));

        //构造器引用
        // 类名::new
        // 构造器引用实际上类似于一个get方法
        // 调用的哪个构造器由参数类型决定
        Supplier<String> s2 = String::new; // Supplier<String> 相当于 Function<void, String>，即无参数构造器
        System.out.println(s2.get().isEmpty());
        Function<String, String> f2 = String::new;
        System.out.println(f2.apply("Hello World6"));

        // 数组引用
        // 实际上是一种特殊的构造器引用，构造器形参是Integer类型，用来指定数组的长度
        Function<Integer, String[]> f3 = String[]::new;
        System.out.println(f3.apply(3).length);
    }
}
