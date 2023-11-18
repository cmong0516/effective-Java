package item14;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Item14 {
    // Comparable을 구현할지 고려하라.

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("c");
        set.add("b");
        set.add("a");

        System.out.println(set);

        // c,b,a 순으로 추가했는데 a,b,c 순서로 출력된다.

        // Comparable
//        public interface Comparable<T> {
//            public int compareTo(T o);
//        }

        // compareTo() 의 일반 규약은 equals 와 비슷하다.
        // 두 객체의 순서를 바꿔도 예상한 결과가 나와야 한다.
        // 1 > 2 && 2 > 3 -> 1 > 3 이다.
        // 크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같아야 한다.
        // compareTo 메서드로 수행한 동치성 검사 결과는 equals 와 같아야 한다.
        // compareTo 와 equals 의 결과가 일관되지 않아도 동작은 하지만
        // Collection , Set ,Map 등은 동치성 검사는 compareTo 를 사용하기 때문에 의도한대로 동작하지 않을수 있다.


        // 값의 차를 기준으로 정렬할때

        Comparator<Object> hashCodeOrder = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.hashCode() - o2.hashCode();
            }
        };

        // 위의 방식은 정수 overflow , 부동소수점 계산 오류 를 일으킬수 있다 아래의 방법을 사용하자.

        Comparator<Object> hashCodeOrder2 = new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare(o1.hashCode(), o2.hashCode());
            }
        };

        Comparator<Object> hashCodeOrder3 = Comparator.comparingInt(o -> o.hashCode());
        }


    }


}
