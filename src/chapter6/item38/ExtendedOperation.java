package chapter6.item38;

public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    // 열거타입인 BasicOperation 은 확장할수 없지만 인터페이스인 Operation 은 확장가능.
    // 연산 타입으로 Operation 을 사용하면 Basic , Extended 모두 사용가능.

    public static void main(String[] args) {
        double x = 4.0;
        double y = 2.0;

        test(ExtendedOperation.class, x, y);

    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }
}
