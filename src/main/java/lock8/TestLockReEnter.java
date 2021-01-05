package lock8;
/**
  *  @Author Liu Haonan
  *  @Date 2021/1/5 14:37
  *  @Description 测试sync的可重入锁
 *                当执行锁方法时，锁方法执行另一个锁方法时，不需要额外获取锁，也就不会被别的线程插入
  */
public class TestLockReEnter {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            phone2.sendMsg();
        },"t1").start();
        new Thread(()->{
            phone2.sendMsg();
        },"t2").start();
    }


}
class Phone2{
    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName()+"sendMsg");
        call();//调用锁方法
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"call");
    }

}
