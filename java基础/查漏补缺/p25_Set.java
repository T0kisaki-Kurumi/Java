package java基础.查漏补缺;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Robbie
 * @since 2024/09/20
 */

public class p25_Set {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(1);
        set.add(null);
        set.add("hello");
        System.out.println(set);

        System.out.println(set.remove(1));
        System.out.println(set.remove(1)); // remove一个不存在的元素并不会发生什么，只会返回false
        System.out.println(set.remove(null));
        System.out.println(set);

        System.out.println(set.contains("hello"));
    }
}
