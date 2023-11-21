package chapter4.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public class IntArrays {
    static List<Integer> intArraysAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            @Override
            public Integer set(int index, Integer value) {
                int oldValue = a[index];
                a[index] = value;

                return oldValue;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
