package chapter9.item59;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Item59 {
    // 라이브러리를 익히고 사용하라.

    static Random rnd = new Random();


    // 무작위 정수 생성
    static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    // rnd.nextInt() 는 양수 , 음수 , 0 이 모두 가능하다.


    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);

        int low = 0;

        for (int i = 0; i < 1000000; i++) {
            if (random(n) < n / 2) {
                low++;
            }
        }

        System.out.println(low);
        // ramdom() 가 이상적으로 동작한다면 50만개가 출력되어야 하지만 666,666 에 가까운 값을 얻음.
        // 무작위 생성수중 2/3 이 중간값보다 낮다.

        // test1 666455
        // test2 665962
        // test3 666515
        // test4 666098
        // test5 665819

        // 대략 전제충 2/3 이 확실히 중간값 보다 낮다.

        int abs = Math.abs(Integer.MIN_VALUE);

        System.out.println("abs = " + abs);
        // abs = -2147483648
        // Math.abs 를 사용하여 양수가 나와야 하지만 Integer.MIN_VALUE 값이 그대로 나온다.

        // 정수 난수를 생성하고 싶다면 메서드를 사용할 필요가 없다.
        // ThreadLocalRandom 을 사용하면 훨씬 빠르게 동작 가능하며 버그없이 원하는대로 동작한다.

        for (int i = 0; i < 10; i++) {
            int randomNumber = ThreadLocalRandom.current().nextInt(100);
            System.out.println(Math.abs(randomNumber));
        }
    }
}
