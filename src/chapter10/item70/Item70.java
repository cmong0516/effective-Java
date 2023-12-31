package chapter10.item70;

public class Item70 {
    // 복구할 수 있는 상황에는 검사 예외를 프로그래밍 오류에는 런타임 예외를 사용하라.

    // 자바의 문제상황을 알리는 타입 세가지
    // 1. 검사예외
    // 2. 런타임 예외
    // 3. 에러

    // 호출하는 쪽에서 복구하리라 여겨지는 상황이라면 검사 예외를 사용하라. ( 검사 , 비검사 를 구분하는 기본 규칙)
    // 검사예외 : catch 로 잡아 처리하거나 바깥으로 전파하도록 강제하여 API 사용자에게 던져주어 그 상황에서 회복해 내라 라고 요구한것.

    // 비검사 Throwable
    // 1. 런타임 예외
    // 2. 에러

    // 런타임 예외 Ex)
    // ArrayIndexOutOfBoundException
    // 배열의 인덱스는 0에서 배열크기 -1 사이 여야 하지만 이것이 지켜지지 않았다.
    // 조건에서 문제가 하나 있다면 복구할수 있는 상황인지 프로그래밍 오류인지 명확히 구분되지가 않는다.

    // 복구가 가능 하다고 생각되면 검사예외를
    // 복구가 불가능 하다고 생각되면 런타임 예외를 사용하자.

}
