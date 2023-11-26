package chapter6.item34;

public class Item34 {
    // int 상수 대신 열거 타입을 사용하라.

    // 정수 열거 패턴.
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    // 열거타입 Enum 사용.
    // 각자의 이름공간이 있어서 이름이 같은 상수도 평화롭게 공존 가능하고 공개되는것은 필드의 이름뿐이라
    // 상수 값이 클라이언트로 컴파일되어 각인되지 않는다.
    // 필드나 메서드 추가가 가능하다.
    public enum Apple{FUJI,PIPPIN,GRANNY_SMITH}
    public enum ORANGE{NAVEL,TEMPLE,BLOOD}

    public static void main(String[] args) {

    }

}
