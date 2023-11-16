package item3;

public class Singleton2 {
    private static final Singleton2 INSTANCE = new Singleton2();

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    public void run() {
        System.out.println("싱글톤 2");
    }
}
