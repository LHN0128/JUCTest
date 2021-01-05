package readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/11 20:07
  *  @Description 使用ReentrantReadWriteLock
  */
public class TestReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyCache2{
    private volatile Map<String,Object> map = new HashMap<>();
    //定义读写锁、更加细粒度的控制
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //写入时，希望只有一个线程读写，防止并发修改
    public void put(String key,Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写入结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
    //读取时不做限制
    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取结束");

    }
}
