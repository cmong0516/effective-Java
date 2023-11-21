package chapter5.item26;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item26 {
    // 로 타입은 사용하지 마라.

    public static void main(String[] args) {
//        List<String> strings = new ArrayList<>();
//        unsafeAdd(strings, Integer.valueOf(42));
//        String s = strings.get(0);
        // -> ClassCastException
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 1; i < 6; i++) {
            set1.add(i);
            set2.add(i);
        }

        set1.add(6);
        set1.add(7);

        int i = numElementsInCommon(set1, set2);

        System.out.println("i = " + i);

        // numElementsInCommon() 메서드가 동작은 하지만 타입에 안전하지 않다.
        // 따라서 비한정적 와일드카드 타입을 대신 사용하는게 좋다.

        int i1 = numElementsInCommon2(set1, set2);

        System.out.println("i1 = " + i1);
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o : s1) {
            if (s2.contains(o)) {
                result++;
            }
        }
        return result;
    }

    static int numElementsInCommon2(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o : s1) {
            if (s2.contains(o)) {
                result++;
            }
        }
        return result;
    }

    // 와일드카드 타입 Set<?> 를 사용함
}
