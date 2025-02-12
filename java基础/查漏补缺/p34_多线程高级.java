package java基础.查漏补缺;

import org.junit.Test;

/**
 * @author Robbie
 * @since 2024/09/26
 */

class p34_MyRun implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            // 让出CPU资源，让其他线程有机会执行（但是当前线程仍然会加入到就绪队列，参与CPU的竞争）
            // 可以让线程执行相对来说更加均匀一些
            // 本质是运行态切换为就绪态
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " : 线程结束");
    }
}

class p34_MyRunSignal implements Runnable {
    public boolean signal = true;
    private int count = 0;

    @Override
    public void run() {
        while (signal) {
            System.out.println(Thread.currentThread().getName() + " : " + count++);
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("线程sleep被中断，实际sleep时间：" + (System.currentTimeMillis() - startTime));
            }
        }
    }
}

public class p34_多线程高级 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main线程优先级=" + Thread.currentThread().getPriority());
        Thread t1 = new Thread(new p34_MyRun(), "t1线程");
        Thread t2 = new Thread(new p34_MyRun(), "t2线程");
        // 线程默认优先级为5（main线程也是）
        System.out.println("t1线程优先级=" + t1.getPriority());
        System.out.println("t2线程优先级=" + t2.getPriority());
        // 可以手动设置线程优先级，最大值为10，最小值为1，越大越优先，但并不是绝对优先，而是优先执行的概率更大
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();

        // 守护线程（Daemon线程）。会在其他线程结束后自动结束。main方法中，也包括main线程
        Thread daemonThread = new Thread(() -> {
            for (int i = 0; ; ++i) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }, "daemonThread");
        daemonThread.setDaemon(true); // 设置为守护线程，必须在线程启动之前，否则会抛出IllegalThreadStateException异常
        daemonThread.start();

        // join方法可以让当前线程等待调用join方法的那个线程执行完，再执行后面的代码
        t1.join();
        t2.join(2000); // 设置超时时间，如果超时，就会继续走下面的代码

        for (int i = 0; i < 10; i++) {
            System.out.println("main线程 : " + i);
        }
    }

    @Test
    public void test1() throws InterruptedException {
        p34_MyRunSignal runnable = new p34_MyRunSignal();
        new Thread(runnable).start();
        Thread.sleep(5000);
        // 可以借助信号量来控制线程的结束
        runnable.signal = false;
        Thread.sleep(5000);
    }

    @Test
    public void test2() throws InterruptedException {
        p34_MyRunSignal runnable = new p34_MyRunSignal();
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            t1.interrupt();
            Thread.sleep((long) (1000 * Math.random()));
        }
        Thread.sleep(5000);
    }
}
