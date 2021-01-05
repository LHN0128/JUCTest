package pool;

import java.util.concurrent.*;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/12 15:57
  *  @Description 使用ThreadPoolExecutor自定义线程池
 *                避免Executors创建线程类引发的OOM
 *                最大线程数为最大值+阻塞队列长度
 *                超过最大线程数+阻塞队列长度，执行超过四种拒绝策略
 *
  */
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()//请求超过最大，抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy()//请求超过最大，哪里来的请求回到哪里处理（此处返回了main）
//                new ThreadPoolExecutor.DiscardPolicy()//请求超过最大，直接丢弃不跑出异常
                new ThreadPoolExecutor.DiscardOldestPolicy()//请求超过最大，尝试丢弃最早的

        );
        try {
            for (int i = 1; i <= 1000; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

}
