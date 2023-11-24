package chapter5.item31;

import chapter5.item28.Chooser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item31 {
    // 한정적 와일드 카드를 사용해 API 유연성을 높여라.

    public static void main(String[] args) {
        Stack31<Number> numberStack = new Stack31<>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);

        System.out.println(objects);

        //

        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6);
        Chooser31<Number> chooser = new Chooser31<>(integerList);
        for (int i = 0; i < 10; i++) {
            Number choose = chooser.choose();
            System.out.println(choose);
        }

        //

        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(3);

        Set<Double> doubles = new HashSet<>();
        doubles.add(2.0);
        doubles.add(4.0);
        doubles.add(6.0);

        Set<? extends Number> numbers = Union31.union(integerSet, doubles);

        System.out.println("numbers = " + numbers);

        Set<Integer> integers1 = Set.of(1, 3, 5);
        Set<Double> doubles1 = Set.of(2.0, 4.0, 6.0);
        Set<Number> numbers1 = Union31.union(integers1, doubles1);


        // 매개변수 : 메서드 선언에 정의한 변수.
        // 인수 : 메서드 호출시 넘기는 실제 값.


    }
}
