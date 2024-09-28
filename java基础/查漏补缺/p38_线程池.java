package java基础.查漏补缺;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Robbie
 * @since 2024/09/27
 */

public class p38_线程池 {
    @Test
    // 测试SynchronousQueue
    public void test1() throws InterruptedException {
        // SynchronousQueue是一个容量为0的BlockingQueue，它不存储元素，任何元素通过put加入时都会阻塞，需要其他线程调用take方法取出元素才会解除阻塞
        // 有公平和非公平两种模式，相当于是线程的先进先出和先进后出。默认非公平
        BlockingQueue<Integer> sq = new SynchronousQueue<>(); // 非公平同步队列，基于栈
//        BlockingQueue<Integer> sq = new SynchronousQueue<>(true); // 公平同步队列，基于队列
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                sq.put(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 2");
                sq.put(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " take " + sq.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " take " + sq.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(500);
        t3.start();
        Thread.sleep(500);
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    @Test
    // 测试ScheduledExecutorService和DelayedWorkQueue
    public void test2() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        Runnable oneTimeTask = () -> System.out.println("一次性任务执行: " + System.currentTimeMillis());
        Runnable periodicTask = () -> System.out.println("周期性任务执行: " + System.currentTimeMillis());
        scheduledExecutorService.schedule(oneTimeTask, 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(periodicTask, 1, 2, TimeUnit.SECONDS);
        // 让主线程等待一段时间，以便观察任务的执行情况
        try {
            Thread.sleep(10000); // 主线程休眠10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        scheduledExecutorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Executors工具类提供了几种预定义的线程池
        // 基于ThreadPoolExecutor
        ExecutorService tp1 = Executors.newFixedThreadPool(3);// 核心线程数=最大线程数=自定义，并使用LinkedBlockingQueue作为工作队列
        ExecutorService tp2 = Executors.newSingleThreadExecutor(); // FixedThreadPool的一个特例，核心线程数=最大线程数=1
        ExecutorService tp3 = Executors.newCachedThreadPool(); // 核心线程数=0，最大线程数=Integer.MAX_VALUE，线程存活时间=60s，使用SynchronousQueue作为工作队列
        ExecutorService tp4 = Executors.newScheduledThreadPool(3); // 核心线程数=自定义，最大线程数=Integer.MAX_VALUE，使用DelayedWorkQueue作为工作队列
                                                                              // DelayedWorkQueue是一个优先级队列，按照延迟时间排序，延迟时间越短，优先级越高
                                                                              // DelayedWorkQueue满时，会调用grow方法增加当前1/2的容量，因此永远不会满
        // 基于ForkJoinPool
        ExecutorService tp5 = Executors.newWorkStealingPool(); // 每个线程维护一个双端队列用于存放任务，当一个线程完成自己队列中的所有任务后，它可以“窃取”另一个线程的队列中的任务来执行

        // 自定义线程池
        // 四种拒绝策略：
        // AbortPolicy：丢弃任务并抛出RejectedExecutionException异常
        // DiscardPolicy：丢弃任务，不抛出异常
        // DiscardOldestPolicy：丢弃队列中等待最久（最早传入）的任务，然后重新尝试执行任务（重复此过程）
        // CallerRunsPolicy：由调用线程处理该任务（相当于不start，而是直接调用run方法）
        ExecutorService myPool = new ThreadPoolExecutor(
                5,  // 核心线程数
                20, // 最大线程数
                60, // 线程存活时间
                TimeUnit.SECONDS, // 线程存活时间单位
                new ArrayBlockingQueue<>(5), // 工作队列
                Executors.defaultThreadFactory(), // 线程工厂，负责new线程
                new ThreadPoolExecutor.AbortPolicy() // 线程拒绝策略
        );
        Thread.sleep(2000);

        // 可以用jconsole查看jvm状态
        try {
            for (int i = 0; i < 100; i++) {
                myPool.submit(() -> {
                    long nextSleepTime = (long) (1000 + 4000 * Math.random());
                    System.out.println(Thread.currentThread().getName() + " running, sleep time: " + nextSleepTime);
                    try {
                        Thread.sleep(nextSleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return Thread.currentThread().getName() + " finished";
                });
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myPool.shutdown();
        }
    }
}
