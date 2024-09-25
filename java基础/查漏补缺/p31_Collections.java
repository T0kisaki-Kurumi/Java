package java基础.查漏补缺;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Robbie
 * @since 2024/09/24
 */

public class p31_Collections {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("world");
        list.add("hello");
        list.add("java");
        list.add("kotlin");
        list.add("python");
        list.add("python");
//        list.add(null);
        System.out.println(list);

        Collections.reverse(list); // 反转
        System.out.println(list);

        Collections.shuffle(list); // 随机排序
        System.out.println(list);

        Collections.sort(list); // 排序，底层是调用Comparable接口的compareTo方法
        System.out.println(list);

//        Collections.sort(list, (a, b) -> {
//            if (a == null && b == null) return 0;
//            if (a == null) return -1;
//            if (b == null) return 1;
//            return ((String) a).compareTo((String) b);
//        }); // 有null的情况下必须重写比较器
//        System.out.println(list);

        Collections.swap(list, 0, 1); // 交换两个元素
        System.out.println(list);

        System.out.println(Collections.max(list)); // 有null的情况下必须重写比较器。min同理

        System.out.println(Collections.frequency(list, "python")); // 统计元素出现的次数

//        List newList = new ArrayList(); // 直接定义一个会报IndexOutOfBoundsException，因为list的size为0
//        List newList = new ArrayList(list.size()); // 带capacity参数的构造方法也不行，虽然定义了capactiy，但是实际上size还是0
//        Collections.copy(newList, list);
//        System.out.println(newList);
        List newList = new ArrayList(list); // 在newList为新创建时，感觉不如直接用构造器
        System.out.println(newList);

        Collections.replaceAll(list, "python", "C++");
        System.out.println(list);
    }
}
