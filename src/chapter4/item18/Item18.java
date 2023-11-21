package chapter4.item18;

import java.util.HashSet;
import java.util.List;

public class Item18 {
    // 상속보다는 컴포지션을 사용하자.
    // 상속 : 하위 클래스가 상위 클래스의 특성을 재정의 한것.
    // 컴포지션 : 기존 클래스가 새로운 클래스의 구성요소가 되는것.

    // 상속은 캡슐화를 위반한다.
    // 상속은 설계에 유연하지 못하다.
    // 다중상속이 불가능하다.

    public static void main(String[] args) {
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());
        s.addAll(List.of("가", "나", "다"));
        System.out.println(s.getAddCount());
        // 3

        InstrumentedHashSet<String> s2 = new InstrumentedHashSet<>();
        s2.addAll(List.of("가", "나", "다"));
        System.out.println(s2.getAddCount());
        // 6
    }
}

