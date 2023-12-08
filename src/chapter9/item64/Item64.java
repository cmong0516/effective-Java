package chapter9.item64;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Item64 {
    // 객체는 인터페이스를 사용해 참조하라.

    // 객체는 클래스가 아닌 인터페이스로 참조하라.
    // 적합한 인터페이스만 있다면 반환값 , 변수 , 필드 모두.

    // Good
    // Set<Son> sonSet = new LinkedHashSet<>();

    // Bad
    // LinkedHashSet<Son> sonSet = new LinkedHashSet<>();

    // Better
    // Set<Son> sonSet = new HashSet<>()

    // 단 이때 순서 정책을 가정하고 동작하는 LinkedHashSet 을 HashSet 으로 바꿀경우 문제가 발생한다.

    // 그럼 구현 타입을 바꾸려는 이유가 뭘까 ??
    // 원래 것보다 성능이 좋거나 기능이 추가되어 있다.

    // EX) HashSet 을 참조하던 변수가 EnumMap 으로 바꾸면 속도가 빨라지고 순회 순서도 키의 순서와 같아진다.
    // 단 EnumMap 은 열거타입에 한해서만 사용할수 있다.

    // 적합한 인터페이스가 없다면 당연히 클래스로 참조해야한다. EX) String , BigInteger

    // 적합한 인터페이스가 없다면 클래스의 계층구조 중 필요한 기능을 만족하는 가장 덜 구체적인 상위 클래스를 타입으로.
}
