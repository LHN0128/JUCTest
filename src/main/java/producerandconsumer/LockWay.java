package producerandconsumer;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/5 16:51
  *  @Description 使用传统lock锁实现生产者消费者问题
 *                使用Condition代替对象监视器
 *
  */
public class LockWay {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Data2 data = (Data2) Data2.class.getDeclaredConstructors()[0].newInstance();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadB").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadC").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadD").start();
    }



}

//判断等待，业务，通知
class Data2{//资源类
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number!=0){
                //等待
                condition.await();
            }
            number++;
            //通知其他线程，增加结束
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+":"+number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while(number==0){
                //等待
                condition.await();
            }
            number--;
            //通知其他线程，减少结束
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+":"+number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}