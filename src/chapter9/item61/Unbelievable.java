package chapter9.item61;

public class Unbelievable {
    static Integer integer;
    static int i;

    public static void main(String[] args) {
        // 기본 타입인 int i 는 0 으로 자동 초기화 되므로 정상 진행.
        System.out.println("i = " + i);
        if (i == 42) {
            System.out.println("믿을수 없군");
        }

        // integer 를 선언하고 초기화 해주지 않았다.
        // NullPointerException 발생.
        // int (기본 타입) Integer (박싱된 기본 타입) 의 혼용 연산 에서는 박싱된 기본 타입의 박싱이 자동으로 풀린다 (언박싱)
        // 또 대부분의 상황에서 박싱된 기본타입을 사용할 경우 성능저하의 원인이 되므로 기본타입을 사용하자.
    }

}
