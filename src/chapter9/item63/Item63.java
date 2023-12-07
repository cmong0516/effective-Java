package chapter9.item63;

import java.util.List;

public class Item63 {
    // 문자열 연결은 느리니 주의하라.

    // 문자열 연결 연산자 (+) 로 문자열 n 개를 잇는 시간은 n2에 비례한다.

    public String statement(List<String> list) {
        String result = "";

        for (String s : list) {
            result += s;
        }

        return result;
    }

    // list size 가 클수록 메서드는 매우 느려진다.

    public String statement2(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }

        return sb.toString();
    }

    // StringBuilder 를 사용하면 성능을 크게 개선할수 있다.
}
