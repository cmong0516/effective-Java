package chapter7.item43;

import java.util.HashMap;
import java.util.Map;

public class Item43 {
    // 람다보다는 메서드 참조를 사용하라.

    // map.merge(key , 1, (count,incr) -> count + incr);
    // 주어진 키가 맵 안에 없다면 key,1 저장.
    // 있다면 키,함수결과 저장.

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("test2", 2);

        map.merge("test1",1, Integer::sum);

        map.merge("test2", 1, Integer::sum);

        System.out.println("map = " + map);
        // map = {test2=3, test1=1}

        // 이미 존재하는 "test2" 키에는 value 2 에 1을 더해주었고
        // 존재하지 않았던 "test1" 키에는 value 로 1 을 저장하였다.
    }

    // 정적 Integer::parseInt        str -> Integer.parseInt(str)
    // 한정적 Instant.now()::isAfter     Instant then = Instant.now();   t -> then.isAfter(t)
    // 비한정적 String::toLowerCase      str -> str.toLowerCase()
    // 생성자 TreeMap<K,V>::new      () -> new TreeMap<K,V>()
    // 배열생성자 int[]::new          length -> new int[length]

    // 람다 보다 메서드 참조쪽이 더 간결하고 보기 쉬운 경우가 있다.
    // 이를 고려하여 가독성을 높이자.
}
