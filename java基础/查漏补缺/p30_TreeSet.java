package java基础.查漏补缺;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Robbie
 * @since 2024/09/24
 */

public class p30_TreeSet {
    public static void main(String[] args) {
        // TreeSet底层使用了TreeMap
        // 无参时，调用自带的compareTo方法
        TreeSet tset1 = new TreeSet();
        tset1.add("apple");
        tset1.add("banana");
        tset1.add("orange");
        tset1.add("pear");
        tset1.add("grape");
//        tset1.add(null); // null不能添加，因为不好比较。添加后报NPE
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

        TreeSet tset3 = new TreeSet((o1, o2) -> ((String)o1).length() - ((String)o2).length());
//        TreeSet tset3 = new TreeSet(Comparator.comparingInt(o -> ((String) o).length())); // 升序可以直接这样写
        tset3.add("apple");
        tset3.add("banana");
        tset3.add("orange"); // 注意这边不会加入orange。因为底层只会替换调用TreeMap的put方法，只会替换值不会替换键。相当于是PRESENT替换了PRESENT。
        System.out.println(tset3);
    }
}
