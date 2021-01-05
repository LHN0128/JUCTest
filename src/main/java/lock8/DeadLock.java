package lock8;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
/**
  *  @Author Liu Haonan
  *  @Date 2021/1/5 15:31
  *  @Description 创造死锁，用于排查
  */
public class DeadLock {
    public static void main(String[] args) {
        String lockA = "A";
        String lockB = "B";
        new Thread(new Mythread(lockA,lockB)).start();
        new Thread(new Mythread(lockB,lockA)).start();
    }
}

class Mythread implements Runnable{
    private String lockA;
    private String lockB;

    public Mythread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() +"lock:"+lockA+"get"+lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() +"lock:"+lockB+"get"+lockA);
            }
        }
    }
}
