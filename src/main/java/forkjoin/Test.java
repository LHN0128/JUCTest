package forkjoin;

import java.util.Collection;
import java.util.HashMap;
import java.util.OptionalLong;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Test {

    /**
     *  @Author Liu Haonan
     *  @Date 2020/12/16 17:31
     *  @Description 直接累加测试
     */
    public static void test1(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0L; i < 100000L; i++) {

            sum+=i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+",时间为："+(end-start));

    }
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/16 18:26
      *  @Description 使用forkjoin
      */
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new TestForkJoin(0L, 10000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTask);
        Long sum = submit.get();
        System.out.println(sum);


        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+",时间为："+(end-start));
    }
    /**
      *  @Author Liu Haonan
      *  @Date 2020/12/16 18:27
      *  @Description 使用并行Stream流
      */
    public static void test3(){
        long sum = LongStream.rangeClosed(0L, 10_0000_0000).parallel().sum();
        long sum2 = LongStream.rangeClosed(0L, 10_0000_0000).parallel().reduce(Long::sum).getAsLong();
        System.out.println(sum);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test3();
        

    }
}
