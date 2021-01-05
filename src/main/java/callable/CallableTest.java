package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/8 20:25
  *  @Description 使用FutureTask设置Callable的线程任务
 *                  使用get方法获取线程任务的返回值
 *                  向Thread中传入FutureTask
  */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(myThread);
        new Thread(integerFutureTask).start();
        System.out.println(integerFutureTask.get());
    }
}
class MyThread implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        int i = 12;
        return i;
    }
}
