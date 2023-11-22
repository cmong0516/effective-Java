package chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack29<E> {

    // 이 클래스는 원래 제네릭 타입어야야 마땅하다. 제네릭으로 변경해보자.

    // 1. 배열 elements 는 push(E) 로 넘어온 E 인스턴스만 담는다.
    // 2. 따라서 타입 안전성을 보장하지만 이 배열의 런타임 타입은 E[] 가 아니라 Object[] 다.

//    private Object[] elements;
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack29() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];

    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    // pop 을 해서 사용하지 않게된 객체들이 여전히 참조되고 있다.
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        // push 에서 E 타입만 허용하므로 안전한 형변환이다.
        @SuppressWarnings("uncheked")E result = (E) elements[--size];
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    // GC는 객체 참조 하나를 살려두면 그 객체가 참조하는 모든 객체를 회수해가지 못한다.

    //개선책

    public Object pop2() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];

        // 참조 해제!
        elements[size] = null;
        return result;
    }
}
