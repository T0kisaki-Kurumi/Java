package java基础.查漏补缺;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @author Robbie
 * @since 2024/10/08
 */

class p46_Animal {
    public void walk() {
        System.out.println("动物走路");
    }
}

class Cat extends p46_Animal implements Cloneable, Serializable {
    protected static final int WEIGHT = 80;
    public String name = "Tom";
    private int age = 18;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    protected Cat(int age) {
        this.age = age;
    }

    private Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void cry() {
        System.out.printf("%d岁的%s喵喵叫\n", age, name);
    }

    private String eat(String food) throws NullPointerException {
        System.out.println("猫吃" + food);
        return food;
    }

    public void test_performance() {
//        double i = Math.random();
    }
}

public class p46_反射 {
    public static void main(String[] args) throws Exception {
        // 正常创建对象和用反射在底层都调用了ClassLoader的loadClass方法
//        Cat cat = new Cat();
        Class<?> clazz1 = Class.forName("java基础.查漏补缺.Cat");
        Class<?> clazz2 = Class.forName("java基础.查漏补缺.p46_Animal");
        String s = "abc";

        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz2.getClassLoader());
        System.out.println(String.class.getClassLoader());
        System.out.println(int.class.getClassLoader());
    }

    @Test
    // 反射获取Class对象的三种方式
    public void test1() throws Exception {
        Class<?> clazz1 = Class.forName("java基础.查漏补缺.Cat");// 通过全类名获取类对象
        Class<?> clazz2 = Cat.class;// 通过类对象获取类对象
        Cat cat = new Cat();
        Class<?> clazz3 = cat.getClass();// 通过实例对象获取类对象
        System.out.println(clazz1 == clazz2);// true
        System.out.println(clazz1 == clazz3);// true
    }

    @Test
    // 反射获取构造方法相关信息
    public void test2() throws Exception {
        Class<?> clazz = Class.forName("java基础.查漏补缺.Cat");
        Constructor[] constructors1 = clazz.getConstructors(); // 获取所有public的构造方法
        Constructor[] constructors2 = clazz.getDeclaredConstructors(); // 获取所有构造方法
        Constructor constructor1 = clazz.getConstructor(String.class); // 获取单个public的构造方法
        Constructor constructor2 = clazz.getDeclaredConstructor(String.class, int.class); // 获取单个任意的构造方法。注意基本类型也有字节码文件，不要写Integer.class
        System.out.println(Arrays.toString(constructors1));
        System.out.println(Arrays.toString(constructors2));
        System.out.println(constructor1);
        System.out.println(constructor2);

        // 获取权限修饰符，返回的是一个整数，具体含义见java.lang.reflect.Modifier类
        int modifiers1 = constructor1.getModifiers();
        int modifiers2 = constructor2.getModifiers();
        System.out.println(modifiers1); // 1 = Modifier.PUBLIC
        System.out.println(modifiers2); // 4 = Modifier.PROTECTED

        // 获取参数信息
        Parameter[] parameters1 = constructor1.getParameters();
        Parameter[] parameters2 = constructor2.getParameters();
        System.out.println(Arrays.toString(parameters1));
        System.out.println(Arrays.toString(parameters2));

        // 通过构造方法创建实例对象
        Cat cat1 = (Cat) constructor1.newInstance("Kitty");
        constructor2.setAccessible(true);
        Cat cat2 = (Cat) constructor2.newInstance("Kitty", 20);
        cat1.cry();
        cat2.cry();
    }

    @Test
    // 反射获取成员变量相关信息
    public void test3() throws Exception {
        Class<?> clazz = Class.forName("java基础.查漏补缺.Cat");
        Field[] fields1 = clazz.getFields(); // 获取所有public的成员变量
        Field[] fields2 = clazz.getDeclaredFields(); // 获取所有成员变量
        Field field1 = clazz.getField("name"); // 获取单个public的成员变量
        Field field2 = clazz.getDeclaredField("WEIGHT"); // 获取单个任意的成员变量
        System.out.println(Arrays.toString(fields1));
        System.out.println(Arrays.toString(fields2));
        System.out.println(field1);
        System.out.println(field2);
        int modifiers1 = field1.getModifiers();
        int modifiers2 = field2.getModifiers();
        System.out.println(modifiers1); // 1 = Modifier.PUBLIC
        System.out.println(modifiers2); // 28 = 16 + 8 + 4 = Modifier.FINAL + Modifier.STATIC + Modifier.PROTECTED

        // 获取成员变量的值
        Cat cat = new Cat();
        System.out.println(field1.get(cat)); // 注意获取到的都是Object类型
        field2.setAccessible(true);
        System.out.println(field2.get(cat));

        // 设置成员变量的值
        field1.set(cat, "Kitty");
        System.out.println(field1.get(cat));

        // 获取成员变量的类型
        Class<?> type1 = field1.getType();
        Class<?> type2 = field2.getType();
        System.out.println(type1);
        System.out.println(type2);
    }

    @Test
    // 反射获取方法相关信息
    public void test4() throws Exception {
        Class<?> clazz = Class.forName("java基础.查漏补缺.Cat");
        Method[] methods1 = clazz.getMethods(); // 获取所有public的方法（包括继承的）
        Method[] methods2 = clazz.getDeclaredMethods(); // 获取所有的方法（不包括继承的）
        Method method1 = clazz.getMethod("walk"); // 获取单个public的方法（包括继承的）
        Method method2 = clazz.getDeclaredMethod("eat", String.class); // 获取单个任意的方法（不包括继承的）
        System.out.println(Arrays.toString(methods1));
        System.out.println(Arrays.toString(methods2));
        System.out.println(method1);
        System.out.println(method2);

        // 获取权限修饰符，返回的是一个整数，具体含义见java.lang.reflect.Modifier类
        int modifiers1 = method1.getModifiers();
        int modifiers2 = method2.getModifiers();
        System.out.println(modifiers1); // 1 = Modifier.PUBLIC
        System.out.println(modifiers2); // 2 = Modifier.PRIVATE

        // 获取方法的名字
        System.out.println(method1.getName());

        // 获取方法的形参
        Parameter[] parameters1 = method1.getParameters();
        Parameter[] parameters2 = method2.getParameters();
        System.out.println(Arrays.toString(parameters1));
        System.out.println(Arrays.toString(parameters2));

        // 获取方法的返回值类型
        Class<?> returnType1 = method1.getReturnType();
        Class<?> returnType2 = method2.getReturnType();
        System.out.println(returnType1);
        System.out.println(returnType2);

        // 获取方法抛出的异常类型
        Class<?>[] exceptionTypes1 = method1.getExceptionTypes();
        Class<?>[] exceptionTypes2 = method2.getExceptionTypes();
        System.out.println(Arrays.toString(exceptionTypes1));
        System.out.println(Arrays.toString(exceptionTypes2));

        // 运行方法并返回结果
        method2.setAccessible(true);
        Object food = method2.invoke(clazz.newInstance(), "fish");
        System.out.println(food);

    }

    @Test
    // 反射获取类本身相关信息
    public void test5() throws Exception {
        Class<?> clazz = Class.forName("java基础.查漏补缺.Cat");
        System.out.println(clazz.getName()); // 全类名
        System.out.println(clazz.getSimpleName()); // 类名
        System.out.println(clazz.getPackage()); // 包名
        System.out.println(clazz.isArray()); // 是否是数组
        System.out.println(clazz.isInterface()); // 是否是接口
        System.out.println(clazz.isPrimitive()); // 是否是基本类型
        System.out.println(clazz.isEnum()); // 是否是枚举类型
        System.out.println(clazz.isAnnotation()); // 是否是注解类型
        System.out.println(clazz.isAnonymousClass()); // 是否是匿名类
        System.out.println(clazz.isLocalClass()); // 是否是局部类
        System.out.println(clazz.isMemberClass()); // 是否是成员类
        System.out.println(clazz.isSynthetic()); // 是否是合成类
        System.out.println(clazz.getSuperclass()); // 父类
        System.out.println(Arrays.toString(clazz.getInterfaces())); // 实现的接口

        // isAssignableFrom和instanceof的区别
        // 都可以判断一个类是否是另一个类或接口本身或其子类
        System.out.println(p46_Animal.class.isAssignableFrom(clazz)); // 父类.class.isAssignableFrom(子类.class)
        System.out.println(clazz.newInstance() instanceof p46_Animal); // 子类对象 isInstance 父类名

    }

    @Test
    // 反射性能测试和优化
    public void test6() throws Exception {
        final int COUNT = 100000000;
        Cat cat = new Cat();
        Class<?> clazz = cat.getClass();
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            cat.test_performance();
        }
        long end = System.currentTimeMillis();
        System.out.println("普通调用耗时：" + (end - start) + "ms");

        Method method = clazz.getMethod("test_performance");
        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            method.invoke(cat);
        }
        end = System.currentTimeMillis();
        System.out.println("反射调用耗时：" + (end - start) + "ms");

        method.setAccessible(true);
        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            method.invoke(cat);
        }
        end = System.currentTimeMillis();
        System.out.println("关闭访问检查后反射调用耗时：" + (end - start) + "ms");
    }

    @Test
    // 可以获取class对象的类型
    public void test7() throws Exception {

    }
}
