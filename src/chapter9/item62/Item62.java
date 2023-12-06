package chapter9.item62;

public class Item62 {
    // 다른 타입이 적절하다면 String 사용을 피하라.

    // 문자열은 다른 값 타입을 대신하기에 적합하지 않다.
    // 데이터가 수치형 이라면 int long float double 예/아니오 형태라면 boolean

    // 문자열은 열거 타입을 대신하기에 적합하지 않다.
    // 상수 열거의 목적일 경우 Enum 이 더 좋다.

    // 문자열은 혼합 타입을 대신하기에 적합하지 않다.
    String compoundKey = className + "#" + i.next();
    // 위의 코드를 보면 # 로 구분되는 두 요소중 하나의 요소에 # 가 들어가면 어떻게 할것인가 ?
    // 또 각 요소에 개별 접근을 하려면 귀찮다.
    // 또 equals, toString ,compareTo 메서드를 제공할수 없다.

    // 문자열은 권한을 표현하기에 적합하지 않다.
    public class ThreadLocal62 {
        private ThreadLocal62() {

        }

        // String 으로 된 key 값
        public static void set(String key, Object value) {

        }

        public static Object get(String key) {

        }
    }
    // 위 방법은 스레드를 구분하는 문자열 키가 전역 공유 라는점이다.
    // 의도대로 동작하려면 각 클라이언트가 고유한 키를 제공해야 한다.

    public class ThreadLocal622 {
        private ThreadLocal622() { }

        public static class Key {
            Key() { }
        }

        public static Key getKey() {
            return new Key();
        }

        public static void set(Key key, Object value) {

        }

        public static Object get(Key key) {

        }
    }
    // String 을 별도의 Key 로 변경해주었다.
    // Key 는 스레드 지역변수.

    public final class ThreadLocal623<T> {
        public ThreadLocal623();
        public void set(T value);
        public T get();
    }

    // 타입 안정성을 위해 Object 를 제네릭으로 변경해주었다.

}
