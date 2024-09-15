package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/15
 */

public class p16_三元运算符的一个细节 {
    public static void main(String[] args) {
        Object obj = true ? 1 : 2.0;
        System.out.println(obj); // 输出 1.0，因为三元运算符看作一个整体，java会选择更高的类型作为结果类型
    }
}
