package item11;

import java.util.HashMap;
import java.util.Map;

public class Item11 {
    // equals 를 재정의 할때 hashCode 도 재정의 하라.
    // equals 를 사용항 물리적으로 다를지라도 논리적으로 같다고 재정의 한경우 같은 해시코드를 반환해야 한다.

    public static void main(String[] args) {
        Map<PhoneNumber, String> map = new HashMap<>();
        map.put(new PhoneNumber(011,242,2444), "테스트1");

        String s = map.get(new PhoneNumber(011, 242, 2444));

        System.out.println("s = " + s);

        // equals 재정의 , hashCode 재정의 X -> s == null

        // equals 재정의 , hashCode 재정의 -> s = 테스트1
    }
}
