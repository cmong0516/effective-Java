package chapter2.item6;

public class Item6 {
    // 불필요한 객체 생성을 피하라
    // ex
    String string = new String("Hello");

    // 위의 string 은 실행될 때마다 새로운 인스턴스를 만들어낸다.

    String string2 = "Hello";

    // 이렇게 하면 매번 새로운 인스턴스를 생성하지 않는다.

    // ex2

    private static long sum() {
        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        return sum;
    }

    // 오토박싱으로 Long 타입을 long 으로 바꿔준다.
    // 하지만 Long 객체를 아주아주 많이 생성하게 된다
    // sum 타입을 Long 이 아니라 long 으로 해주면 성능향상.

    private static long sum2() {
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        return sum;
    }
}
