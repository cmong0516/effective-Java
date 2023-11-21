package chapter4.item19;

public class Item19 {
    // 상속을 고려해 설계하고 문서화 하라.
    // 그러지 않았을 경우 상속을 금지해라.
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();

        // null
        // Instant
        // 상위 클래스의 생성자는 하위 클래스의 생성자가 인스턴스 필드를 초기화하기도 전에 overrideMe를 호출하기 때문이다.

        // super() 먼저 실행
        // 결과 : null

        // Sub class 생성자에서 instant 초기화.
        // 결과 : instant

        // 사실 NullPointerException 이 발생해야 하지만 System.out.println() 이 Null 출력이 가능.
    }

}
