package java基础.查漏补缺;

import org.junit.Test;

import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @author Robbie
 * @since 2024/09/27
 */

// 容量为1的队列
class p37_Queue {
    public static int state = 0; // 0表示没东西，1表示有东西。用int便于以后扩展更多的状态
    public static int count = 10; // 记录还需要生产的数量，每生产一个减一
    public static Object lock = new Object(); // 锁
}

class p37_Producer implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (p37_Queue.lock) {
                if (p37_Queue.count == 0) {
                    break;
                } else {
                    if (p37_Queue.state == 1) {
                        try {
                            p37_Queue.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("生产者生产了第" + (10 - p37_Queue.count + 1) + "个产品");
                        p37_Queue.state = 1;
                        p37_Queue.lock.notifyAll();
                    }
                }
            }
        }
    }
}

class p37_Consumer implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (p37_Queue.lock) {
                if (p37_Queue.count == 0) {
                    break;
                } else {
                    if (p37_Queue.state == 0) {
                        try {
                            p37_Queue.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        p37_Queue.count--;
                        System.out.println("消费者消费了第" + (10 - p37_Queue.count) + "个产品");
                        p37_Queue.state = 0;
                        p37_Queue.lock.notifyAll();
                    }
                }
            }
        }
    }
}

class p37_Producer2 implements Runnable {
    BlockingQueue<String> queue;
    int produceCount = 0;

    public p37_Producer2(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 注意put和add的区别，put是阻塞的，add是非阻塞的
                // 如果队列已满，put会一直阻塞，直到队列有空余位置，而add则会直接返回，并抛出IllegalStateException 异常
                queue.put("产品");
                System.out.println("生产者生产了第" + (produceCount++ + 1) + "个产品");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class p37_Consumer2 implements Runnable {
    BlockingQueue<String> queue;
    int consumeCount = 0;
    public p37_Consumer2(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String product = queue.take();
                System.out.println("消费者消费了第" + (consumeCount++ + 1) + "个产品");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class p37_Consumer_手动 implements Runnable {
    BlockingQueue<String> queue;
    int consumeCount = 0;
    public p37_Consumer_手动(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                scanner.nextLine();
                String product = queue.take();
                System.out.println("消费者消费了第" + (consumeCount++ + 1) + "个产品");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class p37_简易的生产者消费者模型 {
    // 手写一个容量为1的阻塞队列
    @Test
    public void test1() throws InterruptedException {
        Thread producer = new Thread(new p37_Producer());
        Thread consumer = new Thread(new p37_Consumer());
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    // 使用阻塞队列：ArrayBlockingQueue（有界）或 ListBlockingQueue（无界（实际上还是有限制，是INT.MAX_VALUE））
    @Test
    public void test2() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        Thread producer = new Thread(new p37_Producer2(queue));
        Thread consumer = new Thread(new p37_Consumer2(queue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        Thread producer = new Thread(new p37_Producer2(queue));
        Thread consumer = new Thread(new p37_Consumer_手动(queue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
