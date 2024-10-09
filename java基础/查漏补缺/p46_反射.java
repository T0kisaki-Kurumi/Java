package java基础.查漏补缺;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Robbie
 * @since 2024/10/08
 */

class Cat {
    public String name = "Tom";

    public void cry() {
        System.out.println("喵喵叫");
    }

    public void test_performance() {
//        double i = Math.random();
    }
}

public class p46_反射 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> clazz = Class.forName("java基础.查漏补缺.Cat"); // 通过全类名获取类对象
        Object obj = clazz.newInstance(); // 通过类对象创建实例对象
        System.out.println(obj.getClass());
        Method method = clazz.getDeclaredMethod("cry"); // 通过类对象获取方法对象
        method.invoke(obj); // 通过方法对象调用方法
        Field field = clazz.getField("name"); // 通过类对象获取属性对象
        System.out.println(field.get(obj)); // 通过属性对象获取属性值
        Constructor constructor = clazz.getConstructor(); // 通过类对象获取构造方法对象
//        Constructor constructor = clazz.getDeclaredConstructor(); // 通过类对象获取构造方法对象
        System.out.println(constructor);
    }

    @Test
    // 反射性能测试和优化
    public void test1() throws Exception {
        final int COUNT = 100000000;
        Cat cat1 = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            cat1.test_performance();
        }
        long end = System.currentTimeMillis();
        System.out.println("普通调用耗时：" + (end - start) + "ms");

        Class<?> clazz1 = Class.forName("java基础.查漏补缺.Cat");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getMethod("test_performance");
        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            method1.invoke(obj1);
        }
        end = System.currentTimeMillis();
        System.out.println("反射调用耗时：" + (end - start) + "ms");

        Class<?> clazz2 = Class.forName("java基础.查漏补缺.Cat");
        Object obj2 = clazz2.newInstance();
        Method method2 = clazz2.getMethod("test_performance");
        method2.setAccessible(true);
        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            method2.invoke(obj2);
        }
        end = System.currentTimeMillis();
        System.out.println("关闭访问检查后反射调用耗时：" + (end - start) + "ms");
    }
}
