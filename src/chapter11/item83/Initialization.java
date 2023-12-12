package chapter11.item83;

public class Initialization {
    // 일반적인 인스턴스 필드 초기화 방법.
    private final FieldType field1 = computeFiledValue();

    private FieldType field2;

    // synchronized 접근자를 사용한 인스턴스 필드 초기화 방법.
    private synchronized FieldType getField2() {
        if (field2 == null) {
            field2 = computeFieldValue();
        }

        return field2;
    }

    // 정적 필드용 지연 초기화 홀더 클래스 관용구
    // getField() 가 처음 호출되는 순간 FieldHolder.field 가 처음 읽히면서 computeFieldValue() 를 사용해 초기화 한다.
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }

    private static FieldType getField() {
        return FieldHolder.field;
    }

    // 인스턴스 필드 지연 초기화용 이중검사 관용구
    private volatile FieldType field4;

    private FieldType getField4() {
        FieldType result = field4;
        // result 를 사용하여 필드가 이미 초기화된 상황에서는 그 필드를 딱 한번만 읽도록 보장한다.
        if (result != null) { // 락을 사용하지 않은 첫번째 검사
            return result;
        }

        synchronized (this) {
            if (field4 == null) { // 락을 사용한 두번째 검사
                field4 = computeFieldValue();
            }

            return field4;
        }
    }

    // 단일검사 관용구 (초기화가 중복해서 일어날수 있다.)
    private volatile FieldType field5;

    private FieldType getField5() {
        FieldType result = field5;
        if (result == null) {
            field5 = result = computeFieldValue();
        }
        return result;
    }

    private static FieldType computeFieldValue() {
        return new FieldType();
    }

    // 대부분의 필드는 지연시키지 말고 곧바로 초기화 해야 한다.
    // 성능 최적화를 위해 , 위험한 초기화 순환을 막기 위해 꼭 써야한다면
    // 인스턴스 필드 -> 이중곰사 관용구
    // 정적 필드 -> 연 초기화 홀더 클래스
    // 반복 초기화 가능한 인스턴스 필드 -> 단일검사 관용구

}
