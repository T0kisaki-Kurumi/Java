package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/12
 */

public class p1_数据类型 {
    public static void main(String[] args) {
        // 小数默认是double类型，如果要float类型必须加上f或F
//        float f1 = 3.14;
        float f2 = 3f;
        float f3 = .14f;

        // 科学计数法
        float f4 = 5.12e3f;
        float f5 = 5.12e-3f;

        // 精度损失
        double d1 = 2.7;
        double d2 = 8.1 / 3.0;
        System.out.println(d1);
        System.out.println(d2);

        // char类型2个字节，可以存放汉字
        char c1 = '中';
    }
}
