package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/15
 */

public class p15_包装类型 {
    public static void main(String[] args) {
        // jdk1.5之前的装箱拆箱需要手动进行，jdk1.5之后可以自动进行
        // 装箱: Integer i1 = 10 等价于：
        Integer i1 = Integer.valueOf(10);
        // 拆箱: int i2 = i1 等价于：
        int i2 = i1.intValue();

        // 其他几种基本数据类型的装箱拆箱
        Byte b1 = Byte.valueOf((byte) 1);
        byte b2 = b1.byteValue();

        Short s1 = Short.valueOf((short) 1);
        short s2 = s1.shortValue();

        Long l1 = Long.valueOf(1L);
        long l2 = l1.longValue();

        Float f1 = Float.valueOf(1.0f);
        float f2 = f1.floatValue();

        Double d1 = Double.valueOf(1.0);
        double d2 = d1.doubleValue();

        Boolean bool1 = Boolean.valueOf(true);
        Boolean bool2 = bool1.booleanValue();

        Character c1 = Character.valueOf('A');
        char c2 = c1.charValue();

        // 基本数据类型到String的转换，以Integer为例
        int i3 = 1;
//        String str1 = i3.toString(); // 这个方法比较垃圾，要求必须是包装类型
        String str2 = i3 + "";
        String str3 = String.valueOf(i3);

        // String到基本数据类型的转换
        String str4 = "1";
        int i4 = Integer.valueOf(str4);
        int i5 = Integer.parseInt(str4); // 比valueOf好，因为valueOf返回包装类型，还需要拆箱
        int i6 = new Integer(str4);

        // Integer创建机制：
        // valueOf代码：
//        public static Integer valueOf(int i) {
//            if (i >= Integer.IntegerCache.low && i <= Integer.IntegerCache.high)
//                return Integer.IntegerCache.cache[i + (-Integer.IntegerCache.low)];
//            return new Integer(i);
//        }
        // -128到127的Integer对象会被缓存，避免频繁创建对象, 范围外才new
        Integer i7 = 1;
        Integer i8 = 1;
        Integer i9 = 128;
        Integer i10 = 128;
        System.out.println(i7 == i8);
        System.out.println(i9 == i10);

        // 但是只要有基本数据类型，判断的就是值是否相等，而不是引用是否相等！！
        int i11 = 1;
        int i12 = 128;
        System.out.println(i7 == i11);
        System.out.println(i9 == i12);
    }
}
