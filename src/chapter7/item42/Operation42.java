package chapter7.item42;

import java.util.function.DoubleBinaryOperator;

public enum Operation42 {
    // PLUS("+") {
    //        public double apply(double x, double y) { return x + y; }
    //    },
    //    MINUS("-") {
    //        public double apply(double x, double y) { return x - y; }
    //    },
    //    TIMES("*") {
    //        public double apply(double x, double y) { return x * y; }
    //    },
    //    DIVIDE("/") {
    //        public double apply(double x, double y) { return x / y; }
    //    };

    // 람다로 변환.
    PLUS  ("+", (x, y) -> x + y),
    MINUS ("-", (x, y) -> x - y),
    TIMES ("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator operator;

    //@FunctionalInterface
    //public interface DoubleBinaryOperator {
    //    /**
    //     * Applies this operator to the given operands.
    //     *
    //     * @param left the first operand
    //     * @param right the second operand
    //     * @return the operator result
    //     */
    //    double applyAsDouble(double left, double right);
    //}
    // DoubleBinaryOperator 는 double 타입 인수 2개를 받아 double 타입 결과를 돌려주는 interface다.
    // 위에선 double x,y 를 받아 double 타입 결과를 반환하는 람다식을 사용했다.
    // 람다의 특이점 : 람다는 자신을 참조할수 없다. (this 사용 불가)
    // 자신을 참조해야하는 상황이 오면 익명클래스 그외 람다를 사용하자.

    Operation42(String symbol, DoubleBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return operator.applyAsDouble(x, y);
    }

}
