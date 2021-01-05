import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/8 20:41
      *  @Description 当所有线程达到共同屏障时，执行后续操作
     *                加法计数器
      */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("所有线程执行完毕");
        });
        for (int i = 0; i < 7; i++) {
            final int temp = i;//这里注意，thread中的lambda拿不到外层的变量i
            new Thread(()->{//Runnable中的异常必须捕获
                System.out.println(temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
