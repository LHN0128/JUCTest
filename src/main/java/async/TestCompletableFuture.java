package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/22 18:28
  *  @Description 异步回调测试
 *  返回值是泛型，如果返回为空，就是Void
  */
public class TestCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值的
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
        });
        System.out.println("================");
        voidCompletableFuture.get();
        //有返回值，如果正确执行输出1024，否则输出2333
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
            return 1024;
        });

        System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
            //打印，消费掉两个参数
            System.out.println("t->" + t);//正常的返回结果
            System.out.println("u->" + u);//错误信息
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 233333333;//错误的返回结果
        }).get());

    }


}
