package java基础.查漏补缺;

import java.util.LinkedHashSet;

/**
 * @author Robbie
 * @since 2024/09/23
 */

public class p27_LinkedHashSet {
    public static void main(String[] args) {
        // LinkedHashSet底层在插入的节点之间建立了双向链表，使得可以按照插入的顺序遍历
        // 底层实现是在HashMap的putVal方法的最后调用afterNodeInsertion方法，这个方法在HashSet中为空，而在LinkedHashSet中被重写（底层是HashMap的子类LinkedHashMap），用于建立双向链表
        // 底层table中使用的数据结构类型：
        // ① HashMap使用的是静态内部类HashMap$Node类型，包含成员变量：hash、key、value、next
        // ② LinkedHashMap使用的是内部类LinkedHashMap.Entry类型，包含成员变量：hash、key、value、next、before、after
        // after和next的区别：after是插入顺序的下一个，next是链表中真正挂在当前元素之后的下一个元素
        LinkedHashSet lset = new LinkedHashSet();
        lset.add(1);
        lset.add("Hello");
        lset.add(true);
        lset.add(3.14f);
        lset.add(null);
        lset.add(new Integer(2));
        System.out.println(lset);

        // 为了方便遍历，
    }
}
