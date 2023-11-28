package chapter7.item44;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class Item44 {
    // 표준 함수형 인터페이스를 사용하라.
    // UnaryOperator<T>          T apply(T t)
    // BinaryOperator<T>         T apply(T t1, T t2)
    // Predicate<T>              boolean test(T t)
    // Function<T,R>             R apply(T t)
    // Supplier<T>               T get()
    // Consumer<T>               void accept(T t)

    public static void main(String[] args) {

        Consumer<Integer> consumer = System.out::println;

        Function<String, Integer> function = Integer::parseInt;

        Predicate<Integer> predicate = integer -> integer > 10;

        UnaryOperator<Integer> unaryOperator = integer -> integer;

        BinaryOperator<String> binaryOperator = (s, s2) -> s + s2;

        Supplier<String> supplier = () -> "Hello";

        boolean test = predicate.test(11);
        String hello = binaryOperator.apply("Hello", "World!");
        Integer apply = unaryOperator.apply(5);
        String s = supplier.get();
        Integer apply1 = function.apply("44");

        System.out.println("s = " + s);
        System.out.println("apply = " + apply);
        System.out.println("hello = " + hello);
        System.out.println("test = " + test);
        System.out.println("apply1 = " + apply1);
        consumer.accept(100);
    }

}
