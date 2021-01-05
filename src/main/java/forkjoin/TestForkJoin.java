package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/16 17:02
  *  @Description 求和计算。当数据量大于临界值时，使用Forkjoin计算
 *              使用ForkJoinPool执行。MapReduce思想
 *              使用fork()方法划分任务，采用join()方法集合任务
 *              使用ForkJoinPool启动
  */
public class TestForkJoin extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    //临界值
    private Long temp = 5100L;

    public TestForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        if((end-temp)<temp){//如果计算量小于临界值，采用普通求和
            Long sum = 0L;
            for (long i = start; i < end; i++) {
                sum+=i;
            }
            System.out.println(sum);
            return sum;
        }else{//如果计算量大于临界值，采用forkjoin.
            long middle = (start+end)/2;
//            将一个任务拆分为两个任务计算
            TestForkJoin task1 = new TestForkJoin(start, middle);
            TestForkJoin task2 = new TestForkJoin(middle + 1, end);
            //使用fork拆分任务，把任务压入线程队列
            task1.fork();
            task2.fork();
            //使用join获取结果
            return task1.join()+task2.join();

        }
    }

}
