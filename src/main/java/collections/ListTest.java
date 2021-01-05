package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/8 19:23
  *  @Description 测试List的并发修改。
 *                如果是ArrayList，list会抛出并发修改异常
 *                可以使用Collections.synchronizedXXX()返回一个同步集合
 *                使用CopyOnWriteArrayList
  */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{

                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);

            },String.valueOf(i)).start();
        }
    }
}
