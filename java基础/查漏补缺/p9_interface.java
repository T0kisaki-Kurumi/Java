package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/13
 */

interface p9_A {
    int A = 1; // 默认加public static final
}

public class p9_interface {
    public static void main(String[] args) {
        System.out.println(p9_A.A);
    }
}
