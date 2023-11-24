package chapter5.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Stack;

public class Stack31<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    // 코드 29-3 배열을 사용한 코드를 제네릭으로 만드는 방법 1 (172쪽)
    // 배열 elements는 push(E)로 넘어온 E 인스턴스만 담는다.
    // 따라서 타입 안전성을 보장하지만,
    // 이 배열의 런타임 타입은 E[]가 아닌 Object[]다!
    @SuppressWarnings("unchecked")
    public Stack31() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size==0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // 코드 31-1 와일드카드 타입을 사용하지 않은 pushAll 메서드 - 결함이 있다! (181쪽)
//    public void pushAll(Iterable<E> src) {
//        for (E e : src)
//            push(e);
//    }

    // 코드 31-2 E 생산자(producer) 매개변수에 와일드카드 타입 적용 (182쪽)
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src)
            push(e);
    }

//    // 코드 31-3 와일드카드 타입을 사용하지 않은 popAll 메서드 - 결함이 있다! (183쪽)
//    public void popAll(Collection<E> dst) {
//        while (!isEmpty())
//            dst.add(pop());
//    }

    // Stack<Number> 의 원소를 Object용 컬렉션으로 옮기려고 할때
    // Collection<Object> 는 Collection<Number> 의 하위타입이 아니다 라는 에러 발생.

    // 코드 31-4 E 소비자(consumer) 매개변수에 와일드카드 타입 적용 (183쪽)
    public void popAll(Collection<? super E> dst) {
        // E의 상위타입 이어야 한다. , (모든 타입은 자기 자신의 상위타입 이다.)
        while (!isEmpty())
            dst.add(pop());
    }

    // 유연성을 극대화 하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드 카드 타입을 사용해라.
    // PECS
    // producer-extends
    // consumer-super
    // 매개변수화 타입 T 가 생산자 라면 <? extends T>
    // 소비자 라면 <? super T>

}
