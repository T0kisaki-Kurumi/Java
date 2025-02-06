package java基础.java8_17;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Robbie
 * @since 2024/11/11
 */

public class p4_var {
    public static void main(String[] args) {
        var x = 10;
        for(var i = 0; i < 10; i++) {
            System.out.println(i);
        }
        HashMap<String, Integer> map = new HashMap<>();
        Set<Map.Entry<String, Integer>> entry1 = map.entrySet();
        var entry2 = map.entrySet();

        // 不能使用的情况
        int[] arr1 = {1,2,3};
//        var arr2 = {4,5,6}; // 无法推断类型
    }
}
