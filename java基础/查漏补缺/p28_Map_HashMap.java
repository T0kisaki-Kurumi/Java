package java基础.查漏补缺;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Robbie
 * @since 2024/09/23
 */

public class p28_Map_HashMap {
    @Test
    public void test1(){
        Map hashMap = new HashMap();
        hashMap.put("apple", 10);
        hashMap.put("orange", 20);
        hashMap.put("banana", 30);
        hashMap.put("pineapple", 40);
        hashMap.put(null, 50);
        hashMap.put(1, "aaa");
        hashMap.put("pineapple", 114); // key重复会替换，重复的本质仍然是：hash值相同，且是同一个对象或者equals为true

        System.out.println(hashMap);

        // HashMap使用的是静态内部类HashMap$Node类型，包含成员变量：hash、key、value、next。 HashMap$Node是Map$Entry的实现类。
        // 为了方便遍历，HashMap中有一个静态内部类HashMap$EntrySet，类型定义为：final class EntrySet extends AbstractSet<Map.Entry<K,V>>
        // 也就是说，HashMap$EntrySet也是一种Set，但泛型类型指定为Map.Entry<K,V>。由于HashMap$Node实现了Map$Entry，可以直接存入
        Set<Map.Entry> entrySet = hashMap.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
//        // 或者直接使用forEach方法遍历
//        hashMap.forEach((k, v) -> System.out.println(k + " : " + v));

        // 此外还有keySet()、values()方法，可以分别获取key集合（Set类型）和value集合（Collection类型）。
        Set keySet = hashMap.keySet();
        Collection collection = hashMap.values();
        System.out.println(keySet);
        System.out.println(collection);
    }

    @Test
    public void test2(){
        // 一些基本操作
        Map map = new HashMap();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put(null, 5);
        System.out.println(map);
        System.out.println("remove value: "+ map.remove("a"));; // 根据key删除元素
        System.out.println(map.remove("b", 2));; // 根据key和value删除元素
        System.out.println(map);
        Integer value = (Integer) map.get("b"); // 因为没有指定泛型，get方法返回Object类型，需要强转
        System.out.println(value);
        System.out.println(map.containsKey("c"));
        System.out.println(map.containsValue(4));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        map.clear();
        System.out.println(map.isEmpty());
    }

    public static void main(String[] args) {
    }
}
