package java基础.查漏补缺;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Robbie
 * @since 2024/09/28
 */

public class p39_BlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        for (int i = 0; i < 10; i++) {
            queue.add("item" + i);
        }
        // 有三种加入阻塞队列的方法
//        System.out.println(queue.add("item10")); // 成功返回true，阻塞时抛出IllegalStateException
//        queue.put("item10"); // 阻塞，直到队列有空位
        System.out.println(queue.offer("item10")); // 尝试将元素立即插入到队列中，如果成功则返回 true，如果队列已满则返回 false
//        System.out.println(queue.offer("item10", 5, TimeUnit.SECONDS)); // 可以设置超时时间，不设置默认为0
    }
}
