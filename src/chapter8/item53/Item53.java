package chapter8.item53;

import java.util.List;
import java.util.Optional;

public class Item53 {
    // 가변인수는 신중히 사용하라.

    static int sum(int... args) {
        int sum = 0;
        for (int arg : args)
            sum += arg;
        return sum;
    }

    static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        int min = args[0];

        for (int i = 0; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }

        return min;
    }

    // min() 은 args 에 인수가 0개 일경우 런타임에서 IllegalArgumentException 이 발생한다.

    //개선
    static int min2(int firstArg, int... remainingArgs) {
        int min = firstArg;

        for (int remainingArg : remainingArgs) {
            if (remainingArg < min) {
                min = remainingArg;
            }
        }

        return min;
    }

    // 하지만 위의 방법도 효율적이지 않다.
    // 가변인수를 사용하여 메서드가 호출될 때마다 새로운 배열을 생성하게 된다.
    // 이럴때 해결 방법으로는 메서드 다중정의를 사용하여 매개변수가 1~3 개 일때의 메서드를 다중정의 하고
    // 그 외에는 가변인수를 사용한다.

    // 위의 sum , min 같은 경우 더 좋은 해결방법이 있을것 같다.
    static int min2(List<Integer> integers) {
        Optional<Integer> min = integers.stream().min(Integer::compareTo);
        return min.orElse(0);
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        int i = min2(list);

        System.out.println("i = " + i);

    }
}
