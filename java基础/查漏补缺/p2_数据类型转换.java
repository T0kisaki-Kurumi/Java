package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/12
 */

public class p2_数据类型转换 {
    public static void main(String[] args) {
        // 自动类型转换
        // 常量直接赋值是可以的，因为会先判断类型再赋值
        byte b1 = 100;
        // 变量赋值时不能大范围赋值给小范围
        int i1 = 100;
//        byte b2 = i1;
        // byte/short 虽然范围小于/等于char，但是也不能自动转换
//        char c2 = b1;
        // 但是byte/short/char可以参与运算，运算结果为int类型
        short s1 = 1;
        char c3 = 2;
//        short s2 = s1 + c3;
        int i2 = s1 + c3;
    }
}
