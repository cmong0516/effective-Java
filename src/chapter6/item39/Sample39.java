package chapter6.item39;

public class Sample39 {
    @Test39
    public static void m1() {

    }

    public static void m2() {

    }

    @Test39
    public static void m3() {
        throw new RuntimeException("실패");
    }

    public static void m4() {

    }

    @Test39
    public void m5() {
    }   // 잘못 사용한 예: 정적 메서드가 아니다.

    public static void m6() {
    }

    @Test39
    public static void m7() {    // 실패해야 한다.
        throw new RuntimeException("실패");
    }

    public static void m8() {
    }


}
