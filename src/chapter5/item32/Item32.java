package chapter5.item32;

import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Item32 {
    // 제네릭과 가변인수를 함께 쓸 때는 신중하라.

    public static void main(String[] args) {
//        dangerous(List.of("Hello World"));
        // ClassCastException

//        String[] strings = pickTwo("좋은", "빠른", "저렴한");
//        System.out.println(strings.toString());
        // ClassCastException
        // pickTwo 의 반환값을 strings 에 저장하기 위해 String[] 로 형변환 하는 코드를
        // 컴파일러가 자동으로 생성.

        List<String> strings = pickTwo2("좋은", "빠른", "저렴한");

        System.out.println("strings = " + strings.toString());

        // List.of 는 @SafeVarargs 가 붙어있다.

        // 결론
        // 가변인수 varargs 는 제네릭과 궁합이 좋지 않다.
        // 가변인수가 [] 로 노출되고 배열과 제네릭 타입 규칙이 서로 다르기 때문.
    }

    static void dangerous(List<String>... stringlists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringlists;
        objects[0] = intList;             // 힙 오염 발생
        String s = stringlists[0].get(0); // ClassCastException

        // 제네릭 varargs 배열 매개변수에 값을 저장하는것은 안전하지 않다.
        // 가변인수 메서드를 호출할때 varargs 매개변수를 담는 제네릭 배열이 만들어짐.
        // 메서드가 이 배열에 아무것도 저장하지 않고
        // 그 배열의 참조가 노출되지 않는다면
        // 타입 안전하다.
        // @SafeVarargs 어노테이션이 붙은 메서드에는 노출해도 된다.
    }

    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, b);
            case 1:
                return toArray(a, c);
            case 2:
                return toArray(b, c);
        }
        throw new AssertionError(); // 도달할 수 없다.
    }

    static <T> List<T> pickTwo2(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a,b);
            case 1: return List.of(a,c);
            case 2: return List.of(b,c);
        }

        throw new AssertionError();
    }
}

