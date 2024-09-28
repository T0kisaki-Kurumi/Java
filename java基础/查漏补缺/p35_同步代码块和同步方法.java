package java基础.查漏补缺;

import org.junit.Test;

/**
 * @author Robbie
 * @since 2024/09/26
 */

class p35_MyRunnable implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            if (method()) {
                break;
            }
        }
    }

    synchronized boolean method() {
        if (count < 50) {
            System.out.println(Thread.currentThread().getName() + " : " + count++);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return false;
        } else {
            return true;
        }
    }
}

public class p35_同步代码块和同步方法 {
    static final Object lock = new Object();
    static int count = 0;

    public static void main(String[] args) {
    }

    @Test
    public void test1() throws InterruptedException {
        Runnable runnable = () -> {
            while (count < 50) {
                System.out.println(Thread.currentThread().getName() + " : " + count++);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        // 多个线程同时操作共享变量会出现问题
        Thread t_unsafe1 = new Thread(runnable);
        Thread t_unsafe2 = new Thread(runnable);
        Thread t_unsafe3 = new Thread(runnable);
        t_unsafe1.start();
        t_unsafe2.start();
        t_unsafe3.start();
        t_unsafe1.join();
        t_unsafe2.join();
        t_unsafe3.join();
    }

    @Test
    public void test2() throws InterruptedException {
        Runnable runnable = () -> {
            // 不要把while和synchronized位置写反，不然会导致一个线程干所有活，因为Thread.sleep()不会释放锁
            while (true) {
                synchronized (lock) {
//                synchronized (p35_同步代码块.class) { // 常见操作：用当前类的类对象作为锁对象
                    if (count < 50) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        break;
                    }
                }
            }
        };
        // 可以使用同步代码块，借助同步锁解决。注意这个锁对象可以是任意类型，但必须也是共享的
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    @Test
    public void test3() throws InterruptedException {
//        Thread t1 = new Thread(new p35_MyRunnable());
//        Thread t2 = new Thread(new p35_MyRunnable());
//        Thread t3 = new Thread(new p35_MyRunnable());
        // 注意这边必须用同一个Runnable对象创建线程，因为共享变量count不是static的
        p35_MyRunnable myRunnable = new p35_MyRunnable();
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        Thread t3 = new Thread(myRunnable);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
