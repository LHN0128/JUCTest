package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/11 21:02
  *  @Description 阻塞队列的四种API使用
  */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/11 20:40
      *  @Description 抛出异常
      */
    public static void test1(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //当队列满时，继续添加，抛出异常
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //当队列空时，继续读取，抛出异常
        System.out.println(blockingQueue.remove());
    }
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/11 20:47
      *  @Description 有返回值
      */
    public static void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
//        offer方法添加元素
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //当队列满时，返回false
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());


    }
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/11 20:53
      *  @Description 无限等待
      */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //队列满，等待加入
//        blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //没有第四个，阻塞
//        System.out.println(blockingQueue.take());


    }
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //添加元素,有限等待,设置等待时间
        System.out.println(blockingQueue.offer("c",2, TimeUnit.SECONDS));



        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //取出元素，有限等待
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));

    }
}
