package chapter8.item52;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetList52 {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }

        System.out.println("set = " + set);
        System.out.println("list = " + list);

        // set = [-3, -2, -1, 0, 1, 2]
        // list = [-3, -2, -1, 0, 1, 2]

        for (int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(i);
        }

        System.out.println("set = " + set);
        System.out.println("list = " + list);

        // set = [-3, -2, -1]
        // list = [-2, 0, 2]

        // [-3,-2,-1] 이 호출되리라 예상했지만 list 에서 결과가 다르다.
        // Set 을 구현한 TreeSet 에는 remove(int index) 가 없다.
        // 따라서 remove(Object) 가 실행되어 해당 객체를 제거하게 된다.
        // List 를 구현한 ArrayList 에는 remove(int index) 가 있다.
        // 따라서 remove 메서드 실행시 해당 index 의 요소를 제거한다.
        // 같은 동작을 원한다면 List 에서는 remove((Integer) index) 로 형변환을 해서
        // remove(int )가 아니라 remove(Object ) 를 실행하게 해야한다.
        // 이런 현상이 생기는 이유는 List 인터페이스가 remove 메서드를 다중정의 했기 때문이다.
    }
}
