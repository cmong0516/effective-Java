package chapter2.item5;

public class Logger {

    private static final Logger INSTANCE = new Logger();
    private Logger() {
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void run(String text) {
        System.out.println(text);
    }
}
