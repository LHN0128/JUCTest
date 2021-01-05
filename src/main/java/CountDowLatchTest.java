import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDowLatchTest {
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/8 20:32
      *  @Description CountDownLatch计数器类
      */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);//设置总数是6
        for (int i = 0; i < 6; i++) {
            new Thread(()->{

                latch.countDown();

            },String.valueOf(i)).start();
        }
        latch.await();
        System.out.println("所有线程已经运行结束");
    }
}
