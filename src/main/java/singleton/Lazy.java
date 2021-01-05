package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
  *  @Author Liu Haonan
  *  @Date 2021/1/4 14:54
  *  @Description DCL懒汉式单例模式
  */
public class Lazy {
    private static boolean signal = false;
    private Lazy(){
        //当采用反射创建对象时，为了保证单例，在构造器中再加一层判断
        synchronized (Lazy.class){
            if (signal==false) {
                signal = true;
            }else{
                throw new RuntimeException("使用反射创建失败");
            }
        }
    }
    private volatile static Lazy lazy;

    private static Lazy getInstance(){//双重检测锁模式的懒汉式
        //为什么要进行双重锁判断？为了当单例对象成功创建后，获取单例对象时不需要加锁
        if (lazy==null){
            synchronized (Lazy.class){
                if (lazy==null){
                    lazy = new Lazy();
                }
            }

        }
        return lazy;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //当两个

        Constructor<Lazy> constructor = Lazy.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Lazy lazy2 = constructor.newInstance();
        Lazy lazy3 = constructor.newInstance();
        System.out.println(lazy2.hashCode());
        System.out.println(lazy3.hashCode());
    }
}
