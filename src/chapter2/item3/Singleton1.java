package chapter2.item3;

public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public void run() {
        System.out.println("싱글톤 1");
    }
}
