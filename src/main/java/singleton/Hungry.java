package singleton;

public class Hungry {
    private Hungry(){

    }
    //浪费空间
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
