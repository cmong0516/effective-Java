package chapter8.item52;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionClassifier52 {
    public static String classify(Set<?> set) {
        return "집합";
    }

    public static String classify(List<?> list) {
        return "리스트";
    }

    public static String classify(Collection<?> collection) {
        return "그 외";
    }

    public static String classify2(Collection<?> c) {
        return c instanceof Set ? "집합" :
                c instanceof List ? "리스트" : "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> collection : collections) {
            System.out.println(classify(collection));
        }

        // 집합 , 리스트 , 그외 를 출력할것 같지만 그외 만 3번 출력된다.
        // 어느 메서드가 호출될지는 컴파일 단계에서 정해지는데
        // 컴파일 단계에서는 for 문 안의 collection 은 Collection<?> 이다.
        // 따라서 Collection<?> 를 매개변수로 하는 그외 가 출력됨.
        // 재정의한 메서드는 동적으로 선택되고 다중정의한 메서드는 정적으로 선택되기 때문이다.
        // 다중정의가 혼동을 일으키는 상황을 피해야 한다.
    }
}
