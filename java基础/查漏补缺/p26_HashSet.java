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

class TreeifyTest {
    int x;

    public TreeifyTest(int x) {
        this.x = x;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

public class p26_HashSet {
    static void test1(){
        // hashSet的底层使用了hashMap
        // add的时候实际上调用了hashMap的put方法，其中key是元素本身，value是一个占位常量PRESENT（Object类型）
        // Set并不保证元素的顺序，排序方法：
        //     ① 根据hashCode方法计算哈希值
        //     ② 通过hashMap的hash方法再计算一个更加均匀的哈希值：hash = key.hashCode() ^ (key.hashCode() >>> 16)
        //     ③ 计算table中真正的索引位置：index = (n - 1) & hash，其中n是table的大小,因为n是16的倍数，n-1恰好是一个全1的二进制数
        // 注意一个细节！resize的时候判断一个节点在table上新的index，用的是n & hash，而不是n-1 & hash，此时n是一个只有一个1的二进制数，用来判断rehash后某节点在原地(j)还是在新的位置(j+oldCap)
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

        // 避免多个重复元素加入的底层机制：hashCode相同时，再用==（是否真的是同一个对象）和equals（是否满足equals）方法判断是否重复，如果还重复就不加入
        // 简单来说 hashCode和equals方法都相同时，才会插入失败
        // 但hashMap底层保存的并不是hashCode方法得到的哈希值，而是 key.hashCode() ^ (key.hashCode() >>> 16)，这样做为了使哈希更加均匀
        Set set2 = new HashSet();
        set2.add(new MyClass());
        set2.add(new MyClass());
        set2.add(new MyClassEquals());
        set2.add(new MyClassEquals());
        set2.add(new String("apple"));
        set2.add(new String("apple"));
        System.out.println(set2);
    }

    static void test2(){
        Set<TreeifyTest> set = new HashSet();
        // java8中，hashMap的table中某个节点的链表(不算第一个节点)的元素个数达到TREEIFY_THRESHOLD（8）时（如果算上第一个节点则为9个），会调用treeifyBin方法：
        // ① 如果table的大小小于MIN_TREEIFY_CAPACITY（64），则调用resize方法扩容为原来的2倍。此外，table大小小于64时，
        //    hashMap中键值对的个数达到阈值时，也会调用resize扩容。阈值为当前table大小的0.75倍。
        // ② 如果table的大小大于等于MIN_TREEIFY_CAPACITY（64），则将链表转换为红黑树。注意转换为红黑树这个操作只对当前这个链表进行，也就是说加入元素后导致某个链表的元素个数达到8时，
        //    对这个链表调用treeifyBin方法进行树化
        // table的默认大小为16
        for(int i=1;i<=12;i++){
            set.add(new TreeifyTest(i));
        }
        set.add(new TreeifyTest(13));
    }

    static void test3(){
        class HashFather{
            public int x;
        }
        class Hash16 extends HashFather{
            public Hash16(int x){
                this.x = x;
            }

            @Override
            public int hashCode() {
                return 16; // 二进制00010000
            }
        }
        class Hash32 extends HashFather{
            public Hash32(int x){
                this.x = x;
            }

            @Override
            public int hashCode() {
                return 32; // 二进制00100000
            }
        }
        Set<HashFather> set = new HashSet<>();
        for(int i=1; i<=4; ++i){
            set.add(new Hash16(i));
        }
        for(int i=1; i<=4; ++i){
            set.add(new Hash32(i));
        }
        // 发生rehash
        set.add(new Hash16(5));
        set.add(new Hash16(6));
        set.add(new Hash16(7));
        set.add(new Hash16(8));
        set.add(new Hash16(9));
    }

    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
    }
}
