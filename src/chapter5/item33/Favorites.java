package chapter5.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();

    // 와일드카드 타입 중첩 , 키 : 와일드카드 밸류 : Object -> 키와 맵 사이의 타입 관계를 보증하지 않는다.

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    // putFavorite() 은 type 의 제네릭 타입인 인스턴스만 받게 되어있다.
    // 그렇지 않을경우 컴파일 에러를 낼것이다.

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    //    @IntrinsicCandidate
    //    public T cast(Object obj) {
    //        if (obj != null && !isInstance(obj))
    //            throw new ClassCastException(cannotCastMsg(obj));
    //        return (T) obj;
    //    }
}
