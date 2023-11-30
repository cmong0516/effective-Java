package chapter7.item47;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Item47 {
    // 반환 타입으로는 스트림보다 컬렉션.

    // Stream 이 Iterable 을 확장하지 않아서 forEach 가 불가능하다.


    // Stream<E> -> Iterable<E>
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }


    // Iterable<E> -> Stream<E>
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    // 메서드가 스트림 파이프라인 에서만 쓰인다면 스트림을 , 반복문에서만 쓰인다면 Iterable 을 사용.

    // Collection 은 Iterable 의 하위타입 이며 Stream 메서드도 제공한다.
    // 따라서 API 의 반환 타입으로는 Collection 을 사용하는게 좋다.
    // (컬렉션을 반환하려고 덩치큰 시퀀스를 메모리에 올리지 않게 주의)


}