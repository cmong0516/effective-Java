package chapter5.item30;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

public class Item30 {
    // 이왕이면 제네릭 메서드로 만들어라.

    public static void main(String[] args) {
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);

        Set set2 = new HashSet();
        set1.add(3);
        set1.add(4);

        Set union = union(set1, set2);

        System.out.println("union.size() = " + union.size());

        Set set = union2(set1, set2);

        System.out.println("set.size() = " + set.size());

        Set<Integer> set3 = new HashSet<>();
        Set<Integer> set4 = new HashSet<>();
        set3.add(1);
        set3.add(2);
        set4.add(3);
        set4.add(4);

        union2(set3, set4);

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        Integer max = max(list);

        System.out.println("max = " + max);


    }

    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        
        return result;

        // 컴파일은 되지만 경고가 발생한다.
        // Raw use of parameterized class 'HashSet'
        //Unchecked call to 'HashSet(Collection<? extends E>)' as a member of raw type 'java.util.HashSet'
        //Provide the parametrized type for this generic.

        // Unchecked call to 'addAll(Collection<? extends E>)' as a member of raw type 'java.util.Set'

        // 경고를 없애려면 타입을 안전하게 만들어야 한다.
    }

    public static <E> Set<E> union2(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);

        return result;

        // 위처럼 제네릭으로 변경하여 타입 안정성을 지켜주자 경고가 사라졌다.
    }

    // 제네릭 싱글턴 팩터리 패턴
    // IDENTITY_FN 을 UnaryOperator<T> 로 형변환 하면 비검사 경고 발생.
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E result = null;

        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
        // item55 에서 Optional 로 변경예정.
    }
}
