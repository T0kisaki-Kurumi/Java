package java基础.查漏补缺;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robbie
 * @since 2024/09/25
 */

class p32_Generic1<T> {
    public T x;
    T[] ts;
//    T[] ts2 = new T[10]; // 泛型数组不能初始化，因为不能确定开多大的空间

    public p32_Generic1() {
    }

    p32_Generic1(T x) {
        this.x = x;
    }

    // 静态方法和静态成员变量不能使用类的泛型，因为静态方法和成员是在类加载的时候确定的，而类泛型是在创建对象时确定的
//    static T y;
//    static void func(T t){}

    T func(T t) {
        System.out.println(t);
        return t;
    }
}

class p32_Generic2 {
    // 泛型方法可以定义在泛型类或普通类中，方法的泛型和类的泛型是独立的
    // 类泛型和方法泛型使用相同字母时，方法泛型优先
    public static <T> void sfunc(T t) {
        System.out.println(t);
    }

    public <T> void func(T t) {
        System.out.println(t);
    }
}

class p32_Father {
    public void func() {
        System.out.println("father func");
    }
}

class p32_Son extends p32_Father {
    @Override
    public void func() {
        System.out.println("son func");
    }

    public void sonFunc() {
        System.out.println("son specific func");
    }
}

public class p32_泛型 {
    static void test1() throws NoSuchFieldException {
        // 泛型可以在编译时就对类型进行检查，并且减少类型转换带来的效率损失
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
//        list.add(1); // 编译时会报错，类型不匹配
        for (String s : list) { // 遍历时不需要进行类型转换
            System.out.println(s);
        }

        p32_Generic1<String> g1 = new p32_Generic1<>("hello");
        p32_Generic1<Integer> g2 = new p32_Generic1<>(100);
        System.out.println(g1.getClass());
        System.out.println(g2.getClass());
        System.out.println(g1.x.getClass());
        System.out.println(g2.x.getClass());

        p32_Generic1 g3 = new p32_Generic1(); // 没有指定泛型时默认为Object类型
        System.out.println(g3.getClass().getField("x").getType());
    }

    static void test2() {
        List<p32_Father> list = new ArrayList<>();
        list.add(new p32_Father());
        list.add(new p32_Son()); // 子类也可以传入
        for (p32_Father f : list) {
            f.func(); // 调用父类方法时发生多态
            System.out.println(f.getClass());
            if (f instanceof p32_Son) {
                ((p32_Son) f).sonFunc(); // 调用子类特有方法时需要强转
            }
            System.out.println();
        }
    }

    static void test3() {
        p32_Generic2 g = new p32_Generic2();
        // 泛型方法的泛型类型是自动推导的
        g.func(111);
        p32_Generic2.sfunc("hello");

        // 也可以手动指定，但有点多此一举
        g.<String> func("world");
    }

    static void test4() {
        class A {}
        class B extends A {}

        List<A> list1 = new ArrayList<>();
        List<? extends A> list2 = new ArrayList(){{add(new B());}};
        list1.add(new A());
        list1.add(new B());
        list2.add(new A());
        list2.add(new B());
    }

    public static void main(String[] args) throws NoSuchFieldException {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
