package java基础.查漏补缺;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Robbie
 * @since 2024/09/17
 */

public class p20_BigInteger和BigDecimal {
    public static void main(String[] args) {
        BigInteger i1 = new BigInteger("111111111111111111111111111");
        BigInteger i2 = new BigInteger("2222222222222222222222222222222");
        BigInteger i3 = BigInteger.valueOf(3);
        // 加减乘除不能直接用运算符
        System.out.println(i1.add(i2));
        System.out.println(i1.multiply(i3));

        BigDecimal b1 = new BigDecimal("3.1415");
        BigDecimal b2 = new BigDecimal("13");
//        System.out.println(b1.divide(b2)); // 没有设置舍入模式，如果除不尽，会报错
        System.out.println(b1.divide(b2, BigDecimal.ROUND_CEILING));  //ROUND_CEILING表示向上取整，位数和被除数保持一致
    }
}
