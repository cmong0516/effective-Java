package chapter2.item4;

public class Item4 {
    // 인스턴스화를 막으려면 private 생성자를 사용하라.
    // 2023 우테코 미션에서 사용한 Input Output 클래스는 유틸로 판단하여 static 메서드만을 가지고 있었다.
    // 이때 private 생성자를 사용하여 인스턴스화를 하지 못하게 했다.

    // 더 확실한 방법은 private 생성자 사용시 예외 발생시키기.

    private Item4() {
        throw new AssertionError("어허");
    }
}
