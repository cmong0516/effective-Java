package chapter2.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size = 0;

    public Stack() {
        elements = new Object[10];
    }

    public void push(Object o) {
        ensureCapacity();
        elements[size++] = o;
    }

    // pop 을 해서 사용하지 않게된 객체들이 여전히 참조되고 있다.
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
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
