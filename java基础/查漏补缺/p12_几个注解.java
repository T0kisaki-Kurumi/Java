package java基础.查漏补缺;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Robbie
 * @since 2024/09/15
 */


/**
 * 四个常见的元注解：
 * 1. @Retention(RetentionPolicy.RUNTIME)：表示注解的生命周期
 *       RetentionPolicy.SOURCE：在编译期间丢弃，编译后就没有该注解
 *       RetentionPolicy.CLASS （默认值）：在类加载的时候丢弃，运行时没有该注解
 *       RetentionPolicy.RUNTIME：在运行期间保留，可以通过反射获取到该注解
 * 2. @Documented：表示注解是否应该被javadoc工具记录（注意如果要被记录，@Retention必须设置为 RetentionPolicy.RUNTIME）
 * 3. @Inherited：被这个注解修饰的注解具有继承性，即子类可以继承父类的注解
 * 4. @Target：表示注解可以应用到哪些程序元素上
 */


@Retention(
        RetentionPolicy.RUNTIME
//        RetentionPolicy.CLASS
//        RetentionPolicy.SOURCE
)
@Documented
@Inherited
@Target({
//        ElementType.TYPE,  // 表示该注解可以应用到类、接口、枚举、注解类型等上
//        ElementType.FIELD,  // 表示该注解可以应用到字段上
//        ElementType.METHOD,  // 表示该注解可以应用到方法上
//        ElementType.PARAMETER,  // 表示该注解可以应用到方法参数上
//        ElementType.CONSTRUCTOR,  // 表示该注解可以应用到构造器上
//        ElementType.LOCAL_VARIABLE,  // 表示该注解可以应用到局部变量上
//        ElementType.ANNOTATION_TYPE,  // 表示该注解可以应用到注解类型上（注解注解的注解叫做元注解）
//        ElementType.PACKAGE,   // 表示该注解可以应用到包上
//        ElementType.TYPE_PARAMETER,  // 表示该注解可以应用到（泛型的）类型参数上, Java 1.8 及以上版本支持
        ElementType.TYPE_USE  // 表示该注解可以应用到（任何地方的）类型使用上, Java 1.8 及以上版本支持。特例见下面的例子
})
@interface MyAnnotation {

}

public class p12_几个注解 {
    public static void main(String[] args) {
        @MyAnnotation String str = "Hello World";
//        @MyAnnotation java.lang.String str2 = "Hello World";  // 报错
        java.lang. @MyAnnotation String str3 = "Hello World";  // 正确写法
        java.  lang. String str4 = "Hello World"; // 注意.后面正常情况下也可以加空格的
    }

    @SuppressWarnings("all")  // 告诉编译器忽略，all表示所有警告，还有更具体的警告类型
    public void func(){}
}
