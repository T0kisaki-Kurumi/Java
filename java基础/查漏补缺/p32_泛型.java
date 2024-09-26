package java基础.查漏补缺;

import org.junit.Test;

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

// 继承的时候可以指定泛型类型，也可以把泛型继承下来
interface p32_Interface<T> {}
class p32_Impl1 implements p32_Interface<String> {}
class p32_Impl2<T> implements p32_Interface<T> {}
class p32_Impl3 extends p32_Generic1<Integer> {}
class p32_Impl4<T> extends p32_Generic1<T> {}

class p32_GrandFather {}

class p32_Father extends p32_GrandFather {
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
    @Test
    public void test1() throws NoSuchFieldException {
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

    @Test
    public void test2() {
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

    @Test
    public void test3() {
        p32_Generic2 g = new p32_Generic2();
        // 泛型方法的泛型类型是自动推导的
        g.func(111);
        p32_Generic2.sfunc("hello");

        // 也可以手动指定，但有点多此一举
        g.<String> func("world");
    }

    static void test_generic1(List<p32_Father> list){}
    static void test_generic2(List<? extends p32_Father> list){}
    static void test_generic3(List<? super p32_Father> list){}
    static void test_generic4(List<?> list){}

    @Test
    public void test4() {

        List<p32_Father> list1 = new ArrayList<>();
        List<p32_Son> list2 = new ArrayList<>();
        // 数据具有继承性，但方法没有
        list1.add(new p32_Father());
        list1.add(new p32_Son());
        test_generic1(list1);
//        test_generic1(list2); // 类型不匹配
        test_generic2(list1);
        test_generic2(list2); // 如果想要使用子类，可以在形参类型上使用通配符<? extends T>

        List<p32_GrandFather> grandFather = new ArrayList<>();
        List<p32_Father> father = new ArrayList<>();
        List<p32_Son> son = new ArrayList<>();
        List<String> string = new ArrayList<>();
        List<Object> obj = new ArrayList<>();
        List list_no = new ArrayList(); // 未指定泛型类型，可以随便传，但正常不会这样用

        // ? extends p32_Father 泛型类型上限，只能获取类及其子类
//        test_generic2(grandFather);
        test_generic2(father);
        test_generic2(son);
//        test_generic2(string);
//        test_generic2(obj);
        test_generic2(list_no);

        // ? super p32_Father 泛型类型下限，只能获取类及其父类
        test_generic3(grandFather);
        test_generic3(father);
//        test_generic3(son);
//        test_generic3(string);
        test_generic3(obj);
        test_generic3(list_no);

        // ? 通配符，可以获取任何类型
        test_generic4(grandFather);
        test_generic4(father);
        test_generic4(son);
        test_generic4(string);
        test_generic4(obj);
        test_generic4(list_no);

        // ? extends p32_Father赋值后不能加入任何对象，因为假设赋值了一个Son的子类<GrandSon>类型，那么自然不能加入Father和Son
        // 简单来说就是编译器在编译时不知道该泛型类型到底是什么，所以不能进行类型检查
        List<? extends p32_Father> list_extends = new ArrayList<>(); // <>不填默认是Father类型，如果要填只能填Father或其子类
//        list_extends.add(new p32_GrandFather());
//        list_extends.add(new p32_Father());
//        list_extends.add(new p32_Son());

        // ? super p32_Father赋值后可以加入Father及其子类
        // 虽然编译器在编译时也不能确定泛型具体是什么，但是能确定的一件事是最后的类型一定是Father或其父类，那么通过多态，Father及其子类是一定能传入的
        List<? super p32_Father> list_super = new ArrayList<>(); // <>不填默认是Father类型，如果要填只能填Father或其父类
//        list_father.add(new p32_GrandFather());
        list_super.add(new p32_Father());
        list_super.add(new p32_Son());
        // 取出的时候均为<? super p32_Father>类型，需要强转
        p32_Father father1 = (p32_Father) list_super.get(0);
        p32_Son son1 = (p32_Son) list_super.get(1);
    }

    public static void main(String[] args) throws NoSuchFieldException {
    }
}
