package chapter9.item61;

import java.util.Comparator;

public class Item61 {
    // 박싱된 기본 타입보다는 기본 타입을 사용하라.

    // 기본타입
    // int long , double , boolean
    // 박싱된 기본 타입
    // Integer, Long , Double , Boolean

    // 기본 타입은 값 만 가지고 있지만 박싱된 기본 타입은 식별성 을 갖는다. -> 값이 같아도 다르다고 식별될수 있다.

    public static void main(String[] args) {
        Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);
        // SonarLint 가 == 부분 수정을 알려준다.
        // 왜 ?

        int compare = naturalOrder.compare(new Integer(42), new Integer(42));

        System.out.println("compare = " + compare);
        // 같은 new Integer(42) 를 비교하는데 1 이 나왔다.
        // i == j 에서 다르다고 하여 1이 나온것.
        // 박싱된 기본 타입에 == 연산자를 사용하면 오류가 생긴다.
        // 기본 타입을 다루는 비교자가 필요하다면 Comparator.naturalOrder() 를 사용해라.
    }
}
