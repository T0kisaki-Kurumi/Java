package java基础.查漏补缺;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Robbie
 * @since 2024/09/16
 */

public class p19_Arrays相关方法 {
    public static void main(String[] args) {
        // 数组排序
        // 基本类型数组排序底层用的是 DualPivotQuicksort （一种有2个基准值的快速排序算法，称为“双轴快速排序”，而普通的快速排序算法只有一个基准值）
        int[] arr = {5, 2, 8, 3, 9, 1};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        // 但如果要自定义排序规则，必须使用包装类型，此时数组排序底层用的是 TimSort
        Integer[] arr2 = {5, 2, 8, 3, 9, 1};
        Arrays.sort(arr2, (a, b) -> b - a);
//        Arrays.sort(arr2, Comparator.reverseOrder());  // 这个比较像C++中的greater<int>()，一个封装好的Comparator
        System.out.println(Arrays.toString(arr2));
        // 引用类型数组排序底层用的是 TimSort (≈ 归并排序 + 插入排序)
        String[] strArr = {"apple", "pear", "orange", "grape", "banana"};
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));

        // 二分查找
        // 必须保证数组是升序排序的
        Integer[] arr3 = {1, 3, 5, 7, 9};
        System.out.println(Arrays.binarySearch(arr3, 5)); // 2
        // 若数组是降序排序，则结果不可靠
        Integer[] arr4 = {9, 7, 5, 3, 1};
        System.out.println(Arrays.binarySearch(arr4, 7));
        // 如果一定要降序，必须使用 Comparator.reverseOrder()或者自定义排序规则
        System.out.println(Arrays.binarySearch(arr4, 7, Comparator.reverseOrder()));

        // 元素复制
        Integer[] arr5 = {1, 3, 5, 7, 9};
        Integer[] arr6 = Arrays.copyOf(arr5, 10);
        System.out.println(Arrays.toString(arr6));

        // 填充元素
        Integer[] arr7 = {1, 3, 5, 7, 9};
        Arrays.fill(arr7, 114);
        System.out.println(Arrays.toString(arr7));

        // 数组比较是否相等
        Integer[] arr8 = {1, 3, 5, 7, 9};
        Integer[] arr9 = {1, 3, 5, 7, 9};
        System.out.println(Arrays.equals(arr8, arr9)); // true
        Object[] arr10 = {1, 3, 5, 7, 9};
        System.out.println(Arrays.equals(arr8, arr10));

        // 将一组数字转为数组
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
        System.out.println(list1);
    }
}
