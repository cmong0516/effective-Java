package chapter4.item24;

import chapter4.item24.TestClass.PublicSample;

public class Item24 {
    // 멤버 클래스는 되도록 static 으로 만들어라.

    // 중첩 클래스의 종류
    // 1. 정적 멤버 클래스
    // 2. 비정적 멤버 클래스
    // 3. 익명 클래스
    // 4. 지역 클래스

    public static void main(String[] args) {

        // 1. 정적 멤버 클래스
        // 클래스 내부에 static 으로 선언된 클래스
        // 다른 클래스 안에 선언되고 바깥 클래스의 private 멤버에도 접근 가능.
        // private 으로 선언시 바깥 클래스에서만 접근 가능하다.
        new Animal.PublicSample().method();
        // PublicSample 의 outerClass 는 Animal
        // Animal.name == cat
        // publiccat



        // 2. 비정적 멤버 클래스
        // static 이 붙지 않은 멤버 클래스
        // 비정적 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 연결된다.
        // 바깥 인스턴스 없이 생성 불가능하다.

        TestClass testClass = new TestClass();
//        PublicSample publicSample = new PublicSample();
        PublicSample publicSample = testClass.createPublicSample();
        publicSample.printName();
        publicSample.callTestClassMethod();


        // 3. 익명 클래스
        // 익명 클래스는 코드의 어디서든 만들수 있다.
        // 오직 비정적의 문맥에서 사용될 때만 바깥 클래스의 인스턴스를 참조할수 있다.

        // 4. 지역클래스
        // 지역 클래스는 지역 변수를 선언할수 있는 곳이면 어디든 선언할수 있고 유효범위도 같다.
        // 비정적 문맥에서 사용될 때만 바깥 인스턴스를 참조할수 있다.
        // 정적 멤버를 가질수 없다.


    }
}
