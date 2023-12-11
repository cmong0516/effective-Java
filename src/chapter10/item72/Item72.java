package chapter10.item72;

public class Item72 {
    // 표준 예외를 사용하라

    // 프로그래머는 숙련될 수록 코드를 재사용한다.
    // 예외 또한 재사용 하는것이 좋으며 자바 라이브러리는 대부분 API에서 사용하기 충분한 예외를 제공한다.


    // IllegalArgumentException
    // 호출자가 인수로 부적절한 값을 넘길때 사용한다.
    // IllegalStateException
    // 대상 객체가 호출된 메서드를 수행하기에 적합하지 않을경우 사용한다.
    // null 을 허용하지 않는 메서드에 null 을 던지면 NullPointerException
    // 어떤 시퀀스의 허용 범위르 넘는 값을 건넬 때 IndexOutOfBoundsException
    // 단일 스레드에서 사용하려고 설계한 객체를 여러 스레드가 동시에 수정하려 할때 ConcurrentModificationException
    // 클라이언트가 요청한 동작을 대상 객체가 지원하지 않을때 UnsupportedOperationException




}
