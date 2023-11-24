package chapter5.item31;

import java.util.List;

public class RecursiveTypeBound {
    //    public static <E extends Comparable<E>> E max(List<E> list) {
    public static <E extends Comparable<? super E>> E max(List<? extends E> list) {
        // List<E> list -> List<? extends E> list
        // 입력 매개변수 list 는 E 인스턴스를 생산함.

        // <E extends Comparable<E>> -> <E extends Comparable<? super E>>
        // E 는 Comparable 을 확장함. Comparable<E> 는 E 인스턴스를 소비한다.
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E result = null;

        for (E e : list) {
            if (result == null || e.compareTo(result) > 0) {
                result = e;
            }
        }

        return result;
    }

    // Comparable 은 소비자 이므로 Comparable<E> 보다는 Comparable<? super E> 가 좋다.
    // Comparator 도 마찬가지.
}
