package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/8 20:07
  *  @Description Map的并发修改异常
 *                  使用ConcurrentHashMap
  */

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }
}
