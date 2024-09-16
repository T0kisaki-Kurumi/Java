package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/16
 */

public class p18_StringBuffer {
    public static void main(String[] args) {
        // StringBuffer维护的value数组并不是final的，而且是存放在堆内存中的
        // StringBuffer和String的转换
        StringBuffer sb1 = new StringBuffer("hello");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("hello");

        String s1 = sb1.toString();
        String s2 = new String(sb2);
    }
}
