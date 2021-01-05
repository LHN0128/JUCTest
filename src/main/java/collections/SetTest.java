package collections;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
  *  @Author Liu Haonan
  *  @Date 2020/12/8 19:51
  *  @Description set和list同理，hashset是线程不安全的
 *                可以使用Collections.synchronizedSet
 *                使用CopyOnWriteArraySet
  */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 100; i++) {


            new Thread(() -> {

                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);

            }, String.valueOf(i)).start();
        }
    }
}
