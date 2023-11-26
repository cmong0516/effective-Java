package chapter6.item34;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum Operation {
//    PLUS,MINUS,TIMES,DIVIDE;
//
//    public double apply(double x, double y) {
//        switch (this) {
//            case PLUS: return x+y;
//            case MINUS: return x-y;
//            case TIMES: return x*y;
//            case DIVIDE: return x/y;
//        }
//
//        throw new AssertionError("알수 없는 연산: " + this);
//        // 도달하지 않겠지만 새로운 연산이 추가된다면 에러가 발생하거나 apply 메서드에 case 룰 추가해주어야 한다.
//    }

    // 아래처럼 상수별 메서드 구현을 활용하는 방법도 있다.

    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };

    private final String symbol;

    Operation(String symbol) { this.symbol = symbol; }

    @Override public String toString() { return symbol; }


    // apply 가 추상메서드 이기 때문에 반드시 재정의 해야한다.
    public abstract double apply(double x, double y);



    // 전에 누군가 알려준 Map 을 활용한 Enum 에서 원하는 값 찾기.
    // Stream 반복을 하며 비교하여 찾는거보다 실행속도도 훨씬 빠르다고 한다.
    private static final Map<String, Operation> stringToEnum =
            Stream.of(values()).collect(
                    toMap(Object::toString, e -> e));

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

}
