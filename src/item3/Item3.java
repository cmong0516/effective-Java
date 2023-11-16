package item3;

public class Item3 {
    public static void main(String[] args) {
        // private 생성자나 열거 타입으로 싱글턴임을 보증하라

        // 싱글턴 : 객체나 설계상 유일해야 하는 시스템 컴포넌트 , 무상태 객체

        // 1
        // 생성자를 private 으로 두고 인스턴스를 메서드로 반환
        // 단 한가지의 예외 : AccessibleObject.setAccessible 를 사용하여 private 생성자를 호출할수 있다.
        // 싱글턴이면 안전한줄 알았는데 공격방법이 있다 -> 생성자를 수정하여 두번째 객체가 생성되려 할때 예외를 던진다.

        Singleton1 singleton1 = Singleton1.INSTANCE;
        singleton1.run();

        // 2
        // 정적 팩터리 메서드 getInstance() 로 인스턴스를 반환한다.

        Singleton2 singleton2 = Singleton2.getInstance();
        singleton2.run();

        // 3
        // 원소가 하나인 열거타입을 선언한다.

        Singleton3 singleton3 = Singleton3.INSTANCE;
        singleton3.run();
    }

}
