package java基础.查漏补缺;

import java.io.FileNotFoundException;

/**
 * @author Robbie
 * @since 2024/09/15
 */

class MyException extends FileNotFoundException {
}

class Father {
    public void method() throws FileNotFoundException {
//        throw new RuntimeException(); // 运行时异常方法后面可以不用写throws，默认会抛出
        throw new FileNotFoundException();  // 编译异常如果不用try-catch处理，必须在方法后面加上相应的throws
    }
}

class Son1 extends Father {
    // 子类重写父类方法，如果涉及到异常抛出，只能抛出父类中相同的异常或这个异常的子类，并且要加上throws
    @Override
    public void method() throws MyException {
        throw new MyException();
    }
}

//class Son2 extends Father {
//    // 不能抛出其他编译异常
//    @Override
//    public void method() throws FileAlreadyExistsException {
//        throw new FileAlreadyExistsException();
//    }
//}

class Son3 extends Father {
    // 特别的，如果抛出运行时异常，不会有任何问题，因为运行时异常默认抛出，也就是说父类不是没抛出，而是默认抛出了，子类当然可以直接抛出
    @Override
    public void method() {
        throw new RuntimeException();
    }
}

public class p13_异常 {
    public static void main(String[] args) {
        // 五大运行时异常
        // 1. NullPointerException - 空指针异常
        try {
            String str = null;
            System.out.println(str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. IndexOutOfBoundsException - 数组越界异常
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. ClassCastException - 类型转换异常
        try {
            Object i = 1;
            String str = (String) i;
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4. ArithmeticException - 算术异常
        try {
            int a = 10 / 0;
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 5. NumberFormatException - 数字格式异常
        try {
            String str = "abc";
            int num = Integer.parseInt(str);
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
