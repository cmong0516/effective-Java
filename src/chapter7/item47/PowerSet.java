package chapter7.item47;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSet {
    public static final <E> Collection<Set<E>> of(Set<E> set) {
        List<E> src = new ArrayList<>(set);
        if (src.size() > 30) {
            throw new IllegalArgumentException();
        }

        return new AbstractList<Set<E>>() {
            @Override
            public int size() {
                return 1 << src.size();
            }

            @Override
            public boolean contains(Object o) {
                return o instanceof Set && src.containsAll((Set) o);
            }

            @Override
            public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; index >>= 1) {
                    if ((index & 1) == 1) {
                        result.add(src.get(i));
                    }
                }
                return result;
            }
        };
    }

    public static void main(String[] args) {
        Set set = new HashSet(Arrays.asList(args));
        System.out.println(PowerSet.of(set));
    }
}
