package chapter3.item13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack13 implements Cloneable{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack13() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object obj) {
        ensureCapacity();
        elements[size++] = obj;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    public Stack13 clone() {
        try {
            Stack13 result = (Stack13) super.clone();
            result.elements = elements.clone();
//             얕은복사를 하게되어 clone 객체가 원본객체와 같은 elements 를 참조하기 때문에 예외 발생.
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
