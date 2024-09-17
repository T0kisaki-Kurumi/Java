package java基础.查漏补缺;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Robbie
 * @since 2024/09/17
 */

public class p21_日期类 {
    public static void main(String[] args) throws ParseException {
        // 第一代日期Date类
        // 注意导入的是java.util.Date类
        Date d1 = new Date(); // 获取当前日期和时间
        System.out.println(d1); // 直接输出，格式类似于：Tue Sep 17 10:52:22 CST 2024
        // 如果需要格式化输出，可以使用SimpleDateFormat类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss E");
        System.out.println(sdf.format(d1));
        // 还可以把字符串根据自定义的解析规则转为Date对象
        String s = "2024年09月17日 19:00:34 星期二";
        Date d2 = sdf.parse(s);
        System.out.println(d2);

        System.out.println("------------------------------------------------------------------------------");

        // 第二代日期时间Calendar类
        Calendar c = Calendar.getInstance(); // 获取当前日期和时间
        System.out.println(c); // 直接输出会包含各种信息
        // Calendar类没有专门的格式化方法，需要用下面的方法获得后，自己写格式化方法
        System.out.println("年：" + c.get(Calendar.YEAR));
        System.out.println("月：" + (c.get(Calendar.MONTH) + 1)); // 月份从0开始，所以要加1
        System.out.println("日：" + c.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + c.get(Calendar.HOUR_OF_DAY)); // HOUR_OF_DAY是24小时制，HOUR是12小时制
        System.out.println("分：" + c.get(Calendar.MINUTE));
        System.out.println("秒：" + c.get(Calendar.SECOND));
        System.out.println("星期：" + c.get(Calendar.DAY_OF_WEEK)); // 星期从1开始，1代表星期日

        System.out.println("------------------------------------------------------------------------------");

        // 第三代日期时间:LocalDate(日期/年月日)、LocalTime(时间/时分秒)、LocalDateTime(日期时间/年月日时分秒)JDK8加入
        // 前两代缺点：线程不安全、Calendar类不方便格式化、不能处理闰秒等等
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println("年：" + ldt.getYear());
        System.out.println("月：" + ldt.getMonthValue());
        System.out.println("日：" + ldt.getDayOfMonth());
        System.out.println("时：" + ldt.getHour());
        System.out.println("分：" + ldt.getMinute());
        System.out.println("秒：" + ldt.getSecond());
        System.out.println("星期：" + ldt.getDayOfWeek().getValue()); // 星期从1开始，1代表星期日
        // 格式化使用DateTimeFormatter类
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
        String formattedDate = formatter.format(ldt);
        System.out.println(formattedDate);

        // 第三代日期时间戳：Instant类，可以精确到纳秒级别
        Instant ist = Instant.now();
        System.out.println(ist);
        System.out.println(ist.getNano()); // 纳秒部分
        // 可以和Date互相转换
        Date date = Date.from(ist);
        System.out.println(date);
        Instant instant = date.toInstant();
        System.out.println(instant);

        // LocalDateTime可以进行加减计算
        LocalDateTime ldt2 = LocalDateTime.now();
        System.out.println("当前日期时间：" + ldt2);
        LocalDateTime ldt3 = ldt2.plusDays(365); // 加365天
        System.out.println("加365天后的日期时间：" + ldt3);
        LocalDateTime ldt4 = ldt2.minusMonths(2); // 减2个月
        System.out.println("减2个月后的日期时间：" + ldt4);
    }
}
