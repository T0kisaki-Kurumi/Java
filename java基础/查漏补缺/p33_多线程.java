package java基础.查漏补缺;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Robbie
 * @since 2024/09/26
 */

class p33_MyThread extends Thread {


    public p33_MyThread() {
    }

    public p33_MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + " ： " + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class p33_MyRun implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // getName是Thread的方法，实现Runnable接口时如果要调用必须借助Thread类
            System.out.println(Thread.currentThread().getName() + " ： " + i);
            try {
                // sleep也是Thread的方法
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class p33_MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        Thread.sleep(2000);
        return sum;
    }
}

public class p33_多线程 {
    public static void main(String[] args) {
    }

    // 继承Thread类创建线程
    @Test
    public void test1() throws InterruptedException {
        p33_MyThread t1 = new p33_MyThread();
        p33_MyThread t2 = new p33_MyThread("thread2"); // 直接使用构造函数也能设置线程名
        t1.setName("thread1"); // 设置线程名
//        t2.setName("thread2");
//        t1.run();
        t1.start(); // 要调用start方法启动线程，如果调用了run方法，就只是一个普通的方法
        t2.start();
        t1.join(); // join方法可以等待线程执行完毕，如果是main里面就不需要写了，因为main线程会等待所有非守护线程执行完毕
        t2.join();
    }

    // 实现Runnable接口创建线程。优点：可以继承其他类
    @Test
    public void test2() throws InterruptedException {
        p33_MyRun r1 = new p33_MyRun();
        Thread t1 = new Thread(r1);
        t1.start();
        t1.join();
    }

    // 利用Callable接口和Future接口创建线程。优点：可以返回值，可以抛出异常
    @Test
    public void test3() throws InterruptedException, ExecutionException, TimeoutException {
        p33_MyCallable c1 = new p33_MyCallable();
        // 需要创建Future接口的实现类FutureTask类型的对象
        FutureTask<Integer> ft = new FutureTask<>(c1);
        Thread t1 = new Thread(ft); // 注意传入的是FutureTask对象，而不是MyCallable对象
        t1.start();
//        t1.join();

        // 获取返回值
        Integer result = ft.get(); // 无参的get方法会一直等待，因此上面不需要写join方法了
//        Integer result = ft.get(1, TimeUnit.SECONDS); // 带超时时间的get方法，超时后会抛出TimeoutException
        System.out.println("结果：" + result);
    }
}
