package chapter12.item87;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class StringList implements Serializable {
//    private int size = 0;
    private transient int size = 0;
//    private Entry head = null;
    private transient Entry head = null;

    // transient : 직렬화 과정에서 특정 멤버변수를 제외시킨다.
    // 특정 변수의 값이 저장되지 않고 그 변수를 다시 역직렬화 할때 디폴트 값으로 초기화 된다.
    // 1. 민감한 정보를 직렬화 하지 않고 제외시킬때
    // 2. 특정 변수가 일시적 상태를 나타내거나 저장 및 복원이 필요하지 않을때 사용.

//    private static class Entry implements Serializable {
//        String data;
//        Entry next;
//        Entry previous;
//    }

    private static class Entry {
        String data;
        Entry next;
        Entry previous;
    }

    public final void add(String s) {

    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size);

        for (Entry entry = head; entry != null; entry = entry.next) {
            stream.writeObject(entry.data);
        }

    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int numElements = stream.readInt();

        for (int i = 0; i < numElements; i++) {
            add((String) stream.readObject());
        }
    }




    // 논리적으로 이 클래스는 일련의 문자열을 표현한다.
    // 객체의 물리적 표현과 논리적 표현의 차이가 클때 기본 직렬화 형태를 사용하면 네가지 문제가 발생한다.

    // 1. 공개 API 가 현재의 내부 표현 방식에 영구히 묶인다.
    // 2. 너무 많은 공간을 차지할수 있다.
    // 3. 시간이 너무 많이 걸릴수 있다.
    // 4. 스택 오버 플로를 일으킬 수가 있다.
}
