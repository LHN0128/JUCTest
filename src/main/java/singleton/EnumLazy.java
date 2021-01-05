package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
/**
  *  @Author Liu Haonan
  *  @Date 2021/1/4 16:02
  *  @Description 使用枚举的单例模式，当使用反射创建对象时，理应抛出：Cannot reflectively create enum objects
 *                保持单例模式不被反射破坏的办法
  */
public enum EnumLazy {
    INSTANCE;

    private static EnumLazy getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<EnumLazy> enumLazyClass = EnumLazy.class;
        Constructor<EnumLazy> declaredConstructor = enumLazyClass.getDeclaredConstructor(String.class, int.class);//枚举类并不是无参构造器
        declaredConstructor.setAccessible(true);
        EnumLazy instance1 = declaredConstructor.newInstance();
        EnumLazy instance2 = declaredConstructor.newInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());



    }
}
