package chapter8.item54;

import com.sun.jdi.PrimitiveValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item54 {
    // null 이 아닌  빈 컬렉션이나 배열을 반환하라.

    // null 반환이 안좋은 이유.
    // 1. null 을 반환한 메서드를 사용할때 NullPoiniterException 이 발생할수 있다.
    // 2. null 을 반환 할경우 그 반환값이 어떤 의미를 가지는지 명확해지지 않는다.
    // 3. 호출하는 상황에서 null 체크를 수행해야 하므로 코드가 지저분해진다.

    // private final List<Cheese> cheesesInStock = ...;


    // public List<Cheese> getCheeses() {
    //     return cheesesInctock.isEmpty() ? null : new ArrayList<>(cheesesInctock);
    // }

    // null 을 허용할때 항상 이런 검증 코드가 필요하다.
    // List<Cheese> cheeses = shop.getCheeses();
    // if(cheeses != null && cheeses.contains(Cheese.STILTON))

//    public List<Cheese> getCheeses() {
//        return new ArrayList<>(cheesesInStock);
//    }

    // 위의 방법은 상황에 따라 빈 컬렉션 할당이 성능저하를 유발할수도 있다.

    // 개선안
//    public List<Cheese> getCheeses() {
//        return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
//    }

    // Collections.emptyList() , Collections.emptyMap() , Collections.emptySet() 등을 활용하자.
}
