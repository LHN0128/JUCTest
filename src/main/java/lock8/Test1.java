package lock8;

import java.util.concurrent.TimeUnit;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/8 18:46
  *  @Description 1、两个线程执行，先打印哪个？//先发短信，后打电话
 *                2、为什么？是因为synchronized的锁对象是方法的调用者，因此锁对象先调用哪个，哪个就先执行
 *                和休眠没有关系
  */

public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMsg();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            phone.call();
        },"B").start();

    }


}

class Phone{
    public synchronized void sendMsg(){
        System.out.println("send msg");
    }
    public synchronized void call(){
        System.out.println("call");
    }
}
