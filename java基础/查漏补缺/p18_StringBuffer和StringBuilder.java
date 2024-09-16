package java基础.查漏补缺;

/**
 * @author Robbie
 * @since 2024/09/16
 */

public class p18_StringBuffer和StringBuilder {
    public static void main(String[] args) {
        // StringBuffer维护的value数组并不是final的，而且是存放在堆内存中的
        // StringBuffer和String的转换
        StringBuffer sb1 = new StringBuffer("hello");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("hello");

        String s1 = sb1.toString();
        String s2 = new String(sb2);

        // append方法
        StringBuffer sb3 = new StringBuffer("123");
        sb3.append((String)null); // append底层很神奇，如果append一个null，底层调用appendNull方法，会将null转换为字符串"null"加入
        sb3.append(456);
        System.out.println(sb3);

        // StringBuilder和StringBuffer都继承了AbstractStringBuilder，
        // 但是StringBuilder是非线程安全的，效率比StringBuffer高，用在单线程的环境下。表现为：StringBuffer很多方法都加了synchronized关键字，而StringBuilder没有。

    }
}
