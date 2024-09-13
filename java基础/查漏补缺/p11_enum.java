package java基础.查漏补缺;

import java.util.Arrays;

/**
 * @author Robbie
 * @since 2024/09/13
 */

/**
 * 枚举类都隐式地继承了java.lang.Enum类，因此枚举类可以像普通类一样定义构造方法、方法、变量、嵌套类等，但由于单继承机制，不能继承其他类，只能实现接口。
 * 枚举类中的对象实际上是一种语法糖，相当于public static final修饰
 * 如果有成员变量，要写在枚举对象的后面，否则编译器会报错
 */
enum Color {
    RED,
    GREEN,
    BLUE;
}

public class p11_enum {
    public static void main(String[] args) {
        Color green = Color.GREEN;
        System.out.println(green.name()); // 输出枚举对象的名称
        System.out.println(green.ordinal()); // 输出枚举对象的次序，从0开始
        System.out.println(Arrays.toString(Color.values()));
        Color color = Color.valueOf("RED"); // 根据名称获取枚举对象
        System.out.println(color.name());
//        Color color_e = Color.valueOf("PINK"); // 没有这个名称的枚举对象会报错

        Color color1 = Color.RED;
        Color color2 = Color.BLUE;
        System.out.println(color1 == color2);
        System.out.println(color1.compareTo(color2));  // 返回int值，实际上是color1.ordinal() - color2.ordinal()
    }
}
