package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/12 14:58
  *  @Description Executors工具类与线程池创建，不推荐使用，容易OOM
  */
public class TestExecutor {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);//固定大小线程池
//        ExecutorService executorService = Executors.newCachedThreadPool();//可伸缩线程
        try {
            for (int i = 0; i < 100; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
