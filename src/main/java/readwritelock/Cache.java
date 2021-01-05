package readwritelock;

import java.util.HashMap;
import java.util.Map;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/11 20:07
  *  @Description 模拟一个缓存，多线程下读写乱序
 *                加锁
  */
public class Cache {
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
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName() + "写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "写入结束");
    }
    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取结束");

    }
}
