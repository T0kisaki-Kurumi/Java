package java基础.查漏补缺;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Robbie
 * @since 2024/09/20
 */

class MyClass {
    @Override
    public int hashCode() {
        return 93029210; // 字符串"apple"的哈希值
    }

    @Override
    public String toString() {
        return "MyObject";
    }
}

class MyClassEquals {
    @Override
    public int hashCode() {
        return 12345678; // 字符串"apple"的哈希值
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public String toString() {
        return "MyObjectEquals";
    }
}

public class p26_HashSet {
    public static void main(String[] args) {
        // hashSet的底层使用了hashMap
        // Set并不保证元素的顺序，其内部是按照元素的哈希值来排序的。
        // null的哈希值默认为0
        // 如果发生哈希碰撞，按照加入的先后顺序排序
        Set set = new HashSet();
        set.toString();
        set.add(2);
        set.add(1);
        set.add(-1);
        set.add(3);
        set.add(4);
        set.add(0);
        System.out.println(set);
        set.add(null);
        System.out.println(set);
        set.remove(null);
        System.out.println(set);
        System.out.println(set.add(0)); // add返回是否添加成功

        System.out.println("--------------------------------------");

        Set set1 = new HashSet();
        set1.add("apple");
        System.out.println("apple:\t" + "apple".hashCode());
        set1.add("banana");
        System.out.println("banana:\t" + "banana".hashCode());
        set1.add("orange");
        System.out.println("orange:\t" + "orange".hashCode());
        set1.add("pineapple");
        System.out.println("pineapple:\t" + "pineapple".hashCode());
        set1.add("grape");
        System.out.println("grape:\t" + "grape".hashCode());
        set1.add(null);
        // 如果发生哈希碰撞，按照加入的先后顺序排序
        set1.add(new MyClass());
        System.out.println(set1);

        System.out.println("--------------------------------------");

        // 避免多个重复元素加入的底层机制：hashCode相同时，再用equals方法判断是否重复，如果还重复就不加入
        // 简单来说 hashCode和equals方法都相同时，才会插入失败
        Set set2 = new HashSet();
        set2.add(new MyClass());
        set2.add(new MyClass());
        set2.add(new MyClassEquals());
        set2.add(new MyClassEquals());
        set2.add(new String("apple"));
        set2.add(new String("apple"));
        System.out.println(set2);
    }
}
