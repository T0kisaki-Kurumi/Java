package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/12
 */

class A {
    public static int s = 1;
    public static void fs(){}

    public int s2 = 1;

    // 构造方法的最前面隐含了super()和普通代码块的调用this()
    A() {
        System.out.println("构造方法执行");
    }

    // 静态代码块在类被加载时执行一次，且只执行一次
    static {
        System.out.println("静态代码块1执行");
    }
    static {
        System.out.println("静态代码块2执行");
    }

    // 普通代码块在对象被创建时执行一次,可以执行多次
    {
        System.out.println("普通代码块执行");
    }
}

class B extends A {}

public class p7_代码块 {
    public static void main(String[] args) throws Throwable {
        // 加载类的几种方法
        // 1. 创建对象
//        A a = new A();

        // 2. 调用静态方法或访问静态变量
//        System.out.println(A.s);
//        A.fs();

        // 3. 创建子类对象
//        B b = new B();

        // 4. forName方法
//        Class.forName("java基础.查漏补缺.A");

        // 静态代码块只执行一次,普通代码块可以执行多次
        // 调用顺序：静态代码块/静态属性初始化 -> 普通代码块/普通属性初始化 -> 构造方法， 有多个按代码中的定义顺序
        A a = new A();
        A a1 = new A();
        A a2 = new A();
    }
}
