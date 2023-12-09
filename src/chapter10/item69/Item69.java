package chapter10.item69;

public class Item69 {
    // 예외는 진자 예외 상황에만 사용하라.

    public static void main(String[] args) {
//        try {
//            int i = 0;
//            while (true) {
//                range[i++].climb();
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//
//        }

        // 일반적으로 배열의 index 를 넘어가는 값을 찾으려고 하면 예외가 발생하며 프로그램이 종료되지만
        // 제어 흐름용으로 사용했다.
        // 잘 설계된 API 에서도 클라이언트가 정상적인 제어 흐름에서 예외를 사용할 일이 없다.
    }
}
