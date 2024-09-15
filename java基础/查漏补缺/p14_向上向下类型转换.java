package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/15
 */

class p14_Parent {
    public String name = "父类属性";

    public void func1(){
        System.out.println("父类 func1()");
    }

    public void func2(){
        System.out.println("父类 func2()");
    }
}

class p14_Child extends p14_Parent {
    public String name = "子类属性";

    @Override
    public void func1() {
        System.out.println("子类 func1()");
    }

    public void func3(){
        System.out.println("子类 func3()");
    }
}

class p14_AnotherChild extends p14_Parent {
    public void func3(){
        System.out.println("另一个子类 func3()");
    }
}

public class p14_向上向下类型转换 {
    public static void main(String[] args) {
        p14_Parent parent = new p14_Child(); // 多态就是一种向上类型转换，本质上是父类的引用指向子类的对象
        parent.func1();
        parent.func2();
//        parent.func3(); // 但是不能直接调用子类独有的方法，只能调用父类中有的方法

        ((p14_Child) parent).func3(); // 如果一定要用，需要进行向下类型转换，通过强制类型转换将父类引用转换为子类引用
//        ((p14_AnotherChild) parent).func3(); // 注意只能向下转换成它原本的运行时类型，不能转换成其他子类，否则报运行时异常 ClassCastException

        // 但注意：向下类型转换只能转换引用，不能转换对象，即如果一个对象的运行时类型就是父类，那么是不能用向下类型转换的
//        p14_Parent parent1 = new p14_Parent();
//        ((p14_Child) parent1).func3();  // 报运行时异常 ClassCastException

        // 比较反逻辑的一个地方：成员变量（属性）看编译类型而不是运行类型
        System.out.println(parent.name);

        // instanceof 判断的是运行时类型 是否是某个类的类型或其子类类型
        System.out.println(parent instanceof p14_Parent);
        System.out.println(parent instanceof p14_Child);
    }
}
