package chapter9.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class Item65 {
    // 리플렉션 보다는 인터페이스를 사용하라.

    // 리플렉션을 사용하면 컴파일 단계에서 존재하지 않는 클래스를 사용할수 있지만 아래 단점이 있다.

    // 리플렉션의 단점
    // 1. 컴파일 타임 타입 검사가 주는 이점을 사용할수 없다.
    // 2. 리플렉션을 이용하면 코드가 지저분해진다.
    // 3. 리플렉션을 통한 메서드 호출은 일반 메서드 호출보다 매우 느리다.


    public static void main(String[] args) {
        Class<? extends Set<String>>cl = null;

        // Set<String> 인 cl 선언.

        try {
            cl = (Class<? extends Set<String>>) Class.forName(args[0]);
            // Class.forName() 으로 cl 초기화.
        } catch (ClassNotFoundException e) {
            // 클래스를 찾지 못할경우 Error.
            fatalError("클래스를 찾을수 없습니다.");
        }


        // Set<String> 타입의 생성자 선언.
        Constructor<? extends Set<String>> cons = null;
        try {
            // 리플렉션을 활용하여 초기화된 cl 의 매개변수가 없는 생성자를 가져옴.
            cons = cl.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            // 매개변수가 없는 생성자를 찾지 못했을 경우 error
            fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
        }

        // Set<String> s 선언.
        Set<String> s = null;
        try {
            // s 를 cl 의 생성자를 사용한 인스턴스로 초기화.
            s = cons.newInstance();
        } catch (IllegalAccessException e) {
            fatalError("생성자에 접근할 수 없습니다.");
        } catch (InstantiationException e) {
            fatalError("클래스를 인스턴스화할 수 없습니다.");
        } catch (InvocationTargetException e) {
            fatalError("생성자가 예외를 던졌습니다: " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Set을 구현하지 않은 클래스입니다.");
        }

        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
}
