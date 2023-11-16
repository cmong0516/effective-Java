package item1;

public class Item1 {
    // 생성자 대신 정적 팩터리 메서드를 고려하라.

    // 일반적으로 인스턴스를 생성하기 위한 방법은 생성자.
     Car1 car1 = new Car1("생성자유형", 100);

    // 정적 팩터리 메서드
    Car2 car2Oil100 = Car2.of("정적팩터리메서드", 100);
    Car2 car2NoOil = Car2.createNoOil("정적팩터리메서드");

    // createNoOil 이라는 메서드 이름으로 어떤 인스턴스가 생성되는지 한눈에 알수 있다.

}

class Car1 {
    private final String name;
    private final int oil;

    public Car1(String name, int oil) {
        this.name = name;
        this.oil = oil;
    }
}

class Car2 {
    private final String name;
    private final int oil;

    private Car2(String name, int oil) {
        this.name = name;
        this.oil = oil;
    }

    public static Car2 of(String name, int oil) {
        return new Car2(name, oil);
    }

    public static Car2 createNoOil(String name) {
        return new Car2(name, 0);
    }
}

