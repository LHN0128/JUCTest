package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
  *  @Author Liu Haonan
  *  @Date 2021/1/5 13:17
  *  @Description 使用AtomicReference解决ABA问题的乐观锁
 *                 线程a使用ABA修改1->2->1，但是带版本号，线程b修改时，如果是1则改为6
 *                 对于线程b，首先获取版本，然后修改，版本加1.但是中间a线程进行过ABA操作，导致版本号更改，b线程修改失败
  */
public class TestCAS1 {

    public static void main(String[] args) {
        //定义初始值，包括版本号
        //注意此处integer的-128-127
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);
        //创建两个线程
        new Thread(()->{
            System.out.println("a1:"+atomicStampedReference.getStamp());

//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a2:"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a3:"+atomicStampedReference.getStamp());

        },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1:"+stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp+1));//返回布尔


            System.out.println("b2:"+atomicStampedReference.getStamp());

        },"b").start();
    }

}
