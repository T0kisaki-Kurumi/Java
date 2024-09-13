package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/13
 */

class p8_A {
    // 非静态final变量初始化时机：定义时、构造器中、非静态代码块中
    public final int AGE;
    // 静态final变量初始化时机：定义时、静态代码块中
    public static final int SALARY;

    static {
//        AGE = 25;
        SALARY = 5000;
    }

    {
        AGE = 25;
//        SALARY = 5000;
    }
}

class p8_B {
    public final static int AGE = 18;
    public static int SALARY = 3000;

    static {
        System.out.println("执行静态代码块");
    }
}

public class p8_final关键字 {
    public static void main(String[] args) {
        // 包装类都是final类
        Integer i = 10;
        Character c = 'A';
        String s = "Hello";

        // final和static建议一起使用，因为底层做了优化，一起用的时候编译器会在编译时将这些常量的值直接替换到使用它们的地方，而不会进行类的初始化，也就不会执行static代码块
        // 而只用static的时候会正常执行static代码块
        System.out.println(p8_B.AGE);
//        System.out.println(p8_B.SALARY);
    }
}
