package queue;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/11 21:13
  *  @Description 同步队列是阻塞队列的子类
 *                同步队列中只能有一个元素
 *                put进一个元素，需要等待take出来，才能再put
  */
public class TestSynQueue {
    public static void main(String[] args) {
        //声明一个同步队列
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"put 1");
            try {
                blockingQueue.put("1");
                blockingQueue.put("2");
                blockingQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread2").start();
    }
}
