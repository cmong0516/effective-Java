package chapter2.item5;

public class Item5 {
    // 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라.
    // 유연하고 테스트하기 쉽게 코드를 작성해야한다.
    // 의존성이 많은 프로젝트의 경우 코드가 어지러워 지는데 Spring 의 싱글톤 DI 로 해결.

    private final Logger logger;

    public Item5(Logger logger) {
        this.logger = logger;
    }

    public static void main(String[] args) {
        Item5 item5 = new Item5(Logger.getInstance());

        item5.logger.run("Hello");
    }

}
