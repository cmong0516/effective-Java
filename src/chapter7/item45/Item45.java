package chapter7.item45;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Item45 {
    // 스트림은 주의해서 사용하라.

    // Stream
    // 1. 8버전 에서 추가됬다.
    // 2. 스트림은 데이터 원소의 유한 혹은 무한 시퀀스를 뜻한다.
    // 3. 스트림 파이프라인은 이 원소들로 수행하는 연산 단계를 표현하는 개념.

    // Stream 을 사용하기 좋은 상황
    // 1. 원소들의 시퀀스를 일관되게 변환.
    // 2. 원소들의 시퀀스를 필터링.
    // 3. 원소들의 시퀀스를 하나의 연산을 사용해 결합.
    // 4. 원소들의 시퀀스를 컬렉션에 모은다.
    // 5. 원소들의 시퀀스 에서 특정 조건에 맞는 원소를 찾는다.

    public static void main(String[] args) {
        // 2 ~ 다음 소수까지 계속 (무한 스트림) 에서 반복한다.
        // 2의 p 제곱 -1 을 한다.
        // 그중 소수인것을 찾는다.
        // 20개까지.
        // 출력
        primes().map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);

        BigInteger startValue = BigInteger.valueOf(50L);
        BigInteger bigInteger = startValue.nextProbablePrime();
        System.out.println("bigInteger = " + bigInteger);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO,BigInteger::nextProbablePrime);
    }

    // Stream 은 코드를 간단하게만 해준다고 생각하고 사용해왔다.
    // 하지만 그렇지 않은 경우도 있었고 Stream 을 남발하면 오히려 더 읽기 힘들수도
    // 또 모던 자바에 익숙하지 않은 사람이 있을수도 있다.
    // 상황에 맞게 Stream 을 주의해서 사용하자.
}
