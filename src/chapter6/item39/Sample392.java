package chapter6.item39;

public class Sample392 {
    @ExceptionTest39(ArithmeticException.class)
    public static void m1() {
        int i = 0;
        i = i / i;
    }

    @ExceptionTest39(ArithmeticException.class)
    public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest39(ArithmeticException.class)
    public static void m3() {

    }
}
