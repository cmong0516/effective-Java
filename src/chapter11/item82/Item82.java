package chapter11.item82;

public class Item82 {
    // 스레드 안전성 수준을 문서화 하라.

    // 한 메서드를 여러 스레드가 동시에 호출할때 그 메서드가 어떻게 동작 하느냐는 해당 클래스와 이를 사용하는 클라이언트 사이의 중요한 계약과 같다.
    // API 문서에 이에 대한 언급이 없다면 클래스 사용자는 나름의 가정을 해서 사용하게 된다.
    // 만약 그 가정이 틀리면 클라이언트 프로그램은 동기화를 충분히 하지 못하거나 지나치게 한 상태로 모두 심각한 오유로 이어질수 있다.
    // 멀티스레드 환경에서 API를 안전하게 사용하려면 클래스가 지원하는 스레드 안전성 수준을 정확히 명시해야 한다.

    // 스레드 안전성이 높은 순서
    // 1. 불변
    // 2. 무조건적 스레드 안전
    // 3. 조건부 스레드 안전
    // 4. 스레드 안전하지 않음
    // 5. 스레드 절대적

    // 클래스의 스레드 안전성은 보통 클래스의 문서화 주석에 기재하지만 독특한 특성의 메서드는 메서드 주석에 기재한다.
}
