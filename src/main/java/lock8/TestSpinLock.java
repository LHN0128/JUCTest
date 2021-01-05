package lock8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
  *  @Author Liu Haonan
  *  @Date 2021/1/5 14:44
  *  @Description 测试自旋锁，底层采用CAS实现自定义锁
 *                当t1解锁后，t2才有机会解锁。因为t1一直在占用锁自旋等待
  */
public class TestSpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName());
        while(atomicReference.compareAndSet(null,thread)){
            //自旋等待
            System.out.println("1");
        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName());

    }
    public static void main(String[] args) throws InterruptedException {
        TestSpinLock lock = new TestSpinLock();

        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        },"t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lock.myLock();
            try {

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        },"t2").start();
    }
}
