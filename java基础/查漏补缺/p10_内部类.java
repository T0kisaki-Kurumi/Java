package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/13
 */

/**
 * 局部内部类：
 * 局部内部类是指在一个方法或者一个作用域中定义的类，它的作用域仅限于当前方法或者作用域，外部类无法访问局部内部类的成员。
 * 局部内部类的地位相当于一个局部变量，不能使用修饰符，但能使用final
 */
class Outer1 {
    private final static int NUM = 10;
    private int sameName = 20;

    private static void func_outer() {
    }

    public void func() {
        class Inner1 {
            private int sameName = 30;

            public void func_inner() {
                System.out.println(NUM);
                func_outer();

                // 访问内外部同名成员变量默认遵循就近原则，如果一定要访问外部，需要使用：外部类名.this.成员变量名
                System.out.println("外部类同名变量=" + Outer1.this.sameName);
                System.out.println("内部类同名变量=" + sameName);
            }
        }

        Inner1 inner1 = new Inner1();
        inner1.func_inner();
    }
}


/**
 * 匿名内部类：
 * 匿名内部类是一种特殊的内部类，它没有名字，只能在方法内部使用。它的语法格式为：new 父类或接口名() { 重写方法或实现接口的方法体 };
 * 匿名内部类可以简化代码，用于那些只需要用到一次的类，避免定义过多的类。
 * 匿名内部类可以基于接口、抽象类或普通类来实现
 * 匿名内部类本质上是一个对象
 */
interface p10_IF {
    void func();
}

abstract class p10_AB {
    abstract void func();
}

class p10_CL {
    String name;

    public p10_CL(String name) {
        this.name = name;
    }

    public void func() {
        System.out.println("调用普通类函数");
        System.out.println("类名 = " + this.getClass());
    }
}

class Outer2 {
    public static void receive(p10_IF p10_if){
        p10_if.func();
    }

    public void func() {
        p10_IF p10_if = new p10_IF() {
            @Override
            public void func() {
                System.out.println("调用基于接口的匿名内部类的重写方法");
                System.out.println("类名 = " + this.getClass());
            }
        };
        p10_if.func();

        p10_AB p10_ab = new p10_AB() {
            @Override
            void func() {
                System.out.println("调用基于抽象类的匿名内部类的重写方法");
                System.out.println("类名 = " + this.getClass());
            }
        };
        p10_ab.func();

        p10_CL p10_cl = new p10_CL("Jackeylove") {
            @Override
            public void func() {
                System.out.println("调用基于普通类的匿名内部类的重写方法" + ", name=" + name);
                System.out.println("类名 = " + this.getClass());
            }
        };
        p10_cl.func();

        // 注意如果不重写的话，只会调用普通类的方法
        p10_CL p10_cl2 = new p10_CL("Jackeylove");
        p10_cl2.func();

        // 可以简化为lambda表达式，此时甚至都不会生成匿名内部类！
        p10_IF p10_if2 = () -> {
            System.out.println("调用基于接口和lambda表达式的匿名内部类的重写方法");
            System.out.println("类名 = " + this.getClass());
        };
        //单条语句甚至可以省略大括号
//        p10_IF p10_if2 = () -> System.out.println("调用基于接口和lambda表达式的匿名内部类的重写方法");
        p10_if2.func();

        // 匿名内部类本质上是一个对象，可以作为参数传递给方法
        receive(new p10_IF() {
            @Override
            public void func() {
                System.out.println("基于接口的匿名内部类用于传参");
            }
        });
    }
}

/**
 * 成员内部类：
 * 地位相当于一个成员变量，可以用访问修饰符，也可以用static修饰
 */
class Outer3 {
    public class Inner3 {
        public void func() {
            System.out.println("成员内部类");
        }
    }

    public static class Inner3_s {
        public void func() {
            System.out.println("静态成员内部类");
        }
    }
}

public class p10_内部类 {
    public static void main(String[] args) {
//        Outer1 outer1 = new Outer1();
//        outer1.func();

//        Outer2 outer2 = new Outer2();
//        outer2.func();

        Outer3 outer3 = new Outer3();
        Outer3.Inner3 inner3 = outer3.new Inner3(); // 声明普通成员内部类的对象语法有点奇怪
        Outer3.Inner3 inner3_1 = new Outer3().new Inner3(); // 或者直接这样写
//        Outer3.Inner3 inner3 = new outer3.Inner3(); // 这样是不行的
        System.out.println(inner3.getClass()); // 全类名是：java基础.查漏补缺.Outer3$Inner3

        Outer3.Inner3_s inner3_s = new Outer3.Inner3_s(); // 声明静态成员内部类的对象语法符合正常习惯
        inner3_s.func();
//        Outer3.Inner3_s inner3_s1 = Outer3.new Inner3_s(); // 如果还和普通成员内部类一样声明就会报错
    }
}
