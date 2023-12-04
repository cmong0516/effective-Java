package chapter8.item55;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Item55 {
    // 옵셔널 반환은 신중히 하라.

    // 8버전 이전에는 null 반환 , 예외발생 두가지 선택지가 있었는데 8버전 이후 Optional 이 추가되며 null 처리를 깔금하게 할수 있었다.
    // Optianl<T> 가 Collection<T> 를 구현하진 않았지만 Optional 은 원소를 최대 1개 가질수 있는 불변컬렉션이다.

    public static <E extends Comparable<E>> E max(Collection<E> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E result = null;

        for (E e : collection) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    // Optional 반환.
    public static <E extends Comparable<E>> Optional<E> max2(Collection<E> collection) {
        if (collection.isEmpty()) {
            return Optional.empty();
        }

        E result = null;

        for (E e : collection) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        // Optional.of(value) value 에 null 을 넣으면 Exception 발생.
        // Optional.ofNullable(value) 를 사용하면 null 허용.
        // Optional 을 반환할때는 null 을 반환하지 말자 Optional 도입 취지를 무시하는것.
        return Optional.of(result);
    }

    public static <E extends Comparable<E>> Optional<E> max3(Collection<E> collection) {
        return collection.stream().max(Comparator.naturalOrder());
    }

    // Optional 활용
    // 1. orElse(defaultValue) 로 기본값 설정.
    // 2. orElseThrow(Exception) 로 예회 발생.
    // 3. get()

    // 기본값 설정의 비용이 클경우 Supplier<T> 를 인수로 받는 orElseGet 을 사용하자.

    // Optional 사용
    // 1. 결과가 없을수 있으며 클라이언트가 이 상황을 특별하게 처리해야 할때.
    // 단 이때 Optional 도 새로 할당되어 초기화하는 객체이기에 성능이 중요하다면 한번더 생각하기.

}
