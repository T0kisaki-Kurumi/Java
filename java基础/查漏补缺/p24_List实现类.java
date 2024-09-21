package java基础.查漏补缺;

import java.lang.reflect.Field;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author Robbie
 * @since 2024/09/19
 */

public class p24_List实现类 {
    private static int getCapacity(List list) throws Exception {
//        return list.elementData.length; // elementData是default权限，显然并不行
        // elementData定义：transient Object[] elementData;  // transient关键字表示该字段不会被序列化
        Field field = list.getClass().getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] elementData = (Object[]) field.get(list);
        return elementData.length;
    }

    private static int getModCount(List list) throws Exception {
        Field field = AbstractList.class.getDeclaredField("modCount");
//        Field field = list.getClass().getDeclaredField("modCount");  // 不能直接用list对象或者ArrayList类来获取modCount，因为是在AbstractList中定义的。反射的原理是从字节码文件里面获取Field，但ArrayList的字节码文件并没有定义modCount字段。
        field.setAccessible(true);
        int modCount = (int) field.get(list);
        return modCount;
    }

    public static void main(String[] args) throws Exception {
        // ArraryList不保证线程安全
        // 未指定初始容量，默认容量为0，加入第一个元素后变为10，扩容时变为1.5倍
        ArrayList list1 = new ArrayList();
        System.out.println("初始容量：" + getCapacity(list1));
        for(int i=0; i<20; ++i){
            list1.add(i);
            System.out.println(i + " 容量：" + getCapacity(list1) + " modCount：" + getModCount(list1));
        }
        System.out.println("--------------------------------------");
        // 指定初始容量，扩容时候直接变为1.5倍
        ArrayList list2 = new ArrayList(8);
        System.out.println("初始容量：" + getCapacity(list2));
        for(int i=0; i<20; ++i){
            list2.add(i);
            System.out.println(i + " 容量：" + getCapacity(list2) + " modCount：" + getModCount(list2));
        }

        System.out.println("--------------------------------------");

        // Vector是线程安全的，底层使用synchronized关键字保证线程安全。
        // 未指定初始容量，直接就是10，没有和ArrayList一样加入一个元素后才变成10的过程。扩容时变为2倍
        Vector vec1 = new Vector();
        System.out.println("初始容量：" + getCapacity(vec1));
        for(int i=0; i<20; ++i){
            vec1.add(i);
            System.out.println(i + " 容量：" + getCapacity(vec1) + " modCount：" + getModCount(vec1));
        }
        System.out.println("--------------------------------------");
        // 指定初始容量，扩容时变为2倍
        Vector vec2 = new Vector(8);
        System.out.println("初始容量：" + getCapacity(vec2));
        for(int i=0; i<20; ++i){
            vec2.add(i);
            System.out.println(i + " 容量：" + getCapacity(vec2) + " modCount：" + getModCount(vec2));
        }

        System.out.println("-------------------------------------");

        // LinkedList底层是双向链表，不保证线程安全
        LinkedList linklist = new LinkedList();
        linklist.add(1);
        linklist.add("Hello");
        linklist.add(null);
        System.out.println(linklist);
        linklist.remove(); // remove默认删除第一个元素，相当于remove(0)
        System.out.println(linklist);

    }
}
