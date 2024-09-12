package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/12
 */

class rubbish {
    String name;

    public rubbish(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("销毁垃圾" + name);
    }

}

public class p6_finalize {
    public static void main(String[] args) {
        rubbish r1 = new rubbish("r1");
        // 当某个对象没有任何引用的时候，jvm认为其是一个垃圾，在gc的时候会调用finalize方法回收
        r1 = null;
        // 正常在程序中，我们不需要手动调用gc，jvm会自动进行垃圾回收。这里只是为了演示finalize方法的调用
        System.gc();
    }
}
