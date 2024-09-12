package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/12
 */

public class p3_二维数组声明 {
    public static void main(String[] args) {
        int[][] arr = new int[3][4];

        // 二维数组每一行的元素个数可以不同
        int[][] arr2 = new int[3][];
        for (int i = 0; i < 3; i++) {
            arr2[i] = new int[i + 1];
        }
    }
}
