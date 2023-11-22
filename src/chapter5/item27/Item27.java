package chapter5.item27;

import java.util.Arrays;

public class Item27 {
    // 비검사 경고를 제거하라.


    // ArrayList 의 toArray()

    //    public <T> T[] toArray(T[] a) {
    //        if (a.length < size)
    //            // Make a new array of a's runtime type, but my contents:
    //            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    //        System.arraycopy(elementData, 0, a, 0, size);
    //        if (a.length > size)
    //            a[size] = null;
    //        return a;
    //    }

    // @SuppressWarnings 어노테이션은 항상 가능한 좁은 범위에 적용하자.
    // 위의 toArray() 는 메서드 선언부에 적용되어 있는데 이를 지역변수를 추가해서 수정해보자.

//    public <T> T[] toArray(T[] a) {
//        if (a.length < size)
//            // Make a new array of a's runtime type, but my contents:
//            @SuppressWarnings("unchecked")
//                    T[] result = (T[]) Arrays.copyOf(elementData, size, a.getClass());
//            return result;
//        System.arraycopy(elementData, 0, a, 0, size);
//        if (a.length > size)
//            a[size] = null;
//        return a;
//    }

    // return 문을 result 로 선언,초기화 하여 @SuppressWarnings 어노테이션을 달아주었다.
}
