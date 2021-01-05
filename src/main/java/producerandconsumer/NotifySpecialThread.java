package producerandconsumer;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/5 16:51
  *  @Description  使用Condition代替对象监视器,实现精准调用。（原来的线程是乱序调用的）A执行完调用B，B执行完调用C
 *                  设置多个同步监视器
  */
public class NotifySpecialThread {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Data3 data = (Data3) Data3.class.getDeclaredConstructors()[0].newInstance();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "ThreadA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "ThreadB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "ThreadC").start();

    }
}
//判断等待，业务，通知
class Data3{//资源类


    private Lock lock = new ReentrantLock();
    //定义三个对象监视器，用于唤醒指定线程。
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;//number为1时，A执行；2B；3C

    public void printA(){
        lock.lock();
        try {
            while(number!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName());
            //A执行后，唤醒指定的B
            number=2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printB(){
        lock.lock();
        try {
            while(number!=2){
                condition2.await();
            }
            number=3;
            System.out.println(Thread.currentThread().getName());
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while(number!=3){
                condition3.await();
            }
            number=1;
            System.out.println(Thread.currentThread().getName());
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}