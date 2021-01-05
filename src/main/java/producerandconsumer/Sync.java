package producerandconsumer;

import java.lang.reflect.InvocationTargetException;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/5 16:51
  *  @Description 使用传统synchronized实现生产者消费者问题
 *                当并发程度大于2时，线程不安全（应该改为while判断而不是if判断）
  */
public class Sync {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Data data = (Data) Data.class.getDeclaredConstructors()[0].newInstance();

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
class Data{//资源类
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number!=0){
            //等待
            this.wait();
        }
        number++;
        //通知其他线程，增加结束
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+":"+number);
    }
    public synchronized void decrement() throws InterruptedException {
        while(number==0){
            //等待
            this.wait();
        }
        number--;
        //通知其他线程，减少结束
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+":"+number);
    }
}