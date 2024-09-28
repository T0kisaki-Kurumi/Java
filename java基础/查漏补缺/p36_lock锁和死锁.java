package java基础.查漏补缺;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Robbie
 * @since 2024/09/27
 */

class MyRunnable implements Runnable {
    static int count = 0;
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (count >= 500) {
                    break;
                }
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + " " + count++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

class MyRunnableDeadLock implements Runnable {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("t1")) {
            lock1.lock();
            System.out.println("t1 get lock1");
            lock2.lock();
            System.out.println("t1 get lock2");
            System.out.println(" t1 do something");
            System.out.println("t1 release lock2");
            lock2.unlock();
            System.out.println("t1 release lock1");
            lock1.unlock();
        } else {
            lock2.lock();
            System.out.println("t2 get lock2");
            lock1.lock();
            System.out.println("t2 get lock1");
            System.out.println(" t2 do something");
            System.out.println("t2 release lock1");
            lock1.unlock();
            System.out.println("t2 release lock2");
            lock2.unlock();
        }
    }
}

public class p36_lock锁和死锁 {
    @Test
    public void test1() throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
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
    public void test2() throws InterruptedException {
        // 死锁
        MyRunnableDeadLock runnable = new MyRunnableDeadLock();
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public static void main(String[] args) {
    }
}
