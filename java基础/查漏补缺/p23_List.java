package java基础.查漏补缺;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robbie
 * @since 2024/09/19
 */

public class p23_List {
    public static void main(String[] args) {
        List list = new ArrayList(); // 定义List集合
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(1, "d"); // 在索引1处插入元素
        list.add("a");

        System.out.println(list.get(1)); // 支持随机访问
        list.set(3, "cc"); // 修改元素
        System.out.println(list);
        System.out.println(list.indexOf("b")); // 查找元素第一次出现的索引
        System.out.println(list.indexOf("f")); // 不存在返回-1

        System.out.println(list.subList(2, 4)); // 子列表，左闭右开

        list.sort((o1, o2) -> ((String) o1).compareTo((String) o2));
//        list.sort(Comparator.comparing(o -> ((String) o))); // 相当于
        System.out.println(list);

    }
}
