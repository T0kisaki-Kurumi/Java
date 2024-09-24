package java基础.查漏补缺;

import java.util.Hashtable;

/**
 * @author Robbie
 * @since 2024/09/23
 */

public class p29_Hashtable {
    public static void main(String[] args) {
        Hashtable table = new Hashtable();

//        table.put(null, 1);  // Hashtable不允许null键值对，否则抛NPE
//        table.put("a", null);  // Hashtable不允许null键值对，否则抛NPE

        // Hashtable线程安全，HashMap线程不安全
        table.put("a", 1);

        // Hashtable底层一直是数组+链表结构，不会树化。table的size初始为11，扩容时为原来的2倍再加1。扩容阈值也为size的0.75倍，初始为8，即插入第9个元素时，会扩容。
        Hashtable table2 = new Hashtable();
        table2.put("a", 1);
        table2.put("b", 2);
        table2.put("c", 3);
        table2.put("d", 4);
        table2.put("e", 5);
        table2.put("f", 6);
        table2.put("g", 7);
        table2.put("h", 8);
        table2.put("i", 9);
        table2.put("j", 10);
        table2.put("k", 11);
        table2.put("l", 12);
        table2.put("m", 13);
    }
}
