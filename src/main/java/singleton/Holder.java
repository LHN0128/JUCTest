package singleton;

public class Holder {
    private Holder(){}

    public static class inner{
        public static final Holder HOLDER = new Holder();
    }

}
