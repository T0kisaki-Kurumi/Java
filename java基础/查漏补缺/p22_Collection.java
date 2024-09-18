package java基础.查漏补缺;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Robbie
 * @since 2024/09/18
 */

public class p22_Collection {
    public static void main(String[] args) {
        // 用实现子类ArrayList演示Collection接口的常用方法
        Collection list = new ArrayList();
        list.add(1);
        list.add("hello");
        list.add(true);
        list.add(null);
        list.add(1);
        System.out.println(list); // toString方法重写过，可以直接输出集合元素

        list.remove(1);  // Collection接口只有按元素删除的remove方法
        System.out.println(list);

        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(1);
        System.out.println(list1);
        list1.remove(1); // List接口有按index删除的remove方法,传入字面量的时候调用
        list1.remove(new Integer(1)); // 传入其他类型的时候调用的仍然是按元素删除的方法
        System.out.println(list1);
    }
}
