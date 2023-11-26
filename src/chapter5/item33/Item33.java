package chapter5.item33;

import java.util.HashMap;
import java.util.Map;

public class Item33 {
    // 타입 안전 이종 컨테이너 패턴을 고려하라.

    public static void main(String[] args) {

        Map<Class<?>, Object> map1 = new HashMap<>();

        map1.put(String.class, "테스트1");
        map1.put(Integer.class, "테스트2");

        Object o = map1.get(Integer.class);

        System.out.println("o = " + o);
        // Integer.class 키값으 가지고 있는데 Object 하위 타입인 String 값을 사용하고 있다.
        // Favorites 클래스 내부로직으로 이를 해결하자.

        Favorites favorites = new Favorites();

        // 컴파일 에러 테스트
//        favorites.putFavorite(String.class,5);

        // 타입 일치 , 컴파일 성공.
        favorites.putFavorite(String.class, "Java");
        favorites.putFavorite(Integer.class, 0xcafebabe);
        favorites.putFavorite(Class.class, Favorites.class);

        String favorite = favorites.getFavorite(String.class);
        Integer favorite1 = favorites.getFavorite(Integer.class);
        Class<?> favorite2 = favorites.getFavorite(Class.class);

        System.out.printf("%s %x %s%n",favorite, favorite1, favorite2);
    }
}
