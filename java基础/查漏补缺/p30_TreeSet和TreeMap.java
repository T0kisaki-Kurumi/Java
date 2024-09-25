package java基础.查漏补缺;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Robbie
 * @since 2024/09/24
 */

public class p30_TreeSet和TreeMap {
    public static void main(String[] args) {
        // TreeSet底层使用了TreeMap，TreeMap基于红黑树
        // 无参时，调用自带的compareTo方法
        // 加入第一个元素时，会调用compare(key, key)来判断是否为空，除非自定义了Comparator，否则会报NPE
        TreeSet tset1 = new TreeSet();
        tset1.add("apple");
        tset1.add("banana");
        tset1.add("orange");
        tset1.add("pear");
        tset1.add("grape");
        System.out.println(tset1);

        // 有参时，调用传入的Comparator匿名内部类重写的compare方法
        TreeSet tset2 = new TreeSet(((o1, o2) -> ((String)o2).compareTo((String)o1)));
//        TreeSet tset2 = new TreeSet((Comparator.comparing(o -> ((String) o)))); // 升序可以直接这样写
        tset2.add("apple");
        tset2.add("banana");
        tset2.add("orange");
        tset2.add("pear");
        tset2.add("grape");
        System.out.println(tset2);

        TreeSet tset3 = new TreeSet((o1, o2) -> ((String)o2).length() - ((String)o1).length());
//        TreeSet tset3 = new TreeSet(Comparator.comparingInt(o -> ((String) o).length())); // 升序可以直接这样写
        tset3.add("apple");
        tset3.add("banana");
        tset3.add("orange"); // 注意这边不会加入orange。因为底层只会替换调用TreeMap的put方法，只会替换值不会替换键。相当于是PRESENT替换了PRESENT。
        System.out.println(tset3);

        // TreeSet默认情况下不能加入null值，但是可以自定义比较器来处理null值
        TreeSet tset4 = new TreeSet((o1, o2) -> {
            if(o1 == null && o2 == null) return 0;
            if(o1 == null) return -1;
            if(o2 == null) return 1;
            return ((String)o1).compareTo((String)o2);
        });
        tset4.add(null);
        tset4.add("apple");
        tset4.add("banana");
        tset4.add(null);
        System.out.println(tset4);

        TreeMap tmap = new TreeMap((o1, o2) -> ((String)o2).length() - ((String)o1).length());
        tmap.put("apple", 1);
        tmap.put("banana", 2);
        tmap.put("orange", 114); // 注意这边不会加入orange，但是会把和orange长度一样的banana的 值 替换掉，因为底层compare/compareTo方法返回0时，会执行 t.setValue(value);
        System.out.println(tmap);
    }
}
