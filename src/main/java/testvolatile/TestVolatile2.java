package testvolatile;


import java.util.concurrent.atomic.AtomicInteger;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/23 19:18
  *  @Description 测试volatile关键字
 *                线程在执行任务时不能被打断，也不能被分割.
 *                volatile不能保证线程的原子性（会被中断修改变量）
 *                ++不是原子操作，使用atomic中的类型声明变量。
 *                修改变量时，调用其方法，底层是cas操作，native方法
  */
public class TestVolatile2 {
//    private static int num = 0;
    private static AtomicInteger num = new AtomicInteger();
    public static void add(){
//        num++;
        num.getAndIncrement();

    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){//判断线程执行完了没。当前执行的线程数大于2
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);

    }
}
