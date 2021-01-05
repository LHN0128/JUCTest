package testvolatile;

import java.util.concurrent.TimeUnit;
/**
  *  @Author Liu Haonan
  *  @Date 2020/12/23 19:16
  *  @Description 测试volatile关键字。
 *              1、验证保证变量的可见性
 *              不加volatile，程序线程不会停止
  */
public class TestVolatile1 {
    private static /*volatile*/ int num = 0;
    public static void main(String[] args) {
        new Thread(()->{
            while (num==0){

            }
        }).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        num = 1;//将num赋值为1，循环不停止。没有拿到主存中的新值，线程中的num是0
        System.out.println(num);
    }
}
