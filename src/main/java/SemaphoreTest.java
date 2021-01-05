import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/8 21:05
  *  @Description 测试同步信号量使用
  */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName());
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }
}
